package com.lwx.multifunctiondemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.base.BaseActivity;
import com.lwx.multifunctiondemo.utils.ScaleBackgroundAnimation;



public class ScaleBackgroundActivity extends BaseActivity {
    private RelativeLayout mLlBg;
    private Button mBtnShowdailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_background);
    }

    @Override
    public void initViews() {
        mLlBg = (RelativeLayout) findViewById(R.id.ll_bg);
        mBtnShowdailog = (Button) findViewById(R.id.btn_showdailog);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void registEvent() {
        mBtnShowdailog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleBackgroundAnimation scaleBackgroundAnimation=new ScaleBackgroundAnimation();
                scaleBackgroundAnimation.setFillAfter(true);
                scaleBackgroundAnimation.setDuration(1000);
                mLlBg.startAnimation(scaleBackgroundAnimation);
            }
        });
    }

    @Override
    public void releaseResource() {

    }
}
