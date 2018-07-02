package com.well_talent.cjdzblistening.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.well_talent.cjdzblistening.common.app.BaseApp;

/**
 * Created by zhanf on 2018/3/2.
 */

public class SpUtil {

    public static final String USER_INFO = "user_info";//用户个人信息相关sp
    public static final String APP_SETTING = "app_setting";//系统设置sp

    /**
     * ----------------------------------------- 保存系统配置信息 以下---------------------------------------------
     */

    private static SharedPreferences getSpUser() {
        return BaseApp.getInstance().getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
    }

    public static void setUserBoolean(String key, boolean value) {
        SharedPreferences spUser = getSpUser();
        SharedPreferences.Editor editor = spUser.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getUserBoolean(String key, boolean value) {
        SharedPreferences spUser = getSpUser();
        return spUser.getBoolean(key, value);
    }

    public static void setUserString(String key, String value) {
        SharedPreferences spUser = getSpUser();
        SharedPreferences.Editor editor = spUser.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getUserString(String key, String value) {
        SharedPreferences spUser = getSpUser();
        return spUser.getString(key, value);
    }

    public static void setUserInt(String key, int value) {
        SharedPreferences spUser = getSpUser();
        SharedPreferences.Editor editor = spUser.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getUserInt(String key, int value) {
        SharedPreferences spUser = getSpUser();
        return spUser.getInt(key, value);
    }

    public static void setUserLong(String key, long value) {
        SharedPreferences spUser = getSpUser();
        SharedPreferences.Editor editor = spUser.edit();
        editor.putLong(key, value).apply();
    }

    public static long getUserLong(String key, long value) {
        SharedPreferences spUser = getSpUser();
        return spUser.getLong(key, value);
    }


    /**
     *
     */
    public static void clearUserInfo() {
        SharedPreferences spUser = getSpUser();
        SharedPreferences.Editor edit = spUser.edit();
        edit.clear();
        edit.apply();
    }


    /**
     * ----------------------------------------- 保存系统配置信息 以下---------------------------------------------
     */

    private static SharedPreferences getSpSetting() {
        return BaseApp.getInstance().getSharedPreferences(APP_SETTING, Context.MODE_PRIVATE);
    }

    public static void setSettingBoolean(String key, boolean value) {
        SharedPreferences spSetting = getSpSetting();
        SharedPreferences.Editor editor = spSetting.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getSettingBoolean(String key, boolean value) {
        SharedPreferences spSetting = getSpSetting();
        return spSetting.getBoolean(key, value);
    }

    public static void setSettingString(String key, String value) {
        SharedPreferences spSetting = getSpSetting();
        SharedPreferences.Editor editor = spSetting.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getSettingString(String key, String value) {
        SharedPreferences spSetting = getSpSetting();
        return spSetting.getString(key, value);
    }

    public static void setSettingInt(String key, int value) {
        SharedPreferences spSetting = getSpSetting();
        SharedPreferences.Editor editor = spSetting.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setSettingFloat(String key, float value) {
        SharedPreferences spSetting = getSpSetting();
        SharedPreferences.Editor editor = spSetting.edit();
        editor.putFloat(key, value);
        editor.apply();
    }


    public static int getSettingInt(String key, int value) {
        SharedPreferences spSetting = getSpSetting();
        return spSetting.getInt(key, value);
    }

    public static float getSettingFloat(String key, float value) {
        SharedPreferences spSetting = getSpSetting();
        return spSetting.getFloat(key, value);
    }

    public static void setSettingLong(String key, long value) {
        SharedPreferences spSetting = getSpSetting();
        SharedPreferences.Editor editor = spSetting.edit();
        editor.putLong(key, value).apply();
    }

    public static long getSettingLong(String key, long value) {
        SharedPreferences spSetting = getSpSetting();
        return spSetting.getLong(key, value);
    }


    public static void clearSettingInfo() {
        SharedPreferences spSetting = getSpSetting();
        SharedPreferences.Editor edit = spSetting.edit();
        edit.clear();
        edit.apply();
    }

}
