package com.gdsig.system.controller;

import com.gdsig.system.service.RocketMqTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xs
 * @date 2022/12/22上午 10:20
 */
@RestController
@RequestMapping("/system/rocketmq")
public class RocketMqController {

    @Autowired
    RocketMqTestService rocketMqTestService;

    @GetMapping("/send")
    void send(){
        rocketMqTestService.send();
    }

    @GetMapping("/sync")
    void syncSend(){
        rocketMqTestService.syncSend();
    }

    @GetMapping("/async")
    void asyncSend(){
        rocketMqTestService.asyncSend();
    }

    @GetMapping("/delay")
    void delaySend(){
        rocketMqTestService.delaySend();
    }
}
