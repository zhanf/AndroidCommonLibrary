package spider.commonlibrary.base.contract.example;


import spider.commonlibrary.base.BasePresenter;
import spider.commonlibrary.base.BaseView;


/**
 * View 跟 Preseneter 契约关系(绑定)事例
 */
public interface ExampleContract {

    interface View extends BaseView {

        void showUpdateDialog(String versionContent);

        void startDownloadService();
    }

    interface  Presenter extends BasePresenter<View> {

        void checkVersion(String currentVersion);


        void setNightModeState(boolean b);

        void setCurrentItem(int index);

        int getCurrentItem();

        void setVersionPoint(boolean b);

        boolean getVersionPoint();
    }
}
