package com.carsuper.coahr.mvp.model.main;

import com.carsuper.coahr.mvp.contract.main.Exchange_shopping_detail_Contract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ExchangeByStone;
import com.carsuper.coahr.mvp.model.bean.ExchangeDetail;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.model.bean.ExchangeRe;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/20
 * on 11:00
 */
public class Exchange_shopping_detailModel extends BaseModel<Exchange_shopping_detail_Contract.Presenter> implements Exchange_shopping_detail_Contract.Model {
     @Inject
    public Exchange_shopping_detailModel() {
        super();
    }

    @Override
    public void getDetail(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ExchangeDetail>(getApiservice().getExchangeDetail(map.get("c_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<ExchangeDetail>() {
                    @Override
                    public void _onNext(ExchangeDetail s) {
                        if (getPresenter() != null) {
                            if (s.getCode()==0) {
                                getPresenter().getDetailSuccess(s);
                            } else {
                                getPresenter().getDetailFailure(s.getMsg());
                            }
                        }

                    }
                }));
    }

    @Override
    public void exChangeByStone(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ExchangeByStone>(getApiservice().ExchangeByStone(map.get("c_id"),map.get("token"),map.get("o_status"))))
                .subscribeWith(new SimpleDisposableSubscriber<ExchangeByStone>() {
                    @Override
                    public void _onNext(ExchangeByStone s) {
                        if (getPresenter() != null) {
                            if (s.getCode()==0) {
                                getPresenter().exChangeByStoneSuccess(s);
                            } else {
                                getPresenter().exChangeByStoneFailure(s.getMsg());
                            }
                        }

                    }
                }));
    }

    @Override
    public void exChangeRe(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ExchangeRe>(getApiservice().ExchangeRe(map.get("c_id"),map.get("token"),map.get("o_status"),map.get("code"))))
                .subscribeWith(new SimpleDisposableSubscriber<ExchangeRe>() {
                    @Override
                    public void _onNext(ExchangeRe s) {
                        if (getPresenter() != null) {
                            if (s.getCode()==0) {
                                getPresenter().exChangeReSuccess(s);
                            } else {
                                getPresenter().exChangeReFailure(s.getMsg());
                            }
                        }

                    }
                }));
    }

    @Override
    public void getDetailNoLogin(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ExchangeDetail>(getApiservice().getExchangeDetailNoLogin(map.get("c_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<ExchangeDetail>() {
                    @Override
                    public void _onNext(ExchangeDetail s) {
                        if (getPresenter() != null) {
                            if (s.getCode()==0) {
                                getPresenter().getDetailNoLoginSuccess(s);
                            } else {
                                getPresenter().getDetailNoLoginFailure(s.getMsg());
                            }
                        }

                    }
                }));
    }
}
