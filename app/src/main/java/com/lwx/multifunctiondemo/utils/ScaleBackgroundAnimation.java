package com.lwx.multifunctiondemo.utils;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Dell on 2019/5/10.
 */

public class ScaleBackgroundAnimation extends Animation {
    private float mWidth;
    private float mHeight;
    //动画偏移的距离，理论上为了适配，应该使用宽，或者高的百分比，这里方便使用固定值
    private float dValue = 60f;

    private float leftTopX = 0f, leftTopY = 0f;
    private float rightTopX = 0f, rightTopY = 0f;
    private float rightBottomX = 0f, rightBottomY = 0f;
    private float leftBottomX = 0f, leftBottomY = 0f;


    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mHeight = height;
        mWidth = width;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
            //阶段1的动画，时间占比0.5，下方不变，上分开始缩放
            if (interpolatedTime <= 0.5f) {
                float f1 = interpolatedTime * 1 / 0.5f;
                //源定点坐标(分别是:左上,右上,右下,左下)
                float[] src = {0f, 0f, mWidth, 0f, mWidth, mHeight, 0f, mHeight};
                //目标定点坐标
                float[] dest = {leftTopX = dValue * f1, leftTopY = dValue * f1,
                        rightTopX = mWidth - dValue * f1, rightTopY = dValue * f1,
                        mWidth, mHeight,
                        0f, mHeight};
                //通过src与dest的坐标，来计算出变换的矩阵，
                t.getMatrix().setPolyToPoly(src, 0, dest, 0, 4);
            } else {
                //阶段2动画时间占比0.4，上方不变，下方缩放
                float f2 = (interpolatedTime - 0.5f) * 1 / 0.5f;
                float[] src = {0f, 0f, mWidth, 0f, mWidth, mHeight, 0f, mHeight};
                float[] dest = {leftTopX, leftTopY,
                        rightTopX, rightTopY,
                        rightBottomX = mWidth - dValue * f2, rightBottomY = mHeight - dValue * f2,
                        leftBottomX = dValue * f2, leftBottomY = mHeight - dValue * f2};
                //最后一个参数表示测控点的数量 取值范围是: 0到4
                //为4时 可以进行 缩放、旋转、平移、错切以及任何形变
                t.getMatrix().setPolyToPoly(src, 0, dest, 0, 4);
            }
    }
}
