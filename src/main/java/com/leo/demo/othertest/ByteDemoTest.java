package com.leo.demo.othertest;

/**
 * @ClassName: ByteDemoTest
 * @Description: 关于位运算的测试
 * <p>
 * 移位运算符
 * 把整数的二进制位进行左移或右移
 * 按位左移 << ,右侧补0,
 * 按位右移 >> ,左侧补符号位(最高位)
 * 无符号按位右移 >>> , 左侧补0
 * @Author: leo825
 * @Date: 2019-10-09 14:36
 * @Version: 1.0
 */
public class ByteDemoTest {

    public static void main(String[] args) {
        int xx = 20;
        /**
         * xx在内存中的二进制形式为：
         * 0000 0000 0000 0000 0000 0000 0001 0100
         * 左移1位为：xx << 1
         * 0000 0000 0000 0000 0000 0000 0010 1000
         *
         */
        System.out.println(xx + "  << 之后为： " + (xx << 1));//即20*2=40

        /**
         * xx在内存中的二进制形式为：
         * 0000 0000 0000 0000 0000 0000 0001 0100
         * 右移1位为：xx >> 1
         * 0000 0000 0000 0000 0000 0000 0000 1010
         *
         */
        System.out.println(xx + "  >> 之后为： " + (xx >> 1));//即20/2=10

        xx = -20;
        /**
         * xx在内存中的二进制形式为：
         * 1111 1111 1111 1111 1111 1111 1110 1100
         * 左移1位为：xx << 1
         * 1111 1111 1111 1111 1111 1111 1101 1000
         * 负数补码转原码：补码的补码就是原码1000 0000 0000 0000 0000 0000 0010 1000
         */
        System.out.println(xx + " << 之后为： " + (xx << 1));
        /**
         * xx在内存中的二进制形式为：
         * 1111 1111 1111 1111 1111 1111 1110 1100
         * 右移1位为：xx >> 1
         * 1111 1111 1111 1111 1111 1111 1111 0110
         * 负数补码转原码：补码的补码就是原码1000 0000 0000 0000 0000 0000 0000 1010
         */
        System.out.println(xx + " >> 之后为： " + (xx >> 1));

        xx = 20;
        /**
         * xx在内存中的二进制形式为：
         * 0000 0000 0000 0000 0000 0000 0001 0100
         * 无符号移一位：xx>>>1
         * 0000 0000 0000 0000 0000 0000 0000 1010
         */
        System.out.println(xx + "  >>> 之后为： " + (xx >>> 1));

        xx = -20;
        /**
         * xx在内存中的二进制形式为：
         * 1111 1111 1111 1111 1111 1111 1110 1100
         * 无符号移一位：xx>>>1
         * 0111 1111 1111 1111 1111 1111 1111 0110
         */
        System.out.println(xx + " >>> 之后为： " + (xx >>> 1));
    }
}
