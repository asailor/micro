package com.gdsig.search.service.impl;

import com.gdsig.search.model.EsAccount;
import com.gdsig.search.repository.EsAccountRepository;
import com.gdsig.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xs
 * @date 2022/12/26下午 5:20
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    EsAccountRepository accountRepository;

    @Override
    public void search() {
        List<EsAccount> esAccounts = (List<EsAccount>) accountRepository.findAll();
        for (EsAccount esAccount : esAccounts) {
            System.out.println(esAccount.toString());
        }
    }

    @Override
    public void create() {

        EsAccount acount = new EsAccount();
        acount.setId(1);
        acount.setCreateTime(new Date());
        acount.setName("xx");
        acount.setUsername("login_xx");
        accountRepository.save(acount);
    }
}
