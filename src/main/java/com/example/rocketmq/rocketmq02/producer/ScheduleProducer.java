package com.example.rocketmq.rocketmq02.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author lishuai
 * @date 2023/2/17
 */
@Slf4j
@Service
public class ScheduleProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void scheduleSend(){
        SendResult result = rocketMQTemplate.syncSend("Schedule-topic1", MessageBuilder.withPayload("延迟消息").build(), 1000,2);
        log.info("延迟-发送消息-->{}",result);
    }
}
