package com.example.rocketmq.rocketmq02.controller;

import com.example.rocketmq.rocketmq02.producer.BatchProducer;
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
    private BatchProducer batchProducer;

    @GetMapping("/batch")
    public void batchsend(){
        batchProducer.BatchSend();
    }

}
