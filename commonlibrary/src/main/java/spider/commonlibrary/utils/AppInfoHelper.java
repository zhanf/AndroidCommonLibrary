/*
 * ApplicationInfoHelper.java
 * classes : com.spider.paiwoya.common.ApplicationInfoHelper
 * @author liujun
 * V 1.0.0
 * Create at 2014-12-26 上午9:12:48
 * Copyright (c) 2014年  Spider. All Rights Reserved.
 */
package spider.commonlibrary.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * @ClassName: AppInfoHelper
 * @Description: 应用信息帮助类
 */
public class AppInfoHelper {
    private static final String TAG = "AppInfoHelper";

    private static final String UMENG_CHANNEL = "UMENG_CHANNEL";
    private static final String UMENG_APPKEY = "UMENG_APPKEY";

    /**
     * 获取渠道
     *
     * @param context
     * @return
     */
    public static String getChannelId(Context context) {
        String channelId = "";
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            channelId = applicationInfo.metaData.getString(UMENG_CHANNEL);
        } catch (NameNotFoundException e) {
            LogUtil.e(TAG, e.getMessage());
        }
        return channelId;

    }

    /**
     * 获取umeng key
     *
     * @param context
     * @return
     */
    public static String getUmenAppKey(Context context) {
        String appKey = "";
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            appKey = applicationInfo.metaData.getString(UMENG_APPKEY);
        } catch (NameNotFoundException e) {
            LogUtil.e(TAG, e.getMessage());
        }
        return appKey;
    }

    /**
     * 获取versionCode
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageInfo pkgInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            versionCode = pkgInfo.versionCode;
        } catch (NameNotFoundException e) {
            LogUtil.e(TAG, e.getMessage());
        }
        return versionCode;

    }

    /**
     * 获取versionNamw
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageInfo pkgInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            versionName = pkgInfo.versionName;
        } catch (NameNotFoundException e) {
            LogUtil.e(TAG, e.getMessage());
        }
        return versionName;
    }

    /**
     * 获取应用名称
     *
     * @param context
     * @return
     */
    public static String getApplicationName(Context context) {
        String versionName = "";
        try {
            PackageInfo pkgInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            versionName = pkgInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (NameNotFoundException e) {
            LogUtil.e(TAG, e.getMessage());
        }
        return versionName;
    }
}
