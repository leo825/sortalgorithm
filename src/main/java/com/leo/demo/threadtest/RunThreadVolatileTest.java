package com.leo.demo.threadtest;

/**
 * Run.java 第20行 for循环中创建了100个线程，第25行将这100个线程启动去执行 addCount()，每个线程执行100次加1
 * 期望的正确的结果应该是 100*100=10000，但是，实际上count并没有达到10000
 * 原因是：volatile修饰的变量并不保证对它的操作（自增）具有原子性。（对于自增操作，可以使用JAVA的原子类AutoicInteger类保证原子自增）
 * 比如，假设 i 自增到 5，线程A从主内存中读取i，值为5，将它存储到自己的线程空间中，执行加1操作，值为6。此时，CPU切换到线程B执行，从主从内存中读取变量i的值。由于线程A还没有来得及将加1后的结果写回到主内存，线程B就已经从主内存中读取了i，因此，线程B读到的变量 i 值还是5
 * 相当于线程B读取的是已经过时的数据了，从而导致线程不安全性。这种情形在《Effective JAVA》中称之为“安全性失败”
 * 综上，仅靠volatile不能保证线程的安全性。（原子性）
 */
public class RunThreadVolatileTest {


    public static void main(String[] args) {
        ThreadVolatileTest[] mythreadArray = new ThreadVolatileTest[100];
        for (int i = 0; i < 100; i++) {
            mythreadArray[i] = new ThreadVolatileTest(0);
        }

        for (int i = 0; i < 100; i++) {
            mythreadArray[i].start();
        }
    }


    /**
     * volatile 与 synchronized 的比较
     * volatile主要用在多个线程感知实例变量被更改了场合，从而使得各个线程获得最新的值。它强制线程每次从主内存中讲到变量，而不是从线程的私有内存中读取变量，从而保证了数据的可见性。
     * 关于synchronized，可参考：JAVA多线程之Synchronized关键字--对象锁的特点
     * 比较：
     * ①volatile轻量级，只能修饰变量。synchronized重量级，还可修饰方法
     * ②volatile只能保证数据的可见性，不能用来同步，因为多个线程并发访问volatile修饰的变量不会阻塞。
     * synchronized不仅保证可见性，而且还保证原子性，因为，只有获得了锁的线程才能进入临界区，从而保证临界区中的所有语句都全部执行。多个线程争抢synchronized锁对象时，会出现阻塞。.
     */
    static class ThreadVolatileTest extends Thread {
        private static int count;

        ThreadVolatileTest(int count) {
            this.count = count;
        }

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
}




