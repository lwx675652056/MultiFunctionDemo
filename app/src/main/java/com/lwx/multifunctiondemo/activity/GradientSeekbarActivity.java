package com.lwx.multifunctiondemo.activity;

import android.os.Bundle;
import android.widget.SeekBar;

import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.base.BaseActivity;

public class GradientSeekbarActivity extends BaseActivity {
    private SeekBar mSeekbarGradientSeekbarActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_seekbar);
    }

    @Override
    public void initViews() {
        mSeekbarGradientSeekbarActivity = (SeekBar) findViewById(R.id.seekbar_gradient_seekbar_activity);

        initSeekbarBitmap();
    }
    /**
     * 初始化seekbarBitmap
     */
    public void initSeekbarBitmap() {
        //手动设置图片大小
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),
//                        R.drawable.icon_seekbar_thumb);
//                int originalWidth = originalBitmap.getWidth();
//                int originalHeight = originalBitmap.getHeight();
//                int newWidth = ScreenUtils.dip2px(mContext, 120);
//                int newHeight = ScreenUtils.dip2px(mContext, 120);
//                float scale = ((float) newHeight) / originalHeight;
//                Matrix matrix = new Matrix();
//                matrix.postScale(scale, scale);
//                Bitmap changedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0,
//                        originalWidth, originalHeight, matrix, true);
//                mSeekbarGradientSeekbarActivity.setThumb(new BitmapDrawable(changedBitmap));
//
//            }
//        }).start();
    }
    @Override
    public void initDatas() {

    }

    @Override
    public void registEvent() {

    }

    @Override
    public void releaseResource() {

    }
}
