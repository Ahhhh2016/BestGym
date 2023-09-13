package com.student.student_base_project.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * 保存管理所有的activity
 */
public class AppManager {
	private Stack<Activity> mActivities = new Stack<Activity>();
	private static AppManager sInstance;
	
	public synchronized static AppManager getInstance() {
		if(sInstance == null) sInstance = new AppManager();
		return sInstance;
	}
	
	private AppManager() {}
	
	/**
	 * 添加activity
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		mActivities.add(activity);
	}
	
	/**
	 * 移除当前activity
	 */
	public void removeThis() {
		if(mActivities.isEmpty()) return;
		
		Activity a = mActivities.lastElement();
		removeActivity(a);
	}
	
	/**
	 * 移除activity
	 * @param activity
	 */
	public void removeActivity(Activity activity) {
		if(activity != null) {
			mActivities.remove(activity);
		}
	}
	/**
	 * 获取最顶层activity
	 */
	public Activity getPeekActivity(){
		boolean isEmpty = mActivities.empty();
		if(isEmpty)
			return null;
		return 	mActivities.peek();
	}

	
	/**
	 * 移除指定activity
	 * @param activity
	 */
	public void finishActivity(Class<?> activity) {
		if(mActivities.isEmpty()) return;
		for(Activity a : mActivities) {
			if(a.getClass().equals(activity)) {
				a.finish();
				removeActivity(a);
				return;
			}
		}
	}
	
	/**
	 * 获取指定activity
	 * @param klass
	 * @return
	 */
	public Activity getActivity(Class<?> klass) {
		for(Activity a : mActivities) {
			if(a.getClass().equals(klass)) return a;
		}
		
		return null;
	}

	
	/**
	 * 获取所有activity
	 */
	public Stack<Activity> getAll() {
		return mActivities;
	}

	/**
	 * 结束所有Activity
	 */
	public  void finishAllActivity() {
		for (int i = 0; i < mActivities.size(); i++) {
			if (null != mActivities.get(i)) {
				mActivities.get(i).finish();
			}
		}
		mActivities.clear();
	}

	/***
	 * 结束Mainactivity以外的所有activity
	 */
	public void finishOtherActivity(Class<?> activity) {
		for (int i = 0; i < mActivities.size(); i++) {
			if (null != mActivities.get(i)) {
				if (!mActivities.get(i).getClass().equals(activity)) {
					mActivities.get(i).finish();
				}
			}
		}
	}
}
