package com.carsuper.coahr.mvp.presenter.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.SettingContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.setting.SettingModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.setting.SettingFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class SettingPresenter extends BasePresenter<SettingContract.View,SettingContract.Model> implements SettingContract.Presenter {
    @Inject
    public SettingPresenter(SettingFragment mview, SettingModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void loginOut(Map<String, String> map) {
        if (mModle != null) {
            mModle.loginOut(map);
        }
    }

    @Override
    public void onLoginOutSuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onLoginOutSuccess(bean);
        }
    }

    @Override
    public void onLoginOutFailure(String failure) {
        if (getView() != null) {
            getView().onLoginOutFailure(failure);
        }
    }
}
