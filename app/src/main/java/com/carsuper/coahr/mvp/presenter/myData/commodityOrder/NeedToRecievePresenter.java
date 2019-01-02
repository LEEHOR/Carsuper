package com.carsuper.coahr.mvp.presenter.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToRecieveContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.commodityOrder.NeedToRecieveModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.NeedToRecieveFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class NeedToRecievePresenter extends BasePresenter<NeedToRecieveContract.View,NeedToRecieveContract.Model> implements NeedToRecieveContract.Presenter {
    @Inject
    public NeedToRecievePresenter(NeedToRecieveFragment mview, NeedToRecieveModel mModel) {
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
    public void ensureRecieve(Map<String, String> map) {
        if (mModle != null) {
            mModle.ensureRecieve(map);
        }
    }

    @Override
    public void onEnsureRecieveSuccess(ResultBean resultBean) {
        if (getView() != null) {
            getView().onEnsureRecieveSuccess(resultBean);
        }
    }

    @Override
    public void onEnsureRecieveFailure(String failure) {
        if (getView() != null) {
            getView().onEnsureRecieveFailure(failure);
        }
    }
}
