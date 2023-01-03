package com.gdsig.system.service.admin.impl;

import com.gdsig.common.dto.system.FunctionDto;
import com.gdsig.common.dto.system.FunctionListParam;
import com.gdsig.common.result.Result;
import com.gdsig.common.vo.FunctionVo;
import com.gdsig.mybatis.mapper.BdMenuMappingMapper;
import com.gdsig.mybatis.model.BdMenuMapping;
import com.gdsig.system.dao.BdMenuMappingDao;
import com.gdsig.system.service.admin.FunctionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xs
 * @date 2022/12/8上午 9:50
 */
@Service("admin/FunctionService")
public class FunctionServiceImpl implements FunctionService {

    @Resource
    BdMenuMappingMapper objMapper;

    @Resource
    BdMenuMappingDao objDao;

    /**
     * 查询记录
     *
     * @param listParam 过滤条件
     * @return {@code Result<List<FunctionVo>>}
     */
    @Override
    public List<FunctionVo> searchRecord(FunctionListParam listParam) {
        PageHelper.startPage(listParam.getPage(), listParam.getLimit());

        List<BdMenuMapping> objs = objDao.searchRecord(listParam);

        List<FunctionVo> vos = new ArrayList<>();
        objs.forEach(obj -> {
            FunctionVo vo = new FunctionVo();
            vo.setId(obj.getId());
            vo.setName(obj.getName());
            vos.add(vo);
        });

        return vos;
    }

    /**
     * 查询详情
     *
     * @param accountId 账号id
     * @param id        组织id
     * @return {@link FunctionVo}
     */
    @Override
    public Result<FunctionVo> findById(String accountId, Integer id) {
        FunctionVo objVO = new FunctionVo();
        BdMenuMapping obj;
        if (id == null || (obj = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "function is null");
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
    public Result<Void> add(String accountId, FunctionDto dto) {
        BdMenuMapping obj = new BdMenuMapping();
        BeanUtils.copyProperties(dto, obj);
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
    public Result<Void> update(String accountId, Integer id, FunctionDto dto) {
        BdMenuMapping obj;
        if (id == null || (obj = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "menu is null");
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
        BdMenuMapping obj;
        if (id == null || (obj = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "menu is null");
        }
        obj.setStatus(false);
        objMapper.updateByPrimaryKey(obj);

        return new Result<>();
    }
}
