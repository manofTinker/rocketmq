package com.example.rocketmq.rocketmq.demo03Batch;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author lishuai
 * @date 2023/2/15
 */
class MessageListSplitter implements Iterator<List<Message>> {

    /**
     * 分割数据大小
     */
    private final int sizeLimit = 1024 * 1024;
    ;

    /**
     * 分割数据列表
     */
    private final List<Message> messages;

    /**
     * 分割索引
     */
    private int currIndex;

    public MessageListSplitter(List<Message> messages) {
        this.messages = messages;
        // 保证单条数据的大小不大于sizeLimit
        messages.forEach(m -> {
            if (m.toString().length() > sizeLimit) {
                throw new RuntimeException("单挑消息不能大于" + sizeLimit + "B");
            }
        });
    }


    @Override
    public boolean hasNext() {
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        int nextIndex = currIndex;
        int totalSize = 0;
        for (; nextIndex < messages.size(); nextIndex++) {
            Message t = messages.get(nextIndex);
            totalSize = totalSize + t.toString().length();
            if (totalSize > sizeLimit) {
                break;
            }
        }
        List<Message> subList = messages.subList(currIndex, nextIndex);
        currIndex = nextIndex;
        return subList;
    }
}
