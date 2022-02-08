package com.brins.base.mvp;

import androidx.annotation.NonNull;
import io.reactivex.disposables.Disposable;
import java.lang.ref.WeakReference;

/**
 * Created on 2017/11/15
 *
 * @author zhengjianhui@lizhi.fm
 */

public abstract class MvpBaseObserver<T> extends BaseObserver<T> implements MvpLifeCycle {
    private Disposable mDisposable;
    private WeakReference<IMvpLifeCycleManager> mManagerRef;

    public MvpBaseObserver(IMvpLifeCycleManager lifeCycleManager) {
        super();
        mManagerRef = new WeakReference<>(lifeCycleManager);
    }

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {
        super.onSubscribe(disposable);
        mDisposable = disposable;

        if (mManagerRef != null) {
            IMvpLifeCycleManager target = mManagerRef.get();

            if (target != null) {
                target.addMvpLifeCycle(this);
            }
        }
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        super.onError(throwable);
        onLifeDestroy();
    }

    @Override
    public void onNext(T t) {
        IMvpLifeCycleManager presenter = mManagerRef != null ? mManagerRef.get() : null;

        if (presenter == null || presenter.isLifeCycleDestroy()) {
            onError(new Exception("presenter is null or destroyed, observer " + this + ", presenter " + presenter));
        } else {
            super.onNext(t);
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        onLifeDestroy();
    }

    @Override
    public void onLifeDestroy() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }

        if (mManagerRef != null) {
            IMvpLifeCycleManager target = mManagerRef.get();

            if (target != null) {
                target.removeMvpLifeCycle(this);
            }

            mManagerRef = null;
        }
    }
}
