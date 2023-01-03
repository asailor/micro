package com.gdsig.auth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xs
 * @date 2022/11/24下午 4:08
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.gdsig.mybatis.mapper")
public class MyBatisConfig {
}
