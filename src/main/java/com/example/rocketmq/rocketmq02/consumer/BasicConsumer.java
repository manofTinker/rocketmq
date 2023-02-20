package com.example.rocketmq.rocketmq02.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author lishuai
 * @date 2023/2/17
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "basic-topic1", consumerGroup = "basic-consumer")
public class BasicConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("消费端->基础消息:{}",message);
    }
}
