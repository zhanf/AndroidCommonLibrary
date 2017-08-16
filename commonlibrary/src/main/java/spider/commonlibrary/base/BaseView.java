package spider.commonlibrary.base;

/**
 * Created by zhanf on 2017/7/3.
 * View基类 加载、空、无网格等状态...
 */
public interface BaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
