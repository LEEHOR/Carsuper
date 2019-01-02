package com.carsuper.coahr.mvp.presenter.myData;

import com.carsuper.coahr.mvp.contract.myData.LoginContract;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.model.myData.LoginModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.LoginFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/7/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class LoginPresenter extends BasePresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {


    @Inject
    public LoginPresenter(LoginFragment mview, LoginModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

        if (getView() != null) {
            getView().showError(t);
        }
    }

    @Override
    public void getVerifyCode(Map<String,String> map) {
        if (mModle != null) {
            mModle.getVerifyCode(map);
        }
    }

    @Override
    public void phoneLogin(Map<String,String> map) {
        if (mModle != null) {
            mModle.phoneLogin(map);
        }
    }

    @Override
    public void wxLogin(Map<String, String> map) {
        if (mModle != null) {
            mModle.wxLogin(map);
        }
    }

    @Override
    public void phoneLoginFail(String throwable) {

        if (getView() != null) {
            getView().phoneLoginFail(throwable);
        }
    }

    @Override
    public void phoneLoginSuccess(LoginBean loginBean) {
        if (getView() != null) {
            getView().phoneLoginSuccess(loginBean);
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
    public void onWXloginSuccess(LoginBean loginBean) {
        if (getView() != null) {
            getView().onWXloginSuccess(loginBean);
        }
    }

    @Override
    public void onWXloginFailure(LoginBean loginBean) {
            getView().onWXloginFailure(loginBean);
    }

    @Override
    public void bindPhone(Map<String, String> map) {
        if (mModle != null) {
            mModle.bindPhone(map);
        }
    }

    @Override
    public void bindPhoneSuccess(BindPhoneBean bindPhoneBean) {
        if (getView() != null) {
            getView().bindPhoneSuccess(bindPhoneBean);
        }
    }

    @Override
    public void bindPhoneFail(BindPhoneBean bindPhoneBean) {
        if (getView() != null) {
            getView().bindPhoneFail(bindPhoneBean);
        }
    }

}
