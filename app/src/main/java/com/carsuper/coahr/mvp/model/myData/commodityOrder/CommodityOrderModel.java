package com.carsuper.coahr.mvp.model.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.CommodityOrderContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityOrderModel extends BaseModel<CommodityOrderContract.Presenter> implements CommodityOrderContract.Model {
    @Inject
    public CommodityOrderModel(){
        super();
    }

    @Override
    public void getCommodityOrderList(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CommodityOrderBean>(getApiservice().getCommodityOrderList(map.get("token"),map.get("order_status"),map.get("page"),map.get("length"))))
            .subscribeWith(new SimpleDisposableSubscriber<CommodityOrderBean>() {
                @Override
                public void _onNext(CommodityOrderBean bean) {
                    if (getPresenter() != null) {
                        if (bean.getCode()==0) {
                            getPresenter().onGetCommodityOrderListSuccess(bean);
                        }else {
                            getPresenter().onGetCommodityOrderListFailure(bean.getMsg());
                        }
                    }
                }
            }));
    }

    @Override
    public void loadMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CommodityOrderBean>(getApiservice().getCommodityOrderList(map.get("token"),map.get("order_status"),map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<CommodityOrderBean>() {
                    @Override
                    public void _onNext(CommodityOrderBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().loadMoreSuccess(bean);
                            }else {
                                getPresenter().loadMoreFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void ensureRecieve(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().ensureRecieve(map.get("token"),map.get("order_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onEnsureRecieveSuccess(bean);
                            }else {
                                getPresenter().onEnsureRecieveFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void reminderOrder(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().reminderOrder(map.get("token"),map.get("order_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onReminderSuccess(bean);
                            }else {
                                getPresenter().onReminderFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void quickPay(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<QuickPayBean>(getApiservice().quickPay(map.get("token"),map.get("order_id"),map.get("order_type"),map.get("payment"))))
                .subscribeWith(new SimpleDisposableSubscriber<QuickPayBean>() {
                    @Override
                    public void _onNext(QuickPayBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onQuickPaySuccess(bean);
                            }else {
                                getPresenter().onQuickPayFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
