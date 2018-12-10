package com.lwx.multifunctiondemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

/**
 * 获得屏幕相关的辅助类
 */
public class ScreenUtils {
	private ScreenUtils() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}
	 // 屏幕宽度（像素）
    public static  int getWindowWidth(Activity context){
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    // 屏幕高度（像素）
    public static int getWindowHeight(Activity activity){
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }
      // 将px值转换为sp值
  	public static int px2sp(Context context, float pxValue) {
  		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
  		return (int) (pxValue / fontScale + 0.5f);
  	}

      // 将sp值转换为px值
  	public static int sp2px(Context context, float spValue) {
  		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
  		return (int) (spValue * fontScale + 0.5f);
  	}
	/**
	 * 获得屏幕的高度和宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int[] getScreenSize(Context context) {
		int[] screenSize = new int[2];
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		screenSize[0] = outMetrics.widthPixels;
		screenSize[1] = outMetrics.heightPixels;
		return screenSize;
	}

	/** 获取导航栏的高度 */
	public static int getNavigationBarHeight(Activity activity) {
		Resources resources = activity.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		// 获取NavigationBar的高度
		int height = resources.getDimensionPixelSize(resourceId);
		return height;
	}

	/** 判断是否有物理返回键 */
	public static boolean checkDeviceHasNavigationBar(Context activity) {

		// 通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
		boolean hasMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey();
		boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

		if (!hasMenuKey && !hasBackKey) {
			// 做任何你需要做的,这个设备有一个导航栏
			return true;
		}
		return false;
	}

	/**
	 * 获得状态栏的高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusHeight(Context context) {

		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

	/**
	 * 获取当前屏幕截图，包含状态栏
	 * 
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithStatusBar(Activity activity) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		int width = getScreenSize(activity)[0];
		int height = getScreenSize(activity)[1];
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
		view.destroyDrawingCache();
		return bp;

	}

	/**
	 * 获取当前屏幕截图，不包含状态栏
	 * 
	 * @param activity
	 * @return
	 */
	public static Bitmap snapShotWithoutStatusBar(Activity activity) {
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		int width = getScreenSize(activity)[0];
		int height = getScreenSize(activity)[1];
		Bitmap bp = null;
		bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
		view.destroyDrawingCache();
		return bp;

	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	/**
     * 获取屏幕中控件底部位置的高度--即控件底部的Y点
     * 
     * @return
     */
    public static int getScreenViewBottomHeight(View view) {
        return view.getBottom();
    }
    
    /**
     * 获取deviceId
     * 
     * @param mContext
     * @return
     */
    public static String getDeviceId(Context mContext)
    {
        String deviceId = getIMEI(mContext);
        
        if (deviceId == null || deviceId.equals(""))
        {
            deviceId = Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID);
            if (deviceId == null || deviceId.equals("") || deviceId.equals("9774d56d682e549c"))
            {
                deviceId = "";
            }
        }

         return deviceId;
    }
    
    /**
     * 获取IMEI <一句话功能简述>
     * 
     * @Description<功能详细描述>
     * 
     * @param mContext
     * @return
     * @LastModifiedDate：2015-1-23
     * @author ren_qiujing
     * @EditHistory：<修改内容><修改人>
     */
    public static String getIMEI(Context mContext)
    {
        TelephonyManager mTm = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTm != null)
        {
            return mTm.getDeviceId();
        }
        return "";
    }
}
