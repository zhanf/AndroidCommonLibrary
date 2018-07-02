package com.well_talent.cjdzblistening.common.base.factory;

import com.well_talent.cjdzblistening.common.base.BasePresenter;
import com.well_talent.cjdzblistening.common.base.BaseView;

/**
 * @description Presenter工厂接口
 */
public interface PresenterMvpFactory<V extends BaseView,P extends BasePresenter<V>> {

    /**
     * 创建Presenter的接口方法
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
