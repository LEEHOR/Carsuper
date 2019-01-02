package com.carsuper.coahr.mvp.presenter.main;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.main.MainContract;
import com.carsuper.coahr.mvp.model.bean.MainBean;
import com.carsuper.coahr.mvp.model.main.MainModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.main.MainFragment;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
public class MainPresenter extends BasePresenter<MainContract.View,MainContract.Model> implements MainContract.Presenter {



    @Inject
    public MainPresenter(MainFragment mview,MainModel mainModel) {
        super(mview,mainModel);
    }


    @Override
    public void showError(Throwable t) {

    }



    @Override
    public void requestMain(Map<String, String> map) {
        if (mModle != null) {
            mModle.requestMain(map);
        }

    }

    @Override
    public void changeSomeFitting(Map<String, String> map) {
        if (mModle != null) {
            mModle.changeSomeFitting(map);
        }
    }

    @Override
    public void onRequestMainSuccess(MainBean mainBean) {
        if (getView() != null) {
            getView().onRequestMainSuccess(mainBean);
        }
    }

    @Override
    public void onRequestMainFailure(String failure) {
        if (getView() != null) {
            getView().onRequestMainFailure(failure);
        }
    }

    @Override
    public void onChangeSuccess(MainBean mainBean) {
        if (getView() != null) {
            getView().onChangeSuccess(mainBean);
        }
    }

    @Override
    public void onChangeFailure(String failure) {
        if (getView() != null) {
            getView().onChangeFailure(failure);
        }
    }

    @Override
    public void startLocation() {
        if (mModle != null) {
            mModle.startLocation();
        }
    }

    @Override
    public void onLocationSuccess(BDLocation location) {
        if (getView() != null) {
            getView().onLocationSuccess(location);
        }
    }

    @Override
    public void onLocationFailure(int failure) {
        if (getView() != null) {
            getView().onLocationFailure(failure);
        }
    }
}
