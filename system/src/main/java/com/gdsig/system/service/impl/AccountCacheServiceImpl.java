package com.gdsig.system.service.impl;

import com.gdsig.common.constant.RedisConsts;
import com.gdsig.mybatis.model.BdAccount;
import com.gdsig.system.service.AccountCacheService;
import com.gdsig.system.service.RedisService;
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
