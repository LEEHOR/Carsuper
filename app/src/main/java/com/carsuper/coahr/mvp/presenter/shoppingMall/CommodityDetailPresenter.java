package com.carsuper.coahr.mvp.presenter.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityDetailContract;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.shoppingMall.CommodityDetailModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityDetailFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityDetailPresenter extends BasePresenter<CommodityDetailContract.View,CommodityDetailContract.Model> implements CommodityDetailContract.Presenter {
    @Inject
    public CommodityDetailPresenter(CommodityDetailFragment mview, CommodityDetailModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getCommodityDetail(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCommodityDetail(map);
        }
    }

    @Override
    public void onGetCommodityDetailSuccess(CommodityDetailBean bean) {
        if (getView() != null) {
            getView().onGetCommodityDetailSuccess(bean);
        }
    }

    @Override
    public void onGetCommodityDetailFailure(String failure) {
        if (getView() != null) {
            getView().onGetCommodityDetailFailure(failure);
        }
    }

    @Override
    public void addToShoppingCart(Map<String, String> map) {
        if (mModle != null) {
            mModle.addToShoppingCart(map);
        }
    }

    @Override
    public void onAddSuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onAddSuccess(bean);
        }
    }

    @Override
    public void onAddFailure(String failure) {
        if (getView() != null) {
            getView().onAddFailure(failure);
        }
    }
}
