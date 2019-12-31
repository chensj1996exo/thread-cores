package com.bistu.thread.cores.collection;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

    // 我们整个队列的容器
    private final LinkedList<Object> list = new LinkedList<>();

    // 计数器 int count
    private final AtomicInteger count = new AtomicInteger(0);

    private final int maxSize;  // 最大容量限制

    private final int minSize = 0;

    private final Object lock = new Object();  // 锁

    public MyQueue(int maxSize){
        this.maxSize = maxSize;
    }

    public void put(Object obj){
        synchronized (lock){
            while(count.get() == maxSize){
                try{
                    lock.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
            //添加新的元素到容器中
            list.add(obj);
            count.getAndIncrement(); // i++
            System.err.println("元素: " + obj + " 已经添加到容器中！");
            // 进行唤醒可能正在等待的take方法操作中的线程
            lock.notify();
        }
    }

    public Object take(){
        Object temp = null;

        synchronized(lock){
            while(count.get() == minSize){
                try{
                    lock.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            temp = list.removeFirst();
            System.err.println("元素: " + temp + " 已经从容器中取走！");
            count.getAndDecrement();
            //进行唤醒可能正在等待的put方法操作的线程
            lock.notify();
        }

        return temp;
    }

    public int size(){
        return count.get();
    }

    public LinkedList<Object> getQueueList(){
        return list;
    }
}
