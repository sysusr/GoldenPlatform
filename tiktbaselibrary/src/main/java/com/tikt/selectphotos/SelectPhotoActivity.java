package com.tikt.selectphotos;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tikt
 *         相册选取图片
 */
public class SelectPhotoActivity extends BaseSelectPhotoActivity implements View.OnClickListener {

	private boolean hasScanPhoto = true;//扫描到了图片
	private GridView mGridView;
	private ImageAdapter mImgAdapter;
	private TextView tv_title;
	private TextView tv_add;
	private ImageButton iv_back;
	private LinearLayout ll_right;
	private List<String> mImgs;
	private RelativeLayout mBottom;
	private TextView mDirName;
	private TextView mPhotoCount;
	private File mCurrentDir;
	private int mMaxCount;//
	private List<FolderBean> mFolderBeans = new ArrayList<>();
	private ProgressDialog mProgressDialog;
	private String[] pic_list;
	private int countOfEmpty;//剩余的图片添加数量
	private int MAX_SELECT = 3;//最大能选择图片的数量
	private int countOfSelect = 0;//选择的图片数量
	private static final int DATA_LOADED = 0x110;
	protected int REQ_Photos = 1103; //调用相册
	private ListImageDirPopupWindow mDirPopupWindow;
	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {

			if (msg.what == DATA_LOADED) {
				mProgressDialog.dismiss();
				//绑定数据到View中
				dataToView();

				initDirPopupWindow();
			}
		}
	};

	protected void initDirPopupWindow() {

		mDirPopupWindow = new ListImageDirPopupWindow(this, mFolderBeans);
		mDirPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				lightOn();
			}
		});

		mDirPopupWindow.setOnDirSelectedListener(new ListImageDirPopupWindow.OnDirSelectedListener() {
			@Override
			public void onSelected(FolderBean folderBean) {
				mCurrentDir = new File(folderBean.getDir()); //更新文件夹
				mImgs = Arrays.asList(mCurrentDir.list(new FilenameFilter() { //更新图片
					@Override
					public boolean accept(File file, String filename) {
//						if (!file.isDirectory()) {
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".jpeg")
									|| filename.endsWith(".png")) {
								return true;
							}
//						}
						return false;

					}
				}));
				mImgAdapter = new ImageAdapter(SelectPhotoActivity.this, pic_list, countOfEmpty, mImgs, mCurrentDir.getAbsolutePath());
				mGridView.setAdapter(mImgAdapter);
				mImgAdapter.setOnItemClick(new ImageAdapter.OnItemClickListener() {
					@Override
					public void onClick(View view, String url) {
						Rect frame = new Rect();
						SelectPhotoActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
						int statusBarHeight = frame.top;
						int[] location = new int[2];
						view.getLocationOnScreen(location);
						location[1] += statusBarHeight;

						int width = view.getWidth();
						int height = view.getHeight();
						Intent intent = new Intent(SelectPhotoActivity.this, ShowBigPhotoActivity.class);
						Bundle extras = new Bundle();
						extras.putString("img", url);
						extras.putInt("INTENT_IMAGE_X_TAG", location[0]);
						extras.putInt("INTENT_IMAGE_Y_TAG", location[1]);
						extras.putInt("INTENT_IMAGE_W_TAG", width);
						extras.putInt("INTENT_IMAGE_H_TAG", height);
						intent.putExtras(extras);
						startActivity(intent);
						SelectPhotoActivity.this.overridePendingTransition(0, 0);
					}

					@Override
					public void onClickSelect(int type) {//1:增加,2:减少
						if (1 == type) {
							countOfSelect++;
							tv_add.setText("(" + countOfSelect + "/" + MAX_SELECT + ")确定");
						} else {
							countOfSelect--;
							tv_add.setText("(" + countOfSelect + "/" + MAX_SELECT + ")确定");
						}

					}
				});
				mPhotoCount.setText(mImgs.size() + "");
				mDirName.setText(folderBean.getName());

				mDirPopupWindow.dismiss();
			}
		});
	}

	/**
	 * 背景变亮
	 */
	private void lightOn() {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 1.0f;
		getWindow().setAttributes(lp);
	}

	private void dataToView() {
		if (mCurrentDir == null) {
			Toast.makeText(this, "未扫描到任何图片", Toast.LENGTH_SHORT).show();
			hasScanPhoto = false;
			return;
		}

//		mImgs = Arrays.asList(mCurrentDir.list());
		mImgs = Arrays.asList(mCurrentDir.list(new FilenameFilter() { //更新图片
			@Override
			public boolean accept(File file, String filename) {
//				if (!file.isDirectory()) {
					if (filename.endsWith(".jpg")
							|| filename.endsWith(".jpeg")
							|| filename.endsWith(".png")) {
						return true;
					}
//				}
				return false;

			}
		}));
		mImgAdapter = new ImageAdapter(this, pic_list, countOfEmpty, mImgs, mCurrentDir.getAbsolutePath());
		mGridView.setAdapter(mImgAdapter);
		mImgAdapter.setOnItemClick(new ImageAdapter.OnItemClickListener() {
			@Override
			public void onClick(View view, String url) {
				Rect frame = new Rect();
				SelectPhotoActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
				int statusBarHeight = frame.top;
				int[] location = new int[2];
				view.getLocationOnScreen(location);
				location[1] += statusBarHeight;

				int width = view.getWidth();
				int height = view.getHeight();
				Intent intent = new Intent(SelectPhotoActivity.this, ShowBigPhotoActivity.class);
				Bundle extras = new Bundle();
				extras.putString("img", url);
				extras.putInt("INTENT_IMAGE_X_TAG", location[0]);
				extras.putInt("INTENT_IMAGE_Y_TAG", location[1]);
				extras.putInt("INTENT_IMAGE_W_TAG", width);
				extras.putInt("INTENT_IMAGE_H_TAG", height);
				intent.putExtras(extras);
				startActivity(intent);
				SelectPhotoActivity.this.overridePendingTransition(0, 0);
			}

			@Override
			public void onClickSelect(int type) {
				if (1 == type) {
					countOfSelect++;
					tv_add.setText("(" + countOfSelect + "/" + MAX_SELECT + ")确定");
				} else {
					countOfSelect--;
					tv_add.setText("(" + countOfSelect + "/" + MAX_SELECT + ")确定");
				}
			}
		});
		mPhotoCount.setText(mMaxCount + "");
		mDirName.setText(mCurrentDir.getName());
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.activity_select_photo;
	}

	/**
	 * 利用ContentProvider扫描手机中的所有图片
	 */
	private void initDatas() {
		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "当前存储卡不可用", Toast.LENGTH_SHORT).show();
			hasScanPhoto = false;
			return;
		}
		mProgressDialog = ProgressDialog.show(this, null, "正在加载...");
		new Thread() {
			public void run() {
				Uri mImgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver cr = SelectPhotoActivity.this.getContentResolver();
				Cursor cursor = cr.query(mImgUri, null, MediaStore.Images.Media.MIME_TYPE
								+ " = ? or " + MediaStore.Images.Media.MIME_TYPE
								+ " = ? ", new String[]{"image/jpeg", "image/png"},
						MediaStore.Images.Media.DATE_MODIFIED);

				Set<String> mDirPaths = new HashSet<>();
				while (cursor.moveToNext()) {
					String path = cursor.getString(cursor.
							getColumnIndex(MediaStore.Images.Media.DATA));
					File parentFile = new File(path).getParentFile();
					if (parentFile == null) {
						continue;
					}
					String dirPath = parentFile.getAbsolutePath();
					FolderBean folderBean = null;

					if (mDirPaths.contains((dirPath))) {
						continue;
					} else {
						mDirPaths.add(dirPath);
						folderBean = new FolderBean();
						folderBean.setDir(dirPath);
						folderBean.setFirstImgPath(path);
					}

					if (parentFile.list() == null) {
						continue;
					}
					//文件夹中图片的数量
					int picSize = parentFile.list(new FilenameFilter() {
						@Override
						public boolean accept(File file, String filename) {
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".jpeg")
									|| filename.endsWith(".png")) {
								return true;
							}
							return false;
						}
					}).length;
					folderBean.setCount(picSize);
					mFolderBeans.add(folderBean);
					if (picSize > mMaxCount) {
						mMaxCount = picSize;
						mCurrentDir = parentFile;
					}


				}
				cursor.close();
				//扫描完成，释放临时变量的内存
//				mDirPaths = null; //变量在方法中，方法结束会自动回收
				/**
				 * 通知Handler扫描完成
				 */
				mHandler.sendEmptyMessage(DATA_LOADED);
			}
		}.start();
	}

	@Override
	protected void initView() {
		tv_title = (TextView) findViewById(R.id.id_selectphoto_top_title);
		tv_add = (TextView) findViewById(R.id.id_selectphoto_top_rightText);
		ll_right = (LinearLayout) findViewById(R.id.id_selectphoto_top_right);
		iv_back = (ImageButton) findViewById(R.id.id_selectphoto_top_back);
		mGridView = (GridView) findViewById(R.id.id_selectphoto_gridview);
		mBottom = (RelativeLayout) findViewById(R.id.id_selectphoto_bottom);
		mDirName = (TextView) findViewById(R.id.id_selectphoto_dirname);
		mPhotoCount = (TextView) findViewById(R.id.id_selectphoto_photocount);

	}

	@Override
	protected void initEvent() {
		Bundle bundle = this.getIntent().getExtras();
		pic_list = bundle.getStringArray("pic_list");
		countOfEmpty = bundle.getInt("count");
		MAX_SELECT = pic_list.length;//3:发图片,1:修改头像,修改背景
		countOfSelect = MAX_SELECT - countOfEmpty;//已选择图片的数量

		initDatas();
		tv_title.setText("选择图片");
		tv_add.setText("(" + countOfSelect + "/" + MAX_SELECT + ")确定");
		iv_back.setVisibility(View.VISIBLE);
		tv_add.setVisibility(View.VISIBLE);
		ll_right.setOnClickListener(this);
		iv_back.setOnClickListener(this);
		mBottom.setOnClickListener(this);

	}

	@Override
	protected void initViewsAndEvents() {

		initView();
		initEvent();
	}

	@Override
	public void onClick(View view) {
		int i = view.getId();
		if (i == R.id.id_selectphoto_top_back) {
			finish();
		} else if (i == R.id.id_selectphoto_bottom) {
			if (hasScanPhoto) {
				mDirPopupWindow.setAnimationStyle(R.style.PopupAnimation);
				mDirPopupWindow.showAsDropDown(mBottom, 0, 0);
				lightOff();
			} else {
				Toast.makeText(this, "未扫描到任何图片", Toast.LENGTH_SHORT).show();
			}

		} else if (i == R.id.id_selectphoto_top_right) {
			Intent mIntent = new Intent();
			mIntent.putExtra("pic_list", ImageAdapter.pic_list);
			setResult(REQ_Photos, mIntent);
			finish();
		}

	}

	/**
	 * 背景变暗
	 */
	private void lightOff() {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.3f;
		getWindow().setAttributes(lp);
	}


}
