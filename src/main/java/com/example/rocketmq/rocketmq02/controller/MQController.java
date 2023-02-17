package com.example.rocketmq.rocketmq02.controller;

import com.example.rocketmq.rocketmq02.producer.*;
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

    @Autowired
    private BasicProducer basicProducer;

    @Autowired
    private TagProducer tagProducer;

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private ReplyProducer replyProducer;

    @GetMapping("/sync")
    public void syncSend() {
        basicProducer.Synsend();
    }

    @GetMapping("/async")
    public void AsyncSend() {
        basicProducer.Asynsend();
    }

    @GetMapping("/oneway")
    public void oneWaySend() {
        basicProducer.OnwaySend();
    }

    @GetMapping("/batch")
    public void batchsend() {
        batchProducer.BatchSend();
    }

    @GetMapping("/tag")
    public void tagSend() {
        tagProducer.TagSend();
    }

    @GetMapping("/orderly")
    public void orderlySend() throws InterruptedException {
        orderProducer.orderSend();
    }

    public void replySend() {
        replyProducer.replySend();
    }

}
