package com.tikt.selectphotos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by tikt on 16-3-1.
 */

public abstract class BaseSelectPhotoActivity extends AppCompatActivity {

	protected Activity mActivity;
	protected InputMethodManager inputMethodManager;//获得软键盘Manager

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mActivity = this;
		if (getContentViewLayoutID() != 0) {
			setContentView(getContentViewLayoutID());
		} else {
			//TODO
		}

	}
	@Override
	protected void onStop() {
		this.onTrimMemory(TRIM_MEMORY_UI_HIDDEN);
		super.onStop();
	}

	/**
	 * bind layout resource file
	 *
	 * @return id of layout resource
	 */
	protected abstract int getContentViewLayoutID();

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initViewsAndEvents();
	}

	protected void initView() {

	}

	protected abstract void initEvent();

	protected void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * init all views and add events
	 */
	protected abstract void initViewsAndEvents();

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d("TAG", "keyboard is down");
			if (mActivity.getCurrentFocus() != null) {
				if (mActivity.getCurrentFocus().getWindowToken() != null) {
					inputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
				}
			}
		}
		return super.onTouchEvent(event);
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
}
