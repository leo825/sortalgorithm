package com.leo.demo.othertest;

import java.util.concurrent.ThreadLocalRandom;

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

    /**
     * 生成5位随机数
     *
     * @return
     */
    public static String generatePicKey() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        final int A = 'A', Z = 'Z';

        StringBuilder picKey = new StringBuilder();
        while (picKey.length() < 5) {
            int number = rand.nextInt(Z + 1);
            if (number >= A) {
                picKey.append((char) number);
            }
        }
        return picKey.toString();
    }


    /**
     * 判断字符串非空
     * @return
     */
    public static boolean isBlack1(String str){
        if(null == str || "".equals(str)){
            return true;
        }
        return false;
    }
    /**
     * 判断字符串非空
     * @return
     */
    public static boolean isBlack2(String str){
        if(null == str || str.length() == 0){
            return true;
        }
        return false;
    }

    /**
     * 拼接字符串
     * @return
     */
    public static void concatStringTest(){
        String s1 = "hello ";
        System.out.println(s1.concat("world"));
        System.out.println(s1);
    }

    public static void main(String[] args) {
        String test = "abcdefga";
        System.out.println("原字符串===" + test);
        System.out.println("a最后一次出现的位置" + getStringLastTime(test, "a"));
        System.out.println("将字符串倒置===" + reverseString(test));

        System.out.println("当前的test===" + test);
        System.out.println("去掉第一个字符为===" + removeCharAt(test, 1));

        int length = 1000;
        String testStr = "";
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < length; i++){
            isBlack1(testStr);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("isBlack1耗时：" + (endTime1 - startTime));

        for (int i = 0; i < length; i++){
            isBlack2(testStr);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("isBlack2耗时：" + (endTime2 - endTime1));
        concatStringTest();
        byte b= 127;
        byte b2 = (byte) (b+1);
        System.out.println(b);
        System.out.println(b+1);
        System.out.println(b2);

        String charge = "21.42";
        String formateCharge = charge.split("\\.")[0]+"元，" + charge.split("\\.")[1].substring(0,1) + "角，" +charge.split("\\.")[1].substring(1,2) + "分";
        System.out.println(formateCharge);
    }
}
