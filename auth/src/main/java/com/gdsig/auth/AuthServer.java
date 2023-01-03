package com.gdsig.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xs
 * @date 2022/11/29下午 5:16
 */
@SpringBootApplication
@EnableEurekaClient
public class AuthServer {
    public static void main(String[] args) {
        SpringApplication.run(AuthServer.class,args);
    }

}
