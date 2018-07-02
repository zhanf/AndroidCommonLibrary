package com.well_talent.cjdzblistening.common.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.well_talent.cjdzblistening.common.R;
import com.well_talent.cjdzblistening.common.app.BaseApp;


/**
 * Created by codeest on 2016/8/4.
 */
public class ToastUtil {

    static ToastUtil td;

    public static void longShow(@StringRes int resId) {
        longShow(BaseApp.getInstance().getString(resId));
    }

    public static void longShow(String msg) {
        if (td == null) {
            td = new ToastUtil(BaseApp.getInstance());
        }
        td.setText(msg);
        td.create().show();
    }

    public static void shortShow(@StringRes int resId) {
        longShow(BaseApp.getInstance().getString(resId));
    }

    public static void shortShow(String msg) {
        if (td == null) {
            td = new ToastUtil(BaseApp.getInstance());
        }
        td.setText(msg);
        td.createShort().show();
    }

    Context context;
    Toast toast;
    String msg;

    private ToastUtil(Context context) {
        this.context = context.getApplicationContext();
    }

    private Toast create() {
        if (null == toast) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);

        }
        toast.setText(msg);
        toast.setDuration(Toast.LENGTH_LONG);
        return toast;
    }

    private Toast createShort() {
        if (null == toast) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.setDuration(Toast.LENGTH_SHORT);
        return toast;
    }

    private void setText(String text) {
        msg = text;
    }
}
