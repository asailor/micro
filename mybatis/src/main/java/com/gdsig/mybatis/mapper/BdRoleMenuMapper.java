package com.gdsig.mybatis.mapper;

import com.gdsig.mybatis.model.BdRoleMenu;
import com.gdsig.mybatis.model.BdRoleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BdRoleMenuMapper {
    long countByExample(BdRoleMenuExample example);

    int deleteByExample(BdRoleMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BdRoleMenu record);

    int insertSelective(BdRoleMenu record);

    List<BdRoleMenu> selectByExample(BdRoleMenuExample example);

    BdRoleMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BdRoleMenu record, @Param("example") BdRoleMenuExample example);

    int updateByExample(@Param("record") BdRoleMenu record, @Param("example") BdRoleMenuExample example);

    int updateByPrimaryKeySelective(BdRoleMenu record);

    int updateByPrimaryKey(BdRoleMenu record);
}