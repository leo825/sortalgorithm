package com.leo.demo.interfacetest;

/**
 * @author Administrator
 * @Date 2019/8/8 14:31
 * @TODO
 */
public class Apple implements IFruit<Apple> {

    @Override
    public String getFruitName(){
        return "我是苹果";
    }
}
