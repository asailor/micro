package com.gdsig.system.service;

import com.gdsig.mybatis.model.BdAccount;
import com.gdsig.security.pojo.AccountDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xs
 * @date 2022/11/24下午 4:05
 */
public interface AccountService {

    BdAccount findById(String id);

    BdAccount findByNumber(String number);

    AccountDetails findUserByNumber(String number);

}
