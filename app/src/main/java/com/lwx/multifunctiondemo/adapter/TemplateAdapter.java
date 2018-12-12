package com.lwx.multifunctiondemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.lwx.multifunctiondemo.R;
import com.lwx.multifunctiondemo.bean.FunctionBean;
import java.util.List;

/**
 *
 *  listview,gridview适配器模板
 *
 * Created by Dell on 2018/12/12.
 */
public class TemplateAdapter extends BaseAdapter{
    private List<FunctionBean> mEntities;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickLister mOnItemClickLister;
    public TemplateAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setListDatas(List<FunctionBean> mEntities) {
        if (null != mEntities)
            this.mEntities = mEntities;
        notifyDataSetChanged();
    }

    public void addListDatas(List<FunctionBean> mEntities) {
        if (null != this.mEntities && null != mEntities && mEntities.size() > 0)
            this.mEntities.addAll(mEntities);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (null == mEntities)
            return 0;
        return mEntities.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_function_list, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((FunctionBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final FunctionBean entity, ViewHolder holder, final int posotion) {
        // TODO implement

    }

    protected class ViewHolder {

        public ViewHolder(View view) {
        }
    }

    public OnItemClickLister getOnItemClickLister() {
        return mOnItemClickLister;
    }

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        mOnItemClickLister = onItemClickLister;
    }

    public interface OnItemClickLister{
        void onItemClick(int position,FunctionBean entity);
    }
}
