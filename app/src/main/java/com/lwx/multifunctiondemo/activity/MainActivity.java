package com.lwx.multifunctiondemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.adapter.CommonAdapter;
import com.lwx.multifunctiondemo.adapter.MainListAdapter;
import com.lwx.multifunctiondemo.base.AbstractActivity;
import com.lwx.multifunctiondemo.utils.ModelUtils;

public class MainActivity extends AbstractActivity implements AdapterView.OnItemClickListener {
    private ListView mLvMainActivity;
    private CommonAdapter mCommonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleLeftImageVisible(false);
        setTitleCenterTextView("功能展示");
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

                break;
            default:

                break;

        }
    }

    @Override
    public void releaseResource() {

    }


}
