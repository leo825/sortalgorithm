package com.leo.demo.othertest;

public class ExtendTest {

	public static void main(String[] agrs) {

		A a = new B();//实例化的时候先加载的是两个类中的静态数据;然后才执行的构造函数，静态变量只执行一次
		B b = new B();
	}

}

class A {

	static {
		System.out.println("这个是A类");
	}

	public A() {
		System.out.println("A类初始化");
	}

}

class B extends A {
	static {
		System.out.println("这个是B类");
	}

	public B() {
		System.out.println("B类初始化");
	}

}

