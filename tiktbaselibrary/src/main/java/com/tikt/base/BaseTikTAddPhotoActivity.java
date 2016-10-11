package com.tikt.base;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.tikt.addphotowithgridview.AddPhotosGridView;
import com.tikt.selectphotos.SelectPhotoActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Created by tikt on 16-9-20.
 * 用于添加图片的activity基类
 */

public abstract class BaseTikTAddPhotoActivity extends HttpTikTActivity {

	protected int MY_PERMISSIONS_REQUEST_CAMERA = 10011;
	//	protected String[] photo_uri;  //图片路径
	protected String fileName;//拍摄得到的照片uri
	protected int REQ_Camear = 1101; //调用相机后返回的参数
	protected int REQ_Photos = 1103; //调用相册
	protected boolean hasPhoto = false;//是否添加照片


	/**
	 * 6.0申请相机权限
	 */
	protected void requestCameraAccess() {
		Log.e("TAG", "requestCameraAccess");
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
			//请求权限
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
			//判断是否需要 向用户解释，为什么要申请该权限
			ActivityCompat.shouldShowRequestPermissionRationale(this,
					Manifest.permission.CAMERA);
		} else {
			hasCameraAccess();
		}
	}

	/**
	 * 当完成照片则隐藏添加按钮,和pop
	 */
	protected void onPhotoFinish() {
	}

	/**
	 * 异步执行压缩string数组
	 */
	public class ZipStrsAsyncTask extends AsyncTask<String, Integer, String> {
		String[] needZip_str;

		public ZipStrsAsyncTask(String[] strs) {
			needZip_str = strs;

		}

		@Override
		protected String doInBackground(String... strings) {

			for (int i = 0; i < needZip_str.length; i++) {
				if (!TextUtils.isEmpty(needZip_str[i]) && !needZip_str[i].endsWith("new.jpg")) {
					String new_path = zipPhoto(needZip_str[i]);
					Log.e("TAG", "zip_path==" + new_path);
//					photo_uri[i] = new_path;
				} else {
//					photo_uri[i] = needZip_str[i];
				}


			}
			handler.sendEmptyMessage(100);
//			Log.e("TAG", "photo_uri==" + Arrays.asList(photo_uri));

			return null;
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			ZipStrsIsFinish();
		}
	}

	/**
	 * 数组图片压缩完成
	 */
	protected void ZipStrsIsFinish() {

	}

	/**
	 * 异步压缩单张照片
	 */
	public class ZipStrAsyncTask extends AsyncTask<String, Integer, String> {

		String needZip_str;


		public ZipStrAsyncTask(String str) {
			needZip_str = str;
		}


		@Override
		protected String doInBackground(String... strings) {
			String new_path = zipPhoto(needZip_str);
			Log.e("TAG", "zip_path==" + new_path);
			handler.sendEmptyMessage(100);
			return new_path;
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			ZipStrIsFinish(s);

		}

	}

	/**
	 * 单张图片压缩完成
	 */
	protected void ZipStrIsFinish(String photo) {
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
				case 100:
					hidenLoadingProgress();
					break;
			}


		}
	};


	/**
	 * 压缩原图
	 *
	 * @param old_uri
	 */
	private String zipPhoto(String old_uri) {
		if (null != old_uri) {

			Log.i("TAG", "压缩原图的路径==" + old_uri);
			String str = null;
			Date date = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间，进一步转化为字符串
			date = new Date();
			str = RandomString(2) + format.format(date);
			String new_filename = Environment.getExternalStorageDirectory() + "/myImage/" + str + "_new.jpg";
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			int iv_wid = 0;
			int iv_height = 0;
			try {
				BitmapFactory.decodeStream(new FileInputStream(old_uri), null, o);
				iv_height = o.outHeight;
				iv_wid = o.outWidth;
				Log.i("TAG", "zipPhoto: opts.outWidth=="+o.outWidth);
				Log.i("TAG", "zipPhoto: opts.outHeight=="+o.outHeight);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			FileOutputStream b = null;
			FileInputStream fileInputStream = null;
			Bitmap bitmap = null;
			BitmapFactory.Options opts = new BitmapFactory.Options();
			int hh = 800;//
			int ww = 600;//
			int be = 1;
			if (iv_wid > iv_height && iv_wid > ww) {
				be =  (iv_wid / ww);
			} else if (iv_wid < iv_height && iv_height > hh) {
				be = (iv_height / hh);
			}
			if (be <= 0)
				be = 1;
			Log.i("TAG", "zipPhoto: be=="+be);
			opts.inSampleSize = be;//设置采样率
			opts.inPurgeable = true;// 同时设置才会有效
			opts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
			try {
				fileInputStream = new FileInputStream(old_uri);
			/*
				bitmap 占用内存过多,使用Options
			 */
				bitmap = BitmapFactory.decodeStream(fileInputStream, null, opts);
				b = new FileOutputStream(new_filename);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 60, b);// 把数据写入文件
				bitmap.recycle();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {

					fileInputStream.close();
					b.flush();
					b.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("TAG", "Exception==" + e);
				}
			}

			return new_filename;
		}
		return null;
	}

	/**
	 * 生成随机字符串
	 *
	 * @param length
	 * @return
	 */
	public String RandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < length; i++) {
			int num = random.nextInt(62);
			buf.append(str.charAt(num));
		}

		return buf.toString();
	}

	/**
	 * 删除图片
	 *
	 * @param position
	 */
	protected void removePic(final int position) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				this);
		builder.setMessage("是否删除此图片?");
		builder.setTitle("提示");
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
			}
		});
		builder.setPositiveButton(
				"确认",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
					                    int which) {

						afterRemovePicClick(position);
						dialog.dismiss();

					}
				});

		builder.create().show();
	}

	/**
	 * 确定删除图片
	 */
	protected void afterRemovePicClick(int position) {

	}

	/**
	 * 6.0请求权限的结果
	 *
	 * @param requestCode
	 * @param permissions
	 * @param grantResults
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		//权限申请结果
		if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
			for (int index = 0; index < permissions.length; index++) {
				switch (permissions[index]) {
					case Manifest.permission.CAMERA:
						if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
							/**用户已经受权*/
							hasCameraAccess();
						} else if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
							/**用户拒绝了权限*/
							showToast(this, "应用没有拍照权限，请授权！");
						} else {
							showToast(this, "应用没有拍照权限，请授权！");
						}
						break;
					case Manifest.permission.WRITE_EXTERNAL_STORAGE:
						if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
							/**用户已经受权*/
//                            toSelectPhoto(mActivity);
						} else if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
							/**用户拒绝了权限*/
							showToast(this, "应用没有访问相册的权限，请授权！");
						} else {
							showToast(this, "应用没有访问相册的权限，请授权！");
						}

						break;
					case Manifest.permission.RECORD_AUDIO:
						if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
							/**用户已经受权*/
							//由用户重新按住录音
//							showToast(mActivity, "现在长按可以录音了");
						} else if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
							/**用户拒绝了权限*/
							showToast(this, "应用没有访问麦克风的权限，请授权！");
						} else {
							showToast(this, "应用没有访问麦克风的权限，请授权！");
						}

						break;
				}
			}
		}
	}


	/**
	 * 获得了相机权限
	 */
	protected void hasCameraAccess() {
		onTakeCamera(REQ_Camear);
	}

	/**
	 * 调用相机拍照
	 */
	protected void onTakeCamera(int i) {


		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
			Log.e("TAG", "SD card is not avaiable/writeable right now.");
			Toast.makeText(this, "存储卡不可用", Toast.LENGTH_SHORT).show();
		} else {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			/*
			创建myImage文件夹
			 */
			File file = new File(Environment.getExternalStorageDirectory() + "/myImage/");
			if (!file.exists()) {
				file.mkdirs();
			}
			/*
			* 设置图片保存的路径
			*/
			Date date = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间，进一步转化为字符串
			date = new Date();
			String str = format.format(date);
			fileName = Environment.getExternalStorageDirectory() + "/myImage/" + str + ".jpg";
			Log.i("Tag", "photo fileName==" + fileName);
			Uri photoUri = Uri.fromFile(new File(fileName));
			intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
			startActivityForResult(intent, i);
			Log.e("TAG", "SD card is ok");
		}
	}

	/**
	 * 显示拍摄的照片并刷新列表
	 * <p>
	 * fileName     拍摄获得的照片路径
	 *
	 * @param gridView    需要刷新的组件
	 * @param refreshlist 需要刷新的图片列表
	 */
	protected void showPhoto(AddPhotosGridView gridView, List<String> refreshlist) {

		refreshlist.add(fileName);
		gridView.refreshImgsList(refreshlist);
		hasPhoto = true;

		ZipStrAsyncTask task = new ZipStrAsyncTask(fileName);
		task.execute();

		showLoadingProgress(this);

	}

	/**
	 * 从相册中选择图片
	 */
	protected void toSelectPhotos(String[] photos) {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
			Log.e("TAG", "SD card is not avaiable/writeable right now.");
			showToast(this, "存储卡不可用");
			return;
		} else {
			/*
			创建myImage文件夹
			 */
			File file = new File(Environment.getExternalStorageDirectory() + "/myImage/");
			if (!file.exists()) {
				file.mkdirs();
				Log.e("TAG", "file.exists()==" + file.mkdirs());
			}

			Bundle bundle = new Bundle();
			int count = 0;
			for (int i = 0; i < photos.length; i++) {
				if (TextUtils.isEmpty(photos[i])) {
					count += 1;
					Log.i("Tag", "count==" + count);
				}
			}
			bundle.putInt("count", count);
			bundle.putStringArray("pic_list", photos);
			Intent intent = new Intent(this, SelectPhotoActivity.class);
			intent.putExtras(bundle);
			startActivityForResult(intent, REQ_Photos);
		}
	}
}
