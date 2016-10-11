package com.tikt.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tikt.selectphotos.R;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by tikt on 16-2-26.
 */
public abstract class HttpTikTFragment extends BaseTikTFragment {

	protected TextView title;
	protected AsyncHttpClient clientHeader;
	protected AsyncHttpClient clientHeaderParams;
	protected AsyncHttpClient clientAuth;
	protected AsyncHttpClient clientAuthParams;
	protected InputMethodManager manager;//获得软键盘Manager
	protected SharedPreferences sp;
	protected String token;
	protected Context context;

	public HttpTikTFragment() {

	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
		token = sp.getString("token", "");
		manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	protected void isSuccess() {

	}

	protected void isError() {

	}

	protected void startGetClientWithHeader(final String url) {
		clientHeader = new AsyncHttpClient();
		clientHeader.addHeader("SdkVersion", "1");

		clientHeader.get(url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Log.e("TAG", "startGetClientWithHeader==onFailure1");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
				Log.e("TAG", "startGetClientWithHeader==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				Log.e("TAG", "startGetClientWithHeader==onFailure3");
				onClientError(url, throwable);
			}
		});
	}

	protected void startPostClientWithHeader(final String url) {
		clientHeader = new AsyncHttpClient();
		clientHeader.addHeader("SdkVersion", "1");
		clientHeader.post(url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Log.e("TAG", "startPostClientWithHeader==onFailure1");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
				Log.e("TAG", "startPostClientWithHeader==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				Log.e("TAG", "startPostClientWithHeader==onFailure3");
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

		clientHeaderParams.get(url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Log.e("TAG", "startGetClientWithHeaderParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
				Log.e("TAG", "startGetClientWithHeaderParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				Log.e("TAG", "startGetClientWithHeaderParams==onFailure3");
				onClientError(url, throwable);
			}
		});
	}

	protected void startPostClientWithHeaderParams(final String url, RequestParams params) {
		clientHeaderParams = new AsyncHttpClient();
		clientHeaderParams.setTimeout(10000);
		clientHeaderParams.setURLEncodingEnabled(true);
		clientHeaderParams.addHeader("SdkVersion", "1");
		clientHeaderParams.post(url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Log.e("TAG", "startPostClientWithHeaderParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
				Log.e("TAG", "startPostClientWithHeaderParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				Log.e("TAG", "startPostClientWithHeaderParams==onFailure3");
				onClientError(url, throwable);
			}
		});
	}

	protected void startGetClientWithAtuh(final String url) {
		clientAuth = new AsyncHttpClient();
		clientAuth.addHeader("SdkVersion", "1");
		clientAuth.addHeader("Authorization", token);
		clientAuth.get(url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Log.e("TAG", "startGetClientWithAtuhParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
				Log.e("TAG", "startGetClientWithAtuhParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				Log.e("TAG", "startGetClientWithAtuhParams==onFailure3");
				onClientError(url, throwable);
			}


		});
	}

	protected void startPostClientWithAtuh(final String url) {
		clientAuth = new AsyncHttpClient();
		clientAuth.addHeader("SdkVersion", "1");
		clientAuth.addHeader("Authorization", token);
		clientAuth.post(url, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Log.e("TAG", "startGetClientWithAtuhParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
				Log.e("TAG", "startGetClientWithAtuhParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				Log.e("TAG", "startGetClientWithAtuhParams==onFailure3");
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
		clientAuthParams.get(url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				super.onFailure(statusCode, headers, throwable, errorResponse);
				Log.e("TAG", "startGetClientWithAtuhParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
				Log.e("TAG", "startGetClientWithAtuhParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				Log.e("TAG", "startGetClientWithAtuhParams==onFailure3");
				onClientError(url, throwable);
			}


		});
	}

	protected void startPostClientWithAtuhParams(final String url, RequestParams params) {
		clientAuthParams = new AsyncHttpClient();
		clientAuthParams.setTimeout(10000);
		clientAuthParams.setURLEncodingEnabled(true);
		clientAuthParams.addHeader("SdkVersion", "1");
		clientAuthParams.addHeader("Authorization", token);

		clientAuthParams.post(url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("TAG", url + " : " + response.toString());
				onClientSuccess(url, response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Log.e("TAG", "startPostClientWithAtuhParams==onFailure1");
				super.onFailure(statusCode, headers, throwable, errorResponse);
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
				Log.e("TAG", "startPostClientWithAtuhParams==onFailure2");
				onClientError(url, throwable);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				Log.e("TAG", "startPostClientWithAtuhParams==onFailure3");
				onClientError(url, throwable);
			}

		});
	}

	protected abstract void onClientSuccess(String url, JSONObject json);


	protected void onClientError() {
//		Log.e("TAG", "404");
		if (isAdded()) {
			showToast(context, getString(R.string.serviceIsError));
		}
	}

	protected void onClientError(String url, Throwable throwable) {
//		Log.e("TAG", throwable.toString());
//		Log.e("TAG", "404");
		/*如果activity先被销毁而网络在次此之后执行了该方法
			会报错:Fragment not attached to Activity
			是因为Fragment的还没有Attach到Activity时，调用了如getResource()等，需要上下文Content的函数。
			所以先通过isAdded()判断
		 */
		if (isAdded()) {
			hidenLoadingProgress();
			showToast(getActivity(), getString(R.string.serviceIsError));
		}
	}

	protected void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	protected void showSnackbar(View view, String msg) {
		Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
	}


	@Override
	public void onDestroy() {
		if (clientHeader != null) {
			clientHeader.cancelRequests(context, true);
			clientHeader = null;
			Log.d("TAG", "clientHeader is cancel");
		}
		if (clientAuth != null) {
			clientAuth.cancelRequests(context, true);
			clientAuth = null;
			Log.d("TAG", "clientAuth is cancel");
		}
		if (clientHeaderParams != null) {
			clientHeaderParams.cancelRequests(context, false);
			clientHeaderParams = null;
			Log.d("TAG", "clientHeaderParams is cancel");
		}
		if (clientAuthParams != null) {
			clientAuthParams.cancelRequests(context, true);
			clientAuthParams = null;
			Log.d("TAG", "clientAuthParams is cancel");
		}
		super.onDestroy();
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
	 * 保留2位小数
	 *
	 * @param f
	 * @return
	 */
	protected String NumberFormat2(float f) {
		return String.format("%.2f", f);
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
}
