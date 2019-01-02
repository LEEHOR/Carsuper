package com.carsuper.coahr.mvp.contract.base;

import android.support.annotation.Nullable;

/**
 * Author： hengzwd on 2018/6/5.
 * Email：hengzwdhengzwd@qq.com
 */
public interface BaseContract {

    interface View {
        void showError(Throwable t);
        void showLoading();
        void dismissLoading();
    }

    interface Presenter {

        void showLoading();

        void dismissLoading();

        void showError(Throwable t);

        void detachView();

    }

    interface Model {

        void  AttachPresenter(BaseContract.Presenter presenter);
        void detachPresenter();
    }
}
