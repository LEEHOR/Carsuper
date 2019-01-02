package com.carsuper.coahr.mvp.presenter.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityRecommendContract;
import com.carsuper.coahr.mvp.model.shoppingMall.CommodityRecommendModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityRecommendFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityRecommendPresenter extends BasePresenter<CommodityRecommendContract.View,CommodityRecommendContract.Model> implements CommodityRecommendContract.Presenter {

    @Inject
    public CommodityRecommendPresenter(CommodityRecommendFragment mview, CommodityRecommendModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
