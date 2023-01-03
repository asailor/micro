package com.gdsig.system.service;

import com.gdsig.mybatis.model.BdAccount;

/**
 * @author xs
 * @date 2022/11/28下午 5:01
 */
public interface AccountCacheService {

    BdAccount getAccount(String number);

    void setAccount(BdAccount account);
}
