package com.carsuper.coahr.mvp.view.myData.myPoints;

import com.carsuper.coahr.mvp.contract.myData.myPoints.MyPointsContract;
import com.carsuper.coahr.mvp.presenter.myData.myPoints.MyPointsPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyPointsFragment extends BaseFragment<MyPointsContract.Presenter> implements MyPointsContract.View {
    @Inject
    MyPointsPresenter myPointsPresenter;
    @Override
    public MyPointsContract.Presenter getPresenter() {
        return myPointsPresenter;
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
