package com.carsuper.coahr.mvp.presenter.myData.VisitMaintance;

import com.carsuper.coahr.mvp.contract.myData.visitMaintance.MyVisitMaintanceContract;
import com.carsuper.coahr.mvp.model.myData.VisitMaintance.MyVisitMaintanceModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.visitMaintance.MyVisitMaintanceFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyVisitMaintancePresenter extends BasePresenter<MyVisitMaintanceContract.View,MyVisitMaintanceContract.Model> implements MyVisitMaintanceContract.Presenter {
   @Inject
    public MyVisitMaintancePresenter(MyVisitMaintanceFragment mview, MyVisitMaintanceModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
