package com.leo.demo.iotest.nettytest.nettycallback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName: FutureTest
 * @Description: ${description}
 * @Author: leo825
 * @Date: 2020-01-16 17:17
 * @Version: 1.0
 */
public class FutureTest {
    public static void main(String[] args) {
        FutureTest futureTest = new FutureTest();

        Worker worker = object -> {
            System.out.println(Thread.currentThread().getName() + " 这是个耗时业务");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return object + " world";
        };

        Wrapper wrapper = new Wrapper();
        wrapper.setParam("你好");
        wrapper.setWorker(worker);
        wrapper.addListener(result -> {
            System.out.println(Thread.currentThread().getName() + " 得到回调结果：" + result);
        });

        //定义异步执行
        CompletableFuture future = CompletableFuture.supplyAsync(() -> futureTest.doWork(wrapper));

        //异步执行，然后futrue获取结果
        try {
            future.get(800, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            //超时了
            wrapper.getListener().result("time out exception");
        }
    }

    /**
     * 构建装饰器
     *
     * @param wrapper
     * @return
     */
    private Wrapper doWork(Wrapper wrapper)  {
        String result = null;
        result = wrapper.getWorker().action(wrapper.getParam());
        System.out.println(Thread.currentThread().getName() + " 回调结果：" + result);
        wrapper.getListener().result(result);
        return wrapper;
    }
}
