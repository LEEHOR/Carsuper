package com.carsuper.coahr.mvp.contract.main;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ExchangeRe;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/12/29
 * on 13:28
 */
public interface FragmentPayPass_C {
    interface View extends BaseContract.View {

        void exChangeReSuccess(ExchangeRe exchangeRe);
        void exChangeReFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        //领取礼品
        void exChangeRe(Map<String,String> map);
        void exChangeReSuccess(ExchangeRe exchangeRe);
        void exChangeReFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void exChangeRe(Map<String,String> map);
    }
}
