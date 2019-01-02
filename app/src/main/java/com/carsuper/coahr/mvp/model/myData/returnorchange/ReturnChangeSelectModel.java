package com.carsuper.coahr.mvp.model.myData.returnorchange;

import com.carsuper.coahr.mvp.contract.myData.returnorchange.ReturnChangeSelectContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.RefundApplyBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 */
public class ReturnChangeSelectModel extends BaseModel<ReturnChangeSelectContract.Presenter> implements ReturnChangeSelectContract.Model {


    @Inject
    public ReturnChangeSelectModel (){
        super();
    }

    @Override
    public void refundApply(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<RefundApplyBean>(getApiservice().refundApply(map.get("token"),map.get("order_id"))))
            .subscribeWith(new SimpleDisposableSubscriber<RefundApplyBean>() {
                @Override
                public void _onNext(RefundApplyBean bean) {
                    if (getPresenter() != null) {
                        if (bean.getCode()==0) {
                            getPresenter().onRefundApplySuccess(bean);
                        }else {
                            getPresenter().onRefundApplyFailure(bean.getMsg());
                        }
                    }
                }
            }));
    }
}
