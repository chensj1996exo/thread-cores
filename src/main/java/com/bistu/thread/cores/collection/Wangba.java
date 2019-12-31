package com.bistu.thread.cores.collection;

import java.util.concurrent.DelayQueue;

public class Wangba implements Runnable{

    private DelayQueue<Wangmin> delayQueue = new DelayQueue<Wangmin>();

    private boolean start = true;  //表示网吧开始营业

    private void startMachine(String id, String name, int money){
        Wangmin wm = new Wangmin(id, name, System.currentTimeMillis() + money * 1000);
        System.out.println("网名：" + name + ", 身份证：" + id + ", 缴费：" + money + ", 开始上网！");
        delayQueue.add(wm);
    }

    private void overMachine(Wangmin wm){
        System.out.println("网民：" + wm.getName() + ", 身份证：" + wm.getId() + ", 已经到了下机时间！");
    }

    @Override
    public void run() {
        while(start){
            try{
                Wangmin wm = delayQueue.take();
                overMachine(wm);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Wangba wb = new Wangba();
        System.out.println("网吧开始营业：");
        Thread thread = new Thread(wb);
        thread.start();

        wb.startMachine("001", "张三", 2);
        wb.startMachine("002", "李四", 5);
        wb.startMachine("003", "王五", 10);
    }
}
