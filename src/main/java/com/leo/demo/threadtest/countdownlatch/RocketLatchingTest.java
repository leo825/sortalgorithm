package com.leo.demo.threadtest.countdownlatch;

import java.util.concurrent.*;

/**
 * @ClassName: CountDownLatchDemo
 * @Description: 火箭发射的案例
 * 1、管理发射的主程序需要等待各个检查子程序完成才能发射
 * @Author: leo825
 * @Date: 2019-08-21 10:10
 * @Version: 1.0
 */
public class RocketLatchingTest {

    public static void main(String[] args) throws InterruptedException {
        //主线程阻塞
        CountDownLatch mainPro = new CountDownLatch(1);
        //子线程阻塞
        CountDownLatch childPro = new CountDownLatch(5);

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            int costTime = threadLocalRandom.nextInt(10);
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        mainPro.await();//等待主线程执行完毕，获取执行信号
                        System.out.println(Thread.currentThread().getName() + " 开始处理子程序...");
                        System.out.println("处理时间为：" + costTime + "秒");
                        Thread.sleep(costTime * 1000);
                        System.out.println(Thread.currentThread().getName() + " 准备就绪！！！");
                        childPro.countDown();//完成预期工作并发出完成信号
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.out.println("发射程序开始准备...");
        mainPro.countDown();
        childPro.await();
        System.out.println("点火！！！");
        executor.shutdown();
    }
}
