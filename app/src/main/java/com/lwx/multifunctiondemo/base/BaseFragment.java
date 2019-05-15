package com.lwx.multifunctiondemo.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.utils.Constants;
import com.lwx.multifunctiondemo.widget.PublicTitleView;


/**
 * @author win64
 * @title: BaseFragment.java
 * @description: 基类Fragment
 * @date 2015-8-11 下午1:25:31
 */
public abstract class BaseFragment extends Fragment {

    protected boolean isTemplate = false; // 是否使用模板 , 默认有标题栏
    protected Context mContext;
    protected FragmentActivity mActivity;
    public View mMainView;// 填充的主view
    protected ViewGroup mainBody;// 主体显示
    public View childView = null; // 子类的View
    public PublicTitleView public_title_view;// 标题栏

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();
        mActivity = getActivity();
        mMainView = inflater.inflate(R.layout.fragment_view_main, null);
        mainBody = (ViewGroup) mMainView.findViewById(R.id.fragment_main_view);
        public_title_view = mMainView.findViewById(R.id.public_title_view);
        childView = createViewAdded(inflater, savedInstanceState);
        if (childView != null) {
            mainBody.removeAllViews();
            mainBody.addView(childView);
        }
        if (isTemplate) {// 不使用模板就把标题栏去掉
            public_title_view.setTitlebarVisibility(View.GONE);
        }
        initFragmentView();// 初始化添加的布局的对象
        registFragmentEvent();// 布局的view对象注册事件
        return mMainView;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseFragmentResource();
    }

    /**
     * @param inflater
     * @param savedInstanceState
     * @return 参数说明
     * @author win64
     * @methods createViewAdded
     * @description 子类重写此方法，设置填充的layout布局
     * @date 2015-8-11 下午5:58:35
     */
    public abstract View createViewAdded(LayoutInflater inflater, Bundle savedInstanceState);

    /**
     * @author win64
     * @methods initFragmentView
     * @description 初始化fragment中的对象
     * @date 2015-8-11 下午6:01:29 参数说明
     */
    public abstract void initFragmentView();

    /**
     * @author win64
     * @methods registFragmentEvent
     * @description 注册点击事件
     * @date 2015-8-11 下午6:03:30 参数说明
     */
    public abstract void registFragmentEvent();

    /**
     * @methods releaseFragmentResource
     * @description 释放fragment的资源
     * @date 2015-8-7 上午11:44:10 参数说明
     */
    public abstract void releaseFragmentResource();


}
