package com.gdsig.system.config;

import com.gdsig.security.config.SecurityConfig;
import com.gdsig.system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author xs
 * @date 2022/11/28上午 10:50
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SystemSecurity extends SecurityConfig {

    @Autowired
    private AccountService accountService;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> accountService.findUserByNumber(username);
    }
}
