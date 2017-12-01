package com.tony.netty.rpc.mock;

import com.tony.netty.rpc.core.client.MessageSendExecutor;
import com.tony.netty.rpc.mock.service.Calculate;
import org.springframework.beans.factory.annotation.Value;

/**
 * Author jiangwj20966 on 2017/11/22.
 */
public class SingleRequestTest {
    public static void main(String[] args) {
        final MessageSendExecutor executor = new MessageSendExecutor("192.168.247.160:18888");
        Calculate calc = executor.execute(Calculate.class);
        System.out.printf("request a:%d b:%d\n", 1, 2);
        int add = calc.add(1, 2);
        System.out.println("calc add result:[" + add + "]");
        executor.stop();
    }
}
