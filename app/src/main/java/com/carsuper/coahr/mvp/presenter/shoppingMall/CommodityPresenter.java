package com.carsuper.coahr.mvp.presenter.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityContract;
import com.carsuper.coahr.mvp.model.shoppingMall.CommodityModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityPresenter extends BasePresenter<CommodityContract.View,CommodityContract.Model> implements CommodityContract.Presenter {

    @Inject
    public CommodityPresenter(CommodityFragment mview, CommodityModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
