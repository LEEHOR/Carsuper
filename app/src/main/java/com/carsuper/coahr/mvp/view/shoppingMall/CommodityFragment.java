package com.carsuper.coahr.mvp.view.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityContract;
import com.carsuper.coahr.mvp.presenter.shoppingMall.CommodityPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityFragment extends BaseFragment<CommodityContract.Presenter> implements CommodityContract.View {

    @Inject
    CommodityPresenter commodityPresenter;
    @Override
    public CommodityContract.Presenter getPresenter() {
        return commodityPresenter;
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
