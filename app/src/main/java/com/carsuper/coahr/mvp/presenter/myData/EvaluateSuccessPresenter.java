package com.carsuper.coahr.mvp.presenter.myData;

import com.carsuper.coahr.mvp.contract.myData.EvaluateSuccessContract;
import com.carsuper.coahr.mvp.model.myData.EvaluateSuccessModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.EvaluateSuccessFragment;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class EvaluateSuccessPresenter extends BasePresenter<EvaluateSuccessContract.View,EvaluateSuccessContract.Model> implements EvaluateSuccessContract.Presenter {
    @Inject
    public EvaluateSuccessPresenter(EvaluateSuccessFragment mview, EvaluateSuccessModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }
}
