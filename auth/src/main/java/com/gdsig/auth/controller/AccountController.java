package com.gdsig.auth.controller;

import com.gdsig.auth.service.AccountService;
import com.gdsig.common.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xs
 * @date 2022/11/24下午 4:04
 */

@RestController
@RequestMapping("/auth")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    CommonResult<String> register(@RequestParam String username, @RequestParam("password") String password){
        return accountService.register(username, password);
    }

    @PostMapping("/login")
    CommonResult<String> login(@RequestParam String username, @RequestParam("password") String password){
        return accountService.login(username, password);
    }
}
