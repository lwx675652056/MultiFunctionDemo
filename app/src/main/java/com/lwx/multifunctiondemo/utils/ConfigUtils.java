package com.lwx.multifunctiondemo.utils;

import android.content.Context;

/**
 * 信息的存储通用类
 *
 * @author lvyanbo
 */
public class ConfigUtils {

    /**获取token*/
    private static final String USER_TOKEN = "user_token";

    public static String getUserToken(Context context, String def) {
        return SharedPreferencesUtil.getString(context, USER_TOKEN, def);
    }

    public static void savaUserToken(Context context, String tokenValue) {
        SharedPreferencesUtil.putString(context, USER_TOKEN, tokenValue);
    }

    /** 记录是否第一次打开 */
    private static final String IS_FIRST_LAUNCH = "is_first_launch";

    public static boolean isFirstLaunch(Context context) {
        return SharedPreferencesUtil.getBoolean(context, IS_FIRST_LAUNCH, true);
    }

    public static void saveFirstLaunch(Context context, boolean firstLaunchStatus) {
        SharedPreferencesUtil.putBoolean(context, IS_FIRST_LAUNCH, firstLaunchStatus);
    }

}

