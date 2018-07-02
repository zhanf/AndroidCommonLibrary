package com.well_talent.cjdzblistening.common.base.factory;

import com.well_talent.cjdzblistening.common.base.BasePresenter;
import com.well_talent.cjdzblistening.common.base.BaseView;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @description 标注创建Presenter的注解
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BasePresenter> value();
}
