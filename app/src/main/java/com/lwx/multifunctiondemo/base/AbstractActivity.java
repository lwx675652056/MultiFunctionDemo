package com.lwx.multifunctiondemo.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.utils.ActivityManager;
import com.lwx.multifunctiondemo.utils.Constants;
import com.yanzhenjie.sofia.Sofia;

/**
 * @description: 基类Activity
 * @date 2016-4-13 下午13:44:47
 */
public abstract class AbstractActivity extends FragmentActivity {

	protected ViewGroup main_view, title_parentlayout;// 内容主题，标题栏
	public View abstractView;// view_main的布局对象
	protected TextView title_lefttextview, title_centertextview, title_righttextview;// 标题栏的文字
	protected ImageView title_leftimageview, title_centerimageview, title_rightimageview;// 标题的图标
	protected View title_bottomline;// 标题栏的底部横线
	// 上下文
	protected Context mContext;
	protected Activity mActivity;
	protected Application mApplication;
	protected FrameLayout framelayout_title_leftimageview;//左侧图片点击区域

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setImmersionStatus();
		setContentView(R.layout.activity_view_main);
		initActivityViews();
		setTitleSize();
		setTitleEvent();
		ActivityManager.getInstance().pushActivity(this);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);// 默认不弹出软键盘
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	/**
	 * @methods setImmersionStatus
	 * @description 设置浸入式的状态栏
	 */
	public void setImmersionStatus() {
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			Sofia.with(this)
					.statusBarDarkFont()//状态栏浅色字体
					.invasionStatusBar()//内容入侵状态栏
					.navigationBarBackground(ContextCompat.getColor(this, R.color.black))//导航栏背景色
					.statusBarBackground(Color.TRANSPARENT);//状态栏背景色
			// 透明状态栏
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			// 透明导航栏
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}

	/**
	 * @methods setTitleVisible
	 * @description 设置title栏的可见性
	 * @date 2015-11-17 下午5:08:26
	 * @param flag
	 *            参数说明
	 */
	protected void setTitleVisible(boolean flag) {
		if (flag)
			title_parentlayout.setVisibility(View.VISIBLE);
		else
			title_parentlayout.setVisibility(View.GONE);

	}
	/**设置标题栏背景颜色**/
	protected void setTitleBackground(int color) {
			title_parentlayout.setBackgroundColor(color);
	}
	/**设置标题文字颜色**/
	protected void setTitleTextColor(int color){
		title_centertextview.setTextColor(color);
	}
	/**设置点击左侧图片**/
	private void setTitleEvent() {
		framelayout_title_leftimageview.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	/**
	 * @methods setTitleLeftImageVisible
	 * @description 设置左侧的图片是否可见
	 * @date 2015-8-7 下午6:10:19 参数说明
	 */
	protected void setTitleLeftImageVisible(boolean isImageVisible) {
		if (isImageVisible)
			framelayout_title_leftimageview.setVisibility(View.VISIBLE);
		else
			framelayout_title_leftimageview.setVisibility(View.GONE);
	}
	/**
	 * @methods setTitleLeftImageVisible
	 * @description 设置左侧的文字是否可见
	 * @date 2015-8-7 下午6:10:19 参数说明
	 */
	protected void setTitleLeftTextVisible( boolean isTextVisible) {
		if (isTextVisible)
			title_lefttextview.setVisibility(View.VISIBLE);
		else
			title_lefttextview.setVisibility(View.GONE);
	}

	/**
	 * @methods setTitleCenterImageVisible
	 * @description 设置中间文字是否可见
	 * @date 2015-8-7 下午6:10:19 参数说明
	 */
	protected void setTitleCenterTextVisible(boolean isTextVisible) {
		if (isTextVisible) {
			title_centertextview.setVisibility(View.VISIBLE);
		} else {
			title_centertextview.setVisibility(View.GONE);
		}
	}

	/**
	 * @methods setTitleCenterImageVisible
	 * @description 设置中间图片是否可见
	 * @date 2015-8-7 下午6:10:19 参数说明
	 */
	protected void setTitleCenterImageVisible(boolean isImageVisible) {
		if (isImageVisible) {
			title_centerimageview.setVisibility(View.VISIBLE);
		} else {
			title_centerimageview.setVisibility(View.GONE);
		}
	}

	/**
	 * @methods setTitleRightImageVisible
	 * @description 设置右侧的图片是否可见
	 * @date 2015-8-7 下午6:10:19 参数说明
	 */
	protected void setTitleRightImageVisible(boolean isImageVisible) {
		if (isImageVisible) {
			title_rightimageview.setVisibility(View.VISIBLE);
		} else {
			title_rightimageview.setVisibility(View.GONE);
		}
	}
	/**
	 * @methods setTitleRightImageVisible
	 * @description 设置右侧的文字是否可见
	 * @date 2015-8-7 下午6:10:19 参数说明
	 */
	protected void setTitleRightTextVisible(boolean isTextVisible) {
		if (isTextVisible) {
			title_righttextview.setVisibility(View.VISIBLE);
		} else {
			title_righttextview.setVisibility(View.GONE);
		}
	}

	/**
	 * @methods setTitleLeftTextView
	 * @description 设置左侧的文字
	 * @date 2015-6-2 上午10:52:06
	 * @param titleFonts
	 *            参数说明
	 */
	protected void setTitleLeftTextView(String titleFonts) {
		if (titleFonts!=null&&!"".equals(titleFonts)){
			title_lefttextview.setVisibility(View.VISIBLE);
			title_lefttextview.setText(titleFonts);
		}
	}

	/**
	 * @methods setTitleCenterTextView
	 * @description 设置中间侧的文字
	 * @date 2015-6-2 上午10:52:06
	 * @param titleFonts
	 *            参数说明
	 */
	protected void setTitleCenterTextView(String titleFonts) {
		title_centertextview.setText(titleFonts);
	}

	/**
	 * @methods setTitleRightTextView
	 * @description 设置右侧的文字
	 * @date 2015-6-2 上午10:52:06
	 * @param titleFonts
	 *            参数说明
	 */
	protected void setTitleRightTextView(String titleFonts) {
		if (titleFonts!=null&&!"".equals(titleFonts)){
			title_righttextview.setVisibility(View.VISIBLE);
			title_righttextview.setText(titleFonts);
		}
	}

	/**
	 * @methods setTitleLeftImageView
	 * @description 设置左侧的图标
	 * @date 2015-6-2 上午10:54:32
	 * @param iamgeID
	 *            参数说明
	 */
	protected void setTitleLeftImageView(int iamgeID) {
		title_leftimageview.setImageResource(iamgeID);
	}

	/**
	 * @methods setTitleCenterImageView
	 * @description 设置中间的图标
	 * @date 2015-6-2 上午10:54:32
	 * @param iamgeID
	 *            参数说明
	 */
	protected void setTitleCenterImageView(int iamgeID) {
		title_centerimageview.setVisibility(View.VISIBLE);
		title_centerimageview.setImageResource(iamgeID);
	}

	/**
	 * @methods setTitleRightImageView
	 * @description 设置右侧的图标
	 * @date 2015-6-2 上午10:54:32
	 * @param
	 *
	 */
	protected void setTitleRightImageView(int iamgeID) {
		title_rightimageview.setVisibility(View.VISIBLE);
		title_rightimageview.setImageResource(iamgeID);
	}

	
	/**
	 * @methods setTitleBottomLineisVisible
	 * @description 设置title底部的线是否可见
	 * @date 2015-6-2 上午11:09:29 参数说明
	 */
	protected void setTitleBottomLineisVisible(boolean isVisible) {
		if (isVisible) {
			title_bottomline.setVisibility(View.VISIBLE);
		} else {
			title_bottomline.setVisibility(View.INVISIBLE);
		}

	}

	/**
	 * @methods setTitleSize
	 * @description 设置标题的高度
	 * @date 2015-6-2 上午10:36:56 参数说明
	 */
	private void setTitleSize() {
		LayoutParams params = title_parentlayout.getLayoutParams();
		params.height = (int) (Constants.screenHeight * 0.06563);
		title_parentlayout.setLayoutParams(params);
	}

	// 初始化对象
	private void initActivityViews() {
		mContext = this;
		mActivity = this;
		mApplication = getApplication();
		title_parentlayout = (ViewGroup) findViewById(R.id.title_parentlayout);
		main_view = (ViewGroup) findViewById(R.id.activity_main_view);
		title_lefttextview = (TextView) findViewById(R.id.title_lefttextview);
		title_centertextview = (TextView) findViewById(R.id.title_centertextview);
		title_righttextview = (TextView) findViewById(R.id.title_righttextview);
		title_leftimageview = (ImageView) findViewById(R.id.title_leftimageview);
		title_centerimageview = (ImageView) findViewById(R.id.title_centerimageview);
		title_rightimageview = (ImageView) findViewById(R.id.title_rightimageview);
		title_bottomline = findViewById(R.id.title_bottomline);
		framelayout_title_leftimageview = (FrameLayout) findViewById(R.id.framelayout_title_leftimageview);
	}

	@Override
	public void setContentView(int layoutResID) {
		if (layoutResID == R.layout.activity_view_main) {
			abstractView = LayoutInflater.from(this).inflate(layoutResID, null);
			super.setContentView(abstractView);
		} else {
			main_view.removeAllViews();
			View inflate = this.getLayoutInflater().inflate(layoutResID, null);
			inflate.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			main_view.addView(inflate);
			initViews();// 初始化view对象
			initDatas();// 初始化必要的数据
			registEvent();// 为view对象注册点击事件
		}
	}

	@Override
	public void setContentView(View view) {
		main_view.removeAllViews();
		main_view.addView(view);

	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		main_view.removeAllViews();
		main_view.addView(view, params);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityManager.getInstance().popActivity(AbstractActivity.this);
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(abstractView.getWindowToken(), 0);
		releaseResource();
	}

	/**
	 * @methods initViews
	 * @description 初始化view对象
	 * @date 2015-8-7 上午11:54:49 参数说明
	 */
	public abstract void initViews();

	/**
	 * @methods initDatas
	 * @description 获取必要的初始化数据
	 * @date 2015-8-7 下午1:23:44 参数说明
	 */
	public abstract void initDatas();

	/**
	 * @methods registEvent
	 * @description 注册点击事件
	 * @date 2015-8-7 下午1:22:28 参数说明
	 */
	public abstract void registEvent();

	/**
	 * @methods releaseResource
	 * @description 释放资源
	 * @date 2015-8-7 上午11:44:10 参数说明
	 */
	public abstract void releaseResource();

}
