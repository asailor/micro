package com.gdsig.auth.service.impl;

import com.gdsig.auth.service.AccountCacheService;
import com.gdsig.auth.service.AccountService;
import com.gdsig.common.constant.RedissionKeyConsts;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.util.JwtTokenUtil;
import com.gdsig.mybatis.mapper.BdAccountMapper;
import com.gdsig.mybatis.model.BdAccount;
import com.gdsig.mybatis.model.BdAccountExample;
import com.gdsig.security.pojo.Account;
import com.gdsig.security.pojo.AccountDetails;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author xs
 * @date 2022/11/24下午 4:08
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private BdAccountMapper accountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    AccountCacheService accountCacheService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Override
    public CommonResult<String> register(String number, String passwrod) {

        BdAccount account = findByNumber(number);
        if (!Objects.isNull(account)) {
            return CommonResult.fail("账号已存在");
        }

        // redis锁
        RLock redissonLock = redissonClient.getLock(RedissionKeyConsts.ACCOUNT_REGISTER_KEY + number);
        try {

            // 5秒后未获取到锁返回false
            redissonLock.tryLock(5, TimeUnit.SECONDS);
            account = new BdAccount();
            account.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            account.setNumber(number);
            account.setName("张三");
            account.setRoleId(1);
            account.setStatus(1);
            account.setPassword(passwordEncoder.encode(passwrod));
            account.setPhone("1");
            account.setCreateTime(new Date());
            accountMapper.insert(account);
            redissonLock.unlock();
        } catch (InterruptedException e) {
            return CommonResult.fail("系统异常，请稍后重试");
        }

        return CommonResult.ok("注册成功", null);
    }

    @Override
    public CommonResult<String> login(String number, String passwrod) {

        BdAccount account = findByNumber(number);
        if (account == null) {
            return CommonResult.fail("帐号或密码错误");
        }

        Account user = new Account();
        BeanUtils.copyProperties(account, user);
        AccountDetails accountDetails = new AccountDetails(user);

        if (!passwordEncoder.matches(passwrod, accountDetails.getPassword())) {
            return CommonResult.fail("密码错误");
        }

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(accountDetails,
                        null, accountDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtTokenUtil.generateToken(accountDetails.getUsername(), secret, expiration);

        account.setJwt(token);
        accountMapper.updateByPrimaryKey(account);
        accountCacheService.setAccount(account);
        return CommonResult.ok(token);
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
            return null;
        }
        Account user = new Account();
        BeanUtils.copyProperties(account, user);

        return new AccountDetails(user);
    }
}
