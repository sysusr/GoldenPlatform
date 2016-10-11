package com.tikt.selectphotos;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tikt on 15-12-18.
 */
public class ImageAdapter extends BaseAdapter {
	public static Set<String> mSelectedImg = new HashSet<>();
	public static String[] pic_list;//原有的数据
	private String mDirPath;
	private List<String> mImgPaths;
	private LayoutInflater mInflater;
	private Context context;
	private int mScreenWidth;
	private int countOfEmpty;//剩余的图片添加位置

	public ImageAdapter(Context context, String[] old_list, int count, List<String> mDatas, String dirPath) {
		this.pic_list = old_list;
		this.countOfEmpty = count;
		this.mDirPath = dirPath;
		this.mImgPaths = mDatas;
		mInflater = LayoutInflater.from(context);
		this.context = context;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
//		resetDatasAndView();
	}

	@Override
	public int getCount() {
		return mImgPaths.size();

	}

	@Override
	public Object getItem(int i) {
		return mImgPaths.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(final int i, View view, ViewGroup viewGroup) {
		final ViewHolder viewHolder;
		if (view == null) {
			view = mInflater.inflate(R.layout.item_selectphoto_layout, viewGroup, false);
			viewHolder = new ViewHolder();
			viewHolder.mImg = (ImageView) view.findViewById(R.id.id_item_selectphoto_image);
			viewHolder.mSelect = (ImageButton) view.findViewById(R.id.id_item_selectphoto_select);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		//重置状态
		viewHolder.mImg.setImageResource(R.drawable.zanwu);
		viewHolder.mSelect.setImageResource(R.drawable.uncheck);
		viewHolder.mImg.setColorFilter(null);

		viewHolder.mImg.setMaxWidth(mScreenWidth / 3);

//		String ImgUri = mDirPath + "/" + mImgPaths.get(i);
//		if(ImgUri.endsWith())

		LocalImageLoader.getInstance(3, LocalImageLoader.Type.LIFO).
				loadImage(mDirPath + "/" + mImgPaths.get(i), viewHolder.mImg);
		resetDatasAndView();
		final String filePath = mDirPath + "/" + mImgPaths.get(i);
		viewHolder.mSelect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (((mSelectedImg.size() <= countOfEmpty)) || countOfEmpty > 0) {
					//已经被选择
					if (mSelectedImg.contains(filePath)) {
						deletePicPath(filePath);
						mSelectedImg.remove(filePath);
						/**
						 * 代替notifyDataSetChanged();避免数据刷新导致闪屏
						 */
						viewHolder.mImg.setColorFilter(null);
						viewHolder.mSelect.setImageResource(R.drawable.uncheck);
						if(null!=mListener){
							//TODO
							mListener.onClickSelect(2);//1:增加,2:减少
						}

					} else { // 未被选择

						addPicPath(filePath); // 保存选择的图片路径
						mSelectedImg.add(filePath);
						viewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
						viewHolder.mSelect.setImageResource(R.drawable.selected);
						if(null!=mListener){
							//TODO
							mListener.onClickSelect(1);//1:增加,2:减少
						}
					}
//				notifyDataSetChanged(); //会导致闪屏
				} else { //最多只能选3张
					//已经被选择
					if (mSelectedImg.contains(filePath)) {
						deletePicPath(filePath);
						mSelectedImg.remove(filePath);
						/**
						 * 代替notifyDataSetChanged();避免数据刷新导致闪屏
						 */
						viewHolder.mImg.setColorFilter(null);
						viewHolder.mSelect.setImageResource(R.drawable.uncheck);
						if(null!=mListener){
							//TODO
							mListener.onClickSelect(2);//1:增加,2:减少
						}
					} else { // 未被选择
						Toast.makeText(context, "已经选满了", Toast.LENGTH_SHORT).show();

					}

				}
			}
		});
		viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(null!=mListener){
					mListener.onClick(viewHolder.mImg,filePath);
				}
//				Intent intent = new Intent(context, ShowBigPhotoActivity.class);
//				Bundle bundle = new Bundle();
//				bundle.putString("img", filePath);
//				intent.putExtras(bundle);
//				context.startActivity(intent);
			}
		});

		if (mSelectedImg.contains(filePath)) {
			Log.i("TAG", "mSelectedImg.contains(filePath)" + filePath);
			viewHolder.mImg.setColorFilter(Color.parseColor("#77000000"));
			viewHolder.mSelect.setImageResource(R.drawable.selected);
		}
		return view;
	}

	/**
	 * interface
	 */
	public interface OnItemClickListener {
		void onClick(View view, String url);
		void onClickSelect(int type);//设置top上选择的图片数量
	}

	private OnItemClickListener mListener;

	public void setOnItemClick(OnItemClickListener listener) {
		mListener = listener;
	}

	/**
	 * 每次都根据pic_list中的数据重置图片勾选状态
	 */
	private void resetDatasAndView() {
		mSelectedImg.clear();
		for (int i = 0; i < pic_list.length; i++) {
			if (null != pic_list[i]) {
				mSelectedImg.add(pic_list[i]);

			}
		}
	}

	/**
	 * 删除已选中的
	 *
	 * @param path
	 */
	private void deletePicPath(String path) {
		countOfEmpty++;
		for (int i = 0; i < pic_list.length; i++) {
			if (path.equals(pic_list[i])) {
				Log.i("TAG", "path delete==" + path);
				pic_list[i] = null;
				break;
			}
		}
	}

	/**
	 * 添加未选中的
	 *
	 * @param path
	 */
	private void addPicPath(String path) {
		if (countOfEmpty > 0) {
			countOfEmpty--;
		}
		for (int i = 0; i < pic_list.length; i++) {
			if (null == pic_list[i]) {
				Log.i("TAG", "path add==" + path);
				pic_list[i] = path;
				break;
			}
		}
	}

	private class ViewHolder {
		ImageView mImg;
		ImageButton mSelect;
	}
}
