package com.gdsig.system.service.admin.impl;

import com.gdsig.common.dto.system.OrgunitDto;
import com.gdsig.common.dto.system.OrgunitListParam;
import com.gdsig.common.result.Result;
import com.gdsig.common.util.StringUtil;
import com.gdsig.common.vo.OrgunitVo;
import com.gdsig.mybatis.mapper.BdOrgunitMapper;
import com.gdsig.mybatis.model.BdOrgunit;
import com.gdsig.system.dao.BdOrgunitDao;
import com.gdsig.system.service.admin.OrgunitService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author xs
 * @date 2022/12/7下午 4:25
 */

@Service("admin/OrgunitService")
public class OrgunitServiceImpl implements OrgunitService {

    @Resource
    BdOrgunitMapper objMapper;

    @Resource
    BdOrgunitDao objDao;

    /**
     * 查询记录
     *
     * @param orgListParam 过滤条件
     * @return {@code Result<List<OrgunitVo>>}
     */
    @Override
    public List<OrgunitVo> searchRecord(OrgunitListParam orgListParam) {
        PageHelper.startPage(orgListParam.getPage(), orgListParam.getLimit());

        List<BdOrgunit> roles = objDao.searchRecord(orgListParam);

        List<OrgunitVo> orgunitVos = new ArrayList<>();
        roles.forEach(obj -> {
            OrgunitVo orgVo = new OrgunitVo();
            orgVo.setId(obj.getId());
            orgVo.setName(obj.getName());
            orgunitVos.add(orgVo);
        });

        return orgunitVos;
    }

    /**
     * 查询详情
     *
     * @param accountId 账号id
     * @param id        组织id
     * @return {@link OrgunitVo}
     */
    @Override
    public Result<OrgunitVo> findById(String accountId, Integer id) {
        OrgunitVo objVO = new OrgunitVo();
        BdOrgunit obj;
        if (id == null || (obj = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "orgunit is null");
        }

        objVO.setName(obj.getName());
        objVO.setId(obj.getId());

        return new Result<>(objVO);
    }

    /**
     * 新增
     *
     * @param accountId 用户id
     * @param dto       参数
     * @return {@link Result <Void>}
     */
    @Override
    public Result<Void> add(String accountId, OrgunitDto dto) {
        BdOrgunit obj = new BdOrgunit();

        BeanUtils.copyProperties(dto, obj);

        obj.setCreateTime(new Date());
        obj.setStatus(true);

        objMapper.insert(obj);
        return new Result<>();
    }

    /**
     * 修改
     *
     * @param accountId 用户id
     * @param id        组织id
     * @param dto       参数
     * @return {@link Result<Void>}
     */
    @Override
    public Result<Void> update(String accountId, Integer id, OrgunitDto dto) {
        BdOrgunit obj;
        if (id == null || (obj = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "role is null");
        }

        BeanUtils.copyProperties(dto, obj);
        objMapper.updateByPrimaryKey(obj);
        return new Result<>();
    }

    /**
     * 删除
     *
     * @param accountId 用户id
     * @param id        组织id
     * @return {@link Result<Void>}
     */
    @Override
    public Result<Void> delete(String accountId, Integer id) {
        BdOrgunit obj;
        if (id == null || (obj = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "orgunit is null");
        }
        obj.setStatus(false);
        objMapper.updateByPrimaryKey(obj);

        return new Result<>();
    }

    /**
     * trees
     *
     * @param accountId 账号id
     * @param id        组织id
     * @return {@link OrgunitVo}
     */
    @Override
    public Result<List<OrgunitVo>> trees(String accountId, Integer id) {

        BdOrgunit orgunit = objMapper.selectByPrimaryKey(id);
        if (orgunit == null){
            return new Result<>(false, "orgunit is null");
        }
        List<OrgunitVo> childVoList = new ArrayList<>();
        loadTree(childVoList, new ArrayList<BdOrgunit>() {{
            add(orgunit);
        }});

        return new Result<>(childVoList);
    }

    private void loadTree(List<OrgunitVo> voList, List<BdOrgunit> objSons) {
        if (objSons == null || objSons.isEmpty()) {
            return;
        }

        objSons.stream().filter(BdOrgunit::getStatus)
                .sorted(Comparator.comparing(BdOrgunit::getModifyTime).reversed())
                .forEach(org -> {

                    OrgunitVo orgunitVo = new OrgunitVo();
                    voList.add(orgunitVo);

                    orgunitVo.setId(org.getId());
                    orgunitVo.setName(org.getName());
                    orgunitVo.setLevel(org.getLevel());
                    orgunitVo.setIcon("jstree-folder");

                    OrgunitVo.LiAttr liAttr = new OrgunitVo.LiAttr();
                    liAttr.setName(org.getName());
                    liAttr.setAddress(org.getAddress());
                    liAttr.setContact(org.getContact());
                    orgunitVo.setLiAttr(liAttr);

                    OrgunitVo.State state = new OrgunitVo.State();
                    state.setOpened(StringUtil.getIntValue(org.getLevel(), -1) <= 2);
                    state.setSelected(false);
                    orgunitVo.setState(state);

                    OrgunitListParam listParam = new OrgunitListParam();
                    listParam.setParentId(org.getId());
                    List<BdOrgunit> orgunits = objDao.searchRecord(listParam);

                    if (!orgunits.isEmpty()){
                        List<OrgunitVo> childVoList = new ArrayList<>();
                        orgunitVo.setOrgunitVos(childVoList);
                        loadTree(childVoList, orgunits);
                    }

        });

    }
}
