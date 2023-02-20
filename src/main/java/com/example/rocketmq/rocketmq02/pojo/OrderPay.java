package com.example.rocketmq.rocketmq02.pojo;

import lombok.Data;
import sun.plugin2.message.Serializer;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lishuai
 * @date 2023/2/20
 */
@Data
public class OrderPay implements Serializable {

    private static final long serialize = 6785405514983538778L;

    private String msgid;

    private BigDecimal money;
}
