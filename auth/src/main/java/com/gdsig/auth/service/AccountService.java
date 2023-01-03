package com.gdsig.auth.service;

import com.gdsig.common.result.CommonResult;
import com.gdsig.mybatis.model.BdAccount;
import com.gdsig.security.pojo.AccountDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xs
 * @date 2022/11/24下午 4:05
 */
public interface AccountService {

    @Transactional(rollbackFor = Exception.class)
    CommonResult<String> register(String number, String passwrod);

    @Transactional(rollbackFor = Exception.class)
    CommonResult<String> login(String number, String passwrod);

    BdAccount findByNumber(String number);

    AccountDetails findUserByNumber(String number);

}
