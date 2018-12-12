package com.lwx.multifunctiondemo;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 注意:
 *  1.这个文件必须在一级目录下
 *  2.remote host closed connection during handshake
 *   这个错误可能是证书认证原因,通过修改当前系统时间为第二天(尝试可行)
 *
 * Created by Dell on 2018/12/12.
 */
@GlideModule
public class MyAPPGlideModule extends AppGlideModule {
}
