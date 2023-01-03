package com.gdsig.mybatis.mapper;

import com.gdsig.mybatis.model.BdRoleMapping;
import com.gdsig.mybatis.model.BdRoleMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BdRoleMappingMapper {
    long countByExample(BdRoleMappingExample example);

    int deleteByExample(BdRoleMappingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BdRoleMapping record);

    int insertSelective(BdRoleMapping record);

    List<BdRoleMapping> selectByExample(BdRoleMappingExample example);

    BdRoleMapping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BdRoleMapping record, @Param("example") BdRoleMappingExample example);

    int updateByExample(@Param("record") BdRoleMapping record, @Param("example") BdRoleMappingExample example);

    int updateByPrimaryKeySelective(BdRoleMapping record);

    int updateByPrimaryKey(BdRoleMapping record);
}