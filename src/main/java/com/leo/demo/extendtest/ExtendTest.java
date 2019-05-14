package com.leo.demo.extendtest;

public class ExtendTest {

	public static void main(String[] agrs) {

		A a = new B();//实例化的时候先加载的是两个类中的静态数据;然后才执行的构造函数，静态变量只执行一次
		B b = new B();
	}

}

