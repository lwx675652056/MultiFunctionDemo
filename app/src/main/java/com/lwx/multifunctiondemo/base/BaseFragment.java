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


/**
 * @author win64
 * @title: BaseFragment.java
 * @package com.vungu.fruit.fragment
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
    public View titleView;// 标题栏

    protected ViewGroup title_parentlayout;//标题栏
    protected TextView title_lefttextview, title_centertextview, title_righttextview;// 标题栏的文字
    protected ImageView title_leftimageview, title_centerimageview, title_rightimageview;// 标题的图标
    protected View title_bottomline;// 标题栏的底部横线
    protected FrameLayout framelayout_title_leftimageview;//左侧图片点击区域

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
        titleView = mMainView.findViewById(R.id.title_parentlayout);
        initTitleView();// 初始化标题栏
        setTitleSize();// 设置标题栏的高度
        setTitleEvent();// 设置标题栏的点击事件
        childView = createViewAdded(inflater, savedInstanceState);
        if (childView != null) {
            mainBody.addView(childView);
        }
        if (isTemplate) {// 不使用模板就把标题栏去掉
            titleView.setVisibility(View.GONE);
        }
        initFragmentView();// 初始化添加的布局的对象
        registFragmentEvent();// 布局的view对象注册事件
        return mMainView;

    }

    /**
     * @author win64
     * @methods initTitleView
     * @description 初始化title栏
     * @date 2015-8-11 下午6:45:04 参数说明
     */
    private void initTitleView() {
        title_parentlayout = (ViewGroup)mMainView.findViewById(R.id.title_parentlayout);
        title_lefttextview = (TextView) mMainView.findViewById(R.id.title_lefttextview);
        title_centertextview = (TextView) mMainView.findViewById(R.id.title_centertextview);
        title_righttextview = (TextView) mMainView.findViewById(R.id.title_righttextview);
        title_leftimageview = (ImageView) mMainView.findViewById(R.id.title_leftimageview);
        title_centerimageview = (ImageView) mMainView.findViewById(R.id.title_centerimageview);
        title_rightimageview = (ImageView) mMainView.findViewById(R.id.title_rightimageview);
        title_bottomline = mMainView.findViewById(R.id.title_bottomline);
        framelayout_title_leftimageview = (FrameLayout) mMainView.findViewById(R.id.framelayout_title_leftimageview);
    }

    private void setTitleEvent() {
        framelayout_title_leftimageview.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    /**
     * @methods setTitleLeftImageVisible
     * @description 设置左侧的图片是否可见
     * @date 2015-8-7 下午6:10:19 参数说明
     */
    protected void setTitleLeftImageVisible(boolean isImageVisible) {
        if (isImageVisible)
            framelayout_title_leftimageview.setVisibility(View.VISIBLE);
        else
            framelayout_title_leftimageview.setVisibility(View.GONE);
    }
    /**
     * @methods setTitleLeftImageVisible
     * @description 设置左侧的文字是否可见
     * @date 2015-8-7 下午6:10:19 参数说明
     */
    protected void setTitleLeftTextVisible( boolean isTextVisible) {
        if (isTextVisible)
            title_lefttextview.setVisibility(View.VISIBLE);
        else
            title_lefttextview.setVisibility(View.GONE);
    }

    /**
     * @methods setTitleCenterImageVisible
     * @description 设置中间文字是否可见
     * @date 2015-8-7 下午6:10:19 参数说明
     */
    protected void setTitleCenterTextVisible(boolean isTextVisible) {
        if (isTextVisible) {
            title_centertextview.setVisibility(View.VISIBLE);
        } else {
            title_centertextview.setVisibility(View.GONE);
        }
    }

    /**
     * @methods setTitleCenterImageVisible
     * @description 设置中间图片是否可见
     * @date 2015-8-7 下午6:10:19 参数说明
     */
    protected void setTitleCenterImageVisible(boolean isImageVisible) {
        if (isImageVisible) {
            title_centerimageview.setVisibility(View.VISIBLE);
        } else {
            title_centerimageview.setVisibility(View.GONE);
        }
    }

    /**
     * @methods setTitleRightImageVisible
     * @description 设置右侧的图片是否可见
     * @date 2015-8-7 下午6:10:19 参数说明
     */
    protected void setTitleRightImageVisible(boolean isImageVisible) {
        if (isImageVisible) {
            title_rightimageview.setVisibility(View.VISIBLE);
        } else {
            title_rightimageview.setVisibility(View.GONE);
        }
    }
    /**
     * @methods setTitleRightImageVisible
     * @description 设置右侧的文字是否可见
     * @date 2015-8-7 下午6:10:19 参数说明
     */
    protected void setTitleRightTextVisible(boolean isTextVisible) {
        if (isTextVisible) {
            title_righttextview.setVisibility(View.VISIBLE);
        } else {
            title_righttextview.setVisibility(View.GONE);
        }
    }

    /**
     * @methods setTitleLeftTextView
     * @description 设置左侧的文字
     * @date 2015-6-2 上午10:52:06
     * @param titleFonts
     *            参数说明
     */
    protected void setTitleLeftTextView(String titleFonts) {
        if (titleFonts!=null&&!"".equals(titleFonts)){
            title_lefttextview.setVisibility(View.VISIBLE);
            title_lefttextview.setText(titleFonts);
        }
    }

    /**
     * @methods setTitleCenterTextView
     * @description 设置中间侧的文字
     * @date 2015-6-2 上午10:52:06
     * @param titleFonts
     *            参数说明
     */
    protected void setTitleCenterTextView(String titleFonts) {
        title_centertextview.setText(titleFonts);
    }

    /**
     * @methods setTitleRightTextView
     * @description 设置右侧的文字
     * @date 2015-6-2 上午10:52:06
     * @param titleFonts
     *            参数说明
     */
    protected void setTitleRightTextView(String titleFonts) {
        if (titleFonts!=null&&!"".equals(titleFonts)){
            title_righttextview.setVisibility(View.VISIBLE);
            title_righttextview.setText(titleFonts);
        }
    }

    /**
     * @methods setTitleLeftImageView
     * @description 设置左侧的图标
     * @date 2015-6-2 上午10:54:32
     * @param iamgeID
     *            参数说明
     */
    protected void setTitleLeftImageView(int iamgeID) {
        title_leftimageview.setImageResource(iamgeID);
    }

    /**
     * @methods setTitleCenterImageView
     * @description 设置中间的图标
     * @date 2015-6-2 上午10:54:32
     * @param iamgeID
     *            参数说明
     */
    protected void setTitleCenterImageView(int iamgeID) {
        title_centerimageview.setVisibility(View.VISIBLE);
        title_centerimageview.setImageResource(iamgeID);
    }

    /**
     * @methods setTitleRightImageView
     * @description 设置右侧的图标
     * @date 2015-6-2 上午10:54:32
     * @param
     *
     */
    protected void setTitleRightImageView(int iamgeID) {
        title_rightimageview.setVisibility(View.VISIBLE);
        title_rightimageview.setImageResource(iamgeID);
    }


    /**
     * @methods setTitleBottomLineisVisible
     * @description 设置title底部的线是否可见
     * @date 2015-6-2 上午11:09:29 参数说明
     */
    protected void setTitleBottomLineisVisible(boolean isVisible) {
        if (isVisible) {
            title_bottomline.setVisibility(View.VISIBLE);
        } else {
            title_bottomline.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * @methods setTitleSize
     * @description 设置标题的高度
     * @date 2015-6-2 上午10:36:56 参数说明
     */
    private void setTitleSize() {
        LayoutParams params = title_parentlayout.getLayoutParams();
        params.height = (int) (Constants.screenHeight * 0.06563);
        title_parentlayout.setLayoutParams(params);
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
