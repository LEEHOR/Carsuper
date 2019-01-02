package com.carsuper.coahr.mvp.presenter.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.CarBrandContract;
import com.carsuper.coahr.mvp.model.bean.CarBrandBean;
import com.carsuper.coahr.mvp.model.maintenance.CarBrandModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.maintenance.CarBrandFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarBrandPresenter extends BasePresenter<CarBrandContract.View,CarBrandContract.Model> implements CarBrandContract.Presenter {

    @Inject
    public CarBrandPresenter(CarBrandFragment mview, CarBrandModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getCarBrand(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCarBrand(map);
        }
    }

    @Override
    public void onGetCarBrandSuccess(CarBrandBean brandBean) {
        if (getView() != null) {
            getView().onGetCarBrandSuccess(brandBean);
        }
    }

    @Override
    public void onGetCarBrandFailure(String failure) {
        if (getView() != null) {
            getView().onGetCarBrandFailure(failure);
        }
    }
}
