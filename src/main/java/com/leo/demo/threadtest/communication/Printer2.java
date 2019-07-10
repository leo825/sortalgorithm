package com.leo.demo.threadtest.communication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @Date 2019/5/17 11:36
 * @TODO
 *
 * 为什么wait()和notify()必须要使用sychronized?
 * 如果不用就会报错IllegalMonitorStateException.
 */
public class Printer2 implements IPrinter{
    /**
     * 这里的cunt可以换成
     */
    private AtomicInteger count = new AtomicInteger(1);

    /**
     * 打印数字
     */
    public synchronized void printDigit(int digit){
        while (count.get() % 3 == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(digit);
        count.incrementAndGet();
        notifyAll();
    }

    /**
     * 打印字母
     */
    public synchronized void printLetter(char c){
        while (count.get()  % 3 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(c);
        count.incrementAndGet();
        notifyAll();
    }
}
