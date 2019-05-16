package com.lwx.multifunctiondemo.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
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
import com.lwx.multifunctiondemo.activity.BaseApplication;
import com.lwx.multifunctiondemo.utils.ActivityManager;
import com.lwx.multifunctiondemo.utils.BaseAlertToast;
import com.lwx.multifunctiondemo.utils.Constants;
import com.lwx.multifunctiondemo.utils.ScreenUtils;
import com.lwx.multifunctiondemo.widget.PublicTitleView;


/**
 * @description: 基类Activity
 * @date 2016-4-13 下午13:44:47
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected ViewGroup main_view;// 内容主题
    public View abstractView;// view_main的布局对象
    public PublicTitleView mPublicTitleView;//标题布局
    // 上下文
    protected Context mContext;
    protected Activity mActivity;
    private BaseAlertToast alertToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setImmersionStatus();
        setContentView(R.layout.activity_view_main);
        initActivityViews();
        getScreenSize();
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
    private void setImmersionStatus() {
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			// 透明导航栏
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
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

    // 初始化对象
    private void initActivityViews() {
        mContext = this;
        mActivity = this;
        main_view = (ViewGroup) findViewById(R.id.activity_main_view);
        mPublicTitleView = (PublicTitleView) findViewById(R.id.public_title_view);
    }

    private void getScreenSize() {
        int[] screenSizes = ScreenUtils.getScreenSize(this);
        BaseApplication.getmApplication().setScreenSize(screenSizes[0], screenSizes[1]);
    }

    public void showShortToast(String text) {
        if (alertToast == null) alertToast = BaseAlertToast.getInstance();
        alertToast.show(mContext, text);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().popActivity(BaseActivity.this);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            View view = getCurrentFocus();
            if (view != null && imm.isActive()) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
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
