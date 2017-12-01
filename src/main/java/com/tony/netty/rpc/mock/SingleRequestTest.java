package com.tony.netty.rpc.mock;

import com.tony.netty.rpc.core.sender.MessageSendExecutor;
import com.tony.netty.rpc.mock.service.Calculate;
import com.tony.netty.rpc.mock.service.ListService;
import com.tony.netty.rpc.mock.service.impl.ListServiceImpl;

import java.util.List;
import java.util.Map;

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

        ListService listService = executor.execute(ListService.class);
        List<String> stringList = listService.getList();
        for (String s : stringList) {
            System.out.println(s);
        }

        Map map = listService.getMap();
        System.out.println(map);
        List<Map<String, String>> mapList = listService.getListMap();
        System.out.println(mapList);
        System.out.println(listService.getList());

        ListService listService1 = new ListServiceImpl();
        System.out.println(listService1.getListMap());
        System.out.println(listService1.getMap());
        executor.stop();
    }
}
