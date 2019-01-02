package com.carsuper.coahr.mvp.model.main;

import com.carsuper.coahr.mvp.contract.main.FragmentPayPass_C;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ExchangeRe;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/20
 * on 11:00
 */
public class FragmentPayPassModel extends BaseModel<FragmentPayPass_C.Presenter> implements FragmentPayPass_C.Model{
    @Inject
    public FragmentPayPassModel() {
        super();
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
}
