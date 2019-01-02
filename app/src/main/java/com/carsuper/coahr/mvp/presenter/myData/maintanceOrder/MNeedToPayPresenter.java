package com.carsuper.coahr.mvp.presenter.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MNeedToPayContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.maintanceOrder.MNeedToPayModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MNeedToPayFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MNeedToPayPresenter extends BasePresenter<MNeedToPayContract.View,MNeedToPayContract.Model> implements MNeedToPayContract.Presenter {
    @Inject
    public MNeedToPayPresenter(MNeedToPayFragment mview, MNeedToPayModel mModel) {
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
    public void quickPay(Map<String, String> map) {
        if (mModle != null) {
            mModle.quickPay(map);
        }
    }

    @Override
    public void onQuickPaySuccess(QuickPayBean bean) {
        if (getView() != null) {
            getView().onQuickPaySuccess(bean);
        }
    }

    @Override
    public void onQuickPayFailure(String failure) {
        if (getView() != null) {
            getView().onQuickPayFailure(failure);
        }
    }
}

