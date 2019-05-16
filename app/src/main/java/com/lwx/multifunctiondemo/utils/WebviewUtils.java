package com.lwx.multifunctiondemo.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.activity.BaseApplication;
import com.lwx.multifunctiondemo.activity.WebActivity;
import com.lwx.multifunctiondemo.base.BaseActivity;
import com.lwx.multifunctiondemo.jsInterface.BaseJsInterface;

import java.util.HashMap;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

/**
 * Created by DELL on 2017/12/28/028.
 * 新增 WebView 统一配置
 * update by pujuntao on 2018/5/9
 */

public class WebviewUtils {
    private BaseActivity mActivity;
    private WebView mWebView;

    private BaseJsInterface baseJsInterface = null;

    public WebviewUtils(BaseActivity mActivity, WebView mWebView) {
        this.mActivity = mActivity;
        this.mWebView = mWebView;
    }


    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    public void initWebViewSetting() {
        if (mWebView == null) return;
        WebSettings mWebSettings = mWebView.getSettings();
        //设置初始化参数
        //设置webView
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setBuiltInZoomControls(true);
        if (NetWorkUtil.isAccessNetwork(mActivity)) {
            //有网络的情况不走缓存
            mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        } else {
            //没网络的情况优先走缓存
            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setDomStorageEnabled(true);//对H5支持
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setSaveFormData(true);
        //设置WebView是否以http、https方式访问从网络加载图片资源，默认false
        mWebSettings.setBlockNetworkImage(false);
        //设置WebView是否从网络加载资源，Application需要设置访问网络权限，否则报异常
        mWebSettings.setBlockNetworkLoads(false);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setLoadsImagesAutomatically(true);//支持自动加载图片
        //不显示webview缩放按钮
        mWebSettings.setDisplayZoomControls(false);
        //混合网络协议内容(即 支持http 和 https同时存在)
        if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            //设置不保存密码
            mWebSettings.setSavePassword(false);
        }
        //APP缓存目录
        mWebSettings.setAppCachePath(mActivity.getCacheDir().getAbsolutePath() + "CACHE_WEB");
        /****************************定位需要的属性***********************************/
        //设置定位的数据库路径
        String dir = BaseApplication.getmApplication().getDir("database", Context.MODE_PRIVATE).getPath();
        mWebSettings.setGeolocationDatabasePath(dir);
        //启用地理定位
        mWebSettings.setGeolocationEnabled(true);
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        mWebView.addJavascriptInterface(baseJsInterface, BaseJsInterface.NAME);
    }

    //设置JavascriptInterface，避免设置的名称与BaseJsInterface的相同
    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    public void addJavascriptInterface(Object object, String name) {
        if (mWebView == null || object == null || name == null) return;
        if (name.equals(BaseJsInterface.NAME))
            throw new RuntimeException("The name of the setting cannot be 'demo'");
        mWebView.addJavascriptInterface(object, name);
    }

    //设置下载监听
    public void setDownLoadListener() {
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(final String url,
                                        String userAgent,
                                        String contentDisposition,
                                        String mimetype, long contentLength) {
                if (url.endsWith(".apk")) {
                    new AlertDialog.Builder(mActivity)
                            .setTitle("温馨提示")
                            .setMessage("需要下载该文件，是否继续？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("继续", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                        if (intent.resolveActivity(mActivity.getPackageManager()) != null) {
                                            mActivity.startActivity(intent);
                                        }else {
                                            mActivity.showShortToast("请安装浏览器后重新下载");
                                        }
                                    }catch (Exception e){
                                        e.printStackTrace();
                                        mActivity.showShortToast("下载异常,请稍后重试");
                                    }
                                }
                            })
                            .create().show();
                }
            }
        });

    }

    //同步cookie
    public void syncCookie(String url) {
        // TODO: 2018/5/9/009  同步cookie方法待定
//        if (url == null) return;
//        if (!TextUtils.isEmpty(mWebView.getUrl())) {
//            if (newWebView == null) {
//                newWebView = new WebView(mActivity);
//                initWebViewSetting(newWebView, null);
//            }
//            //加载网页
//            ApiService.getInstance().loadWebByUrlToClub(newWebView, url);
//        } else {
//            ApiService.getInstance().loadWebByUrlToClub(mWebView, url);
//        }
    }

    //注入js
    public void injectJs(String jsString) {
        if (jsString == null) return;
        mWebView.loadUrl("javascript:" + jsString);
    }

    //注入token
    public void injectDiv(String keyString, String valString) {
        if (keyString == null || valString == null) return;
        injectJs("var html=document.createElement(\"div\");html.innerHTML=\""
                + valString
                + "\";html.setAttribute(\"class\",\""
                + keyString
                + "\");html.setAttribute(\"style\",\"display:none\");document.body.appendChild(html);");
    }

    //加载url，可包含header
    public static void loadUrl(WebView webView, String url, boolean withToken, boolean withGzip) {
        if (webView == null || TextUtils.isEmpty(url)) return;
        if ((!withToken) && (!withGzip)) {
            if (url.startsWith("http")) webView.loadUrl(url);
            else webView.loadData(url, "text/html", "uft-8");
            return;
        }

        HashMap<String, String> header = new HashMap<>();
        if (withToken) {
            String tokenString = BaseApplication.getmApplication().getUser_token();
            if (!TextUtils.isEmpty(tokenString)) header.put("X-Token", tokenString);
        }
        if (withGzip) header.put("Content-Encoding", "gzip");

        if (header.size() < 1) {
            if (url.startsWith("http")) webView.loadUrl(url);
            else webView.loadData(url, "text/html", "uft-8");
        } else {
            if (url.startsWith("http")) webView.loadUrl(url, header);
            else webView.loadData(url, "text/html", "uft-8");
        }
    }

    public static void loadLocalError(WebView webView) {
        webView.loadUrl("file:///android_asset/error.html");
    }

    public static void loadBlank(WebView webView) {
        webView.loadUrl("about:blank");
    }

}
