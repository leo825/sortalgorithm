package com.leo.demo.threadtest.communication;

/**
 * @author Administrator
 * @Date 2019/5/17 11:29
 * @TODO 测试线程通信
 * 题目：一个线程打印1~52，另一个线程打印字符A~Z,打印顺序为12A34B56C78D......5152Z，要求线程间通信
 */
public class ThreadCommunicationTest {

    /**
     * 第一种实现方式,经测试耗时较长
     */
    public static void Printer1Test(){
        long stat = System.currentTimeMillis();
        final Printer1 printer = new Printer1();
        threadImpl(printer);
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("Printer1Test耗时：" + (end-stat));
        System.out.println();
    }

    /**
     * 第二种方式实现
     */
    public static void Printer2Test(){
        long stat = System.currentTimeMillis();
        final Printer2 printer = new Printer2();
        threadImpl(printer);
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("Printer2Test耗时：" + (end - stat));
        System.out.println();
    }

    /**
     * 第三种种方式实现
     */
    public static void Printer3Test(){
        long stat = System.currentTimeMillis();
        final Printer3 printer = new Printer3();
        threadImpl(printer);
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("Printer3Test耗时：" + (end - stat));
        System.out.println();
    }


    private static void threadImpl(IPrinter printer){
        //实现一个打印数字的线程
        new Thread(()-> {
            for (int i = 1; i <= 52; i++) {
                printer.printDigit(i);
            }
        }).start();

        //实现一个打印字母的线程
        new Thread(()->{
            for (char c = 'A'; c <= 'Z'; c++) {
                printer.printLetter(c);
            }
        }).start();
    }

    public static void main(String[] args) {
        Printer1Test();
        Printer2Test();
        Printer3Test();
    }
}
