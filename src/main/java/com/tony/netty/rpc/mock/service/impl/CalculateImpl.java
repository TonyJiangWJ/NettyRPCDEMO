package com.tony.netty.rpc.mock.service.impl;

import com.tony.netty.rpc.mock.service.Calculate;

/**
 * Author jiangwj20966 on 2017/11/22.
 */
public class CalculateImpl implements Calculate {
    public int add(int a, int b) {
        return a + b;
    }
}
