package com.example.rocketmq.rocketmq02.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author lishuai
 * @date 2023/2/17
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "Tag-topic1", selectorExpression = "tag1||tag2", consumerGroup = "TagConsumer")
public class TagConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("消费端->tag消息:{}", message);
    }
}
