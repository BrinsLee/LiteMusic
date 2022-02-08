package com.brins.base.mvp;

import androidx.annotation.CallSuper;

/**
 * Created by mj.zeng on 2018/12/24.
 */

public abstract class BaseMvpComponent implements IBaseView {

    @CallSuper
    @Override
    public void onLifeCycleDestroy() {
        IBasePresenter presenter = getPresenter();
        if (null != presenter) {
            presenter.onDestroy();
            IBaseModel model = presenter.getModel();
            if (null != model) {
                model.onDestroy();
            }
        }
    }
}
