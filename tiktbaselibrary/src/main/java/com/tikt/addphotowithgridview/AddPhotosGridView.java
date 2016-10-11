package com.tikt.addphotowithgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;

import java.util.List;

/**
 * Created by tikt on 16-9-1.
 */
public class AddPhotosGridView extends GridView {

	private List<String> imglist;//图片地址
	private ImgsGridViewAdapter adapter;
	private Context context;
	private int limitCount = 3;//图片最大个数限制
	public AddPhotosGridView(Context context) {
		super(context);
		this.context = context;
		Log.i("TAG", "TestGridView: 1");



	}

	public AddPhotosGridView(Context context, AttributeSet attrs) {
//		this(context);
		super(context, attrs);
		this.context = context;
		Log.i("TAG", "TestGridView: 2");

	}

	public AddPhotosGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		Log.i("TAG", "TestGridView: 3");
	}


public void setImgsList(List<String> imglist){
	setImgsList(imglist,limitCount);

}
	public void setImgsList(List<String> imglist, int limitCount){
		this.limitCount = limitCount;
		this.imglist = imglist;
		int i = imglist.size();

			adapter = new ImgsGridViewAdapter(context,imglist,limitCount);
			adapter.setImgListener(new ImgsGridViewAdapter.ImgClickListener() {
				@Override
				public void setImgClick(int type,int position) {//0：已经添加的，1：未添加的

					if(null!=mListener){
						mListener.viewListener(type,position);

					}
				}
			});
			setAdapter(adapter);

	}
	public void refreshImgsList(List<String> imglist){
		this.imglist = imglist;
		if(null!=adapter){
			adapter.refresh(imglist);
		}
	}

public interface onItemViewListener{
	void viewListener(int type, int position);
}
	private onItemViewListener mListener;
	public void setItemViewClick(onItemViewListener listener){
		this.mListener = listener;
	}

	/*@Override
	public boolean performItemClick(View view, int position, long id) {
		Log.i("TAG", "performItemClick: =="+position);

		if(imglist.size()<limitCount){

		}
//		return super.performItemClick(view, position, id);
		return true;
	}*/


	@Override
	public void onMeasure(int widthMeasureSpec,int heightMeasureSpec){

		//
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}





}
