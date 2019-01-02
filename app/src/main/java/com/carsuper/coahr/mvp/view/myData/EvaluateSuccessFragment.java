package com.carsuper.coahr.mvp.view.myData;

import com.carsuper.coahr.mvp.contract.myData.EvaluateSuccessContract;
import com.carsuper.coahr.mvp.presenter.myData.EvaluateSuccessPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class EvaluateSuccessFragment extends BaseFragment<EvaluateSuccessContract.Presenter> implements EvaluateSuccessContract.View {

    @Inject
    EvaluateSuccessPresenter evaluateSuccessPresenter;
    @Override
    public EvaluateSuccessContract.Presenter getPresenter() {
        return evaluateSuccessPresenter;
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
