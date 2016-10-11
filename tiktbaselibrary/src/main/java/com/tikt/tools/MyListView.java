package com.tikt.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 解决ListView和ScrollView的冲突
 * @author XU
 *
 */
public class MyListView extends ListView {
	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
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
