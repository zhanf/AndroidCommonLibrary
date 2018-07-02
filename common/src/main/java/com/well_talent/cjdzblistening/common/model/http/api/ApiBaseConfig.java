package com.well_talent.cjdzblistening.common.model.http.api;

import android.text.TextUtils;

import com.well_talent.cjdzblistening.common.BuildConfig;

/**
 * Created by zhanf on 2018/3/5.
 */

public class ApiBaseConfig {

    public static final String APK_DOWNLOAD_URL = "http://codeest.me/apk/geeknews.apk";

    public static final String HOST_DEV = "http://api-dev.well-talent.com.cn/";//开发 测试环境
    public static final String HOST_BETA = "http://api-pre.well-talent.com.cn/";//预发布环境
    public static final String HOST_RELEASE = "http://api.well-talent.com.cn/";//正式环境

    public static final String HOST_OAUTH_QQ = "https://graph.qq.com/oauth2.0/";//QQ登录获取unionId,友盟已默认帮助调用，可不调用
//    public static final String HOST = "http://192.168.0.70:8080/";

    public static final String USER_ACCOUNT_ID = "userAccountId";
    public static final String SALT = "salt";

    public static final String LISTENING = "listening";
    public static final String DAB_APP_TYPE = "2";//2为 dzb 听力 App

    public static final int REQUEST_CODE_100 = 100;
    public static final int REQUEST_CODE_101 = 101;
    public static final int REQUEST_CODE_102 = 102;

    public static final String SUCCESS = "200";
    public static final String CODE_SALT = "403";//单点登录的salt
    public static final String USER = "6010";//手机号码已注册
    public static final String VISITOR = "6001";//手机号码未注册
    public static final String PWD_ERROR = "6002";//手机号或密码不正确
    public static final String CODE_ERROR = "6006";//验证码填写错误

    public static final String LISTENING_CATE_ID = "listening_cate_id";//基础训练分类id


    public static String getHost() {
        if (TextUtils.equals(BuildConfig.BUILD_TYPE, "debug")) {
            return HOST_DEV;
        } else if (TextUtils.equals(BuildConfig.BUILD_TYPE, "beta")) {
            return HOST_BETA;
        } else if (TextUtils.equals(BuildConfig.BUILD_TYPE, "release")) {
            return HOST_RELEASE;
        }
        return "";
    }

}
