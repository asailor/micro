package com.gdsig.search.repository;

import com.gdsig.search.model.EsAccount;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author xs
 * @date 2022/12/27上午 9:43
 */

//@Component
public interface EsAccountRepository extends ElasticsearchRepository<EsAccount, Integer> {

}
