package com.carsuper.coahr.mvp.presenter.myData.integralCenter;

import com.carsuper.coahr.mvp.contract.myData.integralCenter.PointContract;
import com.carsuper.coahr.mvp.model.bean.IntegralCenterBean;
import com.carsuper.coahr.mvp.model.myData.integralCenter.PointModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.integralCenter.IntegralCenterFragment;
import com.carsuper.coahr.mvp.view.myData.integralCenter.PointFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class PointPresenter extends BasePresenter<PointContract.View,PointContract.Model> implements PointContract.Presenter {

    @Inject
    public PointPresenter(PointFragment mview, PointModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getPointList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getPointList(map);
        }
    }

    @Override
    public void onGetPointListSuccess(IntegralCenterBean bean) {
        if (getView() != null) {
            getView().onGetPointListSuccess(bean);
        }
    }

    @Override
    public void onGetPointListFailure(String failure) {
        if (getView() != null) {
            getView().onGetPointListFailure(failure);
        }
    }
}
