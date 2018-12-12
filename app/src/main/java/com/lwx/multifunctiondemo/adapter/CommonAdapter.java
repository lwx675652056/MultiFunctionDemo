package com.lwx.multifunctiondemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * GridView , Listview适配器基类,,配合ViewHolder类使用
 * @description: 抽取出来的基类适配器
 * @author win64
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	protected int mlayoutId;
	protected int overPosition;

	public CommonAdapter(Context context, List<T> datas, int layoutId) {
		this.mContext = context;
		this.mDatas = datas;
		this.mlayoutId = layoutId;
		mInflater = LayoutInflater.from(context);
	}

	public CommonAdapter(Context context, int layoutId) {
		this.mContext = context;
		this.mlayoutId = layoutId;
		mInflater = LayoutInflater.from(context);
	}

	public void setListDatas(List<T> mDatas) {
		if (null != mDatas)
			this.mDatas = mDatas;
		notifyDataSetChanged();
	}

	public void addListDatas(List<T> mDatas) {
		if (null != this.mDatas && null != mDatas && mDatas.size() > 0)
			this.mDatas.addAll(mDatas);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (null == mDatas)
			return 0;
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = ViewHolder.get(mContext, convertView, parent, mlayoutId, position);
		convert(holder, getItem(position));
		measureItemHeight(holder.getConvertView());
		overPosition=position;
		System.out.println("-----overPosition-----"+overPosition);
		return holder.getConvertView();
	}

	public abstract void convert(ViewHolder holder, T bean);

	public abstract void measureItemHeight(View convertView);
}
