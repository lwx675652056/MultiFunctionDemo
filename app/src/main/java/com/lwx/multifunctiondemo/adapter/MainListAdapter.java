package com.lwx.multifunctiondemo.adapter;

import android.content.Context;
import android.view.View;

import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.bean.FunctionBean;

import java.util.List;

/**
 * Created by Dell on 2018/12/12.
 */

public class MainListAdapter extends CommonAdapter<FunctionBean>{
    public MainListAdapter(Context context, List<FunctionBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, FunctionBean bean) {
        holder.setText(R.id.tv_title_function_adapter,bean.getTitle());
        holder.setText(R.id.tv_describe_function_adapter,bean.getDescribe());
    }

    @Override
    public void measureItemHeight(View convertView) {

    }
}
