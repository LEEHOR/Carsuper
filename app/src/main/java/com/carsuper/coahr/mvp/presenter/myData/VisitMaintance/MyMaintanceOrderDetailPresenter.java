package com.carsuper.coahr.mvp.presenter.myData.VisitMaintance;

import com.carsuper.coahr.mvp.contract.myData.visitMaintance.MyMaintanceOrderDetailContract;
import com.carsuper.coahr.mvp.model.myData.VisitMaintance.MyMaintanceOrderDetailModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.visitMaintance.MyMaintanceOrderDetailFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyMaintanceOrderDetailPresenter extends BasePresenter<MyMaintanceOrderDetailContract.View,MyMaintanceOrderDetailContract.Model> implements MyMaintanceOrderDetailContract.Presenter {
   @Inject
    public MyMaintanceOrderDetailPresenter(MyMaintanceOrderDetailFragment mview, MyMaintanceOrderDetailModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
