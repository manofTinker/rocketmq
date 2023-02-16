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
public class MessageListSplitter implements Iterable<List<Message>>{
    @Override
    public Iterator<List<Message>> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super List<Message>> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<List<Message>> spliterator() {
        return Iterable.super.spliterator();
    }
}
