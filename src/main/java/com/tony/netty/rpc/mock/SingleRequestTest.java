package com.tony.netty.rpc.mock;

import com.tony.netty.rpc.core.sender.MessageSendExecutor;
import com.tony.netty.rpc.mock.service.Calculate;

/**
 * Author jiangwj20966 on 2017/11/22.
 */
public class SingleRequestTest {
    public static void main(String[] args) {
        final MessageSendExecutor executor = new MessageSendExecutor("192.168.247.161:18888");
        Calculate calc = executor.execute(Calculate.class);
        System.out.printf("request a:%d b:%d\n", 1, 2);
        int add = calc.add(1, 2);
        System.out.println("calc add result:[" + add + "]");
        executor.stop();
    }
}
