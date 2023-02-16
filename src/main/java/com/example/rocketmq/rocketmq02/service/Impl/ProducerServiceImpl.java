package com.example.rocketmq.rocketmq02.service.Impl;

import com.example.rocketmq.rocketmq02.pojo.User;
import com.example.rocketmq.rocketmq02.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lishuai
 * @date 2023/2/16
 */
@Slf4j
@Service
public class ProducerServiceImpl implements ProducerService {

    @Value("${rocketmq.producer.send-message-timeout}")
    private Integer messageTimeOut;

    private static final String topic = "TOPIC_TEST";

    @Autowired
    private RocketMQTemplate rocketMQTemplatel;

    @Override
    public void send(User user) {
        rocketMQTemplatel.convertAndSend(topic + ":tag1", user);
        //rocketMQTemplatel.send(topic+":tag1", MessageBuilder.withPayload(user).build());
    }

    /**
     * 同步发送
     *
     * @param msg
     * @return
     */
    @Override
    public SendResult sendSyncMsg(String msg) {
        org.apache.rocketmq.client.producer.SendResult sendResult = rocketMQTemplatel.syncSend(topic + ":tag1", MessageBuilder.withPayload(msg).build());
        log.info("发送消息为:{}", sendResult);
        return sendResult;
    }

    /**
     * 异步发送
     *
     * @param msg
     */
    @Override
    public void sendAsyncMsg(String msg) {
        rocketMQTemplatel.asyncSend(topic, MessageBuilder.withPayload(msg).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("成功-->{}", sendResult.getMsgId());
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("失败--->{}", throwable);
                throwable.printStackTrace();
            }
        });
    }


    @Override
    public void delaySendMsg(String msg, int Dalaylevel) {
        rocketMQTemplatel.syncSend(topic, MessageBuilder.withPayload(msg).build(), messageTimeOut, Dalaylevel);
    }

    /**
     * 单项发送
     *
     * @param msg
     */
    @Override
    public void sendOneWayMsg(String msg) {
        rocketMQTemplatel.sendOneWay(topic, MessageBuilder.withPayload(msg).build());
    }
}
