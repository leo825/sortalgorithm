package com.leo.demo.sort;

//常见的算法
public class NormalAlgorithm{

	/**
	*fibnoacci数，例如：1，1，2，3，5
	*
	*/
	public static int fibnoacci(int n){
		if(n == 0 || n==1)
			return 1;
		else return fibnoacci(n-1)+fibnoacci(n-2);
		
	}
	public static void main(String[] agrs){
		int n = 4;
		System.out.println(fibnoacci(n));
	
	}
}