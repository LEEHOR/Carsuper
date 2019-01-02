package com.carsuper.coahr.mvp.presenter.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.myData.commodityOrder.NeedToEvaluateModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.NeedToEvaluateFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/9.
 * Email：hengzwdhengzwd@qq.com
 */
public class NeedToEvaluatePresenter extends BasePresenter<NeedToEvaluateContract.View,NeedToEvaluateContract.Model> implements NeedToEvaluateContract.Presenter {

    @Inject
    public NeedToEvaluatePresenter(NeedToEvaluateFragment mview, NeedToEvaluateModel mModel) {
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
