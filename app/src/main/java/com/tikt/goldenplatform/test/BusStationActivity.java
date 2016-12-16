package com.tikt.goldenplatform.test;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SearchView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.tikt.goldenplatform.Api;
import com.tikt.goldenplatform.R;
import com.tikt.goldenplatform.base.BaseAppActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.Bind;

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


	@Override
	protected int getContentViewLayoutID() {
		return R.layout.activity_bus_station;
	}

	@Override
	protected void initView() {
	}

	@Override
	protected void initEvent() {

		busName = getIntent().getExtras().getString("BUS_NAME");
		busID = getIntent().getExtras().getInt("BUS_ID");


//		searchBusStation();

		searchBus();
		//{"VEHICLE_POS":[{"TIME":1476191369956,"STRANK":22,"CAR_ID":1910,"STATION_NAME":"联丰路丽园南路口(送子鸟医院)","CUR_STATION_ID":161,"FLAG":"1","POSITION":1,"TIMEN":1476191369956},{"TIME":1476191368358,"STRANK":7,"CAR_ID":157,"STATION_NAME":"金家漕","CUR_STATION_ID":32384,"FLAG":"1","POSITION":0,"TIMEN":1476191368358},{"TIME":1476191363512,"STRANK":28,"CAR_ID":390,"STATION_NAME":"关爱小区","CUR_STATION_ID":1459,"FLAG":"1","POSITION":1,"TIMEN":1476191363512},{"TIME":1476191368433,"STRANK":16,"CAR_ID":162,"STATION_NAME":"药行街(开明街)","CUR_STATION_ID":1624,"FLAG":"1","POSITION":1,"TIMEN":1476191368433}]}

		idBusStationActivitySearch.setOnSearchClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "searchview is click");
				busName = idBusStationActivitySearch.getQuery().toString();
				searchBusStation();
			}
		});
		idBusStationActivitySearch.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View view, int i, KeyEvent keyEvent) {
				Log.i(TAG, "setOnKeyListener == "+i);
				return false;
			}
		});
	}

	private void searchBusStation() {
		RequestParams params = new RequestParams();
		params.put("PUB_USER_ID", "");
		params.put("DEVICE_ID", "867995029570416");
		params.put("BUS_LINE_NAME", busName);
		params.put("BUS_LINE_ID", busID);
		params.put("FLAG", FLAG);
		params.put("ADV_FLAG", 1);
		params.put("SERVICE_NAME", "BusInfoService");
		params.put("method", "queryBusLineInfo2");
		startPostClientWithHeaderParams(Api.BaseUrl, params);
		//
//        getBusLine("{\"lineName\":\"528\"}");
	}

//        startPostClientWithHeaderParams(Api.BaseUrl,params);

        //{"VEHICLE_POS":[{"TIME":1476191369956,"STRANK":22,"CAR_ID":1910,"STATION_NAME":"联丰路丽园南路口(送子鸟医院)","CUR_STATION_ID":161,"FLAG":"1","POSITION":1,"TIMEN":1476191369956},{"TIME":1476191368358,"STRANK":7,"CAR_ID":157,"STATION_NAME":"金家漕","CUR_STATION_ID":32384,"FLAG":"1","POSITION":0,"TIMEN":1476191368358},{"TIME":1476191363512,"STRANK":28,"CAR_ID":390,"STATION_NAME":"关爱小区","CUR_STATION_ID":1459,"FLAG":"1","POSITION":1,"TIMEN":1476191363512},{"TIME":1476191368433,"STRANK":16,"CAR_ID":162,"STATION_NAME":"药行街(开明街)","CUR_STATION_ID":1624,"FLAG":"1","POSITION":1,"TIMEN":1476191368433}]}

private void searchBus(){
	RequestParams params = new RequestParams();
	try {
		params.put("",getCipherString("{\"lineName\":\"528\"}"));
		params.setContentEncoding("utf-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	startPostClientWithHeaderParams(Api.getBusLineUrl,params);
}


    @Override
    protected void onClientSuccess(String url, JSONObject json) {

        switch (url){
            case Api.BaseUrl:
                Gson gson = new Gson();
//                BusRealTimeEntity station = gson.fromJson(json.toString(), BusRealTimeEntity.class);
//                station.getCRYPTOGRAPH();
                try {
//                    Log.i(TAG, "onClientSuccess: =="+new String(DESCodeUitl.decode(Base64.decode(station.getCRYPTOGRAPH().getBytes("utf-8"), 0), "nbt_ztesoft_!@#$%^&=="), "utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Api.getBusLineUrl:
                try {
                    Log.i(TAG, "onClientSuccess: =="+getOriginString(json.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }


    private void getBusLine(String str){
        RequestParams params = new RequestParams();
        try {
            Log.i(TAG, "getBusLine: =="+getCipherString(str));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
            String str1 = "￼$ￆￔ￦ﾖ￢ﾔￖﾖ$l$\u001E\f\\ￅ?w$\uFFDE";
            String str2 = "123123123123";
        params.put("",str2);
//        try {
//            params.put("",getCipherString(str));
//            params.put("",str1);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        Log.i(TAG, "params: =="+params.toString());
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(Api.getBusLineUrl);
        post.addHeader("charset", "UTF-8");
        post.addHeader("Cookie", "");
        client.getParams().setParameter("http.connection.timeout", Integer.valueOf(100000));
        client.getParams().setParameter("http.socket.timeout", Integer.valueOf(100000));

        try {
            post.setEntity(new StringEntity(str1, "UTF-8"));
            HttpResponse response = client.execute(post);
            Log.i(TAG, "response\uff1a " + response);
            Log.i(TAG, "\u8fd4\u56de\u7684StatusCode\uff1a " + response.getStatusLine().getStatusCode());
            String reuslt = EntityUtils.toString(response.getEntity(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 加密
     * @param source
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getCipherString(String source) throws UnsupportedEncodingException {
        if (source.trim().equals("")) {
            return "";
        }
        byte[] sb = new String(source.getBytes("UTF-8"), "UTF-8").getBytes("UTF-8");
        byte[] sbNew = new byte[sb.length];
        StringBuilder sbb = new StringBuilder();
        for (int i = 0; i < sb.length; i++) {
            byte t = byteEncryption(sb[i]);
            sbNew[i] = t;
            sbb.append((char) t);
        }
        return sbb.toString();
    }
    public static byte byteEncryption(byte nSrc) {
        byte nDst = (byte) 0;
        for (int i = 0; i < 8; i++) {
            if ((nSrc & ((byte) (1 << EN_KEY[i]))) != 0) {
                nDst = (byte) ((1 << i) | nDst);
            }
        }
        return nDst;
    }

    /**
     * 解密
     * @param cipherString
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getOriginString(String cipherString) throws UnsupportedEncodingException {
        if (cipherString.trim().equals("")) {
            return "";
        }
        String drr = cipherString;
        byte[] drrByte = new byte[drr.length()];
        for (int i = 0; i < drrByte.length; i++) {
            drrByte[i] = byteDecryption(Byte.valueOf((byte) drr.charAt(i)).byteValue());
        }
        return new String(drrByte, "UTF-8");
    }


    public static byte byteDecryption(byte nSrc) {
        byte nDst = (byte) 0;
        for (int i = 0; i < 8; i++) {
            if ((nSrc & ((byte) (1 << DE_KEY[i]))) != 0) {
                nDst = (byte) ((1 << i) | nDst);
            }
        }
        return nDst;
    }

    public static int[] DE_KEY;
    public static int[] EN_KEY;

    static {
        int[] arrayOfInt1 = new int[8];
        arrayOfInt1[0] = 7;
        arrayOfInt1[1] = 2;
        arrayOfInt1[2] = 5;
        arrayOfInt1[3] = 4;
        arrayOfInt1[5] = 1;
        arrayOfInt1[6] = 3;
        arrayOfInt1[7] = 6;
        EN_KEY = arrayOfInt1;
        int[] arrayOfInt2 = new int[8];
        arrayOfInt2[0] = 4;
        arrayOfInt2[1] = 5;
        arrayOfInt2[2] = 1;
        arrayOfInt2[3] = 6;
        arrayOfInt2[4] = 3;
        arrayOfInt2[5] = 2;
        arrayOfInt2[6] = 7;
        DE_KEY = arrayOfInt2;
    }

}
