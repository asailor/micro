package com.gdsig.mybatis.mapper;

import com.gdsig.mybatis.model.BdAccount;
import com.gdsig.mybatis.model.BdAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BdAccountMapper {
    long countByExample(BdAccountExample example);

    int deleteByExample(BdAccountExample example);

    int deleteByPrimaryKey(String id);

    int insert(BdAccount record);

    int insertSelective(BdAccount record);

    List<BdAccount> selectByExampleWithBLOBs(BdAccountExample example);

    List<BdAccount> selectByExample(BdAccountExample example);

    BdAccount selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BdAccount record, @Param("example") BdAccountExample example);

    int updateByExampleWithBLOBs(@Param("record") BdAccount record, @Param("example") BdAccountExample example);

    int updateByExample(@Param("record") BdAccount record, @Param("example") BdAccountExample example);

    int updateByPrimaryKeySelective(BdAccount record);

    int updateByPrimaryKeyWithBLOBs(BdAccount record);

    int updateByPrimaryKey(BdAccount record);
}