package com.example.rocketmq.rocketmq02.consumer.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author lishuai
 * @date 2023/2/20
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "TX-Topic1", consumerGroup = "TX-Consumer")
public class TxConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("收到的事务消息为:{}", message);
    }
}
