package com.leo.demo.interfacetest;

/**
 * @author Administrator
 * @Date 2019/8/8 14:35
 * @TODO
 */
public class MainClass {

    public static void main(String[] args) {
        IFruit fruit = new Apple();
        String fruitName = fruit.getFruitName();
        System.out.println(fruitName);
    }
}
