package com.gdsig.mybatis.mapper;

import com.gdsig.mybatis.model.BdRoleMenuMapping;
import com.gdsig.mybatis.model.BdRoleMenuMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BdRoleMenuMappingMapper {
    long countByExample(BdRoleMenuMappingExample example);

    int deleteByExample(BdRoleMenuMappingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BdRoleMenuMapping record);

    int insertSelective(BdRoleMenuMapping record);

    List<BdRoleMenuMapping> selectByExample(BdRoleMenuMappingExample example);

    BdRoleMenuMapping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BdRoleMenuMapping record, @Param("example") BdRoleMenuMappingExample example);

    int updateByExample(@Param("record") BdRoleMenuMapping record, @Param("example") BdRoleMenuMappingExample example);

    int updateByPrimaryKeySelective(BdRoleMenuMapping record);

    int updateByPrimaryKey(BdRoleMenuMapping record);
}