package com.tikt.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by tikt on 16-10-9.
 * 绑定layout
 */

public abstract class BaseTikTFragment extends MostBasicTikTFragment {

	public BaseTikTFragment() {
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
		initViewsAndEvents();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
	}

}
