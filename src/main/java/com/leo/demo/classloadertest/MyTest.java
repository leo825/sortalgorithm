package com.leo.demo.classloadertest;

/**
 * @author Administrator
 * @Date 2019/5/8 16:41
 * @TODO 这是一个测试类，目的是为ClassLoasderTest方法测试用
 */
public class MyTest {


    public MyTest(){
        System.out.println("这是MyTest的空构造方法");
    }

    public static void hello(){
        System.out.println("hello world222");
    }

    public static void main(String[] args) {
        hello();
    }
}
