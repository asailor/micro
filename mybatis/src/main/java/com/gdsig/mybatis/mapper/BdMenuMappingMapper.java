package com.gdsig.mybatis.mapper;

import com.gdsig.mybatis.model.BdMenuMapping;
import com.gdsig.mybatis.model.BdMenuMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BdMenuMappingMapper {
    long countByExample(BdMenuMappingExample example);

    int deleteByExample(BdMenuMappingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BdMenuMapping record);

    int insertSelective(BdMenuMapping record);

    List<BdMenuMapping> selectByExample(BdMenuMappingExample example);

    BdMenuMapping selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BdMenuMapping record, @Param("example") BdMenuMappingExample example);

    int updateByExample(@Param("record") BdMenuMapping record, @Param("example") BdMenuMappingExample example);

    int updateByPrimaryKeySelective(BdMenuMapping record);

    int updateByPrimaryKey(BdMenuMapping record);
}