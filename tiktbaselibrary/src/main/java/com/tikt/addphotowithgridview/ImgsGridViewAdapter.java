package com.tikt.addphotowithgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tikt.selectphotos.R;

import java.util.List;


/**
 * Created by tikt on 16-9-1.
 */
public class ImgsGridViewAdapter extends BaseAdapter {
	private List<String> list;
	private Context context;
	private LayoutInflater inflater;
	private int limitCount;
	private boolean isFull;//是否全部占满
	int i ;
	public ImgsGridViewAdapter(Context context, List<String> list, int limit){
		this.limitCount = limit;
		this.list = list;
		this.context = context;
		this.i =list.size();
		this.inflater = LayoutInflater.from(context);

	}

	public void refresh(List<String> list){
		this.list = list;
		this.i =list.size();
		notifyDataSetChanged();

	}
	@Override
	public int getCount() {
		if(i<limitCount){
			isFull = false;
			return i+1;
		}else{
			isFull = true;
			return limitCount;
		}
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;
		if(null==convertView){
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_imgsgridview_list,parent,false);
			viewHolder.img = (ImageView) convertView.findViewById(R.id.id_item_imgsgridview_list_img);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();

		}

		final int type;//0：已经添加的，1：未添加的
		if(isFull){
		Glide.with(context).load(list.get(position)).error(R.drawable.zanwu).into(viewHolder.img);
			type = 0;
		}else{
			if(getCount()-1==position){
		Glide.with(context).load(R.drawable.icon_add_photo).into(viewHolder.img);
				type = 1;
			}else{
		Glide.with(context).load(list.get(position)).error(R.drawable.zanwu).into(viewHolder.img);
				type = 0;
			}
		}

		viewHolder.img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(null!=mListener){
					mListener.setImgClick(type,position);
				}
			}
		});

		return convertView;
	}

	public interface ImgClickListener{
		void setImgClick(int type, int position);
	}
	private ImgClickListener mListener;
	public void setImgListener(ImgClickListener listener){
		this.mListener = listener;
	}

	class ViewHolder{
		ImageView img;
	}

}
