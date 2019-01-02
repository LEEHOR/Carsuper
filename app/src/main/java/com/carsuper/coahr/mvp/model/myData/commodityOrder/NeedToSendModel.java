package com.carsuper.coahr.mvp.model.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToSendContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class NeedToSendModel extends BaseModel<NeedToSendContract.Presenter> implements NeedToSendContract.Model {


    @Inject
    public NeedToSendModel(){
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
}
