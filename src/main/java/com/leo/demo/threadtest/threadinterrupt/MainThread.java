package com.leo.demo.threadtest.threadinterrupt;

import java.util.concurrent.*;

/**
 * @author Administrator
 * @Date 2019/7/29 20:08
 * @TODO 业务场景：1、线程池运行线程 2、将超过20s还未执行的线程终止运行
 */
public class MainThread {

    private static final long OVER_TIME = 2;

    public static void main(String[] args) {
//        //核心线程池数量
//        int corePoolSize = 2;
//        //最大线程池数量
//        int maximumPoolSize = 10;
//        //线程存活时间:如果线程池当前拥有超过corePoolSize的线程，那么多余的线程在空闲时间超过keepAliveTime时会被终止，超时拒绝
//        long keepAliveTime = Long.MAX_VALUE;
//        //线程存活时间的单位，此处是秒
//        TimeUnit unit = TimeUnit.SECONDS;
//        //线程池中的队列，注意：maximumPoolSize 在 corePoolSize + workQueue 都满之后开始启用
//        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(20);
//
//        //初始化一个线程池
//        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
//
//        //开启线程池
//        for (int i = 0; i < 10; i++) {
//            executorService.submit(new MyThread());
//        }
//        //关闭线程池
//        executorService.shutdown();

        for (int i = 0; i < 10; i++) {
            overTimeClose(new MyThread());
        }
    }

    /**
     * 线程超时关闭
     */
    public static void overTimeClose(Runnable runnable) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(runnable);
        try {
            future.get(OVER_TIME, TimeUnit.SECONDS);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("线程超时关闭了...");
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}