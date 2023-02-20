package com.example.rocketmq.rocketmq02.producer.transaction;

import com.example.rocketmq.rocketmq02.pojo.OrderPay;
import com.example.rocketmq.rocketmq02.util.JsonUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author lishuai
 * @date 2023/2/20
 */
@Slf4j
@RocketMQTransactionListener
public class TxListence implements RocketMQLocalTransactionListener {

    /**
     * RocketMQLocalTransactionState说明：
     *
     * COMMIT：
     *         表示事务消息被提交，会被正确分发给消费者。(事务消息先会发送到broker，对消费端不可见，为UNKNOWN状态，在这里回调的时候如果返回COMMIT
     *         那么。消费端马上就会接收到消息。)
     *
     * ROLLBACK：
     *          该状态表示该事务消息被回滚，因为本地事务逻辑执行失败导致（如数据库异常，或SQL异常，或其他未知异常，这时候消息在broker会被删除掉，
     *          不会发送到consumer）
     * UNKNOWN：
     *        表示事务消息未确定，可能是业务方执行本地事务逻辑时间耗时过长或者网络原因等引起的，该状态会导致broker对事务消息进行回查，默认回查
     *        总次数是15次，第一次回查间隔时间是6s，后续每次间隔60s,（消息一旦发送状态就为中间状态：UNKNOWN）
     */

    ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(org.springframework.messaging.Message msg, Object arg) {

        String msgid = msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID).toString();

        map.put(msgid, 1);

        try {
            log.info("正在执行事务...");

            byte[] payload = (byte[]) msg.getPayload();
            String json = new String(payload, RemotingHelper.DEFAULT_CHARSET);

            OrderPay orderPay = JsonUtil.jsonToObject(json, OrderPay.class);

            log.info("事务id为:{},{}", msgid,orderPay);

            TimeUnit.SECONDS.sleep(1);

            log.info("事务执行完成...");
        } catch (Exception e) {
            map.put(msgid, -1);
            log.info("事务执行异常...");
            e.printStackTrace();
        }

        map.put(msgid, 2);

        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        String msgid = msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID).toString();
        Integer integer = map.get(msgid);

        log.info("会查事务ID：{},事务状态:{}", msgid, integer);

        if (integer == 1) {
            log.info("事务,正在执行中");
            return RocketMQLocalTransactionState.UNKNOWN;
        } else if (integer == 2) {
            log.info("事务，提交");
            return RocketMQLocalTransactionState.COMMIT;
        } else if (integer == 3) {
            log.info("事务，未知");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
