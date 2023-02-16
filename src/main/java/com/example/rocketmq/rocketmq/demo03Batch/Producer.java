package com.example.rocketmq.rocketmq.demo03Batch;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量消息
 *
 * @author lishuai
 * @date 2023/2/15
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("batch-producer");

        producer.setNamesrvAddr("192.168.30.135:9876");

        producer.start();

        List<Message> message = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String s = "hello,rocketmq(batch)"+i;
            message.add(new Message("topic3","tag3",s.getBytes()));
        }

        MessageListSplitter splitter = new MessageListSplitter(message);

        while (splitter.hasNext()){
            List<Message> next = splitter.next();
            SendResult send = producer.send(message);
        }

        producer.shutdown();
    }
}
