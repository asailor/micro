package com.gdsig.search.controller;

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

    @PostMapping(value = "/add")
    void create() {
        searchService.create();
    }
}
