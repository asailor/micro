package com.gdsig.auth.service.impl;

import com.gdsig.auth.feign.RoleApi;
import com.gdsig.auth.service.AccountCacheService;
import com.gdsig.auth.service.AccountService;
import com.gdsig.common.constant.RedissionKeyConsts;
import com.gdsig.common.dto.system.RoleDto;
import com.gdsig.common.exception.RespException;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.result.RespCodeEnum;
import com.gdsig.common.util.JwtTokenUtil;
import com.gdsig.common.util.StringUtil;
import com.gdsig.mybatis.mapper.BdAccountMapper;
import com.gdsig.mybatis.model.BdAccount;
import com.gdsig.mybatis.model.BdAccountExample;
import com.gdsig.security.pojo.Account;
import com.gdsig.security.pojo.AccountDetails;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;
import org.apache.commons.lang3.StringUtils;
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
import java.util.*;
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

    @Autowired
    RoleApi roleApi;

    @Override
//    @GlobalTransactional(name = "mrc-create-order", rollbackFor = Exception.class)
    public CommonResult<String> register(String number, String passwrod) {

        BdAccount account = findByNumber(number);
        if (!Objects.isNull(account)) {
            return CommonResult.fail("账号已存在");
        }

        // redis锁
        RLock redissonLock = redissonClient.getLock(RedissionKeyConsts.ACCOUNT_REGISTER_KEY + number);
            // 5秒后未获取到锁返回false
//        redissonLock.tryLock(5, TimeUnit.SECONDS);
        account = new BdAccount();
        account.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        account.setNumber(number);
        account.setName("张三");
        account.setRoleId(1);
        account.setStatus(1);
        account.setPassword(passwordEncoder.encode(passwrod));
        account.setPhone("1");
        account.setCreateTime(new Date());
        account.setOrgunitId(1);
        accountMapper.insert(account);

        RoleDto roleDto = new RoleDto();
        roleDto.setName("seate测试");
        roleDto.setOrgunitId("1");
        roleDto.setTypeId(1);
        roleDto.setSuperadmin(false);

        CommonResult<Void> roleResult = roleApi.add(roleDto);


        boolean flag = true;

        System.out.println(RootContext.getXID());

        if(flag){
            throw new RuntimeException("回滚");
        }

//        redissonLock.unlock();
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
