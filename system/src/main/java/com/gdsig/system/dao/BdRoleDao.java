package com.gdsig.system.dao;

import com.gdsig.common.dto.system.RoleListParam;
import com.gdsig.mybatis.model.BdMenu;
import com.gdsig.mybatis.model.BdRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xs
 * @date 2022/12/7上午 9:39
 */

@Component
@Mapper
public interface BdRoleDao {

    List<BdRole> searchRecord(@Param("queryParam") RoleListParam roleListParam);

    List<BdRole> findByParentId(@Param("parentId") Integer parentId);

    BdRole getSuperAdminRole();
}
