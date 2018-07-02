package com.well_talent.cjdzblistening.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.well_talent.cjdzblistening.common.app.RouterPath;
import com.well_talent.cjdzblistening.common.app.Constants;
import com.well_talent.cjdzblistening.common.base.factory.PresenterMvpFactory;
import com.well_talent.cjdzblistening.common.base.factory.PresenterMvpFactoryImpl;
import com.well_talent.cjdzblistening.common.base.proxy.BaseMvpProxy;
import com.well_talent.cjdzblistening.common.base.proxy.PresenterProxyInterface;
import com.well_talent.cjdzblistening.common.utils.LogUtil;
import com.well_talent.cjdzblistening.common.utils.ToastUtil;


/**
 * Created by codeest on 2016/8/2.
 * MVP Fragment基类
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends SimpleFragment implements BaseView, PresenterProxyInterface<V, P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";

    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mProxy.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
//        SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
        ToastUtil.shortShow("网络连接异常，请重试"+msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    @Override
    public void singleSingOn() {
        ((DialogFragment) ARouter.getInstance().build(RouterPath.Login.PATH_SINGLE_SING_ON).navigation()).show(getChildFragmentManager(), Constants.TAG_SingleSingOnFragment);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtil.d("V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        LogUtil.d("V setPresenterFactory");
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        LogUtil.d("V getPresenterFactory");
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getPresenter(V v) {
        LogUtil.d("V getPresenter");
        return mProxy.getPresenter(v);
    }

    @Override
    public P getPresenter() {
        return getPresenter((V) this);
    }
}