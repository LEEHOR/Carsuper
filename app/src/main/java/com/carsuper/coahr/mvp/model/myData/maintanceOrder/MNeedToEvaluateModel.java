package com.carsuper.coahr.mvp.model.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToEvaluateContract;
import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MNeedToEvaluateContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MNeedToEvaluateModel extends BaseModel<MNeedToEvaluateContract.Presenter> implements MNeedToEvaluateContract.Model {


    @Inject
    public MNeedToEvaluateModel(){
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
}
