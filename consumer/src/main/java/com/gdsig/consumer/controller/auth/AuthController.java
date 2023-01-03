package com.gdsig.consumer.controller.auth;

import com.gdsig.common.result.CommonResult;
import com.gdsig.consumer.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xs
 * @date 2022/11/24下午 5:51
 */

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public CommonResult<String> login(@RequestParam String username, @RequestParam String password){
        return authService.login(username, password);
    }

    @PostMapping("/register")
    public CommonResult<String> register(@RequestParam String username, @RequestParam String password){
        return authService.register(username, password);
    }
}
