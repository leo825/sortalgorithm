package com.leo.demo.threadtest.countdownlatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: Runner
 * @Description: 设计百米赛跑统计时间的小程序
 * 1、10个选手听信号枪，统一开始跑步
 * 2、打印出来每个选手耗时时间单位秒
 * 3、打印比赛总共耗时时间单位秒
 * @Author: leo825
 * @Date: 2019-08-21 09:28
 * @Version: 1.0
 */
public class RunnerTest {

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        /**
         * 设置10名跑步选手
         */
        int runners = 10;

        /**
         * 设置10+1个屏障，因为裁判也需要阻塞，裁判要先到终点
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(runners + 1);

        /**
         * 设置主线程准备时间
         */
        CountDownLatch countDownLatch = new CountDownLatch(1);

        //比赛准备时间
        long beginTime = System.currentTimeMillis();
        System.out.println("运动员就绪准备比赛");

        //使用随机数产生随机运行时间
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        final AtomicInteger firstRunner = new AtomicInteger(0);
        final AtomicInteger lastRunner = new AtomicInteger(0);
        //运动员进入各自的跑道
        for (int i = 0; i < runners; i++) {
            int costTime = threadLocalRandom.nextInt(9,13);
            Thread t = new Thread() {
                @Override
                public void run() {
                    System.out.println("第" + Thread.currentThread().getName() + "个运动员就绪！！！");
                    try {
                        //等待主线程完成的信号
                        countDownLatch.await();

                        if (firstRunner.get() == 0 || lastRunner.get() == 0) {
                            firstRunner.set(costTime);
                            lastRunner.set(costTime);
                        } else {
                            if (firstRunner.get() > costTime) {
                                firstRunner.set(costTime);
                            }
                            if (lastRunner.get() < costTime) {
                                lastRunner.set(costTime);
                            }
                        }
                        Thread.sleep(costTime * 1000);
                        System.out.println("第" + Thread.currentThread().getName() + "个运动员耗时：" + costTime + "s");
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }


        //主线程准备时间
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "准备时间：" + (endTime - beginTime) + "ms");

        //裁判发号施令开始比赛
        System.out.println("开始比赛...");
        countDownLatch.countDown();

        //裁判事先到终点等待,当数字到达10+1的时候开始释放锁
        cyclicBarrier.await();

        System.out.println("百米赛跑第一名成绩：" + firstRunner.get() + "秒");
        System.out.println("百米赛跑最后一名成绩：" + lastRunner.get() + "秒");
    }
}
