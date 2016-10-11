package com.tikt.selectphotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by tikt on 15-12-17.
 * 商品评论的图片加载器
 */
public class LocalImageLoader {
	private static LocalImageLoader mInstance;
	/**
	 * 图片缓存的核心对象
	 */
	private LruCache<String, Bitmap> mLruCache;
	/**
	 * 线程池
	 */
	private ExecutorService mThreadPool;
	private static final int DEAFULT_THREAD_COUNT = 1;
	/**
	 * 队列的调度方式
	 */
	private Type mType = Type.LIFO;
	/**
	 * 任务队列
	 */
	private LinkedList<Runnable> mTaskQueue;
	/**
	 * 后台轮询线程
	 */
	private Thread mPoolThread;
	private Handler mPoolThreadHandler;
	/**
	 * UI线程中的Handler;
	 */
	private Handler mUIHandler;

	private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);
	private Semaphore mSemaphoreThreadPool;

	public enum Type {
		FIFO, LIFO
	}

	private LocalImageLoader(int threadCount, Type type) {
		init(threadCount, type);
	}

	/**
	 * 初始化
	 *
	 * @return
	 */
	private void init(int threadCount, Type type) {

		//后台轮询线程
		mPoolThread = new Thread() {
			@Override
			public void run() {

				Looper.prepare();
				mPoolThreadHandler = new Handler() {

					@Override
					public void handleMessage(Message msg) {
						//线程池去去除一个任务进行执行
						mThreadPool.execute(getTask());
						try {
							mSemaphoreThreadPool.acquire();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}


				};
				//释放一个信号量
				mSemaphorePoolThreadHandler.release();
				Looper.loop();
			}
		};
		mPoolThread.start();

		//获取应用最大可用内存
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheMemory = maxMemory / 8;
		mLruCache = new LruCache<String, Bitmap>(cacheMemory) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
		};

		//创建线程池
		mThreadPool = Executors.newFixedThreadPool(threadCount);
		mTaskQueue = new LinkedList<Runnable>();
		mType = type;

		mSemaphoreThreadPool = new Semaphore(threadCount);
	}

	/**
	 * 从任务队列取出一个方法
	 *
	 * @return
	 */
	private Runnable getTask() {
		if (mType == Type.FIFO) {
			return mTaskQueue.removeFirst();
		} else if (mType == Type.LIFO) {
			return mTaskQueue.removeLast();
		}
		return null;
	}

	public static LocalImageLoader getInstance() {
		if (mInstance == null) {
			synchronized (LocalImageLoader.class) {
				if (mInstance == null) {
					mInstance = new LocalImageLoader(DEAFULT_THREAD_COUNT, Type.LIFO);
				}
			}
		}
		return mInstance;
	}
	public static LocalImageLoader getInstance(int threadCount, Type type) {
		if (mInstance == null) {
			synchronized (LocalImageLoader.class) {
				if (mInstance == null) {
					mInstance = new LocalImageLoader(threadCount, type);
				}
			}
		}
		return mInstance;
	}

	/**
	 * 根据path为imageView设置图片
	 *
	 * @param path
	 * @param imageView
	 */
	public void loadImage(final String path, final ImageView imageView) {
		imageView.setTag(path);
		if (mUIHandler == null) {
			mUIHandler = new Handler() {
				public void handleMessage(Message msg) {
					//获取图片，为imageView回调设置图片
					ImgBeanHolder holder = (ImgBeanHolder) msg.obj;
					Bitmap bm = holder.bitmap;
					ImageView imageview = holder.imageView;
					String path = holder.path;

					if (imageview.getTag().toString().equals(path)) {
						imageview.setImageBitmap(bm);
					}

				}
			};
		}

		//根据path在缓存中获取图片
		Bitmap bm = getBitmapFormLruCache(path);

		if (bm != null) {

			refreashBitmap(bm, path, imageView);
		} else {
			addTasks(new Runnable() {
				@Override
				public void run() {
					//加载图片
					//图片压缩
					//1.获得图片需要显示的大小
					ImageSize imageSize = getImageViewSize(imageView);
					//2.压缩图片
					Bitmap bm = decodeSampledBitmapFromPath(path, imageSize.width, imageSize.height);
					//3.把图片加入到缓存
					addBitmapToLruCache(path, bm);

					refreashBitmap(bm, path, imageView);
					mSemaphoreThreadPool.release();
				}


			});
		}
	}

	private void refreashBitmap(Bitmap bm, String path, ImageView imageView) {
		Message message = Message.obtain();
		ImgBeanHolder holder = new ImgBeanHolder();
		holder.bitmap = bm;
		holder.path = path;
		holder.imageView = imageView;
		message.obj = holder;
		mUIHandler.sendMessage(message);
	}

	/**
	 * 将图片加入LruCache
	 *
	 * @param path
	 * @param bm
	 */
	protected void addBitmapToLruCache(String path, Bitmap bm) {

		if (getBitmapFormLruCache(path) == null) {
			if (bm != null) {
				mLruCache.put(path, bm);
			}
		}
	}

	/**
	 * 根据图片需要显示的宽和高对图片进行压缩
	 *
	 * @param path
	 * @param width
	 * @param height
	 * @return
	 */
	protected Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {

		//获得图片的宽和高,并不把图片加载到内存中
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		options.inSampleSize = caculateInSampleSize(options, width, height);

		//使用获得到的InSampleSize再次解析图片
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options);
		return bitmap;
	}

	/**
	 * 根据需求的宽和高以及图片实际的宽和高计算SampleSize
	 *
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	private int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

		int width = options.outWidth;
		int height = options.outHeight;

		int inSampleSize = 1;
		if (width > reqWidth || height > reqHeight) {
			int widthRadio = Math.round(width * 1.0f / reqWidth);
			int heightRadio = Math.round(height * 1.0f / reqHeight);
			inSampleSize = Math.max(widthRadio, heightRadio);
		}
		return inSampleSize;
	}

	/**
	 * 根据ImageView获取适当的压缩宽和高
	 *
	 * @param imageView
	 * @return
	 */
	protected ImageSize getImageViewSize(ImageView imageView) {

		ImageSize imageSize = new ImageSize();

		DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
		ViewGroup.LayoutParams lp = imageView.getLayoutParams();
//		int width = (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT ? 0:imageView.getWidth());

		int width = imageView.getWidth(); //获取imageView的实际宽度

		if (width <= 0) {
			width = lp.width;//获取imageview在layout中声明的宽度
		}
		if (width <= 0) {
			width = getImageViewFieldValue(imageView, "mMaxWidth"); //检查最大值
		}
		if (width <= 0) {
			width = displayMetrics.widthPixels; //最坏的情况,变为屏幕宽度
		}


		int height = imageView.getHeight(); //获取imageView的实际高度
		if (height <= 0) {
			height = lp.height;//获取imageview在layout中声明的高度
		}
		if (height <= 0) {
			height = getImageViewFieldValue(imageView, "mMaxHeight"); //检查最大值
		}
		if (height <= 0) {
			height = displayMetrics.heightPixels; //最坏的情况,变为屏幕高度
		}
		imageSize.width = width;
		imageSize.height = height;
		return imageSize;
	}

	/**
	 * 通过反射获取imageView的某个属性值
	 *
	 * @param object
	 * @param fieldName
	 * @return
	 */
	private static int getImageViewFieldValue(Object object, String fieldName) {

		int value = 0;

		try {

			Field field = ImageView.class.getDeclaredField(fieldName);
			field.setAccessible(true);

			int fieldValue = field.getInt(object);
			if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
				value = fieldValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	private synchronized void addTasks(Runnable runnable) {
		mTaskQueue.add(runnable);
		//if(mPoolThreadHandler==null)wait();
		try {
			if (mPoolThreadHandler == null) {
				mSemaphorePoolThreadHandler.acquire();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mPoolThreadHandler.sendEmptyMessage(0x100);

	}

	/**
	 * 根据path在缓存中获取图片
	 *
	 * @param path
	 * @return
	 */
	private Bitmap getBitmapFormLruCache(String path) {
		return mLruCache.get(path);
	}

	private class ImageSize {
		int width;
		int height;
	}

	private class ImgBeanHolder {
		Bitmap bitmap;
		ImageView imageView;
		String path;
	}
}
