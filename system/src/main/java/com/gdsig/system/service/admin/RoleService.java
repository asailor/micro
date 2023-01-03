package com.gdsig.system.service.admin;

import com.gdsig.common.dto.system.RoleDto;
import com.gdsig.common.dto.system.RoleListParam;
import com.gdsig.common.result.Result;
import com.gdsig.common.vo.RoleVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xs
 * @date 2022/12/1上午 9:45
 */

public interface RoleService {

    /**
     * 查询记录
     *
     * @param roleListParam 过滤条件
     * @return {@code Result<List<RoleVO>>}
     */
    List<RoleVO> searchRecord(RoleListParam roleListParam);

    /**
     * 查询详情
     *
     * @param accountId 账号id
     * @param id        角色id
     * @return {@link RoleVO}
     */
    Result<RoleVO> findById(String accountId, Integer id);

    /**
     * 新增或修改
     *
     * @param accountId 用户id
     * @param id        角色id
     * @param dto       参数
     * @return {@link Result<Void>}
     */
    @Transactional(rollbackFor = {Exception.class})
    Result<Void> saveOrUpdate(String accountId, Integer id, RoleDto dto) throws Exception;

    /**
     * 删除
     *
     * @param accountId 用户id
     * @param id        角色id
     * @return {@link Result<Void>}
     */
    @Transactional(rollbackFor = {Exception.class})
    Result<Void> delete(String accountId, Integer id);

    /**
     * 菜单
     *
     * @param id    角色id
     * @param types 顶级菜单类型：1管理平台；2App
     * @return {@link RoleVO}
     */
    Result<RoleVO> menus(Integer id, Integer... types);

}
