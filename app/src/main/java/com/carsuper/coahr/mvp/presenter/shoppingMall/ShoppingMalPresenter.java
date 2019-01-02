package com.carsuper.coahr.mvp.presenter.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.ShoppingMalContract;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean;
import com.carsuper.coahr.mvp.model.shoppingMall.ShoppingMalModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.shoppingMall.ShoppingMallFragment;
import com.carsuper.coahr.mvp.view.store.StoreFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingMalPresenter extends BasePresenter<ShoppingMalContract.View,ShoppingMalContract.Model> implements ShoppingMalContract.Presenter {

    @Inject
    public ShoppingMalPresenter(ShoppingMallFragment mview,ShoppingMalModel shoppingMalModel) {
        super(mview,shoppingMalModel);
    }


    @Override
    public void getCommodityList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCommodityList(map);
        }
    }


    @Override
    public void onGetCommodityListSuccess(ShoppingMallBean bean) {
        if (getView() != null) {
            getView().onGetCommodityListSuccess(bean);
        }
    }


    @Override
    public void onGtCommodityListFailure(String failure) {
        if (getView() != null) {
            getView().onGtCommodityListFailure(failure);
        }
    }

    @Override
    public void loadMore(Map<String, String> map) {
        if (mModle != null) {
            mModle.loadMore(map);
        }

    }

    @Override
    public void loadMoreSuccess(ShoppingMallBean bean) {
        if (getView() != null) {
            getView().loadMoreSuccess(bean);
        }
    }

    @Override
    public void loadMoreFailure(String failure) {

        if (getView() != null) {
            getView().loadMoreFailure(failure);
        }
    }
}
