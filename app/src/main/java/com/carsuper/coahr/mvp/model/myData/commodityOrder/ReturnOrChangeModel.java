package com.carsuper.coahr.mvp.model.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.ReturnOrChangeContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class ReturnOrChangeModel extends BaseModel<ReturnOrChangeContract.Presenter> implements ReturnOrChangeContract.Model {


    @Inject
    public ReturnOrChangeModel(){
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
    public void refundCancel(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().refundCancel(map.get("token"),map.get("refund_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onRefundCancelSuccess(bean);
                            }else {
                                getPresenter().onRefundCancelFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
