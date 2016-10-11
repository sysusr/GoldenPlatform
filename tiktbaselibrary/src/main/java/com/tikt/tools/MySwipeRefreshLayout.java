package com.tikt.tools;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by tikt on 16-5-10.
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {

	public MySwipeRefreshLayout(Context context) {
		super(context);
	}
	public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private float xDistance, yDistance, xLast, yLast;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				xDistance = yDistance = 0f;
				xLast = ev.getX();
				yLast = ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				final float curX = ev.getX();
				final float curY = ev.getY();

				xDistance += Math.abs(curX - xLast);
				yDistance += Math.abs(curY - yLast);
				xLast = curX;
				yLast = curY;

				if (xDistance > yDistance) {
					return false;
				}
		}

		return super.onInterceptTouchEvent(ev);
	}

	private OnScrollViewScrollListener mOnScrollViewScrollListener = null;

	@Override
	protected void onScrollChanged(int x, int y, int oldx, int oldy) {
		super.onScrollChanged(x, y, oldx, oldy);
		if (mOnScrollViewScrollListener != null) {
			mOnScrollViewScrollListener.onScrollChanged(x, y, oldx, oldy);
		}
	}

	public void setOnScrollListener(OnScrollViewScrollListener listener) {
		this.mOnScrollViewScrollListener = listener;
	}

	public interface OnScrollViewScrollListener {
		public void onScrollChanged(int x, int y, int oldX, int oldY);
	}
}
