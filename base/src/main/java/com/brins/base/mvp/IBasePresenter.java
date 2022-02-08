package com.brins.base.mvp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/09/13.
 *
 * @author xiongbo
 *
 * TODO:自动生成整个Component的插件
 */

public interface IBasePresenter {


    IBaseModel getModel();

    /**
     * presenter 销毁，释放资源，一般是跟随ui销毁
     * 比如
     * {@link View#onDetachedFromWindow()},
     * {@link Activity#onDestroy()}，
     * {@link Fragment#onDestroyView()}
     */
    void onDestroy();

    /**
     * 停止逻辑执行，比如在View 暂停进入后台时候等等，可以停止逻辑的执行
     */
    void onStopLogic();

    /**
     * 初始化。一般是在ui添加到页面的时候，
     * 比如
     * {@link View#onAttachedToWindow()}，
     * {@link Activity#onCreate(Bundle)},
     * {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     *
     * @param context context
     */
    void init(Context context);


    /**
     * 开始逻辑执行，比如View 进入前台时候等等，重新开始执行逻辑
     */
    void onStartLogic();

}
