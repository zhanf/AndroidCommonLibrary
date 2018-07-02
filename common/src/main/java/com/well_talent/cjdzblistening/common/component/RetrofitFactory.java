package com.well_talent.cjdzblistening.common.component;

import android.support.annotation.NonNull;

import com.well_talent.cjdzblistening.common.BuildConfig;
import com.well_talent.cjdzblistening.common.app.Constants;
import com.well_talent.cjdzblistening.common.model.http.api.ApiBaseConfig;
import com.well_talent.cjdzblistening.common.utils.LogUtil;
import com.well_talent.cjdzblistening.common.utils.SpUtil;
import com.well_talent.cjdzblistening.common.utils.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/7/7.
 */

public class RetrofitFactory {

    private WeakHashMap<String, Retrofit> retrofitMap = new WeakHashMap<>();
    private OkHttpClient okHttpClient;

    private RetrofitFactory() {
        okHttpClient = provideClient();
    }

    public static RetrofitFactory getInstance() {
        return RetrofitManagerHelper.instance;
    }

    private static class RetrofitManagerHelper {
        private static final RetrofitFactory instance = new RetrofitFactory();
    }

    public <T> T create(Class<T> clazz) {
        Retrofit retrofit = RetrofitManagerHelper.instance.getRetrofit(ApiBaseConfig.getHost());
        return retrofit.create(clazz);
    }

    public <T> T createOauth(Class<T> clazz) {
        Retrofit retrofit = RetrofitManagerHelper.instance.getRetrofit(ApiBaseConfig.HOST_OAUTH_QQ);
        return retrofit.create(clazz);
    }

    private Retrofit getRetrofit(String baseUrl) {

        Retrofit retrofit = retrofitMap.get(baseUrl);
        LogUtil.i(baseUrl);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();


            retrofitMap.put(baseUrl, retrofit);
        }
        return retrofit;

    }

    private OkHttpClient provideClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
                return response;
            }
        };
        Interceptor apikey = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl.Builder authorizedUrlBuilder = request.url()
                        .newBuilder()
                        .scheme(request.url().scheme())
                        .host(request.url().host())
                        .addQueryParameter(ApiBaseConfig.USER_ACCOUNT_ID, String.valueOf(SpUtil.getUserInt(ApiBaseConfig.USER_ACCOUNT_ID, 0)))
                        .addQueryParameter(ApiBaseConfig.SALT, String.valueOf(SpUtil.getSettingString(ApiBaseConfig.SALT, "")));

                // 新的请求
                Request newRequest = request.newBuilder()
                        .method(request.method(), request.body())
                        .url(authorizedUrlBuilder.build())
                        .build();

                return chain.proceed(newRequest);
            }
        };
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(apikey)
                .cache(cache)
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                //错误重连
                .retryOnConnectionFailure(true);
        return builder.build();
    }

}
