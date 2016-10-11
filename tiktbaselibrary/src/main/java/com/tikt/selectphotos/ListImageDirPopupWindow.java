package com.tikt.selectphotos;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tikt on 15-12-18.
 */
public class ListImageDirPopupWindow extends PopupWindow {

	private int mWidth;
	private int mHeight;
	private View mConvertView;
	private ListView mListView;
	private List<FolderBean> mDatas;

	public interface OnDirSelectedListener {

		void onSelected(FolderBean folderBean);
	}

	public OnDirSelectedListener mListener;

	public void setOnDirSelectedListener(OnDirSelectedListener mListener) {
		this.mListener = mListener;
	}

	public ListImageDirPopupWindow(Context context, List<FolderBean> datas) {
		calWidthAndHeight(context);
		mConvertView = LayoutInflater.from(context).inflate(R.layout.popwindow_selectphotoactivity_selectfile_layout, null);
		mDatas = datas;

		setContentView(mConvertView);
		setWidth(mWidth);
		setHeight(mHeight);

		setFocusable(true);
		setTouchable(true);
		setOutsideTouchable(true);
		setBackgroundDrawable(new BitmapDrawable());
		setTouchInterceptor(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
					dismiss();
					return true;
				}
				return false;
			}
		});
		initView(context);
		initEvent();
	}

	private void initView(Context context) {
		mListView = (ListView) mConvertView.findViewById(R.id.id_selectphoto_selectfile_list);
		mListView.setAdapter(new ListDirAdapter(context, mDatas));
	}

	private void initEvent() {
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				if(mListener!=null){
					mListener.onSelected(mDatas.get(i));
				}
			}
		});

	}

	/**
	 * 计算popupwindow的宽度和高度
	 *
	 * @param context
	 */
	private void calWidthAndHeight(Context context) {

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);


		mWidth = outMetrics.widthPixels;
		mHeight = (int) (outMetrics.heightPixels * 0.7);
	}

	private class ListDirAdapter extends ArrayAdapter<FolderBean> {

		private LayoutInflater mInflater;
		private List<FolderBean> mDatas;

		public ListDirAdapter(Context context, List<FolderBean> object) {

			super(context, 0, object);
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.item_selectphotoactivity_selectfile_layout, parent, false);
				holder.mImg = (ImageView) convertView.findViewById(R.id.id_item_selectfile_img);
				holder.mDirName = (TextView) convertView.findViewById(R.id.id_item_selectfile_name);
				holder.mDirCount = (TextView) convertView.findViewById(R.id.id_item_selectfile_count);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			FolderBean bean = getItem(position);
			//重置
			holder.mImg.setImageResource(R.drawable.zanwu);

			LocalImageLoader.getInstance().loadImage(bean.getFirstImgPath(), holder.mImg);
			holder.mDirName.setText(bean.getName());
			holder.mDirCount.setText(bean.getCount() + "");


			return convertView;
		}

		private class ViewHolder {
			ImageView mImg;
			TextView mDirName;
			TextView mDirCount;
		}
	}

}
