package com.gdsig.mybatis.mapper;

import com.gdsig.mybatis.model.BdMenu;
import com.gdsig.mybatis.model.BdMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BdMenuMapper {
    long countByExample(BdMenuExample example);

    int deleteByExample(BdMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BdMenu record);

    int insertSelective(BdMenu record);

    List<BdMenu> selectByExample(BdMenuExample example);

    BdMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BdMenu record, @Param("example") BdMenuExample example);

    int updateByExample(@Param("record") BdMenu record, @Param("example") BdMenuExample example);

    int updateByPrimaryKeySelective(BdMenu record);

    int updateByPrimaryKey(BdMenu record);
}