package com.carsuper.coahr.mvp.presenter.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.CarMotorContract;
import com.carsuper.coahr.mvp.model.bean.CarMotorBean;
import com.carsuper.coahr.mvp.model.maintenance.CarMotorModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.maintenance.CarMotorFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarMotorPresenter extends BasePresenter<CarMotorContract.View,CarMotorContract.Model> implements CarMotorContract.Presenter {

    @Inject
    public CarMotorPresenter(CarMotorFragment mview, CarMotorModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getCarDisplaceMent(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCarDisplaceMent(map);
        }
    }

    @Override
    public void onGetCarDisplaceMentSuccess(CarMotorBean bean) {
        if (getView() != null) {
            getView().onGetCarDisplaceMentSuccess(bean);
        }
    }

    @Override
    public void onGetCarDisplaceMentFailure(String failure) {
        if (getView() != null) {
            getView().onGetCarDisplaceMentFailure(failure);
        }
    }
}
