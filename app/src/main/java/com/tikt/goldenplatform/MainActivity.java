package com.tikt.goldenplatform;

import android.content.Intent;
import android.widget.Button;

import com.tikt.goldenplatform.base.BaseAppActivity;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseAppActivity {
    @Bind(R.id.id_mainActivity_btn)
    Button idMainActivityBtn;
    //
    //nbt_ztesoft_!@#$%^&==
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onClientSuccess(String url, JSONObject json) {

    }



    @OnClick(R.id.id_mainActivity_btn)
    public void onClick() {
        Intent intent = new Intent(this,BusStationActivity.class);
        intent.putExtra("BUS_NAME","509");
        intent.putExtra("BUS_ID",3230);
        startActivity(intent);
    }
}
