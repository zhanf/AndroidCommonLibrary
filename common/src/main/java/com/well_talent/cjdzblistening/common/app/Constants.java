package com.well_talent.cjdzblistening.common.app;


import android.os.Environment;

import java.io.File;

/**
 * Created by codeest on 2016/8/3.
 */
public class Constants {

    public static final String APP_PACKAGE_NAME = "com.well_talent.cjdzblistening";

    //================= TYPE ====================

    public static final int TYPE_ZHIHU = 101;

    public static final int TYPE_ANDROID = 102;

    public static final int TYPE_IOS = 103;

    public static final int TYPE_WEB = 104;

    public static final int TYPE_GIRL = 105;

    public static final int TYPE_WECHAT = 106;

    public static final int TYPE_GANK = 107;

    public static final int TYPE_GOLD = 108;

    public static final int TYPE_VTEX = 109;

    public static final int TYPE_SETTING = 110;

    public static final int TYPE_LIKE = 111;

    public static final int TYPE_ABOUT = 112;

    //================= KEY ====================

    //    public static final String KEY_API = "f95283476506aa756559dd28a56f0c0b"; //需要APIKEY请去 http://apistore.baidu.com/ 申请,复用会减少访问可用次数
    public static final String KEY_API = "52b7ec3471ac3bec6846577e79f20e4c"; //需要APIKEY请去 http://www.tianapi.com/#wxnew 申请,复用会减少访问可用次数

    public static final String KEY_ALIPAY = "aex07566wvayrgxicnaraae";

    public static final String LEANCLOUD_ID = "mhke0kuv33myn4t4ghuid4oq2hjj12li374hvcif202y5bm6";

    public static final String LEANCLOUD_SIGN = "badc5461a25a46291054b902887a68eb,1480438132702";

    public static final String BUGLY_ID = "257700f3f8";

    //================= PATH ====================

    //    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_DATA = BaseApp.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "codeest" + File.separator + "GeekNews";

    public static final String SHOWMODE = "showmode";
    public static final String SENTENCEMODE = "sentencemode";
    public static final String COURSEMODE = "coursemode";

    public static final String DICTATIONISFREE = "dictationIsFree";
    public static final String DICTATIONTIME = "dictationTime";
    //    友盟appid
    public static final String UM_APPID = "5ad01373a40fa3693b000010";

    public static final String QQ_APP_ID = "1106762717";
    public static final String QQ_APP_KEY = "sk3xIUtOouYtRXvy";
    public static final String WECHAT_APP_ID = "wx488555c9eb4004bc";
    public static final String WECHAT_APP_SECRET = "ea98fc896c2201938dc130a37d7ec821";

    public static final String TAG_SingleSingOnFragment = "PATH_SINGLE_SING_ON";
    public static final String WEB_ABOUT_URL = "http://dazuiba-h5-v2.container.well-talent.com.cn/intro/about";
}
