package com.lwx.multifunctiondemo.activity;

import android.app.Application;

import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.utils.ConfigUtils;
import com.lwx.multifunctiondemo.utils.Constants;
import com.lwx.multifunctiondemo.utils.ScreenUtils;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Dell on 2018/12/10.
 */

public class BaseApplication extends Application{
    private static BaseApplication mBaseApplication = null;
    /**
     * 设置 和 获取 屏幕的分辨率
     */
    private int wid, hei;
    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        //bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), getResources().getString(R.string.bugly_appid), false);
    }

    public static BaseApplication getmApplication() {
        return mBaseApplication;
    }

    public void setScreenSize(int wid, int hei) {
        this.wid = wid;
        this.hei = hei;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public int getHei() {
        return hei;
    }

    public void setHei(int hei) {
        this.hei = hei;
    }

    // 获取token
    public String getUser_token() {
        return ConfigUtils.getUserToken(getApplicationContext(), "");
    }
}
