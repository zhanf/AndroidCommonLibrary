package com.well_talent.cjdzblistening.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.well_talent.cjdzblistening.common.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: liujun
 * Date: 2015-10-30
 * Time: 09:02
 * Des:底部tab
 */
public class BottomTabLayout extends LinearLayout {
    private static final int DEFAULT_TXT_SIZE = 10;
    private Drawable tabBackground;
    private int txtColor;
    private int txtSelectedColor;
    private int txtSize;
    private View selectedTab;
    private List<Tab> tabs = new ArrayList<>();
    private OnTabClickListener onTabClickListener;

    public BottomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BottomTabLayout);
        txtColor = typedArray.getColor(R.styleable.BottomTabLayout_tabview_txt_color, context.getResources().getColor(R.color.tabview_txt_color));
        txtSelectedColor = typedArray.getColor(R.styleable.BottomTabLayout_tabview_txt_selected_color, context.getResources().getColor(R.color.tabview_txt_selected_color));
        txtSize = typedArray.getDimensionPixelSize(R.styleable.BottomTabLayout_tabview_txt_size, DEFAULT_TXT_SIZE);
        tabBackground = typedArray.getDrawable(R.styleable.BottomTabLayout_tabview_background);
        typedArray.recycle();
    }

    public void addTabs(List<Tab> tabList) {
        if (tabList == null) {
            return;
        }
        for (int i = 0; i < tabList.size(); i++) {
            addTab(tabList.get(i));
        }
    }


    public void addTab(Tab tab) {
        if (tab == null) {
            return;
        }
        tabs.add(tab);
        View tabView = createTab(tab);
        addView(tabView);

    }

    private View createTab(Tab tab) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tab, null, false);
        if (tabBackground != null) {
            view.setBackgroundDrawable(tabBackground.getConstantState().newDrawable());
        }
        LayoutParams params = new LayoutParams(0, -1);
        params.weight = 1;
        view.setLayoutParams(params);
        ImageView img = (ImageView) view.findViewById(R.id.tab_img);
        img.setImageResource(tab.unselectedImgId);
        TextView txt = (TextView) view.findViewById(R.id.tab_txt);
        txt.setText(tab.txt);
        txt.setTextColor(txtColor);
        txt.setTextSize(txtSize);
        view.setTag(tab);
        final int pos = getChildCount();
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(pos);
                if (onTabClickListener != null) {
                    onTabClickListener.onBottomTabClick(pos);
                }
            }
        });
        return view;
    }

    public void selectTab(int pos) {
        if (pos + 1 > getChildCount()) {
            return;
        }
        View tab = getChildAt(pos);
        if (selectedTab == null || selectedTab != tab) {
            changeTabStyle(true, tab);
            if (selectedTab != null) {
                changeTabStyle(false, selectedTab);
            }
            selectedTab = tab;
        }
    }

    private void changeTabStyle(boolean selected, View tabView) {
        Tab tab = (Tab) tabView.getTag();
        ImageView img = (ImageView) tabView.findViewById(R.id.tab_img);
        TextView txt = (TextView) tabView.findViewById(R.id.tab_txt);
        if (selected) {
            img.setImageResource(tab.selectedImgId);
            txt.setTextColor(txtSelectedColor);
        } else {
            img.setImageResource(tab.unselectedImgId);
            txt.setTextColor(txtColor);
        }
    }

    public void setOnTabClickListener(OnTabClickListener listener) {
        this.onTabClickListener = listener;
    }

    public static class Tab {

        private String txt;
        private int unselectedImgId;
        private int selectedImgId;

        public Tab(String txt, int unselectedImgId, int selectedImgId) {
            this.txt = txt;
            this.unselectedImgId = unselectedImgId;
            this.selectedImgId = selectedImgId;
        }
    }

    public static interface OnTabClickListener {
        void onBottomTabClick(int pos);
    }
}