package com.carsuper.coahr.mvp.model.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MaintanceOrderContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.ServiceOrderCopyBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MaintanceOrderModel extends BaseModel<MaintanceOrderContract.Presenter> implements MaintanceOrderContract.Model {
    @Inject
    public MaintanceOrderModel(){
        super();
    }

    @Override
    public void getMaintanceOrderList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintanceOrderBean>(getApiservice().getMaintanceOrderList(map.get("token"),map.get("order_status"),map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<MaintanceOrderBean>() {
                    @Override
                    public void _onNext(MaintanceOrderBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onGetMaintanceOrderListSuccess(bean);
                            }else {
                                getPresenter().onGetMaintanceOrderListFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void loadMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintanceOrderBean>(getApiservice().getMaintanceOrderList(map.get("token"),map.get("order_status"),map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<MaintanceOrderBean>() {
                    @Override
                    public void _onNext(MaintanceOrderBean bean) {
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
    public void confirmServiceFinish(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().confrimServiceFinish(map.get("token"),map.get("order_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onConfirmSuccess(bean);
                            }else {
                                getPresenter().onConfirmFailure(bean.getMsg());
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

    @Override
    public void ServiceOrderCopy(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ServiceOrderCopyBean>(getApiservice().serviceOrderCopy(map.get("token"),map.get("order_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<ServiceOrderCopyBean>() {
                    @Override
                    public void _onNext(ServiceOrderCopyBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onCopySuccess(bean);
                            }else {
                                getPresenter().onCopyFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void onCancelOder(Map<String, String> map) {
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
}
