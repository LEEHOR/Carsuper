package com.carsuper.coahr.mvp.presenter.myData;

import com.carsuper.coahr.mvp.contract.myData.InvitationContract;
import com.carsuper.coahr.mvp.model.myData.InvitationModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.InvitationFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class InvitationPresenter extends BasePresenter<InvitationContract.View,InvitationContract.Model> implements InvitationContract.Presenter {

    @Inject
    public InvitationPresenter(InvitationFragment mview, InvitationModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
