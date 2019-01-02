package com.carsuper.coahr.mvp.presenter.myData.myPoints;

import com.carsuper.coahr.mvp.contract.myData.myPoints.ExchangeCommoditySuccessContract;
import com.carsuper.coahr.mvp.model.myData.myPoints.ExchangeCommoditySuccessModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.myPoints.ExchangeCommoditySuccessFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ExchangeCommoditySuccessPresenter extends BasePresenter<ExchangeCommoditySuccessContract.View,ExchangeCommoditySuccessContract.Model> implements ExchangeCommoditySuccessContract.Presenter {
    @Inject
    public ExchangeCommoditySuccessPresenter(ExchangeCommoditySuccessFragment mview, ExchangeCommoditySuccessModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
