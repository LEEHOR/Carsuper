package com.carsuper.coahr.mvp.presenter.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.ReturnOrChangeContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.commodityOrder.ReturnOrChangeModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.ReturnOrChangeFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class ReturnOrChangePresenter extends BasePresenter<ReturnOrChangeContract.View,ReturnOrChangeContract.Model> implements ReturnOrChangeContract.Presenter {
    @Inject
    public ReturnOrChangePresenter(ReturnOrChangeFragment mview, ReturnOrChangeModel mModel) {
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
    public void refundCancel(Map<String, String> map) {
        if (mModle != null) {
            mModle.refundCancel(map);
        }
    }

    @Override
    public void onRefundCancelSuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onRefundCancelSuccess(bean);
        }
    }

    @Override
    public void onRefundCancelFailure(String failure) {
        if (getView() != null) {
            getView().onRefundCancelFailure(failure);
        }
    }
}
