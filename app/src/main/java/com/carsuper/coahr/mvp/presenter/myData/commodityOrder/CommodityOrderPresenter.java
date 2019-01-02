package com.carsuper.coahr.mvp.presenter.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.CommodityOrderContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.commodityOrder.CommodityOrderModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.CommodityOrderFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityOrderPresenter extends BasePresenter<CommodityOrderContract.View, CommodityOrderContract.Model> implements CommodityOrderContract.Presenter {
    @Inject
    public CommodityOrderPresenter(CommodityOrderFragment mview, CommodityOrderModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getCommodityOrderList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCommodityOrderList(map);
        }
    }

    @Override
    public void onGetCommodityOrderListSuccess(CommodityOrderBean bean) {
        if (getView() != null) {
            getView().onGetCommodityOrderListSuccess(bean);
        }
    }

    @Override
    public void onGetCommodityOrderListFailure(String failure) {
        if (getView() != null) {
            getView().onGetCommodityOrderListFailure(failure);
        }
    }

    @Override
    public void loadMore(Map<String, String> map) {
        if (mModle != null) {
            mModle.loadMore(map);
        }
    }

    @Override
    public void loadMoreSuccess(CommodityOrderBean bean) {
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

    @Override
    public void ensureRecieve(Map<String, String> map) {
        if (mModle != null) {
            mModle.ensureRecieve(map);
        }
    }

    @Override
    public void onEnsureRecieveSuccess(ResultBean resultBean) {
        if (getView() != null) {
            getView().onEnsureRecieveSuccess(resultBean);
        }
    }

    @Override
    public void onEnsureRecieveFailure(String failure) {
        if (getView() != null) {
            getView().onEnsureRecieveFailure(failure);
        }
    }

    @Override
    public void reminderOrder(Map<String, String> map) {
        if (mModle != null) {
            mModle.reminderOrder(map);
        }
    }

    @Override
    public void onReminderSuccess(ResultBean resultBean) {
        if (getView() != null) {
            getView().onReminderSuccess(resultBean);
        }
    }

    @Override
    public void onReminderFailure(String failure) {
        if (getView() != null) {
            getView().onReminderFailure(failure);
        }
    }

    @Override
    public void quickPay(Map<String, String> map) {
        if (mModle != null) {
            mModle.quickPay(map);
        }
    }

    @Override
    public void onQuickPaySuccess(QuickPayBean bean) {
        if (getView() != null) {
            getView().onQuickPaySuccess(bean);
        }
    }

    @Override
    public void onQuickPayFailure(String failure) {
        if (getView() != null) {
            getView().onQuickPayFailure(failure);
        }
    }
}
