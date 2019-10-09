package com.leo.demo.jconsoletest;

import java.math.BigInteger;

/**
 * @author Administrator
 * @Date 2019/5/17 10:38
 * @TODO 使用一个test方法来测试观察JConsole的参数
 *
 * 博文：http://www.cnblogs.com/dava/p/6686436.html
 *
 * 1、通过观察发现一次次的堆内存使用量有较大幅度的变化，多次进行GC
 *
 * 2、进一步分析堆内存情况，每次经过GC之后老年区一直都在提升，说明对象在进行GC后被移动到老年区了。
 *
 * 3、通过分析发现，堆区对象在不断生成，并且不断增加，即使在进行回收的时候也无法回收掉，可以理解为，此处有与不断产生
 *    斐波那契 序列并存储出现的问题，由于后续的数据非常大，所以非必须应该放弃缓存，或在获得结果后进行转存，如数据库等。
 *    我们可以查看线程情况，通过下图可以查看main方法中的Thread.sleep();方法话费了2638，可以在程序中去掉。
 *
 */
public class JConsoleTest {
    public static void main(String[] args) {
        BigInteger[] pArr = new BigInteger[10000];
        pArr[0] = new BigInteger("0");
        pArr[1] = new BigInteger("1");
        for (int i = 2; i < 10000; i++) {
            pArr[i] = pArr[i - 1].add(pArr[i - 2]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第" + i + ":" + pArr[i]);
        }
    }
}
