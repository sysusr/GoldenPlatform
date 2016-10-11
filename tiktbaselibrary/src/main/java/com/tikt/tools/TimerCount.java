package com.tikt.tools;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by tikt on 15-12-29.
 * 在TextView上显示倒计时
 */

public class TimerCount extends CountDownTimer {
	TextView view;
//	Context context;
	String text = "";
	public TimeOver mTimeOver;

	//构造函数，都是毫秒为单位
	public TimerCount(TextView view, Context context, long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		this.view = view;
//		this.context = context;
	}
	public TimerCount(TextView view, long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		this.view = view;

	}

	@Override
	public void onFinish() {
		//倒计完成
//			Toast.makeText(context, "本场完成", Toast.LENGTH_SHORT).show();
		mTimeOver.overTime();
		view.setText(text);
	}

	@Override
	public void onTick(long millisUntilFinished) {
		view.setText(msChangehour(millisUntilFinished));
	}

	public interface TimeOver{
		void overTime();
	}
	public void TimeIsOver(TimeOver mTimeOver) {
		this.mTimeOver = mTimeOver;
	}
	public String msChangehour(long millis) {

//		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");//初始化Formatter的转换格式。
		SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");//初始化Formatter的转换格式。
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		String hms = formatter.format(millis);
		return hms;
	}

	/**
	 * 获取当前系统的时间戳
	 */
	private long getTimeStamp() {

		Long tsLong = System.currentTimeMillis() / 1000;
		String ts = tsLong.toString();
		Log.i("TAG", "时间戳===" + ts);
		return tsLong;
	}

	/**
	 * 获取系统当前日期时间
	 * @return
	 */
	private String getDate() {
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
			startTime = df.parse(starttime).getTime() / 1000; //将微妙变为秒
			Log.e("TAG", "startTime===" + startTime);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return startTime;

	}

	public static long getDate2TimeStamp(String starttime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long startTime = 0;
		try {
			startTime = df.parse(starttime).getTime() / 1000; //将微妙变为秒
			Log.e("TAG", "startTime===" + startTime);
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
	public String getTimeStamp2Date(String timestampString, String formats) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat(formats).format(new Date(timestamp));
		return date;
	}

	public String getTimeStamp2Date(String timestampString) {
		String formats = "yyyy-MM-dd HH:mm:ss";
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat(formats).format(new Date(timestamp));
		return date;
	}
}
