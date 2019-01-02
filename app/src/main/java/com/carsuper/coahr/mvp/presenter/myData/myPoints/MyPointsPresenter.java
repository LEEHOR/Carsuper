package com.carsuper.coahr.mvp.presenter.myData.myPoints;

import com.carsuper.coahr.mvp.contract.myData.myPoints.MyPointsContract;
import com.carsuper.coahr.mvp.model.myData.myPoints.MyPointsModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.myPoints.MyPointsFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyPointsPresenter extends BasePresenter<MyPointsContract.View,MyPointsContract.Model> implements MyPointsContract.Presenter {
    @Inject
    public MyPointsPresenter(MyPointsFragment mview, MyPointsModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
