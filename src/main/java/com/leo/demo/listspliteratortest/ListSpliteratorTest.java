package com.leo.demo.listspliteratortest;

import com.leo.demo.threadtest.threadinterrupt.MyThread;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * @ClassName: ListSpliteratorTest
 * @Description: 关于多线程分割list进行的测试
 * @Author: leo825
 * @Date: 2019-09-21 15:33
 * @Version: 1.0
 */
public class ListSpliteratorTest {

    /**
     * 模拟随机数
     */
    private static ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    /**
     * 初始化生成一个list
     *
     * @return
     */
    public static List initMylist() {
        int listSize = 1000;
        List list = new ArrayList<String>();
        for (int i = 0; i < listSize; i++) {
            if (i % 10 == 0) {
                list.add(String.valueOf(i));
            } else {
                list.add("a");
            }
        }
        return list;
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    //计算总和，原子操作用于并发
    private static AtomicInteger atomicsum = new AtomicInteger(0);
    //初始化线程池大小
    private static int threadSize = 10;
    //设置屏障值，子线程执行完再执行主线程
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(threadSize + 1);

    /**
     * 测试使用list分割器处理的处理速度
     *
     * @throws BrokenBarrierException
     * @throws InterruptedException
     */
    public static void ListSpliteratorTest1() throws BrokenBarrierException, InterruptedException {
        //开始时间
        long startTime = System.currentTimeMillis();

        //初始化一个list
        List myList = initMylist();
        //设置线程池大小
        ExecutorService executor = Executors.newFixedThreadPool(threadSize);

        Spliterator spliterator = myList.spliterator();
        for (int i = 0; i < threadSize; i++) {
            Spliterator newspliterator = null;
            //如果是0就使用本身，如果非0就分割另外一部分
            if (i == 0) {
                newspliterator = spliterator;
            } else {
                newspliterator = spliterator.trySplit();
            }
            final Spliterator finalNewspliterator = newspliterator;
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("线程" + threadName + "开始运行了。。。");
                finalNewspliterator.forEachRemaining(o -> {
                    if (isInteger((String) o)) {
                        try {
                            doSomeBusiness();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int num = Integer.parseInt((String) o);
                        atomicsum.getAndAdd(num);
                        System.out.println("数值：" + atomicsum.get() + "------" + threadName);
                    }
                });
                System.out.println("线程" + threadName + "运行结束---");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        cyclicBarrier.await();
        executor.shutdown();
        System.out.println("结果为：" + atomicsum.get());
        long endTime = System.currentTimeMillis();
        System.out.println("使用list分割器耗时：" + (endTime - startTime) + "ms");
    }

    /**
     * 测试不使用list分割器消耗时间
     */
    public static void ListSpliteratorTest2() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        //初始化一个list
        List myList = initMylist();
        int sum = 0;
        for (Object o : myList) {
            if (isInteger((String) o)) {
                doSomeBusiness();
                int num = Integer.parseInt((String) o);
                sum = sum + num;
                System.out.println("计算求得：" + sum);
            }
        }
        System.out.println("结果为：" + sum);
        long endTime = System.currentTimeMillis();
        System.out.println("使用普通for循环耗时：" + (endTime - startTime) + "ms");
    }

    /**
     * 模拟业务代码消耗时间
     */
    public static void doSomeBusiness() throws InterruptedException {
        int costTime = threadLocalRandom.nextInt(100);
        Thread.sleep(costTime);
    }

    /**
     * 执行业务时间使用了一个随机数去处理
     *
     * @param args
     * @throws BrokenBarrierException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        ListSpliteratorTest1();
        ListSpliteratorTest2();
    }
}
