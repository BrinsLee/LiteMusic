package com.brins.base.mvp;

/**
 * Created on 2017/11/27
 *
 * @author zhengjianhui@lizhi.fm
 */

public interface IBaseModel {
    /**
     * 销毁
     */
    void onDestroy();

    void setLifeCycleDestroy(boolean isDestroy);
}
