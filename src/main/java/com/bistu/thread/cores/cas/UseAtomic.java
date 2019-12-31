package com.bistu.thread.cores.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UseAtomic {

    //private static int count = 0;
    private static AtomicInteger count = new AtomicInteger(0);

    public synchronized int add(){
//        count = count + 10;
        count.addAndGet(4);
        count.addAndGet(3);//只能保证当前语句是原子性的，但不能保证整个add方式是原子性的，因此加上synchronized保证了方法是原子性的
        count.addAndGet(2);
        count.addAndGet(1);
        return count.get();
    }

    public static void main(String[] args) {
        UseAtomic ua = new UseAtomic();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("累计结果：" + ua.add());
                }
            }));
        }
        for(Thread t: list){
            t.start();
        }
    }
}
