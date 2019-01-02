package com.carsuper.coahr.mvp.presenter.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.UserInfoContract;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.setting.UserInfoModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.setting.UserInfoFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/17.
 * Email：hengzwdhengzwd@qq.com
 */
public class UserInfoPresenter extends BasePresenter<UserInfoContract.View,UserInfoContract.Model> implements UserInfoContract.Presenter {

    @Inject
    public UserInfoPresenter(UserInfoFragment mview, UserInfoModel mModel) {
        super(mview, mModel);
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

    @Override
    public void unSetWx(Map<String, String> map) {
        if (mModle != null) {
            mModle.unSetWx(map);
        }
    }

    @Override
    public void onUnsetWxSuccess(ResultBean resultBean) {
        if (getView() != null) {
            getView().onUnsetWxSuccess(resultBean);
        }
    }

    @Override
    public void onUnsetWxFailure(String failure) {
        if (getView() != null) {
            getView().onUnsetWxFailure(failure);
        }
    }

    @Override
    public void bindWx(Map<String, String> map) {
        if (mModle != null) {
            mModle.bindWx(map);
        }
    }

    @Override
    public void onBindWxSuccess(ResultBean  bean) {
        if (getView() != null) {
            getView().onBindWxSuccess(bean);
        }
    }

    @Override
    public void onBindWxFailure(String failure) {
        if (getView() != null) {
            getView().onBindWxFailure(failure);
        }
    }
}
