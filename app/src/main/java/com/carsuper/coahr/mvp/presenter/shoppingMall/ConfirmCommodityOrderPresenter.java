package com.carsuper.coahr.mvp.presenter.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.ConfirmOrderContract;
import com.carsuper.coahr.mvp.model.bean.ConfirmCommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.SaveCommodityOrderBean;
import com.carsuper.coahr.mvp.model.shoppingMall.ConfirmOrderModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.shoppingMall.ConfirmCommodityOrderFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ConfirmCommodityOrderPresenter extends BasePresenter<ConfirmOrderContract.View,ConfirmOrderContract.Model> implements ConfirmOrderContract.Presenter {

    @Inject
    public ConfirmCommodityOrderPresenter(ConfirmCommodityOrderFragment mview, ConfirmOrderModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void confirmCommodityOrder(Map<String, String> map) {
        if (mModle != null) {
            mModle.confirmCommodityOrder(map);
        }
    }

    @Override
    public void onConfirmOrderSuccess(ConfirmCommodityOrderBean bean) {
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
    public void saveCommodityOrder(Map<String, String> map) {
        if (mModle != null) {
            mModle.saveCommodityOrder(map);
        }
    }

    @Override
    public void onSaveCommodityOrderSuccess(ConfirmOrderBean bean) {
        if (getView() != null) {
            getView().onSaveCommodityOrderSuccess(bean);
        }
    }

    @Override
    public void onSaveCommodityOrderFailure(String failure) {
        if (getView() != null) {
            getView().onSaveCommodityOrderFailure(failure);
        }
    }
}
