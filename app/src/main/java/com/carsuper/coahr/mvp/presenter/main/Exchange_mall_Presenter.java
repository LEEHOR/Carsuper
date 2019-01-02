package com.carsuper.coahr.mvp.presenter.main;

import com.carsuper.coahr.mvp.contract.main.Exchange_mall_Contract;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.model.main.Exchange_mallModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.Exchange_mall.Fragment_Exchange_mall;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/20
 * on 11:01
 */
public class Exchange_mall_Presenter extends BasePresenter<Exchange_mall_Contract.View,Exchange_mall_Contract.Model> implements Exchange_mall_Contract.Presenter {
    @Inject
    public Exchange_mall_Presenter(Fragment_Exchange_mall mview, Exchange_mallModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getExchangeList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getExchangeList(map);
        }
    }

    @Override
    public void getExchangeListSuccess(ExchangeMallList exchangeMallList) {
        if (getView() != null) {
            getView().getExchangeListSuccess(exchangeMallList);
        }
    }

    @Override
    public void getExchangeListFail(String failure) {
        if (getView() != null) {
            getView().getExchangeListFail(failure);
        }
    }

    @Override
    public void getExchangeListMore(Map<String, String> map) {
        if (mModle != null) {
            mModle.getExchangeListMore(map);
        }
    }

    @Override
    public void getExchangeListMoreSuccess(ExchangeMallList exchangeMallList) {
        if (getView() != null) {
            getView().getExchangeListMoreSuccess(exchangeMallList);
        }
    }

    @Override
    public void getExchangeListMoreFail(String failure) {
        if (getView() != null) {
            getView().getExchangeListMoreFail(failure);
        }
    }

    @Override
    public void getUserInfo(Map<String, String> map) {
        if (mModle != null) {
            mModle.getUserInfo(map);
        }
    }

    @Override
    public void onGetInfoSuccess(PersonInfoBean personInfoBean) {
        if (getView() != null) {
            getView().onGetInfoSuccess(personInfoBean);
        }
    }

    @Override
    public void onGetInfoFailure(String throwle) {
        if (getView() != null) {
            getView().onGetInfoFailure(throwle);
        }
    }
}
