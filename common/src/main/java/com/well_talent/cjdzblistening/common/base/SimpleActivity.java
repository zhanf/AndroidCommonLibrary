package com.well_talent.cjdzblistening.common.base;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.well_talent.cjdzblistening.common.R;
import com.well_talent.cjdzblistening.common.utils.StatusBarCompat;


/**
 * Created by codeest on 16/8/11.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends AppCompatActivity {

    protected Activity mContext;
    protected TextView mToolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mContext = this;
        StatusBarCompat.compat(this, true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        onViewCreated(savedInstanceState);
        initViewAndData();
    }

    protected void setToolBar(Toolbar toolbar, String title, @ColorRes int color) {
        mToolbarTitle = findViewById(R.id.tv_toolbar_title);
        if (null != mToolbarTitle && !TextUtils.isEmpty(title)) {
            mToolbarTitle.setText(title);
            mToolbarTitle.setTextColor(getResources().getColor(color));
        }
//        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    protected void setToolbarTitle(String title) {
        if (null != mToolbarTitle && !TextUtils.isEmpty(title)) {
            mToolbarTitle.setText(title);
            mToolbarTitle.setTextColor(getResources().getColor(R.color.black));
        }
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        setToolBar(toolbar, title, R.color.black);
    }

    //TODO Fragment弹栈
    protected void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            //pop();
        } else {
            finish();
        }
    }

    protected void onViewCreated(Bundle savedInstanceState) {

    }

    /**
     * 打开一个Activity 默认 不关闭当前activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }


    /**
     * 打开一个Activity 默认 关闭当前activity
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    /**
     * 打开一个Activity 默认 传值
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null) intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

    @Override
    public void onLowMemory() {
        Glide.get(this).clearMemory();
        super.onLowMemory();
    }

    protected abstract int getLayout();

    protected abstract void initViewAndData();

}
