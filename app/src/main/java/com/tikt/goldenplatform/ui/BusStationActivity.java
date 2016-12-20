package com.tikt.goldenplatform.ui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SearchView;

import com.tikt.goldenplatform.BusLine;
import com.tikt.goldenplatform.DESCodeUitl;
import com.tikt.goldenplatform.R;
import com.tikt.goldenplatform.base.BaseAppActivity;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 公交运行情况
 */
public class BusStationActivity extends BaseAppActivity {

	@Bind(R.id.id_busStationActivity_search)
	SearchView idBusStationActivitySearch;
	@Bind(R.id.id_busStationActivity_list)
	RecyclerView idBusStationActivityList;
	//
	String busName;//公交线路名称
	int busID;//公交ID
	int FLAG = 1;//FLAG:1,正向、2,反向

	String code = "jmH2nIwTc2uWfs2RpzmajmnahkZxCK8Bmp08QuACszWDR6VcQ3mxRlvQ6X" +
			"HYqEjmFr02V+huHyrTUzkvSAZvX8efy//lZzvMfuPKyzUACXs7eUSU1b1zvrbd" +
			"w91n4ePri1I76YaKNhy3Z/xYaSgE30o9AY6UKHg17efyWIBSW1y7lhKvAPet8I" +
			"Qcgt64K7xyWjUI3gIzeNK8j2xhOPfOAUXsDqXphTBuZf2bXW5HALNW3ChGXo0GC" +
			"djeHRTRLd8RpgoMea2jJlo7XGgVF6v70H/ul+/g9n3flqVxchZRPegwBqiQoZSL" +
			"xT9nudzM0RNYTJgqbtaUW8bhvrk+8qxOA8TWiTMZ5kUsEbi2AZyDOe26l9VwhxL0" +
			"NIfxaBctcxa04LuYHuotTqL72N2lHKBL1DZHdQnv/eyW2FFxQ2Sri1UxXFxoYHV3" +
			"+Ccq5aY9FvDZ6x/ghoRx+Rw/1jglr16P5wwyBG8JVucUIFHoAHZUgag1Xocg/5ct" +
			"ielAblG7LcS1H4L8XtZTKWudiFVErT1dnHWzZh2g9UQ/5YG2L6iIStPGueW+4sYo" +
			"sjgbuNlZwqVv3lFw32WCKCOb+dWn2Pv+sKm3mCgOY21YI2gsm46jNZVD5xhr6EUJ" +
			"JGiBXv6iT+arDmu+LOLmhdlgL8K/krEwNHaRQphMOb16CxiLEyZ6WEwWfq0eppeDX" +
			"24F81ihDO8Nfmv6Y+4qmMhk2vLgmsmeuzxc3NHcq4Fw31+7kvvI5QRc3D25i+Gnu+" +
			"d3h5sRmWDrbxntLU8zjqkbR0IPzP2nvkm3LI2Ncjhdw/GaO4jC2KvnuS+wMgItlzL" +
			"AV6SjxJ1eghYO7OSmodS834xczrUyfw==";

	Retrofit retrofit;

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.activity_bus_station;
	}

	@Override
	protected void initView() {
		retrofit = new Retrofit.Builder()
				.baseUrl("http://Weixin1.nbbus.com:8080/NingboBusWebservice/resources/")
//                .addConverterFactory(GsonConverterFactory.create())
				.addConverterFactory(ScalarsConverterFactory.create())
				.build();
	}

	@Override
	protected void initEvent() {

		busName = getIntent().getExtras().getString("BUS_NAME");
		busID = getIntent().getExtras().getInt("BUS_ID");

		Map<String, String> map = new HashMap<>();
		map.put("lineName", busName);
		onRetrofitPostWithParams(map.toString());




		//{"VEHICLE_POS":[{"TIME":1476191369956,"STRANK":22,"CAR_ID":1910,"STATION_NAME":"联丰路丽园南路口(送子鸟医院)","CUR_STATION_ID":161,"FLAG":"1","POSITION":1,"TIMEN":1476191369956},{"TIME":1476191368358,"STRANK":7,"CAR_ID":157,"STATION_NAME":"金家漕","CUR_STATION_ID":32384,"FLAG":"1","POSITION":0,"TIMEN":1476191368358},{"TIME":1476191363512,"STRANK":28,"CAR_ID":390,"STATION_NAME":"关爱小区","CUR_STATION_ID":1459,"FLAG":"1","POSITION":1,"TIMEN":1476191363512},{"TIME":1476191368433,"STRANK":16,"CAR_ID":162,"STATION_NAME":"药行街(开明街)","CUR_STATION_ID":1624,"FLAG":"1","POSITION":1,"TIMEN":1476191368433}]}

		idBusStationActivitySearch.setOnSearchClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "searchview is click");
				busName = idBusStationActivitySearch.getQuery().toString();

			}
		});
		idBusStationActivitySearch.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View view, int i, KeyEvent keyEvent) {
				Log.i(TAG, "setOnKeyListener == " + i);
				return false;
			}
		});
	}
	//{"VEHICLE_POS":[{"TIME":1476191369956,"STRANK":22,"CAR_ID":1910,"STATION_NAME":"联丰路丽园南路口(送子鸟医院)","CUR_STATION_ID":161,"FLAG":"1","POSITION":1,"TIMEN":1476191369956},{"TIME":1476191368358,"STRANK":7,"CAR_ID":157,"STATION_NAME":"金家漕","CUR_STATION_ID":32384,"FLAG":"1","POSITION":0,"TIMEN":1476191368358},{"TIME":1476191363512,"STRANK":28,"CAR_ID":390,"STATION_NAME":"关爱小区","CUR_STATION_ID":1459,"FLAG":"1","POSITION":1,"TIMEN":1476191363512},{"TIME":1476191368433,"STRANK":16,"CAR_ID":162,"STATION_NAME":"药行街(开明街)","CUR_STATION_ID":1624,"FLAG":"1","POSITION":1,"TIMEN":1476191368433}]}

	/**
	 * 带参Post请求
	 */
	private void onRetrofitPostWithParams(String bus) {
		//        bus = "{\"lineName\":\"528\"}"; 参数原型
		BusLine service = retrofit.create(BusLine.class);
		Call<String> repoCall = null;
		try {
			repoCall = service.listRepos(DESCodeUitl.getCipherString(bus));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Log.i(TAG, "initEvent: repoCall.request().url()==" + repoCall.request().url());
		repoCall.enqueue(new Callback<String>() {
			@Override
			public void onResponse(Call<String> call, Response<String> response) {

				try {
					Log.i(TAG, "onResponse: ==" + DESCodeUitl.getOriginString(response.body()));
					Log.i(TAG, "onResponse: ==" + response.body());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<String> call, Throwable t) {

				Log.i(TAG, "onResponse: ==" + call.toString());

			}
		});

	}
}
