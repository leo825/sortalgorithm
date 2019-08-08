package com.leo.demo.interfacetest;

/**
 * @author Administrator
 * @Date 2019/8/8 14:34
 * @TODO
 */
public class Orange implements IFruit<Orange> {
    @Override
    public String getFruitName(){
        return "我是橘子";
    }
}
