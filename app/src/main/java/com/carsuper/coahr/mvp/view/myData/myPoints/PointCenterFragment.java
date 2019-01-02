package com.carsuper.coahr.mvp.view.myData.myPoints;

import com.carsuper.coahr.mvp.contract.myData.myPoints.PointCenterContract;
import com.carsuper.coahr.mvp.presenter.myData.myPoints.PointCenterPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class PointCenterFragment extends BaseFragment<PointCenterContract.Presenter> implements PointCenterContract.View {

    @Inject
    PointCenterPresenter pointCenterPresenter;
    @Override
    public PointCenterContract.Presenter getPresenter() {
        return pointCenterPresenter;
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
