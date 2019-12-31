package com.bistu.thread.cores.collection;

import java.util.Iterator;
import java.util.concurrent.*;

public class UseBlockingQueue {

    public static void main(String[] args) throws Exception{

        // 1.高性能的无阻塞无界队列
        /**
        ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<>();

        clq.offer("a");
        clq.add("b");
        clq.add("c");
        clq.add("d");

        System.out.println("从头部取出的元素： " + clq.poll());//从头部取出一个元素，并且从容器本身移除
        System.out.println("容器长度： " + clq.size());
        System.out.println("从头部取出的元素： " + clq.peek());//从头部取出一个元素，但不会从容器中移除该元素
        System.out.println("容器长度： " + clq.size());
         */

        // 2.基于阻塞--有界队列
        /**
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(5);
        abq.put("a");
        abq.add("b");
        abq.add("c");
        abq.add("d");
        abq.add("e");

        System.out.println(abq.offer("f", 2, TimeUnit.SECONDS));
        ArrayBlockingQueue<String> abq2 = new ArrayBlockingQueue<>(5);
        abq.drainTo(abq2, 3);

        for(Iterator it = abq2.iterator(); it.hasNext();){
            String string = (String)it.next();
            System.out.println("元素：" + string);
        }
         */

        // 3.基于阻塞--无界队列
        //LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>();

        // 4.不能盛放任何元素的阻塞队列
        SynchronousQueue<String> sq = new SynchronousQueue<>();

        new Thread(new Runnable(){
            public void run(){
                try{
                    System.out.println("元素内容：" + sq.take());
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sq.add("a");
            }
        }, "t2").start();




    }

}
