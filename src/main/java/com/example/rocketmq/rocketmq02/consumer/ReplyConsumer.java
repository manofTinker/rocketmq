package com.example.rocketmq.rocketmq02.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author lishuai
 * @date 2023/2/17
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "reply-topic", consumerGroup = "reply-consumer")
public class ReplyConsumer implements RocketMQReplyListener<String, byte[]> {

    @Override
    public byte[] onMessage(String message) {

        log.info("发送");

        return "返回消息".getBytes(StandardCharsets.UTF_8);
    }
}
