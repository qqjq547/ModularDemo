package com.hjq.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 本地Preference的存取工具类
 *
 * @author huang jiaqiang
 */
public class PrefUtil {
    private SharedPreferences sharedPreference;
    private static PrefUtil preference = null;
    public static void init(Context context) {
        preference = new PrefUtil(context);
    }

    public static synchronized PrefUtil getInstance() {
        return preference;
    }

    private PrefUtil(Context context) {
        String packageName = context.getPackageName() + "_preferences";
        sharedPreference = context.getSharedPreferences(packageName, Context.MODE_PRIVATE);
    }

    public void clean() {
        Editor editor = sharedPreference.edit();
        editor.clear();
        editor.commit();
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        boolean tid = sharedPreference.getBoolean(name, defaultValue);
        return tid;
    }

    public int getInt(String name, int defaultValue) {
        int tid = sharedPreference.getInt(name, defaultValue);
        return tid;
    }

    public long getLong(String name, long defaultValue) {
        long tid = sharedPreference.getLong(name, defaultValue);
        return tid;
    }

    public String getString(String name, String defaultValue) {
        String tid = sharedPreference.getString(name, defaultValue);
        return tid;
    }

    public float getFloat(String name, float defaultValue) {
        float tid = sharedPreference.getFloat(name, defaultValue);
        return tid;
    }

    public void setInt(String name, int value) {
        Editor edit = sharedPreference.edit();
        edit.putInt(name, value);
        edit.commit();
    }

    public void setLong(String name, long value) {
        Editor edit = sharedPreference.edit();
        edit.putLong(name, value);
        edit.commit();
    }

    public void setBoolean(String name, boolean value) {
        Editor edit = sharedPreference.edit();
        edit.putBoolean(name, value);
        edit.commit();
    }

    public void setString(String name, String value) {
        Editor edit = sharedPreference.edit();
        edit.putString(name, value);
        edit.commit();
    }

    public void setFloat(String name, float value) {
        Editor edit = sharedPreference.edit();
        edit.putFloat(name, value);
        edit.commit();
    }
    

}
