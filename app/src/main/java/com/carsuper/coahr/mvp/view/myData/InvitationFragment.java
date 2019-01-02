package com.carsuper.coahr.mvp.view.myData;

import com.carsuper.coahr.mvp.contract.myData.InvitationContract;
import com.carsuper.coahr.mvp.presenter.myData.InvitationPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class InvitationFragment extends BaseFragment<InvitationContract.Presenter> implements InvitationContract.View {

    @Inject
    InvitationPresenter invitationPresenter;
    @Override
    public InvitationContract.Presenter getPresenter() {
        return invitationPresenter;
    }

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


}
