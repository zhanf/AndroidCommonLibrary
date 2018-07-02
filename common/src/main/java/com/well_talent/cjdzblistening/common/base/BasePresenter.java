package com.well_talent.cjdzblistening.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by codeest on 2016/8/2.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();

    void onStateError();

    void onDestroyPresenter();

    void onSaveInstanceState(Bundle outState);

    void onCreatePersenter(@Nullable Bundle savedState);
}
