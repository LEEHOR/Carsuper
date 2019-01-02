package com.carsuper.coahr.mvp.view.myData.visitMaintance;

import com.carsuper.coahr.mvp.contract.myData.visitMaintance.MyVisitMaintanceContract;
import com.carsuper.coahr.mvp.presenter.myData.VisitMaintance.MyVisitMaintancePresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyVisitMaintanceFragment extends BaseFragment<MyVisitMaintanceContract.Presenter> implements MyVisitMaintanceContract.View{

    @Inject
    MyVisitMaintancePresenter myVisitMaintancePresenter;
    @Override
    public MyVisitMaintanceContract.Presenter getPresenter() {
        return myVisitMaintancePresenter;
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
