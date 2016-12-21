package com.tikt.goldenplatform;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import com.loopj.android.http.RequestParams;
import com.tikt.goldenplatform.base.BaseAppActivity;
import com.tikt.goldenplatform.ui.BusStationActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends BaseAppActivity {
	@Bind(R.id.id_mainActivity_btn)
	Button idMainActivityBtn;
	//

	Retrofit retrofit;

	//nbt_ztesoft_!@#$%^&==
	@Override
	protected int getContentViewLayoutID() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void initEvent() {
		retrofit = new Retrofit.Builder()
//				.baseUrl("http://192.168.1.121:8080/")
				.baseUrl("http://Weixin1.nbbus.com:8080/NingboBusWebservice/resources/")
//                .addConverterFactory(GsonConverterFactory.create())
				.addConverterFactory(ScalarsConverterFactory.create())
				.build();
	}

  /*  @Override
    protected void onClientSuccess(String url, JSONObject json) {

    }*/


	@OnClick(R.id.id_mainActivity_btn)
	public void onClick() {
		Intent intent = new Intent(this, BusStationActivity.class);
		intent.putExtra("BUS_NAME", "810");
		intent.putExtra("BUS_ID", 3230);
		startActivity(intent);

//		onRetrofitPostWithParams();
		String str1 = "￼$ￆￔ￦ﾖ￢ﾔￖﾖ$l$\u001E\f\\ￅ?w$\uFFDE";
//        String str1 = "123123123";
        /*RequestParams params = new RequestParams();
        try {
            params.put("",getCipherString("{\"lineName\":\"528\"}"));
            params.setContentEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "params: =="+params.toString());
        startPost(Api.getBusLineUrl,params);*/

		// 1、直接new 一个线程类，传入参数实现Runnable接口的对象（new Runnable），相当于方法二
	    /*new Thread(new Runnable() {
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
        new myThread().start();*/


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

	private void getBusLine(String str) {
		String str1 = "￼$ￆￔ￦ﾖ￢ﾔￖﾖ$l$\u001E\f\\ￅ?w$\uFFDE";

		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(Api.getBusLineUrl);
		post.addHeader("charset", "UTF-8");
		post.addHeader("Cookie", "");
		client.getParams().setParameter("http.connection.timeout", Integer.valueOf(100000));
		client.getParams().setParameter("http.socket.timeout", Integer.valueOf(100000));

		try {
//            post.setEntity(new StringEntity(str1, "UTF-8"));
			post.setEntity(new StringEntity(DESCodeUitl.getCipherString("{\"lineName\":\"528\"}"), "UTF-8"));
			HttpResponse response = client.execute(post);
			Log.i(TAG, "response\uff1a " + response);
			Log.i(TAG, "\u8fd4\u56de\u7684StatusCode\uff1a " + response.getStatusLine().getStatusCode());
			String reuslt = EntityUtils.toString(response.getEntity(), "UTF-8");
			Log.i(TAG, "\u8fd4\u56de\u7684StatusCode\uff1a " + reuslt);
			Log.i(TAG, "\u8fd4\u56de\u7684StatusCode\uff1a " + DESCodeUitl.getOriginString(reuslt));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	private void startPost(final String url, RequestParams params) {
//        clientHeaderParams = new AsyncHttpClient();
//        clientHeaderParams.setURLEncodingEnabled(false);
//        clientHeaderParams.addHeader("charset","UTF-8");
//        clientHeaderParams.addHeader("Cookie", "");
//        clientHeaderParams.addHeader("Content-Type","application/json");
//        try {
//            clientHeaderParams.post(this, url, null, new StringEntity(getCipherString("{\"lineName\":\"528\"}"), "UTF-8"), "utf-8", new AsyncHttpResponseHandler() {
//                @Override
//                public void onSuccess(int i, Header[] headers, byte[] bytes) {
//                    String str = new String(bytes);
//                    try {
//                        Log.i(TAG, "onClientSuccess: =="+getOriginString(str));
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                    String str = new String(bytes);
//                    try {
//                        Log.i(TAG, "onClientonFailure: =="+getOriginString(str));
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
	}

	/**
	 * 带参Post请求
	 */
	private void onRetrofitPostWithParams() {
//        bus = "{\"lineName\":\"528\"}"; 参数原型
		BusLine service = retrofit.create(BusLine.class);
		Call<String> repoCall = null;
		try {
			repoCall = service.listRepos(DESCodeUitl.getCipherString("{\"lineName\":\"528\"}"));
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
