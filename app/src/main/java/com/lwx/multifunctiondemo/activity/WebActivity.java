package com.lwx.multifunctiondemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.base.BaseActivity;
import com.lwx.multifunctiondemo.utils.WebViewFileUploadUtil;
import com.lwx.multifunctiondemo.utils.WebviewUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class WebActivity extends BaseActivity implements OnRefreshListener {
    private WebView mWebview;
    private ProgressBar mProgress;
    private SmartRefreshLayout mSmartRefresh;
    private String web_url = "https://github.com/lwx675652056";
    private WebviewUtils mWebviewUtils;
    private WebViewFileUploadUtil fileUploadUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
    }

    @Override
    protected void onPause() {
        try {
            mWebview.getClass().getMethod("onPause").invoke(mWebview, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        try {
            mWebview.getClass().getMethod("onResume").invoke(mWebview, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    public void initViews() {
        mWebview = (WebView) findViewById(R.id.webview_webActivity);
        mProgress = (ProgressBar) findViewById(R.id.progress_webActivity);
        mSmartRefresh = (SmartRefreshLayout) findViewById(R.id.smartRefresh_webActivity);
        mSmartRefresh.setEnableLoadMore(false);
    }

    @Override
    public void initDatas() {
        mWebviewUtils = new WebviewUtils(this, mWebview);
        mWebviewUtils.initWebViewSetting();
        mWebviewUtils.setDownLoadListener();
        fileUploadUtil = new WebViewFileUploadUtil(this);

        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgress.setVisibility(View.VISIBLE);
                mProgress.setProgress(0);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });

        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title) && !title.startsWith("http")) {
                    mPublicTitleView.setTitleText(title);
                }
            }

            /**上传定位数据*/
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            //5.0之前的设备调用该方法处理文件上传的逻辑
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                fileUploadUtil.openFileChooserImpl(uploadMsg);
            }

            // For Android > 5.0 之后的逻辑调用该方法处理文件上传的逻辑
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg, FileChooserParams fileChooserParams) {
                fileUploadUtil.openFileChooserImplForAndroid5(uploadMsg);
                return true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgress.setProgress(newProgress);
            }
        });


        WebviewUtils.loadUrl(mWebview, web_url, false, true);
    }

    @Override
    public void registEvent() {
        mSmartRefresh.setOnRefreshListener(this);
    }

    //刷新监听
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        WebviewUtils.loadUrl(mWebview, mWebview.getUrl(), false, true);
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {

        } else {
            fileUploadUtil.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
