package com.example.rocketmq.rocketmq02.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量添加
 *
 * @author lishuai
 * @date 2023/2/17
 */
@Slf4j
@Service
public class BatchProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public void BatchSend() {

        String msg = "批量发送" + System.currentTimeMillis();

        log.info("发送消息:{}", msg);

        List<Message> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(MessageBuilder.withPayload(msg).build());
        }

        BatchDataSplittHandler producer = new BatchDataSplittHandler(list);

        while (producer.hasNext()) {
            List<Message> next = producer.next();

            SendResult result = rocketMQTemplate.syncSend("batch-topic1", next);

            if (result.getSendStatus() == SendStatus.SEND_OK) {
                log.info("批量发送成功,数据：{}", result);
            } else {
                log.info("批量发送失败,数据:{}", result);
            }
        }
    }

}
