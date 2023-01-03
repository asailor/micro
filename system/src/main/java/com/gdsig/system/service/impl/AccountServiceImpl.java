package com.gdsig.system.service.impl;

import com.gdsig.mybatis.mapper.BdAccountMapper;
import com.gdsig.mybatis.model.BdAccount;
import com.gdsig.mybatis.model.BdAccountExample;
import com.gdsig.security.pojo.Account;
import com.gdsig.security.pojo.AccountDetails;
import com.gdsig.system.service.AccountCacheService;
import com.gdsig.system.service.AccountService;
import com.gdsig.system.service.RedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xs
 * @date 2022/11/24下午 4:08
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private BdAccountMapper accountMapper;

    @Autowired
    RedisService redisService;

    @Autowired
    AccountCacheService accountCacheService;

    @Override
    public BdAccount findById(String id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public BdAccount findByNumber(String number) {
        BdAccount account = accountCacheService.getAccount(number);
        if (account != null) {
            return account;
        }

        BdAccountExample example = new BdAccountExample();
        example.createCriteria().andNumberEqualTo(number);
        List<BdAccount> accounts = accountMapper.selectByExample(example);
        if (accounts == null || accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }

    @Override
    public AccountDetails findUserByNumber(String number) {

        BdAccount account = findByNumber(number);
        if (account == null) {
            return new AccountDetails();
        }

        Account user = new Account();
        BeanUtils.copyProperties(account, user);

        return new AccountDetails(user);
    }
}
