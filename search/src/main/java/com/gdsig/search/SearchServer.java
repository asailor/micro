package com.gdsig.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xs
 * @date 2022/11/24下午 3:39
 */
@SpringBootApplication
@EnableEurekaClient
public class SearchServer {
    public static void main(String[] args) {
        SpringApplication.run(SearchServer.class,args);
    }
}
