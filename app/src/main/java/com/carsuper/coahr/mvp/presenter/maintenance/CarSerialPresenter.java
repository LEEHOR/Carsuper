package com.carsuper.coahr.mvp.presenter.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.CarSerialContract;
import com.carsuper.coahr.mvp.model.bean.CarSerialBean;
import com.carsuper.coahr.mvp.model.maintenance.CarSerialModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.maintenance.CarSerialFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarSerialPresenter extends BasePresenter<CarSerialContract.View,CarSerialContract.Model> implements CarSerialContract.Presenter {


    @Inject
    public CarSerialPresenter(CarSerialFragment mview, CarSerialModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getCarSerial(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCarSerial(map);
        }
    }

    @Override
    public void onGetCarSerialSuccess(CarSerialBean bean) {
        if (getView() != null) {
            getView().onGetCarSerialSuccess(bean);
        }
    }

    @Override
    public void onGetCarSerialFailure(String failure) {
        if (getView() != null) {
            getView().onGetCarSerialFailure(failure);
        }
    }
}
