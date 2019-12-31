package com.bistu.thread.cores.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UseConcurrentHashMap {

    public static void main(String[] args){

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k1", "vv1");
        map.putIfAbsent("k1", "vvv1");//k1存在的时候，则不进行数据更新，不存在的话，则增加这个键值对。

        for(Map.Entry<String, Object> me: map.entrySet()){
            System.err.println("key: " + me.getKey()+", value: " +me.getValue());
        }

        map.size();

    }
}
