package com.carsuper.coahr.mvp.presenter.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.JoinUsContract;
import com.carsuper.coahr.mvp.model.myData.setting.JoinUsModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.setting.JoinUsFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class JoinUsPresenter extends BasePresenter<JoinUsContract.View,JoinUsContract.Model> implements JoinUsContract.Presenter {

@Inject
    public JoinUsPresenter(JoinUsFragment mview, JoinUsModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
