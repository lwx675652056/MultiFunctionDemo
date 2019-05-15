package com.lwx.multifunctiondemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwx.multifunctiondemo.R;

/**
 * 公共的title
 */
public class PublicTitleView extends LinearLayout {

    public View mView;
    public Context mContext;
    private RelativeLayout mTitlebar;
    //中间文字
    private TextView mTitleName;
    //返回按钮
    private ImageView mBackBtn;
    //右侧第一个图片和第二个图片,第二个图片在第一个的左边
    private ImageView mRightFirstImageView, mRightSecondImageView;
    //右侧文字,默认隐藏
    private TextView mRightText;
    //关闭按钮
    private ImageView mCloseBtn;
    //底部分割线
    private View mDivider;

    public PublicTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public PublicTitleView(Context context) {
        super(context);
        this.mContext = context;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mView = LayoutInflater.from(mContext).inflate(R.layout.public_title_view, this);
        mDivider = mView.findViewById(R.id.bot_divider_titlebar);
        mCloseBtn = (ImageView) mView.findViewById(R.id.img_close_titlebar);
        mTitleName = (TextView) mView.findViewById(R.id.text_name_titlebar);
        mBackBtn = (ImageView) mView.findViewById(R.id.img_back_titlebar);
        mRightFirstImageView = (ImageView) mView.findViewById(R.id.img_share_titlebar);
        mRightSecondImageView = (ImageView) mView.findViewById(R.id.img_favorite_titlebar);
        mRightText = (TextView) mView.findViewById(R.id.text_right_titlebar);
        mTitlebar = (RelativeLayout) mView.findViewById(R.id.rl_titlebar);
    }

    /**
     * 设置右侧第一个imageview图片
     *
     * @param image
     */
    public void setmRightFirstImageViewImage(int image) {
        mRightFirstImageView.setImageResource(image);
    }

    /**
     * 设置右侧第一个imageview点击监听
     *
     * @param clickListener
     */
    public void setRightFirstImageViewListener(OnClickListener clickListener) {
        mRightFirstImageView.setOnClickListener(clickListener);
    }

    /**
     * 设置右侧第一个imageview是否显示
     *
     * @param state
     */
    public void setRightFirstImageViewVisibility(int state) {
        mRightFirstImageView.setVisibility(state);
    }


    public void setRightImageVisibility(int state) {
        mRightFirstImageView.setVisibility(state);
        mRightSecondImageView.setVisibility(state);
    }


    /**
     * 设置右侧第二个ImageView点击监听
     *
     * @param clickListener
     */
    public void setRightSecondImageViewListener(OnClickListener clickListener) {
        mRightSecondImageView.setOnClickListener(clickListener);
    }

    /**
     * 设置右侧第二个ImageView图片
     *
     * @param image_id
     */
    public void setRightSecondImageViewImage(int image_id) {
        mRightSecondImageView.setImageResource(image_id);
    }

    /**
     * 设置右侧第二个ImageView是否显示
     *
     * @param state
     */
    public void setRightSecondImageViewVisibility(int state) {
        mRightSecondImageView.setVisibility(state);
    }


    /**
     * 设置titlebar背景色
     *
     * @param color
     */
    public void setTitlebarBGColor(int color) {
        mTitlebar.setBackgroundResource(color);
    }

    /**
     * 设置是否显示titlebar
     *
     * @param state
     */
    public void setTitlebarVisibility(int state) {
        mTitlebar.setVisibility(state);
    }

    /**
     * 设置右侧文字是否显示
     *
     * @param state
     */
    public void setRightTextVisibility(int state) {
        mRightText.setVisibility(state);
    }

    /**
     * 设置右侧文字
     *
     * @param str
     */
    public void setRightText(String str) {
        mRightText.setText(str);
    }

    /**
     * 设置右侧文字监听
     *
     * @param clickListener
     */
    public void setRightTextListener(OnClickListener clickListener) {
        mRightText.setOnClickListener(clickListener);
    }

    /**
     * 设置右侧文字颜色
     *
     * @param color
     */
    public void setRightTextColor(int color) {
        mRightText.setTextColor(color);
    }

    /**
     * 设置右侧文字大小
     *
     * @param size
     */
    public void setRightTextSize(int size) {
        mRightText.setTextSize(size);
    }

    /**
     * 设置返回监听
     *
     * @param clickListener
     */
    public void setBackBtnListener(OnClickListener clickListener) {
        mBackBtn.setOnClickListener(clickListener);
    }

    /**
     * 设置返回是否显示
     *
     * @param state
     */
    public void setBackBtnVisibility(int state) {
        mBackBtn.setVisibility(state);
    }

    /**
     * 设置返回图片
     *
     * @param image
     */
    public void setBackBtnImage(int image) {
        mBackBtn.setImageResource(image);
    }

    /**
     * 设置标题文字
     *
     * @param str
     */
    public void setTitleText(String str) {
        mTitleName.setText(str);
    }

    /**
     * 设置标题文字大小
     *
     * @param size
     */
    public void setTitleTextSize(int size) {
        mTitleName.setTextSize(size);
    }

    /**
     * 设置标题文字颜色
     *
     * @param cc
     */
    public void setTextTextColor(int cc) {
        mTitleName.setTextColor(cc);
    }

    /**
     * 设置标题文字是否显示
     *
     * @param visible
     */
    public void setTitleTextVisible(int visible) {
        mTitleName.setVisibility(visible);
    }


    /**
     * 设置关闭按钮是否显示
     *
     * @param state
     */
    public void setClosePageVisibility(int state) {
        mCloseBtn.setVisibility(state);
    }

    /**
     * 设置关闭按钮图片
     *
     * @param ima
     */
    public void setClosePageImage(int ima) {
        mCloseBtn.setImageResource(ima);
    }

    /**
     * 点击关闭按钮退出page页面
     *
     * @param onClickListener
     */
    public void setClosePageClickListener(OnClickListener onClickListener) {
        mCloseBtn.setOnClickListener(onClickListener);
    }

    /**
     * 隐藏底部分割线
     */
    public void hideDivider() {
        mDivider.setVisibility(GONE);
    }

}
