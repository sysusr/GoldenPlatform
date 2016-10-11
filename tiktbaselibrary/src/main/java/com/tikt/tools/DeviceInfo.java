package com.tikt.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import java.util.Random;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by tikt on 15-11-10.
 * 设备id
 */
public class DeviceInfo {

	public static String DeviceId(Context context) {
		//google推荐的唯一标示
//		Installation installation = new Installation();
//		String id = installation.id(context);
		String id = JPushInterface.getRegistrationID(context); //使用极光推送的ID
//		Log.e("Tag", "devid==" + id);
		return id;
	}

	/**
	 * 获取软件的versionName
	 *
	 * @param context
	 * @return
	 */
	public static String getAppVersionName(Context context) {
		PackageManager manager = context.getPackageManager();
		PackageInfo info = null;
		String versionName = "1.0";
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
			versionName = info.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 获取软件的versionCode
	 *
	 * @param context
	 * @return
	 */
	public static int getAppVersionCode(Context context) {
		PackageManager manager = context.getPackageManager();
		PackageInfo info = null;
		int versionCode = 1;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
			versionCode = info.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}


	/**
	 * 设备系统sdk版本
	 */
	public static int DeviceSdk() {

		int sdk = Build.VERSION.SDK_INT;
		return sdk;
	}

	/**
	 * 生成随机字符串
	 *
	 * @param length
	 * @return
	 */
	public String RandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < length; i++) {
			int num = random.nextInt(62);
			buf.append(str.charAt(num));
		}

		return buf.toString();
	}

	/**
	 * 设备型号
	 */
	public static String DeviceMod() {

		String modle = Build.MODEL;
		return modle;
	}

	/**
	 * 设备系统版本
	 */
	public static String DeviceVer() {

		String version = Build.VERSION.RELEASE;
		return version;
	}

	/**
	 * 设备网络状态
	 */
	public static String DeviceNet(Context context) {

		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null) {
			int netType = networkInfo.getType();
//			Log.e("Tag", "netType==" + netType);
			String netTypeName = networkInfo.getTypeName();
			return netTypeName;

		} else {
			return "无网络";
		}


	}

	/**
	 * 判断是否联网
	 */
	public static boolean isNetWork(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null) {

			return true;

		} else {
			return false;
		}
	}

	/**
	 * 获取屏幕宽度（像素）
	 *
	 * @return
	 */
	public static int getWidthPixels() {
//		DisplayMetrics metric = new DisplayMetrics();
		float widthPixels = Resources.getSystem().getDisplayMetrics().widthPixels;
//		getWindowManager().getDefaultDisplay().getMetrics(metric);
//		return metric.widthPixels;  // 屏幕宽度（像素）
		return (int)widthPixels;  // 屏幕宽度（像素）

	}

	/**
	 * 获取屏幕高度（像素）
	 *
	 * @return
	 */
	public static int getHeightPixels() {
//		DisplayMetrics metric = new DisplayMetrics();
//		context.getWindowManager().getDefaultDisplay().getMetrics(metric);
//		return metric.heightPixels;  // 屏幕高度（像素）
		float heightPixels = Resources.getSystem().getDisplayMetrics().heightPixels;
		return (int)heightPixels;  // 屏幕宽度（像素）
	}


	/**
	 * 完整的信息，用于请求头
	 */
	public static String onAllMes(Context context) {

//		String id = JPushInterface.getRegistrationID(context);
//		Log.e("Tag", "devid==" + id);
		String MesInfo;
		//
		String version = Build.VERSION.RELEASE;

		//
		String modle = Build.MODEL;
		//
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		String netinfo;
		if (networkInfo != null) {
			int netType = networkInfo.getType();
//			Log.e("Tag", "netType==" + netType);
			String netTypeName = networkInfo.getTypeName();
			netinfo = netTypeName;

		} else {
			netinfo = "无网络";
		}
		MesInfo = "android" + "|" + version + "|" + modle + "|" + netinfo + "|" + getHeightPixels() +"x"+ getWidthPixels();
		return MesInfo;
	}
}
