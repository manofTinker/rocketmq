package com.example.rocketmq.rocketmq.demo02Async;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;

/**
 * 异步发送
 *
 * @author lishuai
 * @date 2023/2/15
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("Async-Comsumer");

        producer.setNamesrvAddr("192.168.30.135:9876");

        producer.start();

        producer.setRetryTimesWhenSendAsyncFailed(0);

        for(int i=0;i<10;i++){
            final int index = i;
            Message message = new Message("topic2", "tag2", "ID123","hello , rocketmq(Async send)".getBytes());

            //默认的延迟级别：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h，共18个级别。
            message.setDelayTimeLevel(1);

            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("--->发送成功:"+index+","+sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("--->报错："+index+","+throwable);
                    throwable.printStackTrace();
                }
            });
        }

        TimeUnit.MINUTES.sleep(1);

        producer.shutdown();
    }
}
