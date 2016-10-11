package com.tikt.alipay;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tikt.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by tikt on 16-1-11.
 * 支付宝调用工具
 */
public class AlipayTool {
	// 商户PID
	private String PARTNER = Constants.PARTNER;
	// 商户收款账号
	private String SELLER = Constants.SELLER;
	// 商户私钥，pkcs8格式
	private String RSA_PRIVATE = Constants.RSA_PRIVATE;
	// 支付宝公钥
	private String RSA_PUBLIC = Constants.RSA_PUBLIC;
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_CHECK_FLAG = 2;
	private String URL;//支付结果回调地址
	private Activity activity;
	private String order;
	private String order_price;
	private int type = -1;//调用时判断的依据
	private String good_name = "支付宝";//订单名
	private String good_detail = "订单";//订单详情
	final private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
				case SDK_PAY_FLAG: {
					PayResult payResult = new PayResult((String) msg.obj);

					// 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
//					String resultInfo = payResult.getResult();

					String resultStatus = payResult.getResultStatus();
					Log.e("Tag", "resultStatus+++" + resultStatus);
					// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
					if (TextUtils.equals(resultStatus, "9000")) {
						Builder builder = new Builder(
								activity);
						builder.setMessage("支付成功！");
						if (null != payIsSuccessListener) {
							payIsSuccessListener.success();
						}
						builder.setTitle("提示");
						builder.setPositiveButton(
								"确认",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
									                    int which) {
										dialog.dismiss();
										if (null != payIsSuccessListener) {
											payIsSuccessListener.aftersuccess();
										}

									}
								});
						builder.create().show();

					} else {
						// 判断resultStatus 为非“9000”则代表可能支付失败
						// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
						if (TextUtils.equals(resultStatus, "8000")) {
							Toast.makeText(activity, "支付结果确认中",
									Toast.LENGTH_SHORT).show();

						} else {
							// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
							// Toast.makeText(AlipayActivity.this, "支付失败",
							// Toast.LENGTH_SHORT).show();
							Builder builder = new Builder(
									activity);
							builder.setMessage("支付失败！");
							builder.setTitle("提示");
							builder.setPositiveButton(
									"确认",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog,
										                    int which) {

											dialog.dismiss();

										}
									});
							builder.create().show();
						}
					}
					break;
				}
				case SDK_CHECK_FLAG: {
//					 Toast.makeText(PayOrderActivity.this, "检查结果为：" + msg.obj,
//					 Toast.LENGTH_SHORT).show();
					break;
				}
				default:
					break;
			}
		}

	};

	//提供支付成功之后的回调操作
	public interface payResultListener {
		void success();

		void aftersuccess();

		void failure();
	}

	private payResultListener payIsSuccessListener;

	public void setPayResultListener(payResultListener listener) {
		payIsSuccessListener = listener;
	}

	public AlipayTool(Activity activity) {
		this.activity = activity;
	}

	public AlipayTool(Activity activity, String url, String orderid, String order_price) {
		this.URL = url;
		this.order = orderid;
		this.order_price = order_price;
		this.activity = activity;
	}

	public AlipayTool(Activity activity, String url, String orderid, String order_price, int type) {
		this.order = orderid;
		this.URL = url;
		this.order_price = order_price;
		this.activity = activity;
		this.type = type;
	}

	public AlipayTool(Activity activity, String url, String orderid, String order_price, String good_name) {
		this.order = orderid;
		this.URL = url;
		this.order_price = order_price;
		this.activity = activity;
		this.good_name = good_name;
	}

	public AlipayTool(Activity activity, String url, String orderid, String order_price, String good_name, String good_detail) {
		this.order = orderid;
		this.URL = url;
		this.order_price = order_price;
		this.activity = activity;
		this.good_name = good_name;
		this.good_detail = good_detail;

	}

	/**
	 * call alipay sdk pay. 调用SDK支付
	 * 此方法需要各种支付参数
	 */
	public void pay() {
		/**
		 * check whether the device has authentication alipay account.
		 * 查询终端设备是否存在支付宝认证账户
		 *
		 */
		Runnable checkRunnable = new Runnable() {
			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask payTask = new PayTask(activity);
				// 调用查询接口，获取查询结果
				boolean isExist = payTask.checkAccountIfExist();
//				Log.i("Tag", "isExist+++++" +isExist );

				Message msg = new Message();
				msg.what = SDK_CHECK_FLAG;
				msg.obj = isExist;
				mHandler.sendMessage(msg);
			}
		};

		Thread checkThread = new Thread(checkRunnable);
		checkThread.start();
//		order = getOutTradeNo();
		// 订单
		Log.e("Tag", "从服务器返回的订单号====" + order);
		String orderInfo = getOrderInfo(good_name, good_detail, order, order_price);
//		String orderInfo = getOrderInfo("支付宝", "倍滋氏订单",  "0.01");

		// 对订单做RSA 签名
		String sign = sign(orderInfo);
		Log.i("Tag", "sign+++" + sign);

		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ getSignType();
		Log.i("Tag", "payInfo+++" + payInfo);
		Runnable payRunnable = new Runnable() {
			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(activity);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	/**
	 * 此方法只需要payInfo参数,目前使用此方法
	 *
	 * @param payInfo
	 */
	public void pay(final String payInfo) {
		/**
		 * check whether the device has authentication alipay account.
		 * 查询终端设备是否存在支付宝认证账户
		 *
		 */
		Runnable checkRunnable = new Runnable() {
			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask payTask = new PayTask(activity);
				// 调用查询接口，获取查询结果
				boolean isExist = payTask.checkAccountIfExist();
//				Log.i("Tag", "isExist+++++" +isExist );

				Message msg = new Message();
				msg.what = SDK_CHECK_FLAG;
				msg.obj = isExist;
				mHandler.sendMessage(msg);
			}
		};

		Thread checkThread = new Thread(checkRunnable);
		checkThread.start();
//		order = getOutTradeNo();
		// 订
		Runnable payRunnable = new Runnable() {
			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(activity);
				String result = null;
				// 调用支付接口，获取支付结果
				try {
					result = alipay.pay(payInfo);
				} catch (NullPointerException e) {
					Log.e("TAG", "result is null");
//					return;
				}
				Log.e("TAG", "result is ==" + result);
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	//返回支付结果,供activity使用
	public String payWithResult() {
		Runnable checkRunnable = new Runnable() {
			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask payTask = new PayTask(activity);
				// 调用查询接口，获取查询结果
				boolean isExist = payTask.checkAccountIfExist();
				Message msg = new Message();
				msg.what = SDK_CHECK_FLAG;
				msg.obj = isExist;
				mHandler.sendMessage(msg);
			}
		};

		Thread checkThread = new Thread(checkRunnable);
		checkThread.start();
//		order = getOutTradeNo();
		// 订单
		Log.e("Tag", "从服务器返回的订单号====" + order);
		String orderInfo = getOrderInfo(good_name, good_detail, order, order_price);

		// 对订单做RSA 签名
		String sign = sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ getSignType();
		Log.i("Tag", "payInfo+++" + payInfo);
		Runnable payRunnable = new Runnable() {
			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(activity);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo);
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
		return null;
	}

	/**
	 * get the sdk version. 获取SDK版本号
	 */
	public void getSDKVersion() {
		PayTask payTask = new PayTask(activity);
		String version = payTask.getVersion();
		Toast.makeText(activity, version, Toast.LENGTH_SHORT).show();
	}

	/**
	 * create the order info. 创建订单信息
	 */
	public String getOrderInfo(String subject, String body, String orderid, String price) {
		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号
		// orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
		orderInfo += "&out_trade_no=" + "\"" + orderid + "\"";
		// //订单号改成由服务器发送，不在本地生成
		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + URL
				+ "/pay/notifyforalipay" + "\"";
		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
//		 orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 */
	public String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);

		Log.i("Tag", "key+++" + key);

		return key;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 *
	 * @param content 待签名订单信息
	 */
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}


}