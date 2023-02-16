package com.example.rocketmq.rocketmq.demo02Async;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.unix.Buffer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.tomcat.util.buf.HexUtils;
import org.apache.tomcat.util.buf.StringUtils;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * 异步发送
 *
 * @author lishuai
 * @date 2023/2/15
 */
public class Comsumer {
    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("base-Consumer2");

        consumer.setNamesrvAddr("192.168.30.135:9876");

        consumer.setMessageModel(MessageModel.BROADCASTING);

        consumer.subscribe("topic2","*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                for (MessageExt messageExt : list) {
                    byte[] body = messageExt.getBody();

                    System.out.println("转换："+ new String(messageExt.getBody()));
                }

                System.out.println(list);

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

    }
}
