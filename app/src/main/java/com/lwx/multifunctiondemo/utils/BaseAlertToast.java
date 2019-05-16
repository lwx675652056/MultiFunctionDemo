package com.lwx.multifunctiondemo.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lwx.multifunctiondemo.R;


/**
 * 修改toast提示框
 */

public class BaseAlertToast {

    private View toastView;
    private TextView tvToastMsg;
    private Toast customToast;

    private BaseAlertToast() {
    }


    public static BaseAlertToast getInstance() {
        return new BaseAlertToast();
    }

    private void initToastView(Context context) {
        toastView = getToastView(context);
        tvToastMsg = (TextView) toastView.findViewById(R.id.toast_text);
        customToast = createToast(context, toastView);
    }

    //获取toast视图
    private static View getToastView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.base_toast_view, null);
    }

    //创建toast
    private static Toast createToast(Context context, View view) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, -200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        return toast;
    }

    //首先实例化，而后每次toast使用同一视图
    public void show(Context mContext, String msg) {
        if (msg == null) return;
        if (toastView == null || tvToastMsg == null || customToast == null) initToastView(mContext);
        tvToastMsg.setText(msg);
        customToast.show();
    }

    //使用时创建，无视图缓存
    public static void showOnce(Context mContext, String msg) {
        if (msg == null) return;
        View contentView = getToastView(mContext);
        TextView tvMsg = (TextView) contentView.findViewById(R.id.toast_text);
        tvMsg.setText(msg);
        createToast(mContext, contentView).show();
    }

}
