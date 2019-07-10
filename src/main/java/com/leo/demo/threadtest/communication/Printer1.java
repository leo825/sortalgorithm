package com.leo.demo.threadtest.communication;

/**
 * @author Administrator
 * @Date 2019/5/17 11:36
 * @TODO
 */
public class Printer1 implements IPrinter{
    /**
     * 这里的cunt可以换成
     */
    private int count = 1;

    /**
     * 打印数字
     */
    public synchronized void printDigit(int digit) {
        while (count % 3 == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(digit);
        count++;
        notifyAll();
    }

    /**
     * 打印字母
     */
    public synchronized void printLetter(char c) {
        while (count % 3 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(c);
        count++;
        notifyAll();
    }
}
