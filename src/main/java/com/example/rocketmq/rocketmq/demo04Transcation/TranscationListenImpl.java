package com.example.rocketmq.rocketmq.demo04Transcation;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.util.StringUtils;

/**
 * @author lishuai
 * @date 2023/2/15
 */
public class TranscationListenImpl implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {

        System.out.println("执行本地事务");

        if (StringUtils.pathEquals("TagA", message.getTags())) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else if (StringUtils.pathEquals("TagB", message.getTags())) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else {
            return LocalTransactionState.UNKNOW;
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        System.out.println("MQ检查消息Tag[" + messageExt.getTags() + "]本地事务执行结果");

        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
