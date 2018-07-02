package com.well_talent.cjdzblistening.common.utils;

import com.well_talent.cjdzblistening.common.BuildConfig;
import com.well_talent.cjdzblistening.common.app.Constants;
import com.orhanobut.logger.Logger;


/**
 * Created by codeest on 2016/8/3.
 */
public class LogUtil {

    public static boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = Constants.APP_PACKAGE_NAME;//Fixme 项目包名，需更换

    public static void e(String tag,Object o) {
        if(isDebug) {
            Logger.e(tag, o);
        }
    }

    public static void e(Object o) {
        LogUtil.e(TAG,o);
    }

    public static void w(String tag,Object o) {
        if(isDebug) {
            Logger.w(tag, o);
        }
    }

    public static void w(Object o) {
        LogUtil.w(TAG,o);
    }

    public static void d(String msg) {
        if(isDebug) {
            Logger.d(TAG,msg);
        }
    }

    public static void i(String msg) {
        if(isDebug) {
            Logger.i(TAG,msg);
        }
    }
}
