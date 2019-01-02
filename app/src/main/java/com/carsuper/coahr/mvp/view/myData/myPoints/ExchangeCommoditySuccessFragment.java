package com.carsuper.coahr.mvp.view.myData.myPoints;

import com.carsuper.coahr.mvp.contract.myData.myPoints.ExchangeCommoditySuccessContract;
import com.carsuper.coahr.mvp.presenter.myData.myPoints.ExchangeCommoditySuccessPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ExchangeCommoditySuccessFragment extends BaseFragment<ExchangeCommoditySuccessContract.Presenter> implements ExchangeCommoditySuccessContract.View {
    @Inject
    ExchangeCommoditySuccessPresenter exchangeCommoditySuccessPresenter;
    @Override
    public ExchangeCommoditySuccessContract.Presenter getPresenter() {
        return exchangeCommoditySuccessPresenter;
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
