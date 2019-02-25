package com.leo.demo.othertest;

import java.math.BigDecimal;

/**
 * @author Leo
 * @version 创建时间:2018-1-9下午1:24:12
 * @parameter E-mail:15895982509@163.com
 */
public class DoubleTest {

    /**
     * 传统比较两个double数据，因为0.1不能被计算机2进制准确表示，会产生误差
     *
     * @param a
     * @param b
     */
    public static void equal(double a, double b) {
        if (a == b) {
            System.out.println(a + " 等于 " + b);
        } else {
            System.out.println(a + " 不等于 " + b);
        }
    }

    /**
     * 建议使用BigDecimal比较两个数大小
     *
     * @param val1
     * @param val2
     * @return
     */
    public static String compare(BigDecimal val1, BigDecimal val2) {
        String result = "";
        if (val1.compareTo(val2) < 0) {
            result = "第二位数大！";
        }
        if (val1.compareTo(val2) == 0) {
            result = "两位数一样大！";
        }
        if (val1.compareTo(val2) > 0) {
            result = "第一位数大！";
        }
        System.out.println(result);
        return result;
    }


    /**
     * 取绝对值，当相差0.000001即认为两个double值相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isEqual(double a, double b) {
        return Math.abs(a - b) < 0.000001;
    }


    public static void main(String[] agrs) {
        double a = 0.2;
        double b = 0.4;
        double c = 0.6;
        equal(a + b, c);
        System.out.println(a);
        System.out.println(b);
        System.out.println(a + b);
        compare(new BigDecimal(a + b), new BigDecimal(c));
        if (isEqual(a + b, c)) {
            System.out.println((a + b) + "===" + c);
        }

    }
}