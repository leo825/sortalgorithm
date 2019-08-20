package com.leo.demo.interfacetest;

/**
 * @author Administrator
 * @Date 2019/8/8 14:35
 * @TODO
 */
public class MainClass {


    /**
     * 测试接口实例化
     */
    public static void test1(){
        IFruit fruit = new Apple();
        String fruitName = fruit.getFruitName();
        System.out.println(fruitName);
    }

    public static void main(String[] args) {
        test1();

    }
}
