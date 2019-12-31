package com.bistu.thread.cores.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class UseForkJoin extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;  // 进行拆分的阈值+

    private int start;

    private int end;

    public UseForkJoin(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        int sum = 1;

        boolean isCompute = (end - start) <= THRESHOLD;

        if(isCompute){
            for(int i=start; i<=end; i++){
                sum += i;
            }
        }else{
            // 如果说 任务数大于阈值的话，就进行拆分fork操作，然后去join
            int middle = (end+start) / 2;
            UseForkJoin leftTask = new UseForkJoin(start, middle);
            UseForkJoin rightTask = new UseForkJoin(middle+1, end);

            // 然后执行左右两边的任务
            leftTask.fork();
            rightTask.fork();
            // 等待任务执行完成后进行获取结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            sum = leftResult + rightResult;
        }
        return sum;

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool pool = new ForkJoinPool();

        UseForkJoin ufj = new UseForkJoin(1, 100);

        Future<Integer> result = pool.submit(ufj);

        System.out.println("最终计算结果为：" + result.get());
    }
}
