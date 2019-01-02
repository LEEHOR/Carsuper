package com.carsuper.coahr.mvp.model.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.LogisticsContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.LogisticsBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/23.
 * Email：hengzwdhengzwd@qq.com
 */
public class LogisticsModel extends BaseModel<LogisticsContract.Presenter> implements LogisticsContract.Model {


    @Inject
    public LogisticsModel(){
        super();
    }


    @Override
    public void getLogistics(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<LogisticsBean>(getApiservice().getCommodityLogistics(map.get("token"),map.get("order_id"))))
        .subscribeWith(new SimpleDisposableSubscriber<LogisticsBean>() {
            @Override
            public void _onNext(LogisticsBean logisticsBean) {
                if (getPresenter() != null) {
                    if (logisticsBean.getCode()==0) {
                        getPresenter().onGetLogisticsSuccess(logisticsBean);
                    }else {
                        getPresenter().onGetLogisticsFailure(logisticsBean.getMsg());
                    }
                }
            }
        }));
    }
}
