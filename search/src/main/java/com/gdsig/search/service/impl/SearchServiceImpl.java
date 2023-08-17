package com.gdsig.search.service.impl;

import com.gdsig.search.model.EsAccount;
import com.gdsig.search.repository.EsAccountRepository;
import com.gdsig.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Pageable pageable = PageRequest.of(0, 10);
        Page<EsAccount> esAccounts = accountRepository.findAll(pageable);

        List<EsAccount> accounts = esAccounts.get().collect(Collectors.toList());
        for (EsAccount esAccount : accounts) {
            System.out.println(esAccount.toString());
        }
    }

    @Override
    public EsAccount get(Integer id) {
        return accountRepository.findById(id).orElse(new EsAccount());
    }

    @Override
    public void add() {

        EsAccount acount = new EsAccount();
        acount.setId(1);
        acount.setCreateTime(LocalDateTime.now());
        acount.setName("xx");
        acount.setUsername("login_xx");
        accountRepository.save(acount);
    }

    @Override
    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }
}
