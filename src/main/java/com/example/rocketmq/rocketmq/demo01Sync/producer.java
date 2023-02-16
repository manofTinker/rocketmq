package com.example.rocketmq.rocketmq.demo01Sync;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 同步发送
 *
 * @author lishuai
 * @date 2023/2/15
 */
public class producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        //1.创建生产者,并指定消费者组名称
        DefaultMQProducer producer = new DefaultMQProducer("base-sync-producer");

        //2.指定nameserver地址
        producer.setNamesrvAddr("192.168.30.135:9876");

        //3.启动生产者
        producer.start();

        //4.指定主题、标签、消息内容
        Message message = new Message("Topic1", "Tag1", "hello , rocketmq".getBytes());

        //5.发送消息
        SendResult send = producer.send(message);

        //6.关闭生产者
        System.out.println(send);

        producer.shutdown();


    }
}
