package com.tony.netty.rpc.servicebean;

import com.tony.netty.rpc.core.MessageSendExecutor;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.CountDownLatch;

/**
 * Author jiangwj20966 on 2017/11/22.
 */
public class RpcParallelTest {

    public static void main(String[] args) throws Exception {
        final MessageSendExecutor executor = new MessageSendExecutor("127.0.0.1:18888");
        //并行度10000
        int parallel = 10000;

        //开始计时
        StopWatch sw = new StopWatch();
        sw.start();

        CountDownLatch signal = new CountDownLatch(1);
        CountDownLatch finish = new CountDownLatch(parallel);

        for (int index = 0; index < parallel; index++) {
            CalcParallelRequestThread client = new CalcParallelRequestThread(executor, signal, finish, index);
            new Thread(client).start();
        }

        //10000个并发线程瞬间发起请求操作
        signal.countDown();
        finish.await();

        sw.stop();

        String tip = String.format("RPC调用总共耗时: [%s] 毫秒", sw.getTime());
        System.out.println(tip);

        executor.stop();
    }
}
