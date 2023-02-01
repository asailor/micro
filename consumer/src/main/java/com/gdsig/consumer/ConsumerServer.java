package com.gdsig.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xs
 * @date 2022/11/24下午 5:40
 */

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.gdsig.consumer.service.*"})
public class ConsumerServer {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerServer.class, args);
    }

}
