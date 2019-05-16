package com.lwx.multifunctiondemo.jsInterface;

import android.webkit.JavascriptInterface;

import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.activity.BaseApplication;

import org.json.JSONObject;

/**
 * Created by Dell on 2019/5/16.
 */

public class BaseJsInterface {
    public static final String NAME = "demo";


    @JavascriptInterface
    public void testDemo(String json) {

    }


}
