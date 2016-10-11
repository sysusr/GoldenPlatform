package com.tikt.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tikt.selectphotos.R;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by tikt on 16-3-1.
 * 带有网络请求的activity
 */

public abstract class HttpTikTActivity extends BaseTikTActivity {

	protected AsyncHttpClient clientHeader;
	protected AsyncHttpClient clientHeaderParams;
	protected AsyncHttpClient clientAuth;
	protected AsyncHttpClient clientAuthParams;
	protected Activity mActivity;
	protected InputMethodManager inputMethodManager;//获得软键盘Manager
	protected SharedPreferences sp;
	protected String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = this;
	}

	@Override
	protected void onStop() {
		if (clientHeader != null) {
			clientHeader.cancelRequests(mActivity, true);
			clientHeader = null;
			Log.d("TAG", "clientHeader is cancel");
		}
		if (clientAuth != null) {
			clientAuth.cancelRequests(mActivity, true);
			clientAuth = null;
			Log.d("TAG", "clientAuth is cancel");
		}
		if (clientHeaderParams != null) {
			clientHeaderParams.cancelRequests(mActivity, false);
			clientHeaderParams = null;
			Log.d("TAG", "clientHeaderParams is cancel");
		}
		if (clientAuthParams != null) {
			clientAuthParams.cancelRequests(mActivity, true);
			clientAuthParams = null;
			Log.d("TAG", "clientAuthParams is cancel");
		}
		this.onTrimMemory(TRIM_MEMORY_UI_HIDDEN);
		super.onStop();
	}



	@Override
	protected void onStart() {
		super.onStart();
		if (null != sp) {
			token = sp.getString("token", "");
		}
	}

	protected void initView() {
		sp = getSharedPreferences("user_info", Context.MODE_PRIVATE);
		token = sp.getString("token", "");
		Log.e("TAG", "inputMethodManager is notnull");
		Log.e("TAG", "token==" + token);
		inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	protected void isSuccess() {

	}

	protected void isError() {

	}

	protected void startGetClientWithHeader(final String url) {
		clientHeader = new AsyncHttpClient();
		clientHeader.addHeader("SdkVersion", "1");

		clientHeader.get( url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
//				Log.e("TAG", "startGetClientWithHeader==onFailure1++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//				Log.e("TAG", "startGetClientWithHeader==onFailure2++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//				Log.e("TAG", "startGetClientWithHeader==onFailure3++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}
		});
	}

	protected void startPostClientWithHeader(final String url) {
		clientHeader = new AsyncHttpClient();
		clientHeader.addHeader("SdkVersion", "1");
		clientHeader.post( url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
//				Log.e("TAG", "startPostClientWithHeader==onFailure1++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//				Log.e("TAG", "startPostClientWithHeader==onFailure2++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//				Log.e("TAG", "startPostClientWithHeader==onFailure3++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}
		});
	}

	protected void startGetClientWithHeaderParams(final String url, RequestParams params) {
		clientHeaderParams = new AsyncHttpClient();
		clientHeaderParams.setURLEncodingEnabled(true);
		clientHeaderParams.setTimeout(10000);
		clientHeaderParams.addHeader("SdkVersion", "1");

//		clientHeaderParams.get( url, params, new AsyncHttpResponseHandler() {
//			@Override
//			public void onSuccess(int i, Header[] headers, byte[] bytes) {
//				onClientSuccess(url, bytes);
//			}
//
//			@Override
//			public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//				onClientError();
//			}
//		});

		clientHeaderParams.get( url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
//				Log.e("TAG", "startGetClientWithHeaderParams==onFailure1++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//				Log.e("TAG", "startGetClientWithHeaderParams==onFailure2++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//				Log.e("TAG", "startGetClientWithHeaderParams==onFailure3++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}
		});
	}

	/**
	 * 自由设置请求头
	 *
	 * @param url
	 */
	protected void startGetClientWithClientHeaderAndModel(final String url, String header, String model) {
		clientHeader = new AsyncHttpClient();
		clientHeader.setURLEncodingEnabled(true);
		clientHeader.setTimeout(10000);
		clientHeader.addHeader("Version", header);
		clientHeader.addHeader("Model", model);

		clientHeader.get( url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
//				Log.e("TAG", "startGetClientWithHeaderParams==onFailure1++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//				Log.e("TAG", "startGetClientWithHeaderParams==onFailure2++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//				Log.e("TAG", "startGetClientWithHeaderParams==onFailure3++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}
		});
	}


	protected void startPostClientWithHeaderParams(final String url, RequestParams params) {
		clientHeaderParams = new AsyncHttpClient();
		clientHeaderParams.setTimeout(10000);
		clientHeaderParams.setURLEncodingEnabled(true);
		clientHeaderParams.addHeader("SdkVersion", "1");
		clientHeaderParams.post( url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
//				Log.e("TAG", "startPostClientWithHeaderParams==onFailure1++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//				Log.e("TAG", "startPostClientWithHeaderParams==onFailure2++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//				Log.e("TAG", "startPostClientWithHeaderParams==onFailure3++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}
		});
	}

	protected void startGetClientWithAtuh(final String url) {
		clientAuth = new AsyncHttpClient();
		clientAuth.addHeader("SdkVersion", "1");
		clientAuth.addHeader("Authorization", token);
		clientAuth.get( url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
//				Log.e("TAG", "startGetClientWithAtuhParams==onFailure1++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//				Log.e("TAG", "startGetClientWithAtuhParams==onFailure2++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//				Log.e("TAG", "startGetClientWithAtuhParams==onFailure3++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}


		});
	}

	protected void startPostClientWithAtuh(final String url) {
		clientAuth = new AsyncHttpClient();
		clientAuth.addHeader("SdkVersion", "1");
		clientAuth.addHeader("Authorization", token);
		clientAuth.post( url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
//				Log.e("TAG", "startGetClientWithAtuhParams==onFailure1++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//				Log.e("TAG", "startGetClientWithAtuhParams==onFailure2++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//				Log.e("TAG", "startGetClientWithAtuhParams==onFailure3++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}


		});
	}

	protected void startGetClientWithAtuhParams(final String url, RequestParams params) {
		clientAuthParams = new AsyncHttpClient();
//		clientAuthParams.setTimeout(10000);
		clientAuthParams.setURLEncodingEnabled(true);
		clientAuthParams.addHeader("SdkVersion", "1");
		clientAuthParams.addHeader("Authorization", token);
		clientAuthParams.get( url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
//				Log.e("TAG", "startGetClientWithAtuhParams==onFailure1++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//				Log.e("TAG", "startGetClientWithAtuhParams==onFailure2++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//				Log.e("TAG", "startGetClientWithAtuhParams==onFailure3++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}


		});
	}

	protected void startPostClientWithAtuhParams(final String url, RequestParams params) {
		clientAuthParams = new AsyncHttpClient();
		clientAuthParams.setTimeout(20000);
		clientAuthParams.setURLEncodingEnabled(true);
		clientAuthParams.addHeader("SdkVersion", "1");
		clientAuthParams.addHeader("Authorization", token);

		clientAuthParams.post( url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//				Log.e("TAG", "startPostClientWithAtuhParams==onFailure1++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				super.onFailure(statusCode, headers, throwable, errorResponse);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//				Log.e("TAG", "startPostClientWithAtuhParams==onFailure2++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//				Log.e("TAG", "startPostClientWithAtuhParams==onFailure3++"+statusCode);
				Log.e("TAG", url + " : " + statusCode);
				onClientError(url, throwable);
			}

		});
	}


	/**
	 * 接收网络请求的返回结果
	 *
	 * @param url
	 * @param json
	 */
	protected abstract void onClientSuccess(String url, JSONObject json);


	protected void onClientError(String url, Throwable throwable) {
		Log.e("TAG", "url==" + url);
		hidenLoadingProgress();
		showToast(mActivity, getString(R.string.serviceIsError));
	}

	protected void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	protected void showSnackbar(View view, String msg) {
		Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d("TAG", "keyboard is down");
			if (mActivity.getCurrentFocus() != null) {
				if (mActivity.getCurrentFocus().getWindowToken() != null) {
					if (null != inputMethodManager) {
						inputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
					}
				}
			}
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 清空sp
	 */
	protected void clearSharedPreferences() {
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		editor.apply();
	}

	/**
	 * token过期,设置为空
	 */
	protected void setTokenEmpty() {
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("token", "");
		editor.putFloat("MyPoints", 0f);
		editor.apply();
	}

	/**
	 * 显示加载弹窗及信息
	 */
	protected abstract void showLoadingProgressWithStr(Context context, String msg);

	/**
	 * 显示加载弹窗
	 */
	protected abstract void showLoadingProgress(Context context);
	/**
	 * 隐藏加载弹窗
	 */
	protected abstract void hidenLoadingProgress();

	/**
	 * 保留2位小数
	 *
	 * @param f
	 * @return
	 */
	protected String NumberFormat2(float f) {
		return String.format("%.2f", f);
	}
}
