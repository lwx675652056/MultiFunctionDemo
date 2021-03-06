package com.lwx.multifunctiondemo.utils;

import com.lwx.multifunctiondemo.bean.FunctionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 2018/12/12.
 *
 * 数据集合
 */

public class ModelUtils {

    /**
     * 主页面list数据
     * @return
     */
    public static List<FunctionBean> getFunctionList(){
        List<FunctionBean> functionBeans= new ArrayList<>();
        functionBeans.add(new FunctionBean("seekbar","带渐变色的seekbar,并修改thumb图片"));
        functionBeans.add(new FunctionBean("背景缩放","仿京东商品详情背景3D缩小"));
        functionBeans.add(new FunctionBean("webview","封装webview工具类整合各种情况"));
        return functionBeans;
    }
}
