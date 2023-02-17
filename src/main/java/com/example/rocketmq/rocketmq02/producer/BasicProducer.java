package com.example.rocketmq.rocketmq02.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础发送
 *
 * @author lishuai
 * @date 2023/2/17
 */
@Service
@Slf4j
public class BasicProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void Synsend() {

        String msg = "基础发送-同步发送" + System.currentTimeMillis();

        log.info("发送消息：{}", msg);

        rocketMQTemplate.syncSend("basic-topic1", msg);

        log.info("基础发送(同步)-发送成功");
    }


    public void Asynsend() {
        String msg = "基础发送-异步发送" + System.currentTimeMillis();

        log.info("发送消息：{}", msg);

        rocketMQTemplate.syncSend("basic-topic1", msg);

        log.info("基础发送(异步)-发送成功");
    }

    public void OnwaySend() {
        String msg = "基础发送-单向发送" + System.currentTimeMillis();

        log.info("发送消息:{}", msg);

        rocketMQTemplate.sendOneWay("basic-topic1", msg);

        log.info("基础发送(单向)-发送成功");
    }


}
