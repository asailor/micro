package com.gdsig.system.dao;

import com.gdsig.mybatis.model.BdRoleMenu;
import com.gdsig.mybatis.model.BdRoleMenuMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xs
 * @date 2022/12/15下午 3:27
 */

@Component
@Mapper
public interface BdRoleMenuMappingDao {

    List<BdRoleMenuMapping> findByRoleMenuId(@Param("roleMenuId") Integer roleMenuId);
}
