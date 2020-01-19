package com.leo.demo.futuretest;

import java.util.concurrent.*;

/**
 * @ClassName: FutureTest3
 * @Description: ${description}
 * @Author: leo825
 * @Date: 2020-01-16 18:00
 * @Version: 1.0
 */
public class FutureTest3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for(int i = 0; i < 10; i++){
                    System.out.println(i);
                    sum = i;
                }
                TimeUnit.SECONDS.sleep(2);
                return sum;
            }
        });
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(futureTask);
        executor.shutdown();
        Integer result = new Integer(0);
        System.out.println("isDone");
        result = futureTask.get();
        if(futureTask.isCancelled()){
            System.out.println("isCancelled");
        }
        System.out.println("获取到结果：" + result);
    }
}
