package com.example.rocketmq.rocketmq02.service.Impl;

import com.alibaba.fastjson.JSON;
import com.example.rocketmq.rocketmq02.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author lishuai
 * @date 2023/2/16
 */
@Slf4j
@Service
@RocketMQMessageListener(nameServer = "192.168.30.135:9876", topic = "TOPIC_TEST", consumerGroup = "consumer1")
public class ConsumerServiceImpl implements RocketMQListener<User> {

    @Override
    public void onMessage(User message) {
        log.info("收到的消息为--->{}", JSON.toJSONString(message));
    }
}
