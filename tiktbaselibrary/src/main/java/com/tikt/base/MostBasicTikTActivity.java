package com.tikt.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tikt on 16-10-9.
 * 最基础的一个activity
 */

public abstract class MostBasicTikTActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getContentViewLayoutID() != 0) {
			setContentView(getContentViewLayoutID());
		}

	}

	/**
	 * bind layout resource file
	 *
	 * @return id of layout resource
	 */
	protected abstract int getContentViewLayoutID();

	protected abstract void initView();
	protected abstract void initEvent();

	/**
	 * 初始化操作
	 */
	protected void initViewsAndEvents(){
		initView();
		initEvent();
	}
}
