package com.carsuper.coahr.mvp.model.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToPayContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class NeedToPayModel extends BaseModel<NeedToPayContract.Presenter> implements NeedToPayContract.Model {


    @Inject
    public NeedToPayModel(){
        super();
    }

    @Override
    public void getCommodityOrderDetail(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CommodityOrderDetailBean>(getApiservice().getCommodityOrderDetail(map.get("token"),map.get("order_id"))))
            .subscribeWith(new SimpleDisposableSubscriber<CommodityOrderDetailBean>() {
                @Override
                public void _onNext(CommodityOrderDetailBean bean) {
                    if (getPresenter() != null) {
                        if (bean.getCode()==0) {
                            getPresenter().onGetCommodityOrderDetialSuccess(bean);
                        }else {
                            getPresenter().onGetCommodityOrderDetailFailure(bean.getMsg());
                        }
                    }
                }
            }));
    }

    @Override
    public void cancelCommodityOrder(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().cancelCommodityOrder(map.get("token"),map.get("order_id"),map.get("reason"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onCancelOrderSuccess(bean);
                            }else {
                                getPresenter().onCancelOrderFailure(bean.getMsg());
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
