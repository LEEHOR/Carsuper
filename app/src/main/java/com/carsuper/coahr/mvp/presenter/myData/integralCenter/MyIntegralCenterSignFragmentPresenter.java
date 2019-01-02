package com.carsuper.coahr.mvp.presenter.myData.integralCenter;

import com.carsuper.coahr.mvp.contract.myData.integralCenter.MyIntegralCenterSignFragmentContract;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.integralCenter.MyIntegralCenterSignFragmentModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.integralCenter.MyIntegralCenterSignFagment;

import java.util.Map;

import javax.inject.Inject;

public class MyIntegralCenterSignFragmentPresenter extends BasePresenter<MyIntegralCenterSignFragmentContract.View,MyIntegralCenterSignFragmentContract.Model> implements MyIntegralCenterSignFragmentContract.Presenter {

    @Inject
    public MyIntegralCenterSignFragmentPresenter(MyIntegralCenterSignFagment mview, MyIntegralCenterSignFragmentModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void startSign(Map<String, String> map) {
        if (mModle != null) {
            mModle.startSign(map);
        }
    }

    @Override
    public void onSigninSuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onSigninSuccess(bean);
        }
    }

    @Override
    public void onSignInFailure(String failure) {
        if (getView() != null) {
            getView().onSignInFailure(failure);
        }
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
}
