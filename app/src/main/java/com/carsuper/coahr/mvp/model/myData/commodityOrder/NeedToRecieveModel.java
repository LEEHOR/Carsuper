package com.carsuper.coahr.mvp.model.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToRecieveContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class NeedToRecieveModel extends BaseModel<NeedToRecieveContract.Presenter> implements NeedToRecieveContract.Model {


    @Inject
    public  NeedToRecieveModel(){
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
}
