package com.example.rocketmq.rocketmq.demo04Transcation;

import com.example.rocketmq.rocketmq.demo01Sync.producer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author lishuai
 * @date 2023/2/15
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, InterruptedException {

        TranscationListenImpl transcationListen = new TranscationListenImpl();

        TransactionMQProducer producer = new TransactionMQProducer("transcation-producer");

        producer.setNamesrvAddr("192.168.30.135:9876");

        producer.setTransactionListener(transcationListen);

        producer.start();

        String[] tag = {"TagA", "TagB", "TagC"};

        for (int i = 0; i < 3; i++) {

            Message message = new Message("TransactionTopic", tag[i % tag.length], ("hello" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            TransactionSendResult result = producer.sendMessageInTransaction(message, null);
            System.out.println("发送内容:" + result);
            TimeUnit.SECONDS.sleep(1);
            producer.shutdown();

        }


    }
}
