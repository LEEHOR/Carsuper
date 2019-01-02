package com.carsuper.coahr.mvp.presenter.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.NameContract;
import com.carsuper.coahr.mvp.model.bean.SaveUserInfoBean;
import com.carsuper.coahr.mvp.model.myData.setting.NameModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.setting.NameFragment;

import java.util.Map;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class NamePresenter extends BasePresenter<NameContract.View, NameContract.Model> implements NameContract.Presenter {
    @Inject
    public NamePresenter(NameFragment mview, NameModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void updateName(Map<String, RequestBody> map) {
        if (mModle != null) {
            mModle.updateName(map);
        }
    }

    @Override
    public void onUpdateSuccess(SaveUserInfoBean bean) {
        if (getView() != null) {
            getView().onUpdateSuccess(bean);
        }
    }

    @Override
    public void onUpdateFailure(String failure) {
        if (getView() != null) {
            getView().onUpdateFailure(failure);
        }
    }
}
