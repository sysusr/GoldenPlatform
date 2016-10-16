package com.tikt.goldenplatform;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
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
import butterknife.OnClick;

import static com.tikt.goldenplatform.BusStationActivity.getOriginString;

public class MainActivity extends BaseAppActivity {
    @Bind(R.id.id_mainActivity_btn)
    Button idMainActivityBtn;

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

    @Override
    protected void showLoadingProgressWithStr(Context context, String msg) {

    }

    @Override
    protected void showLoadingProgress(Context context) {

    }

    @Override
    protected void hidenLoadingProgress() {

    }


    @OnClick(R.id.id_mainActivity_btn)
    public void onClick() {
//        String str1 = "￼$ￆￔ￦ﾖ￢ﾔￖﾖ$l$\u001E\f\\ￅ?w$\uFFDE";
        String str1 = "123123123";
        RequestParams params = new RequestParams();
        params.put("",str1);
        Log.i(TAG, "params: =="+params.toString());
        startPost(Api.getBusLineUrl,params);
//        Intent intent = new Intent(this,BusStationActivity.class);
//        intent.putExtra("BUS_NAME","509");
//        intent.putExtra("BUS_ID",3230);
//        startActivity(intent);
        // 1、直接new 一个线程类，传入参数实现Runnable接口的对象（new Runnable），相当于方法二
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 写子线程中的操作
                getBusLine("{\"lineName\":\"528\"}");
            }
        }).start();

        // 2、通过实现Runnable接口
        Thread t = new Thread(new myRunnable());
        t.start();

        // 3、通过继承线程类实现
        new myThread().start();


    }
    // Thread是一个类，必须继承
    public class myThread extends Thread {
        @Override
        public void run() {
            super.run();
            // 写子线程中的操作
        }
    }

    // Runnable是一个接口，需要实现
    public class myRunnable implements Runnable {
        @Override
        public void run() {
            // 写子线程中的操作

        }

    }

    private void getBusLine(String str){
        String str1 = "￼$ￆￔ￦ﾖ￢ﾔￖﾖ$l$\u001E\f\\ￅ?w$\uFFDE";

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
            Log.i(TAG, "\u8fd4\u56de\u7684StatusCode\uff1a " + reuslt);
            Log.i(TAG, "\u8fd4\u56de\u7684StatusCode\uff1a " + getOriginString(reuslt));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void startPost(final String url , RequestParams params){
        clientHeaderParams = new AsyncHttpClient();
        clientHeaderParams.addHeader("charset","UTF-8");
        clientHeaderParams.addHeader("Cookie", "");
        clientHeaderParams.addHeader("Content-Type","application/json");
        clientHeaderParams.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String str = new String(bytes);
                try {
                    Log.i(TAG, "onClientSuccess: =="+getOriginString(str));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                String str = new String(bytes);
                try {
                    Log.i(TAG, "onClientonFailure: =="+getOriginString(str));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
