package com.carsuper.coahr.mvp.presenter.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.LogisticsContract;
import com.carsuper.coahr.mvp.model.bean.LogisticsBean;
import com.carsuper.coahr.mvp.model.myData.commodityOrder.LogisticsModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.LogisticsFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/23.
 * Email：hengzwdhengzwd@qq.com
 */
public class LogisticsPresenter extends BasePresenter<LogisticsContract.View,LogisticsContract.Model> implements LogisticsContract.Presenter {

    @Inject
    public LogisticsPresenter(LogisticsFragment mview, LogisticsModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getLogistics(Map<String, String> map) {
        if (mModle != null) {
            mModle.getLogistics(map);
        }
    }

    @Override
    public void onGetLogisticsSuccess(LogisticsBean bean) {
        if (getView() != null) {
            getView().onGetLogisticsSuccess(bean);
        }
    }

    @Override
    public void onGetLogisticsFailure(String failure) {
        if (getView() != null) {
            getView().onGetLogisticsFailure(failure);
        }
    }
}
