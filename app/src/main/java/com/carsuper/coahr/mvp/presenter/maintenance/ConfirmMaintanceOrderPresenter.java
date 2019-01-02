package com.carsuper.coahr.mvp.presenter.maintenance;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.maintenance.ConfirmMaintanceOrderContract;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.StationRecommend;
import com.carsuper.coahr.mvp.model.maintenance.ConfirmMaintanceOrderModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.maintenance.ConfirmMaintanceOrderFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ConfirmMaintanceOrderPresenter extends BasePresenter<ConfirmMaintanceOrderContract.View,ConfirmMaintanceOrderContract.Model> implements ConfirmMaintanceOrderContract.Presenter {

    @Inject
    public ConfirmMaintanceOrderPresenter(ConfirmMaintanceOrderFragment mview, ConfirmMaintanceOrderModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void confirmMaintanceOrder(Map<String, String> map) {
        if (mModle != null) {
            mModle.confirmMaintanceOrder(map);
        }
    }

    @Override
    public void onConfirmOrderSuccess(ConfirmOrderBean bean) {
        if (getView() != null) {
            getView().onConfirmOrderSuccess(bean);
        }
    }

    @Override
    public void onConfirmOrderFailure(String failure) {
        if (getView() != null) {
            getView().onConfirmOrderFailure(failure);
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

    @Override
    public void onGetStationRecommend(Map<String, String> map) {
        if (mModle != null) {
            mModle.onGetStationRecommend(map);
        }
    }

    @Override
    public void onGetStationRecommendSuccess(StationRecommend stationRecommend) {
        if (getView() != null) {
            getView().onGetStationRecommendSuccess(stationRecommend);
        }
    }

    @Override
    public void onGetStationRecommendFailure(String failure) {
        if (getView() != null) {
            getView().onGetStationRecommendFailure(failure);
        }
    }
}
