package com.lwx.multifunctiondemo.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwx.multifunctiondemo.GlideApp;


/**
 * @description: holder的复用类
 * @author win64
 */
public class ViewHolder {
	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;
	private Context mContext;
	public View getConvertView() {
		return mConvertView;
	}

	public int getPosition() {
		return mPosition;
	}

	public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
		this.mViews = new SparseArray<View>();
		mContext=context;
		this.mPosition = position;
		this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
		this.mConvertView.setTag(this);
	}

	public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
		if (null == convertView) {
			return new ViewHolder(context, parent, layoutId, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}

	/**
	 * 通过viewId获取控件
	 * 
	 * @param viewId
	 * @return T
	 * @date 2015-8-5 下午9:38:39
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);

		if (null == view) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * 给ID为viewId的TextView设置文字text，并返回this
	 * 
	 * @param viewId
	 * @param text
	 * @date 2015-8-5 下午11:05:17
	 */
	public ViewHolder setText(int viewId, String text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}

	/**
	 * @methods setImageBitmapFromRes
	 * @description 从资源文件加载位图并设置给图片
	 * @date 2015-8-24 上午11:58:21
	 * @param viewId
	 * @return 参数说明
	 */
	public ViewHolder setImageBitmapFromRes(int viewId, int resId) {
		ImageView img = getView(viewId);
		img.setImageResource(resId);
		return this;
	}

	/**
	 * @methods setImageBitmap
	 * @description 给ID为viewID的ImageView设置Bitmap，并返回this
	 * @date 2015-8-24 上午10:37:53
	 * @param viewId
	 * @param bitmap
	 * @return 参数说明
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView img = getView(viewId);
		img.setImageBitmap(bitmap);
		return this;
	}

	/**
	 * @methods setImageFromUrl
	 * @description 加载网络的图片
	 * @date 2015-8-24 上午11:48:09
	 * @param viewId
	 *            图片的id
	 * @param url
	 *            图片的url
	 * @return 参数说明
	 */
	public ViewHolder setImageFromUrl(int viewId, String url, int defaultImageId) {
		ImageView img = getView(viewId);
		//根据自己的图片加载决定
		//ImageLoader
//		ImageLoader.getInstance().displayImage(
//				url,
//				img,
//				new DisplayImageOptions.Builder().showStubImage(defaultImageId).showImageForEmptyUri(defaultImageId)
//						.showImageOnFail(defaultImageId).cacheInMemory().cacheOnDisc().resetViewBeforeLoading().build(),
//				new ImageLoadingListenerImpl());
		//Glide
		GlideApp.with(mContext).load(url).centerCrop().into(img);
		return this;
	}

}
