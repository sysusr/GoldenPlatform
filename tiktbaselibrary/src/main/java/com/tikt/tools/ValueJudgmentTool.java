package com.tikt.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tikt on 16-1-18.
 * 判断数据是否符合要求
 */
public class ValueJudgmentTool {

	/**
	 * 判断手机号
	 *
	 * @param phone
	 * @return 移动号段正则表达式
	 * <p>
	 * NSString *CM_NUM = @"^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
	 * /**
	 * 联通号段正则表达式
	 * <p>
	 * NSString *CU_NUM = @"^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
	 * <p>
	 * 电信号段正则表达式
	 * <p>
	 * NSString *CT_NUM = @"^((133)|(153)|(177)|(18[0,1,9]))\\d{8}$";
	 */

	public static boolean isPhone(String phone) {
		Pattern pattern = Pattern.compile("^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8])|(13[0-2])|(145)|(15[5-6])|(176)|(18[5,6])|(133)|(153)|(177)|(18[0,1,9]))\\d{8}|((1705)|(1709))\\d{7}$");
		Matcher matcher = pattern.matcher(phone);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 判断身份证
	 */
	private static boolean isUserID(String id) {
		Pattern pattern = Pattern.compile("\\d{17}[0-9X]|\\d{14}[0-9a-zA-Z]");
		Matcher matcher = pattern.matcher(id);
		//&&(id.length()==15||id.length()==18)
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}

	}
}
