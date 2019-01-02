package com.carsuper.coahr.mvp.presenter.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.OrderToMaintenanceContract;
import com.carsuper.coahr.mvp.model.bean.RecommendServiceBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;
import com.carsuper.coahr.mvp.model.maintenance.OrderToMaintenanceModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.maintenance.OrderToMaintenanceFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class OrderToMaintenancePresenter extends BasePresenter<OrderToMaintenanceContract.View,OrderToMaintenanceContract.Model> implements OrderToMaintenanceContract.Presenter {

    @Inject
    public OrderToMaintenancePresenter(OrderToMaintenanceFragment mview, OrderToMaintenanceModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void recommendService(Map<String, String> map) {
        if (mModle != null) {
            mModle.recommendService(map);
        }
    }


    @Override
    public void onRecommendServiceSuccess(RecommendServiceBean bean) {
        if (getView() != null) {
            getView().onRecommendServiceSuccess(bean);
        }
    }

    @Override
    public void onRecommendServiceFailure(String failure) {
        if (getView() != null) {
            getView().onRecommendServiceFailure(failure);
        }
    }

    @Override
    public void saveUserCarDistance(Map<String, String> map) {
        if (mModle != null) {
            mModle.saveUserCarDistance(map);
        }
    }

    @Override
    public void onSaveUserCarDistanceSuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onSaveUserCarDistanceSuccess(bean);
        }
    }

    @Override
    public void onSaveUserCarDistanceFailure(String failure) {
        if (getView() != null) {
            getView().onSaveUserCarDistanceFailure(failure);
        }
    }

    @Override
    public void onGetFilter(Map<String, String> map) {
        if (mModle != null) {
            mModle.onGetFilter(map);
        }
    }

    @Override
    public void onGetFilterSuccess(RecommendServiceBean bean) {
        if (getView() != null) {
            getView().onGetFilterSuccess(bean);
        }
    }

    @Override
    public void onGetFilterFailure(String failure) {
        if (getView() != null) {
            getView().onGetFilterFailure(failure);
        }
    }


}
