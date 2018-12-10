package com.lwx.multifunctiondemo.activity;

import android.app.Application;

import com.lwx.multifunctiondemo.utils.Constants;
import com.lwx.multifunctiondemo.utils.ScreenUtils;

/**
 * Created by Dell on 2018/12/10.
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initParames();
    }

    /**
     * 获取屏幕尺寸
     */
    private void initParames() {
        int[] screenSizes = ScreenUtils.getScreenSize(this);
        Constants.screenWidth = screenSizes[0];
        Constants.screenHeight = screenSizes[1];
        Constants.screenStatus = ScreenUtils.getStatusHeight(this);
    }
}
