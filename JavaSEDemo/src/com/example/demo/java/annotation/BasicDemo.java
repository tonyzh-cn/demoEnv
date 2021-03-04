package com.example.demo.java.annotation;

public class BasicDemo {
	private static String value;

	@ParamValidators({"id,age#"+ParamValidate.INT,"name#"+ParamValidate.STRING})
	public void f(){

	}

	public static void main(String[] args) {
		System.out.println(value);
	}
}
