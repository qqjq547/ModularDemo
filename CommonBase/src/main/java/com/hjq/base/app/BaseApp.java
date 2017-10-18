package com.hjq.base.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.hjq.base.utils.PrefUtil;

import java.util.ArrayList;




public class BaseApp extends Application {
    private static BaseApp instance;
    private ArrayList<Activity> activityList = new ArrayList<Activity>();

    public static synchronized BaseApp getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        PrefUtil.init(this);
        
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    
    public void addActivity(Activity a) {
        activityList.add(a);
    }

    public void removeActivity(Activity a) {
        activityList.remove(a);
    }
    
    public void finishActivity() {
        for (Activity activity : activityList) {
            if (null != activity) {
                activity.finish();
            }
        }
        activityList.clear();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
