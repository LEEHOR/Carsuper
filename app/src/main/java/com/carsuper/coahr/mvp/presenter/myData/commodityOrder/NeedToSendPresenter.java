package com.carsuper.coahr.mvp.presenter.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToSendContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.myData.commodityOrder.NeedToSendModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.NeedToSendFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class NeedToSendPresenter extends BasePresenter<NeedToSendContract.View,NeedToSendContract.Model> implements NeedToSendContract.Presenter {

    @Inject
    public NeedToSendPresenter(NeedToSendFragment mview, NeedToSendModel mModel) {
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
}
