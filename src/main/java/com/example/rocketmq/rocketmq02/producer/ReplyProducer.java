package com.example.rocketmq.rocketmq02.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lishuai
 * @date 2023/2/17
 */
@Service
@Slf4j
public class ReplyProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void replySend() {

        for (int i = 0; i < 10; i++) {

            String msg = "回馈消息-->" + i;

            System.out.println(msg);

            Object receive = rocketMQTemplate.sendAndReceive("reply-topic", msg, String.class);

            log.info("收到消费者的消息为:{}", receive);
        }
    }
}
