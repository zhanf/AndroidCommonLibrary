package com.well_talent.cjdzblistening.common.utils;

import android.support.annotation.NonNull;
import android.util.Base64;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhanf on 2018/4/16.
 */

public class StringUtil {

    public static String formatMobile(@NonNull String mobile) {
        StringBuilder sb = new StringBuilder(mobile);
        sb.insert(7, " ");
        sb.insert(3, " ");
        return sb.toString();
    }

    public static String encryString(String str) {
        try {
            byte[] encryptBytes = RSAUtil.encryptByPublicKey(str.getBytes(), Base64.decode(RSAUtil.PUBLICKEY, Base64.DEFAULT));
            String encry_phone = Base64.encodeToString(encryptBytes, Base64.DEFAULT);
            LogUtil.d("encry_phone -->" + encry_phone);
            return encry_phone;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 校验是否为手机号
     *
     * @param mobile
     * @return boolean
     */
    public static boolean isMobileNumber(String mobile) {
        String strPattern = "^1\\d{10}$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(mobile);
        return m.find();
    }

}
