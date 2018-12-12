package com.lwx.multifunctiondemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @version 创建时间：2016-4-13 下午13:44:47 类说明 自定义GridView解决与ScrollView的冲突
 */

public class GridViewForScrollView extends GridView {

	public GridViewForScrollView(Context context) {
		super(context);
	}

	public GridViewForScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GridViewForScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	/**
	 * 重写该方法，达到使ListView适应ScrollView的效果
	 */
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
