package com.project.CouponMVC.utils;

import java.util.Random;

public class BaseCount {

	private static Random random = new Random();
	
	private final static char[] BASE_COUNT_OF_64= { '0','1','2','3','4','5','6','7','8','9','-','_','a','b','c','d',
													'e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t',
													'u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J',
													'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	private final static char[] BASE_COUNT_OF_10= {'0','1','2','3','4','5','6','7','8','9'};

	/**
	 * @param charAmount
	 * @return a random string with <code>charAmount</code> characters, characters are from '0'-'9','a'-'z','A'-'Z', '-' and '_' .
	 */
	public static String getRandomString(int charAmount) {
		String str = "";
		for (int i = 0; i < charAmount; i++) {
			str += BASE_COUNT_OF_64[random.nextInt(BASE_COUNT_OF_64.length)];
		}
		return str;
	}
	/**
	 * @param maxCharAmount
	 * @return a random number with up to <code>maxCharAmount</code> characters, characters are from '0'-'9'.
	 */
	public static int getRandomNumber(int maxCharAmount) {
		String str = "";
		for (int i = 0; i < maxCharAmount; i++) {
			str += BASE_COUNT_OF_10[random.nextInt(BASE_COUNT_OF_10.length)];
		}
		int num = Integer.parseInt(str);
		return num;
	}
	
}
