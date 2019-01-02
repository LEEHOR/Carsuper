package com.carsuper.coahr.mvp.view.myData.visitMaintance;

import com.carsuper.coahr.mvp.contract.myData.visitMaintance.MyMaintanceOrderDetailContract;
import com.carsuper.coahr.mvp.presenter.myData.VisitMaintance.MyMaintanceOrderDetailPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyMaintanceOrderDetailFragment extends BaseFragment<MyMaintanceOrderDetailContract.Presenter> implements MyMaintanceOrderDetailContract.View {

    @Inject
    MyMaintanceOrderDetailPresenter myMaintanceOrderDetailPresenter;
    @Override
    public MyMaintanceOrderDetailContract.Presenter getPresenter() {
        return myMaintanceOrderDetailPresenter;
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
