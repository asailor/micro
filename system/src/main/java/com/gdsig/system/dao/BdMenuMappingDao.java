package com.gdsig.system.dao;

import com.gdsig.common.dto.system.FunctionListParam;
import com.gdsig.mybatis.model.BdMenuMapping;
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
public interface BdMenuMappingDao {

    List<BdMenuMapping> searchRecord(@Param("queryParam") FunctionListParam listParam);
}
