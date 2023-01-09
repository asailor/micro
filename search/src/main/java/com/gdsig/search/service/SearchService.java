package com.gdsig.search.service;

import com.gdsig.search.model.EsAccount;

/**
 * @author xs
 * @date 2022/12/26下午 5:20
 */
public interface SearchService {

    void search();

    EsAccount get(Integer id);

    void add();

    void delete(Integer id);
}
