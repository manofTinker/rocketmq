package com.example.rocketmq.rocketmq02.producer;

import io.netty.util.Timeout;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 顺序消息生产
 *
 * @author lishuai
 * @date 2023/2/17
 */
@Service
@Slf4j
public class OrderProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void orderSend() throws InterruptedException {

        String msg = "顺序生产消息ID:";

        for (int i = 0; i < 10; i++) {

            log.info("顺序生产消息...");

            int num = (int) Math.random() * 100;

            TimeUnit.MILLISECONDS.sleep(50);

            log.info("顺序生产消息ID:{}", num);

            rocketMQTemplate.syncSendOrderly("orderly-topic1", msg + num, "orderly");

            log.info("已发送...");

        }
    }

}
