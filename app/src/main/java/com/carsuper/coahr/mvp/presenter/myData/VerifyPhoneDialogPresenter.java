package com.carsuper.coahr.mvp.presenter.myData;

import com.carsuper.coahr.mvp.contract.myData.VerifyPhoneDialogContract;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.PhoneMessageBean;
import com.carsuper.coahr.mvp.model.myData.VerifyPhoneDialogModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.VerifyPhoneDialogFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/2.
 * Email：hengzwdhengzwd@qq.com
 */
public class VerifyPhoneDialogPresenter extends BasePresenter<VerifyPhoneDialogContract.View, VerifyPhoneDialogContract.Model> implements VerifyPhoneDialogContract.Presenter {

    @Inject
    public VerifyPhoneDialogPresenter(VerifyPhoneDialogFragment mview, VerifyPhoneDialogModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getVerifyCode(Map<String, String> map) {
        if (mModle != null) {
            mModle.getVerifyCode(map);
        }
    }

    @Override
    public void bindLogin(Map<String, String> map) {
        if (mModle != null) {
            mModle.bindLogin(map);
        }
    }

    @Override
    public void onGetVerifyCodeSuccess(PhoneMessageBean bean) {
        if (getView() != null) {
            getView().onGetVerifyCodeSuccess(bean);
        }
    }

    @Override
    public void onGetVerifyCodeFailure(String failure) {
        if (getView() != null) {
            getView().onGetVerifyCodeFailure(failure);
        }
    }

    @Override
    public void onBindLoginSuccess(BindPhoneBean bindPhoneBean) {
        if (getView() != null) {
            getView().onBindLoginSuccess(bindPhoneBean);
        }
    }

    @Override
    public void onBindLoginFailure(String failure) {
        if (getView() != null) {
            getView().onBindLoginFailure(failure);
        }
    }
}
