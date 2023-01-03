package com.gdsig.auth.service.impl;

import com.gdsig.auth.service.AccountCacheService;
import com.gdsig.auth.service.RedisService;
import com.gdsig.common.constant.RedisConsts;
import com.gdsig.mybatis.model.BdAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xs
 * @date 2022/11/29上午 9:47
 */

@Service
public class AccountCacheServiceImpl implements AccountCacheService {
        
    @Autowired
    private RedisService redisService;

    @Override
    public BdAccount getAccount(String number) {
        String key = RedisConsts.ACCOUNT_KEY + number;
        Object accountObj = redisService.get(key);
        return (BdAccount) accountObj;
    }

    @Override
    public void setAccount(BdAccount account) {
        String key = RedisConsts.ACCOUNT_KEY + account.getNumber();
        redisService.set(key, account);
    }
}
