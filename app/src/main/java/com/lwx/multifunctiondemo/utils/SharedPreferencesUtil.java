package com.lwx.multifunctiondemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class SharedPreferencesUtil {

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("setting", 0);
    }

    public static boolean getBoolean(Context context, String key, boolean def) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getBoolean(key, def);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editPrefs = getSharedPreferences(context).edit();
        editPrefs.putBoolean(key, value);
        editPrefs.apply();
    }

    public static String getString(Context context, String key, String def) {
        try{
            SharedPreferences sp = getSharedPreferences(context);
            return sp.getString(key, def);
        }catch (Exception e){
            e.printStackTrace();
            return def;
        }
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences.Editor editPrefs = getSharedPreferences(context).edit();
        editPrefs.putString(key, value);
        editPrefs.apply();
    }

    public static Long getLong(Context context, String key, Long def) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getLong(key, def);
    }

    public static void putLong(Context context, String key, Long value) {
        SharedPreferences.Editor editPrefs = getSharedPreferences(context).edit();
        editPrefs.putLong(key, value);
        editPrefs.apply();
    }

    public static int getInt(Context context, String key, int def) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getInt(key, def);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences.Editor editPrefs = getSharedPreferences(context).edit();
        editPrefs.putInt(key, value);
        editPrefs.apply();
    }

    public static float getFloat(Context context, String key, float def) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getFloat(key, def);
    }

    public static void putFloat(Context context, String key, int value) {
        SharedPreferences.Editor editPrefs = getSharedPreferences(context).edit();
        editPrefs.putFloat(key, value);
        editPrefs.apply();
    }
    public static void clear(Context context, String key, int value) {
        SharedPreferences.Editor editPrefs = getSharedPreferences(context).edit();
        editPrefs.putFloat(key, value);
        editPrefs.apply();
    }
    //清空sp
    public static void clear(Context context) {
        SharedPreferences.Editor editPrefs = getSharedPreferences(context).edit();
        editPrefs.clear();
        editPrefs.apply();
    }

    /**
     * 移除指定数据
     * @param context
     * @param keys
     */
    public static void clear(Context context, List<String> keys)
    {
        for (int i = 0; i < keys.size(); i++) {
            SharedPreferences.Editor editPrefs = getSharedPreferences(context).edit();
            editPrefs.remove(keys.get(i));
            editPrefs.apply();
        }
    }


}
