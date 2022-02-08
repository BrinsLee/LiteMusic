package com.brins.base.mvp;

/**
 * Created by Administrator on 2017/09/13.
 */

public interface IBaseView<T extends IBasePresenter> {
    void setPresenter(T presenter);

    T getPresenter();

    void onLifeCycleDestroy();
}
