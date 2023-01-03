package com.gdsig.system.service.admin.impl;

import com.gdsig.common.dto.system.MenuDto;
import com.gdsig.common.dto.system.MenuListParam;
import com.gdsig.common.result.Result;
import com.gdsig.common.vo.MenuVo;
import com.gdsig.mybatis.mapper.BdMenuMapper;
import com.gdsig.mybatis.model.BdMenu;
import com.gdsig.system.dao.BdMenuDao;
import com.gdsig.system.service.admin.MenuService;
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
@Service("admin/MenuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    BdMenuMapper objMapper;

    @Resource
    BdMenuDao objDao;

    /**
     * 查询记录
     *
     * @param listParam 过滤条件
     * @return {@code Result<List<MenuVo>>}
     */
    @Override
    public List<MenuVo> searchRecord(MenuListParam listParam) {
        PageHelper.startPage(listParam.getPage(), listParam.getLimit());

        List<BdMenu> objs = objDao.searchRecord(listParam);

        List<MenuVo> vos = new ArrayList<>();
        objs.forEach(obj -> {
            MenuVo vo = new MenuVo();
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
     * @return {@link MenuVo}
     */
    @Override
    public Result<MenuVo> findById(String accountId, Integer id) {
        MenuVo objVO = new MenuVo();
        BdMenu obj;
        if (id == null || (obj = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "menu is null");
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
    public Result<Void> add(String accountId, MenuDto dto) {
        BdMenu obj = new BdMenu();
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
    public Result<Void> update(String accountId, Integer id, MenuDto dto) {
        BdMenu obj;
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
        BdMenu obj;
        if (id == null || (obj = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "menu is null");
        }
        obj.setStatus(false);
        objMapper.updateByPrimaryKey(obj);

        return new Result<>();
    }
}
