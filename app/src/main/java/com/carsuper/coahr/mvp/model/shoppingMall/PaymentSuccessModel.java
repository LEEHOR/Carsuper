package com.carsuper.coahr.mvp.model.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.PaymentSuccessContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.PaymentSuccessBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class PaymentSuccessModel extends BaseModel<PaymentSuccessContract.Presenter> implements PaymentSuccessContract.Model {
    @Inject
    public PaymentSuccessModel(){
        super();
    }

    @Override
    public void getEndingRecommend(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<PaymentSuccessBean>(getApiservice().getEndingRecommend(map.get("token"),map.get("order_id"))))
        .subscribeWith(new SimpleDisposableSubscriber<PaymentSuccessBean>() {
            @Override
            public void _onNext(PaymentSuccessBean paymentSuccessBean) {
                if (getPresenter() != null) {
                    if (paymentSuccessBean.getCode()==0) {
                        getPresenter().onGetRecommendSuccess(paymentSuccessBean);
                    }else {
                        getPresenter().onGetRecommendFailure(paymentSuccessBean.getMsg());
                    }
                }
            }
        }));
    }
}
