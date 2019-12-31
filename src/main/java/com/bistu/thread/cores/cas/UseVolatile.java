package com.bistu.thread.cores.cas;

public class UseVolatile extends Thread{

    private volatile boolean isRunning = true;
    private void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public void run(){
        System.out.println("进入run方法");
        while(isRunning){
            //...
        }
        System.out.println("线程停止！");
    }

    public static void main(String[] args) throws InterruptedException{
        UseVolatile uv = new UseVolatile();
        uv.start();//子线程

        Thread.sleep(1000);
        uv.setRunning(false);//主线程
        System.out.println("isRunning的值已经被设置成了false！");
    }
}
