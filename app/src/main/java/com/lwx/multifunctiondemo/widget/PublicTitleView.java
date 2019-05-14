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
    public TextView mTitleName;
    public TextView mH5TitleName;
    public ImageView mBackBtn;
    public submitCallBack callBack;
    ImageView mShareBtn, mFavoriteBtn;
    TextView jingque;
    RelativeLayout score_bg;
    ImageView wenhao_image;
    View zhinengMatch;
    private ImageView mCloseBtn;
    private View mDivider;

    public PublicTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public PublicTitleView(Context context) {
        super(context);
        this.mContext = context;

    }

    //设置收藏的状态
    public void stateLike(int state) {
        mFavoriteBtn.setVisibility(state);
    }


    public void hideDivider() {
        mDivider.setVisibility(GONE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mView = LayoutInflater.from(mContext).inflate(R.layout.public_title_view, this);
        mDivider = mView.findViewById(R.id.titlt_bot_divider);
        mCloseBtn = (ImageView) mView.findViewById(R.id.img_close);
        mTitleName = (TextView) mView.findViewById(R.id.text_name_setting);
        mH5TitleName = (TextView) mView.findViewById(R.id.tv_h5_title);
        mBackBtn = (ImageView) mView.findViewById(R.id.back);
        mShareBtn = (ImageView) mView.findViewById(R.id.img_share);
        mFavoriteBtn = (ImageView) mView.findViewById(R.id.img_favorite_top);
        jingque = (TextView) mView.findViewById(R.id.jingque);
        score_bg = (RelativeLayout) mView.findViewById(R.id.score_bg);
        wenhao_image = (ImageView) mView.findViewById(R.id.wenhao_image);
        zhinengMatch = mView.findViewById(R.id.jingque2);

//        ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
//        if(BaseApplication.getmApplication().getWid()<=720)
//        {
//            layoutParams.height = 144 * 2 / 3;
//        }else if(BaseApplication.getmApplication().getWid()<=1080)
//        {
//            layoutParams.height = 144;
//
//        }else if(BaseApplication.getmApplication().getWid()<=1440)
//        {
//            layoutParams.height = 144 + 144 / 3;
//        }
//        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        this.setLayoutParams(layoutParams);
    }

    public void setState(int state) {
        mShareBtn.setVisibility(state);
        mFavoriteBtn.setVisibility(state);
    }

    public void shareVisable(int visable) {
        mShareBtn.setVisibility(visable);
    }

    public void setFavorite(boolean favorite) {
        if (favorite) {
            mFavoriteBtn.setImageDrawable(getResources().getDrawable(R.mipmap.public_titleview_full_star));
        } else {
            mFavoriteBtn.setImageDrawable(getResources().getDrawable(R.mipmap.public_titleview_empt_star));
        }
    }

    public void setScoreBG() {
        score_bg.setBackgroundResource(R.color.colors_CC504A);
    }


    public void setScoreBG(int color) {
        score_bg.setBackgroundResource(color);
    }

    public void setFXState(int state) {
        mShareBtn.setVisibility(state);
    }

    public void setjingqueState(int state) {
        jingque.setVisibility(state);
    }

    public void setjingque2State(int state) {
        zhinengMatch.setVisibility(state);
    }

    public void setjingqueText(String str) {
        jingque.setText(str);
    }

    public void jingqueListener(OnClickListener clickListener) {
        jingque.setOnClickListener(clickListener);
    }

    public void jinque2Listener(OnClickListener onClickListener) {
        zhinengMatch.setOnClickListener(onClickListener);
    }

    public void setFavoriteBtnListener(OnClickListener clickListener) {
        mFavoriteBtn.setOnClickListener(clickListener);
    }

    public void setShareBtnListener(OnClickListener clickListener) {
        mShareBtn.setOnClickListener(clickListener);
    }

    public void setFavoriteImage(int image_id) {
        mFavoriteBtn.setImageResource(image_id);
    }

    public void setFavoriteBtnVisibility(int state) {
        mFavoriteBtn.setVisibility(state);
    }

    public void setShareBtnVisibility(int state) {
        mShareBtn.setVisibility(state);
    }

    public void setBackBtnListener(OnClickListener clickListener) {
        mBackBtn.setOnClickListener(clickListener);
    }

    public void setShareImage(int image) {
        mShareBtn.setImageResource(image);
    }

    public void setWHState(int state) {
        wenhao_image.setVisibility(state);
    }

    public void setWHListener(OnClickListener clickListener) {
        wenhao_image.setOnClickListener(clickListener);
    }

    public void setBackBtnVisibility(int state) {
        mBackBtn.setVisibility(state);
    }

    public void setBackLeft(OnClickListener clickListener) {
        mBackBtn.setOnClickListener(clickListener);
    }

    public void setTitleName(String str) {
        mTitleName.setText(str);
    }

    public void setH5TitleName(String name) {
        mH5TitleName.setText(name);
    }

    public void setH5TitleVisible(int visible) {
        mH5TitleName.setVisibility(visible);
    }

    public void setTitleTextSize(int size) {
        mTitleName.setTextSize(size);
    }

    public void setTextNameColor(int cc) {
        mTitleName.setTextColor(cc);
        mH5TitleName.setTextColor(cc);
    }

    public void setTitleNameVisible(int visible) {
        mTitleName.setVisibility(visible);
    }

    public void setInterSubmitface(submitCallBack callBack) {
        this.callBack = callBack;
    }

    public void showClosePageView() {
        mCloseBtn.setVisibility(View.VISIBLE);
    }

    public void hideClosePageView() {
        mCloseBtn.setVisibility(View.GONE);
    }

    public void setClosePageIma(int ima) {
        mCloseBtn.setImageResource(ima);
    }

    //点击关闭按钮退出page页面
    public void addClosePageClickListener(OnClickListener onClickListener) {
        mCloseBtn.setOnClickListener(onClickListener);
    }

    public void setjingqueTextColor(int color) {
        jingque.setTextColor(color);
    }

    public void setBtnEnable(boolean enable) {
        mShareBtn.setEnabled(enable);
        mFavoriteBtn.setEnabled(enable);
    }

    public interface submitCallBack {
        public void submit();
    }

    public void setBackImage(int image) {
        mBackBtn.setImageResource(image);
    }
}
