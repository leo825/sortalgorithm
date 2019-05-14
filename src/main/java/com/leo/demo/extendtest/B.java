package com.leo.demo.extendtest;

/**
 * @author Administrator
 * @Date 2019/5/9 9:45
 * @TODO
 */
class B extends A {
	static {
		System.out.println("这个是B类");
	}

	public B() {
		System.out.println("B类初始化");
	}

}
