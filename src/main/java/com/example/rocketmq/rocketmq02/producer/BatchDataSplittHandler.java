package com.example.rocketmq.rocketmq02.producer;

import lombok.Data;
import org.springframework.messaging.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 分割数据
 *
 * @author lishuai
 * @date 2023/2/17
 */
@Data
public class BatchDataSplittHandler implements Iterator<List<Message>> {

    //限制大小
    private final static int LIMIT_SIZE = 1024 * 1024;

    private List<Message> message;

    //指针
    private int currIndex;

    public BatchDataSplittHandler(List<Message> message) {
        this.message = message;

        message.forEach(m -> {
            if (m.toString().length() > LIMIT_SIZE) {
                throw new RuntimeException("单条消息不能大于" + LIMIT_SIZE + " B");
            }
        });
    }

    @Override
    public boolean hasNext() {
        return currIndex < message.size();
    }

    @Override
    public List<Message> next() {
        int nextIndex = currIndex;

        int totalsize = 0;

        for (; nextIndex < message.size(); nextIndex++) {
            Message message1 = this.message.get(nextIndex);
            totalsize = totalsize + message1.toString().length();

            if (totalsize > LIMIT_SIZE) {
                break;
            }
        }

        List<Message> subList = message.subList(currIndex, nextIndex);
        currIndex = nextIndex;

        return subList;
    }
}
