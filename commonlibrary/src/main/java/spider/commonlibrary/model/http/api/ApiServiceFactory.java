package spider.commonlibrary.model.http.api;

import retrofit2.Retrofit;
import spider.commonlibrary.component.RetrofitManager;


/**
 * Created by Administrator on 2017/7/10.
 */

public class ApiServiceFactory {

    public static <T extends ApiHelper> T getApiService(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit(baseUrl);
        return retrofit.create(clazz);
    }
}
