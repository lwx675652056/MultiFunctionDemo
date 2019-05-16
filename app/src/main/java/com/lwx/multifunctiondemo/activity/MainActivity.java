package com.lwx.multifunctiondemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.adapter.CommonAdapter;
import com.lwx.multifunctiondemo.adapter.MainListAdapter;
import com.lwx.multifunctiondemo.base.BaseActivity;
import com.lwx.multifunctiondemo.utils.ActivityManager;
import com.lwx.multifunctiondemo.utils.ModelUtils;


public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "MainActivity";
    private ListView mLvMainActivity;
    private CommonAdapter mCommonAdapter;
    private long exitTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                startActivity(new Intent(mContext,ScaleBackgroundActivity.class));
                break;
            default:
                startActivity(new Intent(mContext,WebActivity.class));
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
}
