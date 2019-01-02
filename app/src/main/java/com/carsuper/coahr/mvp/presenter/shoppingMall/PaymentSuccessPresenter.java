package com.carsuper.coahr.mvp.presenter.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.PaymentSuccessContract;
import com.carsuper.coahr.mvp.model.bean.PaymentSuccessBean;
import com.carsuper.coahr.mvp.model.shoppingMall.PaymentSuccessModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.shoppingMall.PaymentSuccessFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class PaymentSuccessPresenter extends BasePresenter<PaymentSuccessContract.View,PaymentSuccessContract.Model> implements PaymentSuccessContract.Presenter {
    @Inject
    public PaymentSuccessPresenter(PaymentSuccessFragment mview, PaymentSuccessModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getEndingRecommend(Map<String, String> map) {
        if (mModle != null) {
            mModle.getEndingRecommend(map);
        }
    }

    @Override
    public void onGetRecommendSuccess(PaymentSuccessBean bean) {
        if (getView() != null) {
            getView().onGetRecommendSuccess(bean);
        }
    }

    @Override
    public void onGetRecommendFailure(String failure) {
        if (getView() != null) {
            getView().onGetRecommendFailure(failure);
        }
    }
}
