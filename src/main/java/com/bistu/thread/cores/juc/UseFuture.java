package com.bistu.thread.cores.juc;

import sun.misc.Unsafe;

import java.util.concurrent.*;

public class UseFuture implements Callable<String> {

    private String param;

    public UseFuture(String param){
        this.param = param;
    }

    @Override
    public String call() throws Exception {

        //模拟执行业务逻辑的耗时
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        String ret = this.param + ", 处理完成！";
        return ret;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String param1 = "query1";
        String param2 = "query2";

        FutureTask<String> future1 = new FutureTask<String>(new UseFuture(param1));
        FutureTask<String> future2 = new FutureTask<String>(new UseFuture(param2));

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(future1);
        executorService.submit(future2);

        System.out.println("处理其他相关业务...");
        Thread.sleep(2000);

        String ret1 = future1.get();
        String ret2 = future2.get();

        System.out.println("数据处理完成： " + ret1);
        System.out.println("数据处理完成： " + ret2);

        executorService.shutdown();  //总共耗时3秒钟

    }
}
