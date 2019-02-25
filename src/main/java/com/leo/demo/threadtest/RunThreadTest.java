package com.leo.demo.threadtest;


/**
 * JAVA多线程之volatile 与 synchronized 的比较
 */
public class RunThreadTest {
    public static void main(String[] args) {
        try {
//            RunThread thread = new RunThread();
//            thread.start();
//            Thread.sleep(1000);
//            thread.setRunning(false);

            RunThread2 thread2 = new RunThread2();
            thread2.start();
            Thread.sleep(1000);
            thread2.setRunning(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 现在有两个线程，一个是main线程，另一个是RunThread。它们都试图修改 第三行的 isRunning变量。按照JVM内存模型，main线程将isRunning读取到本地线程内存空间，修改后，再刷新回主内存。
     * 而在JVM 设置成 -server模式运行程序时，线程会一直在私有堆栈中读取isRunning变量。因此，RunThread线程无法读到main线程改变的isRunning变量
     * 从而出现了死循环，导致RunThread无法终止。这种情形，在《Effective JAVA》中，将之称为“活性失败”
     */
    public static class RunThread extends Thread {

        public boolean isRunning = true;

        public boolean isRunning() {
            return isRunning;
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

        @Override
        public void run() {
            System.out.println("进入到run方法中了");
            while (isRunning == true) {
            }
            System.out.println("线程执行完成了");
        }
    }

    /**
     * 解决方法，在第三行代码处用 volatile 关键字修饰即可。这里，它强制线程从主内存中取 volatile修饰的变量。
     * 扩展一下，当多个线程之间需要根据某个条件确定 哪个线程可以执行时，要确保这个条件在 线程 之间是可见的。因此，可以用volatile修饰。
     * 综上，volatile关键字的作用是：使变量在多个线程间可见（可见性）
     */
    public static class RunThread2 extends Thread {

        public volatile boolean isRunning = true;

        public boolean isRunning() {
            return isRunning;
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

        @Override
        public void run() {
            System.out.println("RunThread2进入到run方法中了");
            while (isRunning == true) {
            }
            System.out.println("RunThread2线程执行完成了");
        }
    }

}