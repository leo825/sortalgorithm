package com.leo.demo.othertest;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Administrator
 * @Date 2019/8/5 16:59
 * @TODO 关于随机数的测试
 */
public class RandomTest {

    /**
     * 随机数生成类
     */
    private static Random random = new Random();

    /**
     * 随机数生成类
     */
    private static ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();


    private static String[] ARRAY_1 = {"1", "2", "3", "4", "5"};

    private static String[] ARRAY_2 = {"1", "2", "3"};

    private static String[] ARRAY_3 = {"1", "2", "3", "11", "22", "33"};

    /**
     * 通过Random类获取随机数
     *
     * @param bound
     * @return
     */
    public static int getRandom1(int bound) {
        return random.nextInt(bound);
    }

    /**
     * 通过ThreadLocalRandom类获取随机数
     *
     * @param bound
     * @return
     */
    public static int getRandom2(int bound) {
        return threadLocalRandom.nextInt(bound);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int index;
            if (i % 3 == 0) {
                index = getRandom1(ARRAY_1.length);
                System.out.println(ARRAY_1[index]);
            } else if (i % 3 == 1) {
                index = getRandom1(ARRAY_2.length);
                System.out.println(ARRAY_2[index]);
            } else if (i % 3 == 2) {
                index = getRandom1(ARRAY_3.length);
                System.out.println(ARRAY_3[index]);
            }
        }
    }

}
