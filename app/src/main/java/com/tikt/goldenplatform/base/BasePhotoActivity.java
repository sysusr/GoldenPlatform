package com.tikt.goldenplatform.base;

import android.content.Context;
import android.content.Intent;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.tikt.base.BaseTikTAddPhotoActivity;

/**
 * Created by tikt on 16-10-8.
 */

public abstract class BasePhotoActivity extends BaseTikTAddPhotoActivity {

	protected KProgressHUD HUDprogress;
	protected String TAG = "TAG";

	@Override
	protected void showLoadingProgressWithStr(Context context, String msg) {
		if (null == HUDprogress) {
			HUDprogress = KProgressHUD.create(context)
					.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
					.setLabel(msg)
					.setCancellable(false)
					.setDimAmount(0.5f);
		} else if (HUDprogress.isShowing()) {
			return;
		}
		HUDprogress.show();
	}

	@Override
	protected void showLoadingProgress(Context context) {
		if (null == HUDprogress) {
			HUDprogress = KProgressHUD.create(context)
					.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
					.setCancellable(false)
					.setDimAmount(0.5f);
		} else if (HUDprogress.isShowing()) {
			return;
		}
		HUDprogress.show();
	}

	@Override
	protected void hidenLoadingProgress() {
		if (null != HUDprogress && HUDprogress.isShowing())
			HUDprogress.dismiss();
	}

	/**
	 * 无参跳转Activity
	 * @param activity
	 */
	protected void toActivity(Class activity){
		Intent intent = new Intent(this,activity);
		startActivity(intent);
	}
}
