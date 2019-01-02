package com.carsuper.coahr.mvp.presenter.main;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.main.CityPickerContract;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean;
import com.carsuper.coahr.mvp.model.main.CityPickerModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.main.CityPickerDialogFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/7/18.
 * Email：hengzwdhengzwd@qq.com
 */
public class CityPickPresenter extends BasePresenter<CityPickerContract.View,CityPickerContract.Model> implements CityPickerContract.Presenter {


    @Inject
    public CityPickPresenter(CityPickerDialogFragment mview, CityPickerModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getCityInfo() {
        if (mModle != null) {
            mModle.getCityInfo();
        }
    }

    @Override
    public void getCitySuccess(CityInfoBean cityInfoBean) {
        if (getView() != null) {
            getView().getCitySuccess(cityInfoBean);
        }
    }

    @Override
    public void getCityFailure(String failure) {
        if (getView() != null) {
            getView().getCityFailure(failure);
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
