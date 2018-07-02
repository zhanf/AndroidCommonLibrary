package com.well_talent.cjdzblistening.common.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.well_talent.cjdzblistening.common.component.RxBus;
import com.well_talent.cjdzblistening.common.utils.LogUtil;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * Created by codeest on 2016/8/2.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    private T mView;
    protected CompositeDisposable mCompositeDisposable;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxBus.getDefault().toDefaultFlowable(eventType, act));
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }

    @Override
    public void onStateError() {
        if (null != mView) {
            mView.stateError();
        }
    }

    public T getView() {
        return mView;
    }

    @Override
    public void onDestroyPresenter() {
        LogUtil.d("P onDestroy = ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        LogUtil.d("P onSaveInstanceState = ");
    }

    @Override
    public void onCreatePersenter(@Nullable Bundle savedState) {
        LogUtil.d("P onCreatePersenter = ");
    }
}
