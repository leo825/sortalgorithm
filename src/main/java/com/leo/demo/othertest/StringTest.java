package com.leo.demo.othertest;

/**
 * Created by Administrator on 2019/2/25.
 */
public class StringTest {


    /**
     * 获取字符串最后一次出现的下标
     */
    public static int getStringLastTime(String str1, String str2) {
        int lastPosition = str1.lastIndexOf(str2);
        return lastPosition; //如果返回值为-1说明没找到
    }


    /**
     * 去掉字符串中的某个字符,下标从1开始
     */
    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos - 1) + s.substring(pos, s.length());
    }

    /**
     * 将字符串倒置
     */
    public static String reverseString(String str) {
        return new StringBuffer(str).reverse().toString();
    }


    public static void main(String[] args) {
        String test = "abcdefga";

        System.out.println("原字符串===" + test);
        System.out.println("a最后一次出现的位置" + getStringLastTime(test, "a"));
        System.out.println("将字符串倒置===" + reverseString(test));

        System.out.println("当前的test===" + test);
        System.out.println("去掉第一个字符为===" + removeCharAt(test, 1));

    }
}
