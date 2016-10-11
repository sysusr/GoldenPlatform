package com.tikt.tools;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.tikt.selectphotos.R;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by tikt on 16-1-5.
 * 验证码倒数60秒
 */
public class Count60s extends CountDownTimer {
	TextView view;
	Context context;
	String text;
	//构造函数，都是毫秒为单位
	public Count60s(TextView view, Context context, long millisInFuture, long countDownInterval, String text) {
		super(millisInFuture, countDownInterval);
		this.view = view;
		this.context = context;
		this.text = text;
		view.setEnabled(false); //验证码发送后使按钮不可点击
	}
	@Override
	public void onFinish() {
		//倒计完成
		view.setText(text);
		view.setTextColor(context.getResources().getColor(R.color.colorPrimaryMain));
		view.setEnabled(true);
	}
	@Override
	public void onTick(long millisUntilFinished) {
		view.setTextColor(context.getResources().getColor(R.color.main_dcdcdc));
		view.setText(msChangehour(millisUntilFinished));
	}

	public String msChangehour (long millis){

		SimpleDateFormat formatter = new SimpleDateFormat("ss"+"秒后重新获取");//初始化Formatter的转换格式。
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		String hms = formatter.format(millis);
		return hms;
	}
}
