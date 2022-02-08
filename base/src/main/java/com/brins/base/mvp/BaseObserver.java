package com.brins.base.mvp;

import android.util.Log;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/09/13.
 */

public abstract class BaseObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(@NonNull Disposable disposable) {

    }

    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        Log.w("OnError","BaseObserver " + throwable.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);
}
