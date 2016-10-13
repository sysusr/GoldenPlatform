package com.tikt.goldenplatform;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.tikt.goldenplatform.bean.BusRealTimeEntity;

import org.json.JSONObject;

/**
 * 公交运行情况
 */
public class BusStationActivity extends BaseAppActivity {

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
            super.initView();
    }

    @Override
    protected void initEvent() {

        busName = getIntent().getExtras().getString("BUS_NAME");
        busID = getIntent().getExtras().getInt("BUS_ID");


        RequestParams params = new RequestParams();
        params.put("PUB_USER_ID","");
        params.put("DEVICE_ID","867995029570416");
        params.put("BUS_LINE_NAME",busName);
        params.put("BUS_LINE_ID",busID);
        params.put("FLAG",FLAG);
        params.put("ADV_FLAG",1);
        params.put("SERVICE_NAME","BusInfoService");
        params.put("method","queryBusLineInfo2");
        startPostClientWithHeaderParams(Api.BaseUrl,params);

        //{"VEHICLE_POS":[{"TIME":1476191369956,"STRANK":22,"CAR_ID":1910,"STATION_NAME":"联丰路丽园南路口(送子鸟医院)","CUR_STATION_ID":161,"FLAG":"1","POSITION":1,"TIMEN":1476191369956},{"TIME":1476191368358,"STRANK":7,"CAR_ID":157,"STATION_NAME":"金家漕","CUR_STATION_ID":32384,"FLAG":"1","POSITION":0,"TIMEN":1476191368358},{"TIME":1476191363512,"STRANK":28,"CAR_ID":390,"STATION_NAME":"关爱小区","CUR_STATION_ID":1459,"FLAG":"1","POSITION":1,"TIMEN":1476191363512},{"TIME":1476191368433,"STRANK":16,"CAR_ID":162,"STATION_NAME":"药行街(开明街)","CUR_STATION_ID":1624,"FLAG":"1","POSITION":1,"TIMEN":1476191368433}]}


    }

    @Override
    protected void onClientSuccess(String url, JSONObject json) {

        switch (url){
            case Api.BaseUrl:
                Gson gson = new Gson();
                BusRealTimeEntity station = gson.fromJson(json.toString(), BusRealTimeEntity.class);
                station.getCRYPTOGRAPH();
                try {
                    Log.i(TAG, "onClientSuccess: =="+new String(DESCodeUitl.decode(Base64.decode(station.getCRYPTOGRAPH().getBytes("utf-8"), 0), "nbt_ztesoft_!@#$%^&=="), "utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void showLoadingProgressWithStr(Context context, String msg) {

    }

    @Override
    protected void showLoadingProgress(Context context) {

    }

    @Override
    protected void hidenLoadingProgress() {

    }
}
