package com.lwx.multifunctiondemo.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.adapter.CommonAdapter;
import com.lwx.multifunctiondemo.adapter.MainListAdapter;
import com.lwx.multifunctiondemo.base.AbstractActivity;
import com.lwx.multifunctiondemo.utils.ActivityManager;
import com.lwx.multifunctiondemo.utils.ModelUtils;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AbstractActivity implements AdapterView.OnItemClickListener,EasyPermissions.PermissionCallbacks{
    private static final String TAG = "MainActivity";
    private ListView mLvMainActivity;
    private CommonAdapter mCommonAdapter;
    private long exitTime=0;

    private static final int RC_LOCATION_CONTACTS_PERM = 124;
    private static final String[] CAMERA_AND_READWRITE =
            {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleLeftImageVisible(false);
        setTitleCenterTextView("功能展示");
        setContentView(R.layout.activity_main);
        locationAndContactsTask();
    }

    @Override
    public void initViews() {
        mLvMainActivity = (ListView) findViewById(R.id.lv_main_activity);
        mCommonAdapter=new MainListAdapter(mContext, ModelUtils.getFunctionList(),R.layout.adapter_function_list);
        mLvMainActivity.setAdapter(mCommonAdapter);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void registEvent() {
        mLvMainActivity.setOnItemClickListener(this);
    }

    /**
     * listview 点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(mContext,GradientSeekbarActivity.class));
                break;
            case 1:

                break;
            default:

                break;

        }
    }

    @Override
    public void releaseResource() {

    }


    /**
     * 处理用户的点击物理返回按键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                // 需要清空栈内所有的activity
                ActivityManager.getInstance().clearAll();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 请求权限第一步:
     */
    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    public void locationAndContactsTask() {
        if (EasyPermissions.hasPermissions(this, CAMERA_AND_READWRITE)) {
            // Have permissions, do the thing!
//            Toast.makeText(this, "权限请求成功", Toast.LENGTH_LONG).show();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    "您需要开通权限才可以使用!",
                    RC_LOCATION_CONTACTS_PERM,
                    CAMERA_AND_READWRITE);
        }
    }

    /**
     * 请求权限第二步:获取请求结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 请求权限第三步:将请求结果传给onPermissionsGranted和onPermissionsDenied方法
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());

        //请求权限遭拒绝多次弹出此对话框,调到设置中开启权限(需要自定义处理)
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }
}
