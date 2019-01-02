package com.carsuper.coahr.mvp.presenter.myData.returnorchange;

import com.carsuper.coahr.mvp.contract.myData.returnorchange.ReturnChangeSelectContract;
import com.carsuper.coahr.mvp.model.bean.RefundApplyBean;
import com.carsuper.coahr.mvp.model.myData.returnorchange.ReturnChangeSelectModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.returnorchange.ReturnChangeSelectFragement;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 */
public class ReturnChangeSelectPresenter extends BasePresenter<ReturnChangeSelectContract.View,ReturnChangeSelectContract.Model> implements ReturnChangeSelectContract.Presenter {

    @Inject
    public ReturnChangeSelectPresenter(ReturnChangeSelectFragement mview, ReturnChangeSelectModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void refundApply(Map<String, String> map) {
        if (mModle != null) {
            mModle.refundApply(map);
        }
    }

    @Override
    public void onRefundApplySuccess(RefundApplyBean bean) {
        if (getView() != null) {
            getView().onRefundApplySuccess(bean);
        }
    }

    @Override
    public void onRefundApplyFailure(String failure) {
        if (getView() != null) {
            getView().onRefundApplyFailure(failure);
        }
    }
}
