package com.tikt.base;

import butterknife.ButterKnife;

/**
 * Created by tikt on 16-10-9.
 * 绑定layout的一些操作
 */

public abstract class BaseTikTActivity extends MostBasicTikTActivity {

	/**
	 * 绑定layout
	 *
	 * @param layoutResID
	 */
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		ButterKnife.bind(this);
		initViewsAndEvents();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
	}
}
