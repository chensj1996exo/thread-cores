package com.bistu.thread.cores.safely;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ListAdd2 {

    // 1.定义的承载字符串的容器
    private static List list = new ArrayList();

    // 2.追加方法
    public void add(){
        list.add("abc");
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd2 list1 = new ListAdd2();
        //CountDownLatch:同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
        // 构造器，必须制定一个大于零的计数
        CountDownLatch latch = new CountDownLatch(1);

        // 线程A
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    for (int i = 0; i < 10; i++) {
                        list1.add();
                        System.out.println("当前线程：" +Thread.currentThread().getName() + ", 添加了一个元素..");
                        Thread.sleep(500);
                        if(list1.size() == 5){
                            System.out.println("已经发出了唤醒通知！");
                            // 计数-1
                            latch.countDown();
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }, "A");

        // 线程B
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(list.size() != 5){
                        try{
                            //线程阻塞，直到计数为0时的时候唤醒；可以响应线程中断退出阻塞
                           latch.await();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                    System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
                    throw new RuntimeException();
                }
            }
        });

        B.start();
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        A.start();
    }
}
