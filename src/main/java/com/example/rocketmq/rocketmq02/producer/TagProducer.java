package com.example.rocketmq.rocketmq02.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * 标签过滤消息
 *
 * @author lishuai
 * @date 2023/2/17
 */
@Service
@Slf4j
public class TagProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void TagSend() {

        String topic = "Tag-topic1";

        String msg = "标签过滤消息" + System.currentTimeMillis();

        log.info("消息为:" + msg);

        rocketMQTemplate.syncSend(topic + ":tag1", MessageBuilder.withPayload(topic).build());

        log.info("发送标签过滤消息成功！");

    }
}
