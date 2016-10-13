package com.tikt.goldenplatform;

/**
 * Created by tikt on 16-10-13.
 */

public class Api {

	//查修公交线路
	//Post
	//参数：{"BUS_LINE_NAME":"2","SERVICE_NAME":"BusInfoService","method":"queryBusLines"}
	public static final String BaseUrl = "http://app.nbtong.cn:28090/ubossInterface/mcallremoteservice.do";

	//查询某路车的行驶情况
	//post
	//参数：{"PUB_USER_ID":"","DEVICE_ID":"867995029570416",
	// "BUS_LINE_NAME":"509路","BUS_LINE_ID":0,"FLAG":1,"ADV_FLAG":1,
	// "SERVICE_NAME":"BusInfoService","method":"queryBusLineInfo2"}
	//FLAG:1,正向、2,反向
	//BUS_LINE_ID为0时已BUS_LINE_NAME为准，有正确的BUS_LINE_ID时，BUS_LINE_NAME可以为空
	//url是一样的是什么技术？
	//http://app.nbtong.cn:28090/ubossInterface/mcallremoteservice.do
}
