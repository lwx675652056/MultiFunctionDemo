package com.lwx.multifunctiondemo.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.ValueCallback;

import com.lwx.multifunctiondemo.BuildConfig;
import com.lwx.multifunctiondemo.R;
import java.io.File;
import static android.app.Activity.RESULT_OK;

/**
 * Created by DELL on 2017/11/29/029.
 */

public class WebViewFileUploadUtil implements View.OnClickListener {
    private Activity activity;
    private ValueCallback<Uri> mUploadMessage;
    Dialog dialog;
    private ValueCallback<Uri[]> mUploadMessageForAndroid5;
    int platformVersion;
    public final static int FILECHOOSER_RESULTCODE = 1;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDORID_5 = 2;
    public WebViewFileUploadUtil(Activity activity) {
        this.activity = activity;
        initDialog();
    }
    private void initDialog() {
        //网页中含有弹框界面的初始化操作
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_webupload_select, null);
        view.findViewById(R.id.btn1Id).setOnClickListener(this);
        view.findViewById(R.id.btn2Id).setOnClickListener(this);
        dialog = new Dialog(activity);
        dialog.setTitle("请选择方式");
        dialog.setContentView(view);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.i("zt","消失了");
                if(mUploadMessage!=null)
                {
                    mUploadMessage.onReceiveValue(null);
                }
                if(mUploadMessageForAndroid5!=null)
                {
                    mUploadMessageForAndroid5.onReceiveValue(null);
                }
                mUploadMessage = null;
                mUploadMessageForAndroid5 = null;
            }
        });
    }

    public void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        platformVersion = FILECHOOSER_RESULTCODE;
        dialog.show();
    }

    public void openFileChooserImplForAndroid5(ValueCallback<Uri[]> uploadMsg) {
        mUploadMessageForAndroid5 = uploadMsg;
        platformVersion = FILECHOOSER_RESULTCODE_FOR_ANDORID_5;
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        dialog.hide();
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");
        Intent chooserIntent = null;
        switch (v.getId()) {
            case R.id.btn1Id:
                chooserIntent = createCameraIntent();
                break;
            case R.id.btn2Id:
                chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                break;
        }
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
        activity.startActivityForResult(chooserIntent, platformVersion);
    }

    private String mCameraFilePath;

    @SuppressWarnings("static-access")
    private Intent createCameraIntent() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File externalDataDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File cameraDataDir = new File(externalDataDir.getAbsolutePath() + File.separator + "515aaa");
        cameraDataDir.mkdirs();
        String mCameraFilePath = cameraDataDir.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg";
        this.mCameraFilePath = mCameraFilePath;
        File file = new File(mCameraFilePath);
        cameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //创建拍照存储的图片文件

            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            cameraIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        }

        return cameraIntent;
    }


    public void  onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.i("infozt","调用了");
        if (requestCode == FILECHOOSER_RESULTCODE) {//Android 5.0之前的版本的上传文件的方式

            if (null == mUploadMessage)
                return;

            if(mCameraFilePath!=null)
            {
                Uri uri = Uri.fromFile(new File(mCameraFilePath));
                mUploadMessage.onReceiveValue(uri);
            }else
            {
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                mUploadMessage.onReceiveValue(result);
            }
            //mUploadMessage.onReceiveValue(null);
            mUploadMessage = null;
        } else if (requestCode == FILECHOOSER_RESULTCODE_FOR_ANDORID_5) {//Android 5.0只有的版本的上传文件的方式

            if (null == mUploadMessageForAndroid5)
                return;
            if(mCameraFilePath!=null)
            {
                Uri uri = Uri.fromFile(new File(mCameraFilePath));
                mUploadMessageForAndroid5.onReceiveValue(new Uri[]{uri});
            }
            else
            {
                Uri result = (intent == null || resultCode != RESULT_OK) ? null : intent.getData();
                if (result != null) {
                    mUploadMessageForAndroid5.onReceiveValue(new Uri[]{result});
                }else
                {
                    mUploadMessageForAndroid5.onReceiveValue(null);
                }
            }
            mUploadMessageForAndroid5 = null;
        }
        mCameraFilePath = null;
    }

}
