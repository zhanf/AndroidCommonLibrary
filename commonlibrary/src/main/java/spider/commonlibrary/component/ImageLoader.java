package spider.commonlibrary.component;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import spider.commonlibrary.R;


/**
 * Created by codeest on 2016/8/2.
 */
public class ImageLoader {

    private RequestOptions options;
    private RequestOptions optionsNone;

    private ImageLoader() {
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.color.glide_place_color)
                .error(R.color.glide_place_color)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(true);

        optionsNone = options.diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(false);
    }

    private static ImageLoader getInstance() {
        return ImageLoaderHelper.instance;
    }

    private static class ImageLoaderHelper {
        private static final ImageLoader instance = new ImageLoader();
    }


    private void load(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).apply(options).into(iv);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void load(Activity activity, String url, ImageView iv) {
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).apply(options).into(iv);
        }
    }

    private void loadAll(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
        Glide.with(context).load(url).apply(optionsNone).into(iv);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void loadAll(Activity activity, String url, ImageView iv) {    //不缓存，全部从网络加载
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).apply(optionsNone).into(iv);
        }
    }
}
