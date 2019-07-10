package com.leo.demo.jconsoletest;

import java.math.BigInteger;

/**
 * @author Administrator
 * @Date 2019/5/17 11:03
 * @TODO 舍弃存储之后结果
 */
public class JConsoleTest2 {
    public static void main(String[] args) {
        BigInteger temp1=new BigInteger("0");
        BigInteger temp2=new BigInteger("1");
        BigInteger temp3=new BigInteger("0");
        for (int i = 2; i < 10000; i++) {
            temp3= temp2;
            temp2=temp1.add(temp2);
            temp1=temp3;
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("第"+i+":"+temp2);
        }
    }
}
