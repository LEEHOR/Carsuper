package com.carsuper.coahr.mvp.model.main;

import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.main.Exchange_mall_Contract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/20
 * on 11:00
 */
public class Exchange_mallModel extends BaseModel<Exchange_mall_Contract.Presenter> implements Exchange_mall_Contract.Model {
  @Inject
    public Exchange_mallModel() {
        super();
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

    @Override
    public void getExchangeListMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ExchangeMallList>(getApiservice().getExchangeMallList(map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<ExchangeMallList>() {
                    @Override
                    public void _onNext(ExchangeMallList s) {
                        if (getPresenter() != null) {
                            if (s.getCode()==0) {
                                getPresenter().getExchangeListMoreSuccess(s);
                            } else {
                                getPresenter().getExchangeListMoreFail(s.getMsg());
                            }
                        }

                    }
                }));
    }

    @Override
    public void getUserInfo(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<PersonInfoBean>(getApiservice().getUserInfo(map.get("token"),map.get("uid"))))
                .subscribeWith(new SimpleDisposableSubscriber<PersonInfoBean>() {
                    @Override
                    public void _onNext(PersonInfoBean personInfoBean) {

                        if (getPresenter() != null) {
                            if (personInfoBean.getCode()==0) {
                                getPresenter().onGetInfoSuccess(personInfoBean);
                            }else {
                                getPresenter().onGetInfoFailure(personInfoBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
