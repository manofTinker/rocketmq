package com.example.rocketmq.rocketmq02.producer.transaction;

import com.example.rocketmq.rocketmq02.pojo.OrderPay;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import sun.misc.MessageUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author lishuai
 * @date 2023/2/20
 */
@Service
@Slf4j
public class TxProducer {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    public void tx() {

        UUID uuid = UUID.randomUUID();

        OrderPay pay = new OrderPay();
        long mony = (long) Math.random() * 10000;
        BigDecimal bigDecimal = BigDecimal.valueOf(mony);
        log.info("money:{}",bigDecimal);
        String id = UUID.randomUUID().toString();
        pay.setMsgid(id);
        pay.setMoney(bigDecimal);

        Message<OrderPay> message = MessageBuilder
                .withPayload(pay)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, uuid)
                .setHeader(RocketMQHeaders.KEYS, pay.getMsgid())
                .build();

        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction("TX-Topic1", message, null);

        log.info("发送事务消息:{}", transactionSendResult);
    }
}
