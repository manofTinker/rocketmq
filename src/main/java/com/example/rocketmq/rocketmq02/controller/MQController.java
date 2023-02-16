package com.example.rocketmq.rocketmq02.controller;

import com.example.rocketmq.rocketmq02.pojo.User;
import com.example.rocketmq.rocketmq02.service.Impl.ProducerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lishuai
 * @date 2023/2/16
 */
@Slf4j
@RestController
@RequestMapping("/rocket")
public class MQController {

    @Autowired
    private ProducerServiceImpl producerService;

    @PostMapping("/send/user")
    public void send(@RequestBody User user){
//        User user1 = new User();
//        user1.setUserid(1);
//        user1.setUsername("123");
        producerService.send(user);
        log.info("发送user");
    }
}
