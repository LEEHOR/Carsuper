package com.carsuper.coahr.mvp.presenter.main;

import com.carsuper.coahr.mvp.contract.main.FragmentPayPass_C;
import com.carsuper.coahr.mvp.model.bean.ExchangeRe;
import com.carsuper.coahr.mvp.model.main.FragmentPayPassModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.Exchange_mall.FragmentPayPass;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/20
 * on 11:01
 */
public class FragmentPayPass_Presenter extends BasePresenter<FragmentPayPass_C.View,FragmentPayPass_C.Model> implements FragmentPayPass_C.Presenter {
    @Inject
    public FragmentPayPass_Presenter(FragmentPayPass mview, FragmentPayPassModel mModel) {
        super(mview, mModel);
    }
    @Override
    public void exChangeRe(Map<String, String> map) {
        if (mModle != null) {
            mModle.exChangeRe(map);
        }
    }

    @Override
    public void exChangeReSuccess(ExchangeRe exchangeRe) {
        if (getView() != null) {
            getView().exChangeReSuccess(exchangeRe);
        }
    }

    @Override
    public void exChangeReFailure(String failure) {
        if (getView() != null) {
            getView().exChangeReFailure(failure);
        }
    }
}
