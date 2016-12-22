package com.tikt.goldenplatform;

import retrofit2.http.Url;

/**
 * Created by tikt on 16-10-13.
 */

public class Api {

	/**
	 * 宁波通
	 */
	//查修公交线路
	//Post
	//参数：{"BUS_LINE_NAME":"2","SERVICE_NAME":"BusInfoService","method":"queryBusLines"}
//	public static final String BaseUrl = "http://app.nbtong.cn:28090/ubossInterface/mcallremoteservice.do";

	//查询某路车的行驶情况
	//post
	//参数：{"PUB_USER_ID":"","DEVICE_ID":"867995029570416",
	// "BUS_LINE_NAME":"509路","BUS_LINE_ID":0,"FLAG":1,"ADV_FLAG":1,
	// "SERVICE_NAME":"BusInfoService","method":"queryBusLineInfo2"}
	//FLAG:1,正向、2,反向
	//BUS_LINE_ID为0时已BUS_LINE_NAME为准，有正确的BUS_LINE_ID时，BUS_LINE_NAME可以为空
	//url是一样的是什么技术？
	//http://app.nbtong.cn:28090/ubossInterface/mcallremoteservice.do

	/**
	 * 宁波无线公交
	 * 两条baseUrl都能用，效果一样，他们为什么要两条？
	 * 我去，weixin1是给android用的，weinxin2是iPhone用的,
	 * 而且晚上，weixin1是服务器错误(后来也可以了，但不是很稳定)，但是weixin2却没问题
	 */
//	public static final String BaseUrl = "http://Weixin1.nbbus.com:8080/NingboBusWebservice/resources/";
	public static final String BaseUrl = "http://weixin2.nbbus.com:8080/NingboBusWebservice/resources/";
	//查询某辆车的行驶位置
	public static final String getBusListUrl = "getBusList";
	//查询线路
	public static final String getBusLineUrl = "getLineList";
	//查询某条公交的线路上经过的站
	public static final String getBusStationlistUrl= "getStationList";


}
