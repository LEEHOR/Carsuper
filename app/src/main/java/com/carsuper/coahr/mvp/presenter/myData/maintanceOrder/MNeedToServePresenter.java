package com.carsuper.coahr.mvp.presenter.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MNeedToServeContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.maintanceOrder.MNeedToServeModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MNeedToServeFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MNeedToServePresenter extends BasePresenter<MNeedToServeContract.View,MNeedToServeContract.Model> implements MNeedToServeContract.Presenter {
    @Inject
    public MNeedToServePresenter(MNeedToServeFragment mview, MNeedToServeModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getMaintanceOrderDetail(Map<String, String> map) {
        if (mModle != null) {
            mModle.getMaintanceOrderDetail(map);
        }
    }

    @Override
    public void onGetMaintanceOrderDetialSuccess(MaintanceOrderDetailBean bean) {
        if (getView() != null) {
            getView().onGetMaintanceOrderDetialSuccess(bean);
        }
    }

    @Override
    public void onGetMaintanceOrderDetailFailure(String failure) {
        if (getView() != null) {
            getView().onGetMaintanceOrderDetailFailure(failure);
        }
    }

    @Override
    public void cancelMaintanceOrder(Map<String, String> map) {
        if (mModle != null) {
            mModle.cancelMaintanceOrder(map);
        }
    }

    @Override
    public void onCancelOrderSuccess(ResultBean resultBean) {
        if (getView() != null) {
            getView().onCancelOrderSuccess(resultBean);
        }
    }

    @Override
    public void onCancelOrderFailure(String failure) {
        if (getView() != null) {
            getView().onCancelOrderFailure(failure);
        }
    }

    @Override
    public void confirmServiceFinish(Map<String, String> map) {
        if (mModle != null) {
            mModle.confirmServiceFinish(map);
        }
    }

    @Override
    public void onConfirmSuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onConfirmSuccess(bean);
        }
    }

    @Override
    public void onConfirmFailure(String failure) {
        if (getView() != null) {
            getView().onConfirmFailure(failure);
        }
    }
}


