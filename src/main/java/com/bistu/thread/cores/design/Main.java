package com.bistu.thread.cores.design;

public class Main {

    public static void main(String[] args) {
        FutureClient fc = new FutureClient();
        Data data = fc.request("请求参数...");   //异步执行的
        System.out.println("做其他的相关业务操作！");
        String ret = data.getRequest();   //才是真正的获取实际数据的方法
        System.out.println("---------------: " + ret);
    }
}
