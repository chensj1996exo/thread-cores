package com.bistu.thread.cores.safely;

public class UseThreadLocal {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public void setThreadLocal(String value){
        threadLocal.set(value);
    }

    public String getThreadLocal(){
        return threadLocal.get();
    }

    public static void main(String[] args) {
        UseThreadLocal utl = new UseThreadLocal();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                utl.setThreadLocal("张三");
                System.out.println("线程t1的值：" + utl.getThreadLocal());
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                utl.setThreadLocal("李四");
                System.out.println("线程t2的值：" + utl.getThreadLocal());
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
