package com.example.rocketmq.rocketmq.demo04Transcation;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lishuai
 * @date 2023/2/15
 */
@Slf4j
public class TranscationListenImpl implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {

        if (StringUtils.pathEquals(message.getTags(), "TagA")) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else if (StringUtils.pathEquals(message.getTags(), "TagB")) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else {
            return LocalTransactionState.UNKNOW;
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        log.info("回查:{}", messageExt.getTags());
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
