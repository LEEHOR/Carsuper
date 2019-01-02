package com.carsuper.coahr.mvp.presenter.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToPayContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.commodityOrder.NeedToPayModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.NeedToPayFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class NeedToPayPresenter extends BasePresenter<NeedToPayContract.View,NeedToPayContract.Model> implements NeedToPayContract.Presenter {
    @Inject
    public NeedToPayPresenter(NeedToPayFragment mview, NeedToPayModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getCommodityOrderDetail(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCommodityOrderDetail(map);
        }
    }

    @Override
    public void onGetCommodityOrderDetialSuccess(CommodityOrderDetailBean bean) {
        if (getView() != null) {
            getView().onGetCommodityOrderDetialSuccess(bean);
        }
    }

    @Override
    public void onGetCommodityOrderDetailFailure(String failure) {
        if (getView() != null) {
            getView().onGetCommodityOrderDetailFailure(failure);
        }
    }

    @Override
    public void cancelCommodityOrder(Map<String, String> map) {
        if (mModle != null) {
            mModle.cancelCommodityOrder(map);
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
