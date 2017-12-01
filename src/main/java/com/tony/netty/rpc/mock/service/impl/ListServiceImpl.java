package com.tony.netty.rpc.mock.service.impl;

import com.tony.netty.rpc.mock.service.ListService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListServiceImpl implements ListService {
    public List<String> getList() {
        return Arrays.asList("1", "2", "3");
    }

    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "one");
        map.put("2", "two");
        return map;
    }

    public List<Map<String, String>> getListMap() {
        return Arrays.asList(getMap(), getMap(), getMap());
    }
}
