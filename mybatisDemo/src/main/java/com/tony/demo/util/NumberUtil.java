package com.tony.demo.util;

import java.util.Random;

public class NumberUtil {
	public static int getRandomNumber(int min,int max) {
		Random r=new Random();
		return r.nextInt(max)%(max-min+1) + min;
	}
}
