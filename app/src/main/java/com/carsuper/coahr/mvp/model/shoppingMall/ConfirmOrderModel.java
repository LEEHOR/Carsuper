package com.carsuper.coahr.mvp.model.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.ConfirmOrderContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ConfirmCommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.SaveCommodityOrderBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ConfirmOrderModel extends BaseModel<ConfirmOrderContract.Presenter> implements ConfirmOrderContract.Model {
    @Inject
    public ConfirmOrderModel(){
        super();
    }


    @Override
    public void confirmCommodityOrder(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ConfirmCommodityOrderBean>(getApiservice().confirmCommodityOrder(map)))
        .subscribeWith(new SimpleDisposableSubscriber<ConfirmCommodityOrderBean>() {
            @Override
            public void _onNext(ConfirmCommodityOrderBean bean) {
                if (getPresenter() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onConfirmOrderSuccess(bean);
                    }else {
                        getPresenter().onConfirmOrderFailure(bean.getMsg());
                    }
                }
            }
        }));
    }

    @Override
    public void saveCommodityOrder(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ConfirmOrderBean>(getApiservice().saveCommodityOrder(map)))
                .subscribeWith(new SimpleDisposableSubscriber<ConfirmOrderBean>() {
                    @Override
                    public void _onNext(ConfirmOrderBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onSaveCommodityOrderSuccess(bean);
                            }else {
                                getPresenter().onSaveCommodityOrderFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
