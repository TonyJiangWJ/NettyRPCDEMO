package com.tony.netty.rpc.mock.service;

import java.util.List;
import java.util.Map;

public interface ListService {

    List<String> getList();

    Map<String,String> getMap();

    List<Map<String, String>> getListMap();
}
