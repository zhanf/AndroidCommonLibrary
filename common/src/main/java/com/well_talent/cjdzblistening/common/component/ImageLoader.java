package com.well_talent.cjdzblistening.common.component;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.well_talent.cjdzblistening.common.R;


/**
 * Created by codeest on 2016/8/2.
 */
public class ImageLoader {

    private RequestOptions options;
    private RequestOptions optionsNone;

    private ImageLoader() {
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.holder)
                .error(R.mipmap.holder)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(true);

        optionsNone = options.diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(false);
    }

    public static ImageLoader getInstance() {
        return ImageLoaderHelper.instance;
    }

    private static class ImageLoaderHelper {
        private static final ImageLoader instance = new ImageLoader();
    }


    public void load(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).apply(options).into(iv);
    }

    public void loadGif(Context context,@DrawableRes int id,ImageView iv) {
        Glide.with(context).asGif().load(id).apply(options).into(iv);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void load(Activity activity, String url, ImageView iv) {
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).apply(options).into(iv);
        }
    }

    public void loadAll(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
        Glide.with(context).load(url).apply(optionsNone).into(iv);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void loadAll(Activity activity, String url, ImageView iv) {    //不缓存，全部从网络加载
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).apply(optionsNone).into(iv);
        }
    }
}
