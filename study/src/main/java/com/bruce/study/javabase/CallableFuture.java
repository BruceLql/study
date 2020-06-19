package com.bruce.study.javabase;
/*
 *@ClassName CallableFuture
 *@Description 使用Callable+Future获取执行结果
 *@Author Bruce
 *@Date 2020/6/19 15:55
 *@Version 1.0
 */

import java.util.concurrent.*;

public class CallableFuture {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+result.get());
        } catch (Exception e) {
            if(e instanceof InterruptedException){
                //中端异常处理
            }else if(e instanceof RejectedExecutionException){
                //提交线程池被拒绝异常处理
            }else if(e instanceof ExecutionException){
                //线程执行异常处理
            }else if(e instanceof TimeoutException){
                //等待结果超时异常处理
            }else {
                // 其他异常
            }
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}
class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}