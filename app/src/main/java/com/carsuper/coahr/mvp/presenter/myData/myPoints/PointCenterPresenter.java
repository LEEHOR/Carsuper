package com.carsuper.coahr.mvp.presenter.myData.myPoints;

import com.carsuper.coahr.mvp.contract.myData.myPoints.PointCenterContract;
import com.carsuper.coahr.mvp.model.myData.myPoints.PointCenterModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.myPoints.PointCenterFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class PointCenterPresenter extends BasePresenter<PointCenterContract.View,PointCenterContract.Model> implements PointCenterContract.Presenter {
    @Inject
    public PointCenterPresenter(PointCenterFragment mview, PointCenterModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
