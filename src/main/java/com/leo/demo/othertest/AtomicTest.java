package com.leo.demo.othertest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @Date 2019/4/8 9:02
 * @TODO 测试原子类的操作
 */
public class AtomicTest {


    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        Count count = new Count();
        for(int i=0;i<1000;i++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    count.increase();
                }
            });
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(count.getCount());
    }


    static class Count {
        //共享变量
        private AtomicInteger count = new AtomicInteger(0);

        public AtomicInteger getCount() {
            return count;
        }

        public void increase() {
            count.addAndGet(1);
        }
    }
}
