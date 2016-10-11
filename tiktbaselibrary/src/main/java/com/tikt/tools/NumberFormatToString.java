package com.tikt.tools;

/**
 * Created by tikt on 16-7-1.
 * 将数字按保留位数返回成文字
 */
public class NumberFormatToString {

	/**
	 * 保留2位小数
	 * @param f
	 * @return
	 */
	public static String NumberFormat2(float f) {
		return String.format("%.2f", f);
	}

	public static String NumberFormat2(double f) {
		return String.format("%.2f", f);
	}
}
