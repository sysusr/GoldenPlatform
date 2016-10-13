package com.tikt.goldenplatform.bean;

import java.util.List;

/**
 * Created by tikt on 16-10-13.
 * 宁波通查询公交线路
 */

public class BusTypeEntity {

	/**
	 * BUS_LINE_NAME : 2
	 * method : queryBusLines
	 * BUS_LINES : [{"BUS_LINE_ID":3153,"BUS_LINE_NAME":"2路夜"},{"BUS_LINE_ID":33000,"BUS_LINE_NAME":"2路区"},{"BUS_LINE_ID":3152,"BUS_LINE_NAME":"2路"},{"BUS_LINE_ID":3181,"BUS_LINE_NAME":"29路"},{"BUS_LINE_ID":3060,"BUS_LINE_NAME":"291路"},{"BUS_LINE_ID":3179,"BUS_LINE_NAME":"28路"},{"BUS_LINE_ID":3059,"BUS_LINE_NAME":"283路"},{"BUS_LINE_ID":3058,"BUS_LINE_NAME":"282路"},{"BUS_LINE_ID":3057,"BUS_LINE_NAME":"281路"},{"BUS_LINE_ID":3234,"BUS_LINE_NAME":"27路"},{"BUS_LINE_ID":36004,"BUS_LINE_NAME":"274路"},{"BUS_LINE_ID":36003,"BUS_LINE_NAME":"273路"},{"BUS_LINE_ID":14001,"BUS_LINE_NAME":"272路（停运）"},{"BUS_LINE_ID":62,"BUS_LINE_NAME":"271路"},{"BUS_LINE_ID":3236,"BUS_LINE_NAME":"26路"},{"BUS_LINE_ID":3242,"BUS_LINE_NAME":"25路"},{"BUS_LINE_ID":3136,"BUS_LINE_NAME":"23路"},{"BUS_LINE_ID":3249,"BUS_LINE_NAME":"238路"},{"BUS_LINE_ID":3233,"BUS_LINE_NAME":"22路"},{"BUS_LINE_ID":31000,"BUS_LINE_NAME":"2222路"},{"BUS_LINE_ID":3231,"BUS_LINE_NAME":"21路"},{"BUS_LINE_ID":3288,"BUS_LINE_NAME":"20路"},{"BUS_LINE_ID":3107,"BUS_LINE_NAME":"207路"},{"BUS_LINE_ID":3266,"BUS_LINE_NAME":"206路"},{"BUS_LINE_ID":3168,"BUS_LINE_NAME":"205路"},{"BUS_LINE_ID":3167,"BUS_LINE_NAME":"202路"},{"BUS_LINE_ID":3227,"BUS_LINE_NAME":"201路"}]
	 * IS_LOGGED_1220 : true
	 */

	private String BUS_LINE_NAME;
	private String method;
	private String IS_LOGGED_1220;
	/**
	 * BUS_LINE_ID : 3153
	 * BUS_LINE_NAME : 2路夜
	 */

	private List<BUSLINESEntity> BUS_LINES;

	public String getBUS_LINE_NAME() {
		return BUS_LINE_NAME;
	}

	public void setBUS_LINE_NAME(String BUS_LINE_NAME) {
		this.BUS_LINE_NAME = BUS_LINE_NAME;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIS_LOGGED_1220() {
		return IS_LOGGED_1220;
	}

	public void setIS_LOGGED_1220(String IS_LOGGED_1220) {
		this.IS_LOGGED_1220 = IS_LOGGED_1220;
	}

	public List<BUSLINESEntity> getBUS_LINES() {
		return BUS_LINES;
	}

	public void setBUS_LINES(List<BUSLINESEntity> BUS_LINES) {
		this.BUS_LINES = BUS_LINES;
	}

	public static class BUSLINESEntity {
		private int BUS_LINE_ID;
		private String BUS_LINE_NAME;

		public int getBUS_LINE_ID() {
			return BUS_LINE_ID;
		}

		public void setBUS_LINE_ID(int BUS_LINE_ID) {
			this.BUS_LINE_ID = BUS_LINE_ID;
		}

		public String getBUS_LINE_NAME() {
			return BUS_LINE_NAME;
		}

		public void setBUS_LINE_NAME(String BUS_LINE_NAME) {
			this.BUS_LINE_NAME = BUS_LINE_NAME;
		}
	}
}
