package com.i502tech.gitclubkotlin.utils;

import android.support.annotation.NonNull;

import com.i502tech.gitclubkotlin.api.BaseResponse;
import com.i502tech.gitclubkotlin.model.Resource;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * description $desc$
 * created by jerry on 2019/4/24.
 */
public class RxUtils {
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T extends BaseResponse> ObservableTransformer<T, T> applyBizSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> observable) {
                return observable
                        .map(new Function<T, T>() {
                            @Override
                            public T apply(T t) throws Exception {
                                if (t.getCode() != 0) {
                                    throw new RuntimeException(t.getMsg());
                                }
                                return t;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
