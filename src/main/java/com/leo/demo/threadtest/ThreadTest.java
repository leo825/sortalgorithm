package com.leo.demo.threadtest;

public class ThreadTest implements Runnable {

    private String threadName;

    public ThreadTest(String name) {
        this.threadName = name;
    }

    public void run() {
        System.out.println(threadName + " from ThreadTest");

    }

    public static void main(String[] agrs) {
        ThreadPOJO thread1 = new ThreadPOJO("thread1");
        ThreadTest threadTest = new ThreadTest("thread2");
        Thread thread2 = new Thread(threadTest);
        thread1.start();
        thread2.start();
        System.out.println("main");
    }
}

class ThreadPOJO extends Thread {

    private String threadName;

    public ThreadPOJO(String name) {
        this.threadName = name;
    }

    public void run() {
        System.out.println(threadName + " from ThreadPOJO");
    }
}