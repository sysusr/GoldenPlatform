package com.tikt.selectphotos;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ShowBigPhotoActivity extends BaseSelectPhotoActivity {


	TextView idTopTitle;
	LinearLayout idTopLeft;
	ImageView idShowbigphotoactivityImg;
	private String mImageUrl;

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.activity_show_big_photo;
	}

	@Override
	protected void initView() {
		super.initView();
		idTopTitle = (TextView) findViewById(R.id.id_selectphoto_top_title);
		idTopLeft = (LinearLayout) findViewById(R.id.id_selectphoto_top_left);
		idShowbigphotoactivityImg = (ImageView) findViewById(R.id.id_showbigphotoactivity_img);
		idTopLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void initEvent() {
		idTopTitle.setText("图片预览");
		Bundle bundle = this.getIntent().getExtras();
		mImageUrl = bundle.getString("img");
		LocalImageLoader.getInstance(3, LocalImageLoader.Type.LIFO).
				loadImage(mImageUrl, idShowbigphotoactivityImg);

	}

	@Override
	protected void initViewsAndEvents() {
		initView();
		initEvent();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (isFinishing()) {
			overridePendingTransition(0, 0);
		}
	}

	@Override
	public void onBackPressed() {
//		idShowbigphotoactivityImg.transformOut();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Need to call clean-up
//		mAttacher.cleanup();
	}
}
