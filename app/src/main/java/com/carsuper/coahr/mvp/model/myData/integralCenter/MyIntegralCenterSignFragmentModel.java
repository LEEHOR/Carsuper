package com.carsuper.coahr.mvp.model.myData.integralCenter;

import com.carsuper.coahr.mvp.contract.myData.integralCenter.MyIntegralCenterSignFragmentContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

import javax.inject.Inject;

public class MyIntegralCenterSignFragmentModel extends BaseModel<MyIntegralCenterSignFragmentContract.Presenter> implements MyIntegralCenterSignFragmentContract.Model {

    @Inject
    public MyIntegralCenterSignFragmentModel() {
        super();
    }
    @Override
    public void startSign(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().signin(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onSigninSuccess(bean);
                            }else {
                                getPresenter().onSignInFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getExchangeList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ExchangeMallList>(getApiservice().getExchangeMallList(map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<ExchangeMallList>() {
                    @Override
                    public void _onNext(ExchangeMallList s) {
                        if (getPresenter() != null) {
                            if (s.getCode()==0) {
                                getPresenter().getExchangeListSuccess(s);
                            } else {
                                getPresenter().getExchangeListFail(s.getMsg());
                            }
                        }

                    }
                }));
    }
}
