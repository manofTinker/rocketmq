package com.example.rocketmq.rocketmq.demo01Sync;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author lishuai
 * @date 2023/2/15
 */
public class comsumer {
    public static void main(String[] args) throws MQClientException {

        //1.启动消费者，并指定消费者组名称
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("base-consumer1");

        //2.指定nameserver地址
        consumer.setNamesrvAddr("192.168.30.135:9876");

        //3.订阅主题
        consumer.subscribe("Topic1","*");

        //4.负载均衡模式(默认为负载均衡模式)
        consumer.setMessageModel(MessageModel.CLUSTERING);

        //5.设置消息监听，回调
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                System.out.printf("%s ——>收到新的消息: %s %n",
                        Thread.currentThread().getName(), list);

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //6.启动消费者
        consumer.start();
    }
}
