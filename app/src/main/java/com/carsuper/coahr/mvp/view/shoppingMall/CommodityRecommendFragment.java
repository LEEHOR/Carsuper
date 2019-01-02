package com.carsuper.coahr.mvp.view.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityRecommendContract;
import com.carsuper.coahr.mvp.presenter.shoppingMall.CommodityRecommendPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityRecommendFragment extends BaseFragment<CommodityRecommendContract.Presenter> implements CommodityRecommendContract.View {

    @Inject
    CommodityRecommendPresenter commodityRecommendPresenter;
    @Override
    public CommodityRecommendContract.Presenter getPresenter() {
        return commodityRecommendPresenter;
    }

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}
