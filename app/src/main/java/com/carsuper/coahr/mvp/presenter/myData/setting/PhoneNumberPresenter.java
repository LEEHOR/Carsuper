package com.carsuper.coahr.mvp.presenter.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.PhoneNumberContract;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.model.myData.setting.PhoneNumberModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.setting.PhoneNumberFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class PhoneNumberPresenter extends BasePresenter<PhoneNumberContract.View,PhoneNumberContract.Model> implements PhoneNumberContract.Presenter {
    @Inject
    public PhoneNumberPresenter(PhoneNumberFragment mview, PhoneNumberModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getVerifyCode(Map<String, String> map) {
        if (mModle != null) {
            mModle.getVerifyCode(map);
        }
    }

    @Override
    public void binPhoneNumber(Map<String, String> map) {
        if (mModle != null) {
            mModle.binPhoneNumber(map);
        }
    }

    @Override
    public void onBindPhoneFail(String throwable) {
        if (getView() != null) {
            getView().onBindPhoneFail(throwable);
        }
    }

    @Override
    public void onBindPhoneSuccess(BindPhoneBean bindPhoneBean) {
        if (getView() != null) {
            getView().onBindPhoneSuccess(bindPhoneBean);
        }
    }

    @Override
    public void getVerifyCodeFail(String throwable) {
        if (getView() != null) {
            getView().getVerifyCodeFail(throwable);
        }
    }

    @Override
    public void getVerifyCodeSuccess(String success) {
        if (getView() != null) {
            getView().getVerifyCodeSuccess(success);
        }
    }

    @Override
    public void wxLogin(Map<String, String> map) {
        if (mModle != null) {
            mModle.wxLogin(map);
        }
    }

    @Override
    public void wxLoginSuccess(LoginBean loginBean) {
        if (getView() != null) {
            getView().wxLoginSuccess(loginBean);
        }
    }

    @Override
    public void wxLoginFail(LoginBean loginBean) {
            getView().wxLoginFail(loginBean);
    }
}
