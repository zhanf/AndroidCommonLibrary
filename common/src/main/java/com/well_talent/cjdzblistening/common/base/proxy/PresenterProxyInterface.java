package com.well_talent.cjdzblistening.common.base.proxy;

import com.well_talent.cjdzblistening.common.base.BasePresenter;
import com.well_talent.cjdzblistening.common.base.BaseView;
import com.well_talent.cjdzblistening.common.base.factory.PresenterMvpFactory;

/**
 * @description 代理接口
 */
public interface PresenterProxyInterface<V extends BaseView,P extends BasePresenter<V>> {


    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     * @return 返回PresenterMvpFactory类型
     */
    PresenterMvpFactory<V,P> getPresenterFactory();


    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     * @param v
     */
    P getPresenter(V v);

    P getPresenter();

}
