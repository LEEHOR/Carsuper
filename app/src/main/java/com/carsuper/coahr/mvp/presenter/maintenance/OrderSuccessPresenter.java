package com.carsuper.coahr.mvp.presenter.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.OrderSuccessContract;
import com.carsuper.coahr.mvp.model.maintenance.OrderSuccessModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.maintenance.OrderSuccessFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class OrderSuccessPresenter extends BasePresenter<OrderSuccessContract.View,OrderSuccessContract.Model> implements OrderSuccessContract.Presenter {
    @Inject
    public OrderSuccessPresenter(OrderSuccessFragment mview, OrderSuccessModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
