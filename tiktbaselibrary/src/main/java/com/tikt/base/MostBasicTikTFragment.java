package com.tikt.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tikt on 16-10-9.
 * 最基础的fragment
 */

public abstract class MostBasicTikTFragment extends Fragment {

	public MostBasicTikTFragment() {
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (getContentViewLayoutID() != 0) {
			return inflater.inflate(getContentViewLayoutID(), null);
		} else {
			return super.onCreateView(inflater, container, savedInstanceState);
		}
	}

	/**
	 * bind layout resource file
	 *
	 * @return id of layout resource
	 */
	protected abstract int getContentViewLayoutID();

	/**
	 * init all views and add events
	 */
	protected void initViewsAndEvents() {
		initView();
		initEvent();
	}

	protected abstract void initView();

	protected abstract void initEvent();
}
