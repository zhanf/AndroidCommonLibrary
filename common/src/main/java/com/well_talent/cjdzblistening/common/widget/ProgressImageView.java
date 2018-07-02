package com.well_talent.cjdzblistening.common.widget;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by codeest on 16/9/27.
 */

public class ProgressImageView extends AppCompatImageView {


    private Animation animation;

    public ProgressImageView(Context context) {
        super(context);
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void start() {
        setVisibility(View.VISIBLE);
/*        if (null == animation) {
            animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setFillAfter(true); // 设置保持动画最后的状态
            animation.setDuration(3000); // 设置动画时间
            animation.setInterpolator(new AccelerateInterpolator()); // 设置插入器
        }
        animation.start();*/

        Animatable animatable = (Animatable) getDrawable();
        if (!animatable.isRunning()) {
            animatable.start();
        }
    }

    public void stop() {
        Animatable animatable = (Animatable) getDrawable();
        if (animatable.isRunning()) {
            animatable.stop();
        }
/*        if (null == animation) {
            animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setFillAfter(true); // 设置保持动画最后的状态
            animation.setDuration(3000); // 设置动画时间
            animation.setInterpolator(new AccelerateInterpolator()); // 设置插入器
        }

        animation.cancel();*/
        setVisibility(View.GONE);
    }
}
