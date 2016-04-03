package com.best.util;

public interface ResultCodes {
	/**
	 * 请求成功
	 */
	public static final String CORRECT = "0";
	
	/**
	 * 发生错误
	 */
	public static final String ERROR = "-1";
	
	/**
	 * 机构ID、用户名、密码错误
	 */
	public static final String ACCOUNT_ERROR = "1";
	
	/**
	 * 用户已停用
	 */
	public static final String ACCOUNT_BLOCK = "2";
}
