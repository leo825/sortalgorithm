package com.leo.demo.threadtest;

/**
 * volatile 与 synchronized 的比较
 * volatile主要用在多个线程感知实例变量被更改了场合，从而使得各个线程获得最新的值。它强制线程每次从主内存中讲到变量，而不是从线程的私有内存中读取变量，从而保证了数据的可见性。
 * 关于synchronized，可参考：JAVA多线程之Synchronized关键字--对象锁的特点
 * 比较：
 * ①volatile轻量级，只能修饰变量。synchronized重量级，还可修饰方法
 * ②volatile只能保证数据的可见性，不能用来同步，因为多个线程并发访问volatile修饰的变量不会阻塞。
 * synchronized不仅保证可见性，而且还保证原子性，因为，只有获得了锁的线程才能进入临界区，从而保证临界区中的所有语句都全部执行。多个线程争抢synchronized锁对象时，会出现阻塞。.
 */
public class ThreadVolatileTest extends Thread {
    public static int count;

    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count=" + count);
    }

    @Override
    public void run() {
        addCount();
    }
}
