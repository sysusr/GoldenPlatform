package com.tikt.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

/**
 * Created by tikt on 15-11-9.
 * 百度定位
 */
public class GetLocation {
	private LocationService locationService;
	// 百度定位
	private LocationClient locationClient = null;
	private LocationClientOption mOption, DIYoption;
	private Object objLock = new Object();
	private int LOCATION_COUTNS = 0;// 定位请求次数
	SharedPreferences sp;

	public GetLocation(Context locationContext) {
		sp = locationContext.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		synchronized (objLock) {
			if (locationClient == null) {
				locationClient = new LocationClient(locationContext);
				locationClient.setLocOption(getDefaultLocationClientOption());
			}
		}
	}

	public void locStart(Activity context) {
// -----------location config ------------
		locationService = new LocationService(context);
		//获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
		locationService.registerListener(mListener);
		locationService.setLocationOption(locationService.getDefaultLocationClientOption());
		locationService.start();// 定位SDK
	}


	/***
	 * @return DefaultLocationClientOption
	 */
	public LocationClientOption getDefaultLocationClientOption() {
		if (mOption == null) {
			mOption = new LocationClientOption();
			mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
			mOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
			mOption.setScanSpan(5000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
			mOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
			mOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
			mOption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
			mOption.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
			mOption.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
			mOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
			mOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
			mOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
		}
		return mOption;
	}

	public void locStop() {

		locationService.unregisterListener(mListener); //注销掉监听
		locationService.stop(); //停止定位服务
	}


	/*****
	 * copy funtion to you project
	 * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
	 */
	private BDLocationListener mListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (null != location && location.getLocType() != BDLocation.TypeServerError) {
				StringBuffer sb = new StringBuffer(256);
				sb.append("time : ");
				/**
				 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
				 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
				 */
				sb.append(location.getTime());
				sb.append("\nerror code : ");
				sb.append(location.getLocType());
				sb.append("\nlatitude : ");
				sb.append(location.getLatitude());
				sb.append("\nlontitude : ");
				sb.append(location.getLongitude());
				//
				if (null != callbackListener) {
					if (66 == location.getLocType() || 161 == location.getLocType()) {
						SharedPreferences.Editor editor = sp.edit();// 获取编辑器
						editor.putString("loc_lat", String.valueOf(location.getLatitude())); // 经纬度
						editor.putString("loc_lng", String.valueOf(location.getLongitude()));
						editor.apply();// 提交修改
						callbackListener.locationSuccess(location);
					} else {
						callbackListener.locationFaile();
					}
				}

				//
				sb.append("\nradius : ");
				sb.append(location.getRadius());
				sb.append("\nCountryCode : ");
				sb.append(location.getCountryCode());
				sb.append("\nCountry : ");
				sb.append(location.getCountry());
				sb.append("\ncitycode : ");
				sb.append(location.getCityCode());
				sb.append("\ncity : ");
				sb.append(location.getCity());
				sb.append("\nDistrict : ");
				sb.append(location.getDistrict());
				sb.append("\nStreet : ");
				sb.append(location.getStreet());
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append("\nDescribe: ");
				sb.append(location.getLocationDescribe());
				sb.append("\nDirection(not all devices have value): ");
				sb.append(location.getDirection());
				sb.append("\nPoi: ");
				if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
					for (int i = 0; i < location.getPoiList().size(); i++) {
						Poi poi = (Poi) location.getPoiList().get(i);
						sb.append(poi.getName() + ";");
					}
				}
				if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
					sb.append("\nspeed : ");
					sb.append(location.getSpeed());// 单位：km/h
					sb.append("\nsatellite : ");
					sb.append(location.getSatelliteNumber());
					sb.append("\nheight : ");
					sb.append(location.getAltitude());// 单位：米
					sb.append("\ndescribe : ");
					sb.append("gps定位成功");
				} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
					// 运营商信息
					sb.append("\noperationers : ");
					sb.append(location.getOperators());
					sb.append("\ndescribe : ");
					sb.append("网络定位成功");
				} else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
					sb.append("\ndescribe : ");
					sb.append("离线定位成功，离线定位结果也是有效的");
				} else if (location.getLocType() == BDLocation.TypeServerError) {
					sb.append("\ndescribe : ");
					sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
				} else if (location.getLocType() == BDLocation.TypeNetWorkException) {
					sb.append("\ndescribe : ");
					sb.append("网络不同导致定位失败，请检查网络是否通畅");
				} else if (location.getLocType() == BDLocation.TypeCriteriaException) {
					sb.append("\ndescribe : ");
					sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
				}
//				logMsg(sb.toString());
				Log.e("TAG", "Baidu==" + sb.toString());
			}
		}

	};

	public interface getLocationListener {
		void locationSuccess(BDLocation location);

		void locationFaile();
	}

	private getLocationListener callbackListener;

	public void getLocationCallback(getLocationListener listener) {
		callbackListener = listener;
	}

	/**
	 * 显示请求字符串
	 *
	 * @param str
	 */
	public void logMsg(String str) {
//		try {
//			if (LocationResult != null)
//				LocationResult.setText(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
