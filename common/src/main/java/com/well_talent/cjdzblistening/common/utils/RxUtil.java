package com.well_talent.cjdzblistening.common.utils;


import android.text.TextUtils;
import android.util.Log;

import com.well_talent.cjdzblistening.common.model.http.exception.ListeningException;
import com.well_talent.cjdzblistening.common.model.http.response.ListenListResponse;
import com.well_talent.cjdzblistening.common.model.http.response.ListenResponse;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by codeest on 2016/8/3.
 */
public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxFlowableSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxObservableSchedulerHelper() {    //compose简化线程
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }

        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<ListenResponse<T>, T> handleMyResult() {   //compose判断结果
        return new FlowableTransformer<ListenResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<ListenResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<ListenResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(ListenResponse<T> listenResponse) {
                        if (TextUtils.equals("200", listenResponse.getCode())) {
                            return createData(listenResponse.getData());
                        } else {
                            return Flowable.error(new ListeningException(listenResponse.getMessage(), listenResponse.getCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 统一返回结果处理(data为 object 时)
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<ListenResponse<T>, T> handResultInfo() {
        return new ObservableTransformer<ListenResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ListenResponse<T>> upstream) {
                return upstream.flatMap(new Function<ListenResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(ListenResponse<T> listenResponse) throws Exception {
                        if (TextUtils.equals("200", listenResponse.getCode())) {
                            return createListenData(listenResponse.getData());
                        } else {
                            return Observable.error(new ListeningException(listenResponse.getMessage(), listenResponse.getCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param t
     * @param <T>
     * @return
     */
    private static <T> ObservableSource<T> createListenData(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

    /**
     * 统一返回结果处理(data为List时)
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<ListenListResponse<T>, List<T>> handListResult() {
        return new ObservableTransformer<ListenListResponse<T>, List<T>>() {
            @Override
            public ObservableSource<List<T>> apply(Observable<ListenListResponse<T>> upstream) {
                return upstream.flatMap(new Function<ListenListResponse<T>, ObservableSource<List<T>>>() {
                    @Override
                    public ObservableSource<List<T>> apply(ListenListResponse<T> listenResponse) throws Exception {
                        if (TextUtils.equals("200", listenResponse.getCode())) {
                            return createListenListData(listenResponse.getData());
                        } else {
                            return Observable.error(new ListeningException(listenResponse.getMessage(), listenResponse.getCode()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param t
     * @param <T>
     * @return
     */
    private static <T> ObservableSource<List<T>> createListenListData(final List<T> t) {
        return Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(ObservableEmitter<List<T>> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }


}
