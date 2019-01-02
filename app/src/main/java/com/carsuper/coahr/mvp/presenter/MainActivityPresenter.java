package com.carsuper.coahr.mvp.presenter;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.MainActivityContract;
import com.carsuper.coahr.mvp.model.MainActivityModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.MainActivity;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/7/27.
 * Email：hengzwdhengzwd@qq.com
 */
public class MainActivityPresenter extends BasePresenter<MainActivityContract.View,MainActivityContract.Model> implements MainActivityContract.Presenter {

    @Inject
    public MainActivityPresenter(MainActivity mview, MainActivityModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void startLocation() {
        if (mModle != null) {
            mModle.startLocation();
        }
    }

    @Override
    public void locationSuccess(BDLocation bdLocation) {
        if (getView() != null) {
            getView().locationSuccess(bdLocation);
        }
    }

    @Override
    public void locationFailure(int failureCode) {
        if (getView() != null) {
            getView().locationFailure(failureCode);
        }
    }

}
