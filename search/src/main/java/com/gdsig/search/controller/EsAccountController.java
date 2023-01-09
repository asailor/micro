package com.gdsig.search.controller;

import com.gdsig.search.model.EsAccount;
import com.gdsig.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xs
 * @date 2022/12/27上午 9:49
 */
@RestController
@RequestMapping("/search/accounts")
public class EsAccountController {

    @Autowired
    SearchService searchService;

    @GetMapping
    void list() {
        searchService.search();
    }

    @GetMapping(value = "/{id}")
    EsAccount get(@PathVariable Integer id) {
        return searchService.get(id);
    }

    @PostMapping(value = "/add")
    void create() {
        searchService.add();
    }

    @PostMapping(value = "/{id}/delete")
    void delete(@PathVariable Integer id) {
        searchService.delete(id);
    }

}
