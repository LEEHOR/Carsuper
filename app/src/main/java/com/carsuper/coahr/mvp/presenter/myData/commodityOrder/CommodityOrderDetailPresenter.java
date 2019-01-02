package com.carsuper.coahr.mvp.presenter.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.CommodityOrderDetailContract;
import com.carsuper.coahr.mvp.model.myData.commodityOrder.CommodityOrderDetailModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.CommodityOrderDetailFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityOrderDetailPresenter extends BasePresenter<CommodityOrderDetailContract.View,CommodityOrderDetailContract.Model> implements CommodityOrderDetailContract.Presenter {
    @Inject
    public CommodityOrderDetailPresenter(CommodityOrderDetailFragment mview, CommodityOrderDetailModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
