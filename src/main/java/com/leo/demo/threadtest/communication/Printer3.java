package com.leo.demo.threadtest.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @Date 2019/5/17 11:36
 * @TODO 使用lock和Condition实现线程间通信
 */
public class Printer3 implements IPrinter{
    /**
     * 这里的cunt可以换成
     */
    private int count = 1;

    /**
     * 定义一个锁
     */
    private Lock lock = new ReentrantLock();

    /**
     * 锁执行条件1
     */
    private Condition condition1 = lock.newCondition();
    /**
     * 锁执行条件2
     */
    private Condition condition2 = lock.newCondition();

    /**
     * 打印数字
     */
    public void printDigit(int digit) {
        lock.lock();
        while (count % 3 == 0) {
            try {
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(digit);
        count++;
        condition2.signal();
        lock.unlock();
    }

    /**
     * 打印字母
     */
    public void printLetter(char c) {
        lock.lock();
        while (count % 3 != 0) {
            try {
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(c);
        count++;
        condition1.signal();
        lock.unlock();
    }
}
