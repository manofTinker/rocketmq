package com.example.rocketmq.rocketmq02.service;

import com.example.rocketmq.rocketmq02.pojo.User;
import com.example.rocketmq.rocketmq02.vo.Result;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author lishuai
 * @date 2023/2/16
 */
public interface ProducerService {

    /**
     * 普通发送
     *
     * @param user
     */
    void send(User user);

    /**
     * 同步发送
     *
     * @param msg
     * @return
     */
    SendResult sendSyncMsg(String msg);

    /**
     * 异步发送
     *
     * @param msg
     */
    void sendAsyncMsg(String msg);

    /**
     * 延迟发送
     *
     * @param msg
     * @param Dalaylevel
     */
    void delaySendMsg(String msg, int Dalaylevel);

    /**
     * 发送单向消息（只负责发送消息，不等待应答，不关心发送结果，如日志）
     *
     * @param msg
     */
    void sendOneWayMsg(String msg);

}
