package com.gdsig.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xs
 * @date 2022/11/24下午 4:08
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.gdsig.mybatis.mapper", "com.gdsig.system.dao"})
public class MyBatisConfig {

}
