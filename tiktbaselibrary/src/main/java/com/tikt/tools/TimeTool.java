package com.tikt.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tikt on 16-1-19.
 * 时间计算工具
 */
public class TimeTool {
	/**
	 * 获取当前系统的时间戳
	 */
	public static long getTimeStamp() {

		Long tsLong = System.currentTimeMillis() / 1000;
//		String ts = tsLong.toString();
//		Log.i("TAG", "时间戳===" + ts);
		return tsLong;
	}

	/**
	 * 获取系统当前日期时间
	 *
	 * @return
	 */
	public static String getDate() {
		String time;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		time = df.format(new Date());// new Date()为获取当前系统时间
		return time;
	}

	/**
	 * 日期格式转时间戳
	 */
	public static long getDate2TimeStamp(String starttime, String formats) {
		SimpleDateFormat df = new SimpleDateFormat(formats);
		long startTime = 0;
		try {
			startTime = df.parse(starttime).getTime() / 1000; //将微秒变为秒
//			Log.e("TAG", "startTime===" + startTime);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return startTime;

	}

	public static long getDate2TimeStamp(String starttime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long startTime = 0;
		try {
			startTime = df.parse(starttime).getTime() / 1000; //将微秒变为秒
//			Log.e("TAG", "startTime===" + startTime);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return startTime;

	}

	/**
	 * 时间戳转日期
	 *
	 * @param timestampString
	 * @param formats
	 * @return
	 */
	//自定义日期形式
	public static String getTimeStamp2Date(String timestampString, String formats) {
		long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat(formats).format(new Date(timestamp));
		return date;
	}

	public static String getTimeStamp2Date(long timestampLong, String formats) {
		long timestamp = timestampLong * 1000;
		String date = new SimpleDateFormat(formats).format(new Date(timestamp));
		return date;
	}

	public static String getTimeStamp2Date(int timestampLong, String formats) {
		long timestamp = Long.valueOf(timestampLong) * 1000;
		String date = new SimpleDateFormat(formats).format(new Date(timestamp));
		return date;
	}

	//
	public static String getTimeStamp2Date(String timestampString) {
		String formats = "yyyy-MM-dd HH:mm:ss";
		long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat(formats).format(new Date(timestamp));
		return date;
	}

	public static String getTimeStamp2Date(long timestampLong) {
		String formats = "yyyy-MM-dd HH:mm:ss";
		long timestamp = timestampLong * 1000;
		String date = new SimpleDateFormat(formats).format(new Date(timestamp));
		return date;
	}

	public static String getTimeStamp2Date(int timestampLong) {
		String formats = "yyyy-MM-dd HH:mm:ss";
		long timestamp = Long.valueOf(timestampLong) * 1000;
		String date = new SimpleDateFormat(formats).format(new Date(timestamp));
		return date;
	}

	/**
	 * 可加减天数
	 *
	 * @param timestampLong
	 * @param day
	 * @return
	 */
	public static String getTimeStamp2Date(long timestampLong, int day, String formats) {
		long timestamp = timestampLong * 1000;
		SimpleDateFormat f = new SimpleDateFormat(formats);
		Date b = new Date(timestamp);
		Calendar c = Calendar.getInstance();
		c.setTime(b);
		c.add(Calendar.DATE, day);//加一天,到下个月的1号
		return f.format(c.getTime());
	}

	/**
	 * 计算得到几个月之后的日期
	 */
	public static String getDateAfterMonth(long timestampLong, int month) {
		long timestamp = timestampLong * 1000;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date b = new Date(timestamp);
		Calendar c = Calendar.getInstance();
		c.setTime(b);
		c.add(Calendar.MONTH, month);

		return f.format(c.getTime());
	}

	public static String getDateAfterMonth(int timestampLong, int month) {
		long timestamp = Long.valueOf(timestampLong) * 1000;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date b = new Date(timestamp);
		Calendar c = Calendar.getInstance();
		c.setTime(b);
		c.add(Calendar.MONTH, month);

		return f.format(c.getTime());
	}

	public static String getDateAfterMonth(String timestampLong, int month) {
		long timestamp = Long.valueOf(timestampLong) * 1000;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date b = new Date(timestamp);
		Calendar c = Calendar.getInstance();
		c.setTime(b);
		c.add(Calendar.MONTH, month);

		return f.format(c.getTime());
	}

	public static String getDateBeforeMonth(long timestampLong, int month, String formats) {
		long timestamp = timestampLong * 1000;
		SimpleDateFormat f = new SimpleDateFormat(formats);
		Date b = new Date(timestamp);
		Calendar c = Calendar.getInstance();
		c.setTime(b);
		c.add(Calendar.MONTH, month);
		return f.format(c.getTime());
	}

	public static String getDateBeforeMonth(long timestampLong, int month, int day, String formats) {
		long timestamp = timestampLong * 1000;
		SimpleDateFormat f = new SimpleDateFormat(formats);
		Date b = new Date(timestamp);
		Calendar c = Calendar.getInstance();
		c.setTime(b);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DATE, day);//加一天,到下个月的1号
		return f.format(c.getTime());
	}

	public static String getDateAfterMonth(long timestampLong, int month, String formats) {
		long timestamp = timestampLong * 1000;
		SimpleDateFormat f = new SimpleDateFormat(formats);
		Date b = new Date(timestamp);
		Calendar c = Calendar.getInstance();
		c.setTime(b);
		c.add(Calendar.MONTH, month);
		return f.format(c.getTime());
	}

	public static String getDateAfterMonth(long timestampLong, int month, int day, String formats) {
		long timestamp = timestampLong * 1000;
		SimpleDateFormat f = new SimpleDateFormat(formats);
		Date b = new Date(timestamp);
		Calendar c = Calendar.getInstance();
		c.setTime(b);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DATE, day);//减一天,到该月月底
		return f.format(c.getTime());
	}

	public static String getDateAfterMonth(int timestampLong, int month, String formats) {
		long timestamp = Long.valueOf(timestampLong) * 1000;
		SimpleDateFormat f = new SimpleDateFormat(formats);
		Date b = new Date(timestamp);
		Calendar c = Calendar.getInstance();
		c.setTime(b);
		c.add(Calendar.MONTH, month);

		return f.format(c.getTime());
	}

	public static String getDateAfterMonth(String timestampLong, int month, String formats) {
		long timestamp = Long.valueOf(timestampLong) * 1000;
		SimpleDateFormat f = new SimpleDateFormat(formats);
		Date b = new Date(timestamp);
		Calendar c = Calendar.getInstance();
		c.setTime(b);
		c.add(Calendar.MONTH, month);

		return f.format(c.getTime());
	}


	/**
	 * 计算两个时间相差的月份
	 *
	 * @param beginTime
	 * @param EndTime
	 * @return
	 */
	public static int MonthOfTwoTime(long beginTime, long EndTime) {
		int result = 0;
		long timestamp1 = beginTime * 1000;
		long timestamp2 = EndTime * 1000;
		Date b1 = new Date(timestamp1);
		Date b2 = new Date(timestamp2);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(b1);
		c2.setTime(b2);

		if(c2.get(Calendar.YEAR)>c1.get(Calendar.YEAR)){
			result = (c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR))*12+(c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
		}else{
			result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		}


		return result;
	}

}
