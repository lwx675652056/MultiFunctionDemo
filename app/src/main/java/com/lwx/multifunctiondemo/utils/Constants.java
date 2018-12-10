package com.lwx.multifunctiondemo.utils;



/**
 *
 * @description: 存放常用的变量
 * @author win64
 * @date 2016-4-13 下午13:44:47
 */
public class Constants {

	public static int screenWidth;// 屏幕的宽度
	public static int screenHeight;// 屏幕的高度
	public static int screenStatus;// 屏幕的状态栏高度
	public static int titleHeight;// 屏幕的菜单栏高度

	public static String sharename = "fruitloan";//sharepreferences存储名字

	public static String USER_ID = "uid";// 用户id
	public static String USER_PHONENUM = "phonenum";// 手机号
	public static String USER_STATUS = "status";// 用户状态
	public static String ACCESS_TOKEN = "token";// token
	public static final String IS_LOGIN = "isLogin";// 是否已登录  1已登录0未登录
	public static final String IS_GUID = "true";// 是否通过引导页 true已通过false未通过

	public static final String VERIFY_CODE_SIGN="";//获取验证码后返回sign
	public static final String HOME_CURRENT_TAB_POSITION="HOME_CURRENT_TAB_POSITION";//当前tab选中位置

	public static final String UMENG_APPKEY="5becf97af1f55646370007f2";//友盟appkey
	public static final String WECHAT_APPKEY="wx0255a07d9d3e0c4f";//微信appkey
	public static final String WECHAT_SECRET="9f1b0d0a65ef267418e59f035e0ed2a3";//微信secret

	public static final String service_status="service_status";//0:未认证 1:身份认证 2:绑定银行卡 3:信用报告 4:增值服务
	public static final String has_userinfo="has_userinfo";//是否填写用户资料 ( 0:未写 1 :已写)
	public static final String Pay_Order_Status="pay_order_status";//付费订单状态
	public static final String Pay_Order_No="pay_order_no";//付费订单编号

	public static final String RealName="real_name";//真实姓名
	public static final String CardNumber="card_number";//身份证号

	public static final String CreditReport="credit_report";//信用报告url

	public static final String seekbar_money="seekbar_money";//当前seekbar上money选中值
	public static final String seekbar_month="seekbar_month";//当前seekbar上month选中值

}
