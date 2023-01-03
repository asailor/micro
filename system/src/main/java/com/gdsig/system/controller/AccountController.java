package com.gdsig.system.controller;

import com.gdsig.common.result.CommonResult;
import com.gdsig.mybatis.model.BdAccount;
import com.gdsig.system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xs
 * @date 2022/11/24下午 4:04
 */

@RestController
@RequestMapping("/system")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    CommonResult<BdAccount> findById(@PathVariable String id){
        return CommonResult.ok(accountService.findById(id));
    }
}
