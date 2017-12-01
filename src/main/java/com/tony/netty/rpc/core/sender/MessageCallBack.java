package com.tony.netty.rpc.core.sender;


import com.tony.netty.rpc.core.model.MessageRequest;
import com.tony.netty.rpc.core.model.MessageResponse;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author jiangwj20966 on 2017/11/22.
 */
public class MessageCallBack {
    private MessageRequest request;
    private MessageResponse response;
    private Lock lock = new ReentrantLock();
    private Condition finish = lock.newCondition();

    public MessageCallBack(MessageRequest request) {
        this.request = request;
    }

    public Object start() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("request sent");
            //设定一下超时时间，rpc服务器太久没有相应的话，就默认返回空吧。
            boolean timeout = !finish.await(10 * 1000, TimeUnit.MILLISECONDS);
            if (timeout) {
                System.err.println("请求超时");
                return null;
            }
            if (this.response != null) {
                return this.response.getResult();
            } else {
                return null;
            }
        } finally {
            lock.unlock();
        }
    }

    public void over(MessageResponse response) {
        try {
            lock.lock();
            finish.signal();
            this.response = response;
        } finally {
            lock.unlock();
        }
    }
}
