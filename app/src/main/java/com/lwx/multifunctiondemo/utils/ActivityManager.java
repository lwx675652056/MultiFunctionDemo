package com.lwx.multifunctiondemo.utils;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {
	public Stack<Activity> mActivityStack;
	private static ActivityManager instance;

	private ActivityManager() {
	}

	public static ActivityManager getInstance() {
		if (instance == null) {
			instance = new ActivityManager();
		}
		return instance;
	}

	// 退出栈顶Activity
	public void popActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			mActivityStack.remove(activity);
			activity = null;
		}
	}

	// 获得当前栈顶Activity
	public Activity currentActivity() {
		Activity activity = mActivityStack.lastElement();
		return activity;
	}

	/** 获取栈底部的Activity **/
	public Activity firstElement() {
		Activity activity = mActivityStack.firstElement();
		return activity;
	}

	// 将当前Activity推入栈中
	public void pushActivity(Activity activity) {
		if (mActivityStack == null) {
			mActivityStack = new Stack<Activity>();
		}
		mActivityStack.add(activity);
	}

	// 退出栈中所有Activity
	public void popAllActivityExceptOne(Class<? extends Activity> cls) {
		while (true) {
			Activity activity = firstElement();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);
		}
	}

	// 退出其他的activity
	public void popAllOtherActivity(Class<? extends Activity> cls) {

		for (int i = 0; i < mActivityStack.size(); i++) {
			Activity activity = mActivityStack.get(i);
			if (!activity.getClass().equals(cls)) {
				popActivity(activity);
			}
		}
	}
	//将activity全部关闭掉
	public void clearAll() {
		for (Activity activity : mActivityStack) {
			activity.finish();
		}
	}
}