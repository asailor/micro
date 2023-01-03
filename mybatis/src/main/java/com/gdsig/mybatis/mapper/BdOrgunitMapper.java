package com.gdsig.mybatis.mapper;

import com.gdsig.mybatis.model.BdOrgunit;
import com.gdsig.mybatis.model.BdOrgunitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BdOrgunitMapper {
    long countByExample(BdOrgunitExample example);

    int deleteByExample(BdOrgunitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BdOrgunit record);

    int insertSelective(BdOrgunit record);

    List<BdOrgunit> selectByExample(BdOrgunitExample example);

    BdOrgunit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BdOrgunit record, @Param("example") BdOrgunitExample example);

    int updateByExample(@Param("record") BdOrgunit record, @Param("example") BdOrgunitExample example);

    int updateByPrimaryKeySelective(BdOrgunit record);

    int updateByPrimaryKey(BdOrgunit record);
}