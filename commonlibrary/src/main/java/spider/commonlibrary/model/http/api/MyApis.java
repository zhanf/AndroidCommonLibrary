package spider.commonlibrary.model.http.api;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import spider.commonlibrary.model.bean.VersionBean;
import spider.commonlibrary.model.http.response.MyHttpResponse;

/**
 * Created by codeest on 16/10/10.
 * https://github.com/codeestX/my-restful-api
 */

public interface MyApis extends ApiHelper {

    String HOST = "http://codeest.me/api/geeknews/";

    String APK_DOWNLOAD_URL = "http://codeest.me/apk/geeknews.apk";

    /**
     * 获取最新版本信息
     * @return
     */
    @GET("version")
    Flowable<MyHttpResponse<VersionBean>> getVersionInfo();

    /**
     * 获取最新版本信息
     * @return
     */
    @GET("version")
    Observable<MyHttpResponse<VersionBean>> getHomeInfo();



}
