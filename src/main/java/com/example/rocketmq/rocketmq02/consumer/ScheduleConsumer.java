package com.example.rocketmq.rocketmq02.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author lishuai
 * @date 2023/2/17
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "Schedule-topic1", consumerGroup = "schedule-consumer")
public class ScheduleConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("消费端->延迟消息:{}",message);
    }
}
