package com.carsuper.coahr.mvp.presenter.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.CarHorsePowerContract;
import com.carsuper.coahr.mvp.model.bean.CarHorsePowerBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;
import com.carsuper.coahr.mvp.model.maintenance.CarHorsePowerModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.maintenance.CarHorsePowerFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarHorsePowerPresenter extends BasePresenter<CarHorsePowerContract.View, CarHorsePowerContract.Model> implements CarHorsePowerContract.Presenter {

    @Inject
    public CarHorsePowerPresenter(CarHorsePowerFragment mview, CarHorsePowerModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getCarType(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCarType(map);
        }
    }

    @Override
    public void onGetCarTypeSuccess(CarHorsePowerBean bean) {
        if (getView() != null) {
            getView().onGetCarTypeSuccess(bean);
        }
    }

    @Override
    public void onGetCarTypeFailure(String failure) {
        if (getView() != null) {
            getView().onGetCarTypeFailure(failure);
        }
    }

    @Override
    public void saveUserCarInfo(Map<String, String> map) {
        if (mModle != null) {
            mModle.saveUserCarInfo(map);
        }
    }

    @Override
    public void onSaveUserCarInfoSuccess(SaveUserCarBean bean) {
        if (getView() != null) {
            getView().onSaveUserCarInfoSuccess(bean);
        }
    }

    @Override
    public void onSaveUserCarInfoFailure(String failure) {
        if (getView() != null) {
            getView().onSaveUserCarInfoFailure(failure);
        }
    }
}
