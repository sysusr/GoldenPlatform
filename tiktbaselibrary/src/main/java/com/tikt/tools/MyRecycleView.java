package com.tikt.tools;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by tikt on 16-7-12.
 */
public class MyRecycleView extends RecyclerView {
	public MyRecycleView(Context context) {
		super(context);
	}

	public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	//
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		//
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
