package com.gdsig.mybatis.mapper;

import com.gdsig.mybatis.model.BdRole;
import com.gdsig.mybatis.model.BdRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BdRoleMapper {
    long countByExample(BdRoleExample example);

    int deleteByExample(BdRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BdRole record);

    int insertSelective(BdRole record);

    List<BdRole> selectByExample(BdRoleExample example);

    BdRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BdRole record, @Param("example") BdRoleExample example);

    int updateByExample(@Param("record") BdRole record, @Param("example") BdRoleExample example);

    int updateByPrimaryKeySelective(BdRole record);

    int updateByPrimaryKey(BdRole record);
}