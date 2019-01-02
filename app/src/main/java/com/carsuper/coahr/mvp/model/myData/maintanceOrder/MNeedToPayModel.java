package com.carsuper.coahr.mvp.model.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MNeedToPayContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MNeedToPayModel  extends BaseModel<MNeedToPayContract.Presenter> implements MNeedToPayContract.Model {


    @Inject
    public MNeedToPayModel(){
        super();
    }

    @Override
    public void getMaintanceOrderDetail(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintanceOrderDetailBean>(getApiservice().getMaintanceOrderDetail(map.get("token"),map.get("order_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<MaintanceOrderDetailBean>() {
                    @Override
                    public void _onNext(MaintanceOrderDetailBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onGetMaintanceOrderDetialSuccess(bean);
                            }else {
                                getPresenter().onGetMaintanceOrderDetailFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void cancelMaintanceOrder(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().cancelMaintanceOrder(map.get("token"),map.get("order_id"),map.get("reason"))))
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
