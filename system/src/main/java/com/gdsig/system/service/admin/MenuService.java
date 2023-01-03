package com.gdsig.system.service.admin;

import com.gdsig.common.dto.system.MenuDto;
import com.gdsig.common.dto.system.MenuListParam;
import com.gdsig.common.result.Result;
import com.gdsig.common.vo.MenuVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xs
 * @date 2022/12/7下午 4:17
 */
public interface MenuService {

    /**
     * 查询记录
     *
     * @param listParam 过滤条件
     * @return {@code Result<List<OrgunitVo>>}
     */
    List<MenuVo> searchRecord(MenuListParam listParam);

    /**
     * 查询详情
     *
     * @param accountId 账号id
     * @param id        组织id
     * @return {@link MenuVo}
     */
    Result<MenuVo> findById(String accountId, Integer id);

    /**
     * 新增
     *
     * @param accountId 用户id
     * @param dto       参数
     * @return {@link Result <Void>}
     */
    @Transactional(rollbackFor = {Exception.class})
    Result<Void> add(String accountId, MenuDto dto);

    /**
     * 修改
     *
     * @param accountId 用户id
     * @param id        组织id
     * @param dto       参数
     * @return {@link Result<Void>}
     */
    @Transactional(rollbackFor = {Exception.class})
    Result<Void> update(String accountId, Integer id, MenuDto dto);

    /**
     * 删除
     *
     * @param accountId 用户id
     * @param id        组织id
     * @return {@link Result<Void>}
     */
    @Transactional(rollbackFor = {Exception.class})
    Result<Void> delete(String accountId, Integer id);
}
