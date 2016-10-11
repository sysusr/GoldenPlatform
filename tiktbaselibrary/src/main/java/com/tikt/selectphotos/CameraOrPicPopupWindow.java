package com.tikt.selectphotos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by tikt on 15-12-21.
 * 选择拍照或相册
 */
public class CameraOrPicPopupWindow {

	private int REQ_1 = 1; //调用相机后返回的参数
	private int REQ_2 = 2;
	private int REQ_3 = 3;
	private int REQ_4 = 4; //调用相册
	PopupWindow pop;
	String fileName;
	Activity context;
	private int mWidth;
	private int mHeight;
	TextView tv_camera;
	TextView tv_picture;
	TextView tv_cancal;
	String[] photo_uri = new String[3];  //图片路径
	View view;

	public CameraOrPicPopupWindow(final Activity context, View view) {
		this.context = context;
		this.view = view;
		View shareView = LayoutInflater.from(context).inflate(R.layout.popwindow_cameraorpic_layout, null);

		tv_camera = (TextView) shareView.findViewById(R.id.id_popupwindow_cameraorpic_camera);
		tv_picture = (TextView) shareView.findViewById(R.id.id_popupwindow_cameraorpic_pic);
		tv_cancal = (TextView) shareView.findViewById(R.id.id_popupwindow_cameraorpic_cancal);

		tv_camera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (photo_uri[0] == null) {
					onTakeCamera(REQ_1);
				} else if (photo_uri[1] == null) {
					onTakeCamera(REQ_2);
				} else if (photo_uri[2] == null) {
					onTakeCamera(REQ_3);
				}
			}
		});
		tv_picture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent_select = new Intent(context, SelectPhotoActivity.class);
				context.startActivityForResult(intent_select, REQ_4);
			}
		});
		tv_cancal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				pop.dismiss();
			}
		});

		// 创建PopupWindow对象
		if (null == pop) {
			pop = new PopupWindow(shareView, LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT, false);
		}

		// 需要设置此参数，点击外边可消失
		pop.setBackgroundDrawable(new ColorDrawable());
		// 设置点击窗口外边窗口消失
		pop.setOutsideTouchable(true);
		pop.setAnimationStyle(R.style.PopupAnimation);
		// 设置此参数获得焦点否则无法点击
		pop.setFocusable(true);
		pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				// ShareSDK.stopSDK(MainActivity.this); 关闭pop
				Log.e("TAG", "camera pop is dismiss");
				backgroundAlpha(1f);// 背景还原
			}
		});

	}

	public void show() {
		if (null != pop) {
			pop.showAtLocation(view.getRootView(), Gravity.BOTTOM, 0,
					0);
		}else{
			Log.e("TAG", "camera pop is created");
		}
		backgroundAlpha(0.5f);
	}
	public void popClose(){
		if(null!=pop){
			pop.dismiss();
		}
	}

	/**
	 * 调用相机拍照
	 */
	private void onTakeCamera(int i) {

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		/**
		 * 设置图片保存的路径
		 */
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间，进一步转化为字符串
		date = new Date();
		String str = format.format(date);
		fileName = "/sdcard/myImage/" + str + ".jpg";
		Uri photoUri = Uri.fromFile(new File(fileName));
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
		context.startActivityForResult(intent, i);
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
		mHeight = (int) (outMetrics.heightPixels * 0.5);
	}

	// 弹出pop时使背景变暗
	private void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = context.getWindow().getAttributes();
		lp.alpha = bgAlpha;// 0.0-1.0
		context.getWindow().setAttributes(lp);
	}
}
