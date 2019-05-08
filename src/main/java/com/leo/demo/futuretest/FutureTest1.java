package com.leo.demo.futuretest;

import java.util.concurrent.*;

/**
 * Callable位于java.util.concurrent包下，它也是一个接口，在它里面也只声明了一个方法，只不过这个方法叫做call()：
 * 那么怎么使用Callable呢？一般情况下是配合ExecutorService来使用的，在ExecutorService接口中声明了若干个submit方法的重载版本
 * <p/>
 * <p/>
 * Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
 *
 * @author Administrator
 * @Date 2019/5/8 11:22
 * @TODO
 */
public class FutureTest1 {
    public static void main(String[] args) {
        /**
         * Future提供了三种功能：
         *　1）判断任务是否完成；
         *　2）能够中断任务；
         *　3）能够获取任务执行结果。
         */
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
            System.out.println("task运行结果" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

    //新定义一个task类
    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++)
                sum += i;
            return sum;
        }
    }
}
