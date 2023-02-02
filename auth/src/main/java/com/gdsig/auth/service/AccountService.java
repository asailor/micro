package com.gdsig.auth.service;

import com.gdsig.common.result.CommonResult;
import com.gdsig.mybatis.model.BdAccount;
import com.gdsig.security.pojo.AccountDetails;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xs
 * @date 2022/11/24下午 4:05
 */
public interface AccountService {

    /**
     * GlobalTransactional 分布式事务注解
     *
     * @param number   number
     * @param passwrod passwrod
     * @return CommonResult<String>
     * @throws Exception Exception
     */
    @GlobalTransactional(name = "mrc-create-order")
    CommonResult<String> register(String number, String passwrod) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    CommonResult<String> login(String number, String passwrod);

    BdAccount findByNumber(String number);

    AccountDetails findUserByNumber(String number);

}
