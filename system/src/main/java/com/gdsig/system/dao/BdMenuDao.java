package com.gdsig.system.dao;

import com.gdsig.common.dto.system.MenuListParam;
import com.gdsig.common.dto.system.OrgunitListParam;
import com.gdsig.mybatis.model.BdMenu;
import com.gdsig.mybatis.model.BdOrgunit;
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
public interface BdMenuDao {

    List<BdMenu> searchRecord(@Param("queryParam") MenuListParam listParam);

    List<BdMenu> findByParentId(@Param("parentId") Integer parentId);
}
