package com.carsuper.coahr.mvp.view.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.CommodityOrderDetailContract;
import com.carsuper.coahr.mvp.presenter.myData.commodityOrder.CommodityOrderDetailPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityOrderDetailFragment extends BaseFragment<CommodityOrderDetailContract.Presenter> implements CommodityOrderDetailContract.View {
    @Inject
    CommodityOrderDetailPresenter commodityOrderDetailPresenter;


    @Override
    public CommodityOrderDetailContract.Presenter getPresenter() {
        return commodityOrderDetailPresenter;
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
