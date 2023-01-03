package com.gdsig.consumer.service.auth;

import com.gdsig.common.result.CommonResult;
import com.gdsig.consumer.config.FeignClientsInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xs
 * @date 2022/11/24下午 5:20
 */

@Component
@FeignClient(value = "micro-auth", contextId = "micro-auth", configuration = FeignClientsInterceptor.class)
public interface AuthService {

    @PostMapping("/auth/login")
    CommonResult<String> login(@RequestParam("username") String username, @RequestParam("password") String password);

    @PostMapping("/auth/register")
    CommonResult<String> register(@RequestParam("username") String username, @RequestParam("password") String password);

}
