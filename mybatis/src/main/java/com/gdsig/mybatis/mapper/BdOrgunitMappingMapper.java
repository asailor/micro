package com.gdsig.mybatis.mapper;

import com.gdsig.mybatis.model.BdOrgunitMapping;
import com.gdsig.mybatis.model.BdOrgunitMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BdOrgunitMappingMapper {
    long countByExample(BdOrgunitMappingExample example);

    int deleteByExample(BdOrgunitMappingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BdOrgunitMapping record);

    int insertSelective(BdOrgunitMapping record);

    List<BdOrgunitMapping> selectByExample(BdOrgunitMappingExample example);

    BdOrgunitMapping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BdOrgunitMapping record, @Param("example") BdOrgunitMappingExample example);

    int updateByExample(@Param("record") BdOrgunitMapping record, @Param("example") BdOrgunitMappingExample example);

    int updateByPrimaryKeySelective(BdOrgunitMapping record);

    int updateByPrimaryKey(BdOrgunitMapping record);
}