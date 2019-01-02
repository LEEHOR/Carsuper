package com.carsuper.coahr.mvp.presenter.myData.returnorchange;

import android.net.Uri;

import com.carsuper.coahr.mvp.contract.myData.returnorchange.ApplyReturnChangeContract;
import com.carsuper.coahr.mvp.model.bean.RefundFormBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.returnorchange.ApplyReturnChangeModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.returnorchange.ApplyReturnChangeFragment;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 */
public class ApplyReturnChangePresenter extends BasePresenter<ApplyReturnChangeContract.View,ApplyReturnChangeContract.Model> implements ApplyReturnChangeContract.Presenter {

    @Inject
    public ApplyReturnChangePresenter(ApplyReturnChangeFragment mview, ApplyReturnChangeModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void refundForm(Map<String, String> map) {
        if (mModle != null) {
            mModle.refundForm(map);
        }
    }

    @Override
    public void onRefundFormSuccess(RefundFormBean bean) {
        if (getView() != null) {
            getView().onRefundFormSuccess(bean);
        }
    }

    @Override
    public void onRefundFormFailure(String failure) {
        if (getView() != null) {
            getView().onRefundFormFailure(failure);
        }
    }

    @Override
    public void saveRefund(Map<String, RequestBody> map, List<Uri> uris) {
        if (mModle != null) {
            mModle.saveRefund(map,uris);
        }
    }

    @Override
    public void onSaveRefundSuccess(ResultBean resultBean) {
        if (getView() != null) {
            getView().onSaveRefundSuccess(resultBean);
        }
    }

    @Override
    public void onSaveRefundFailure(String failure) {
        if (getView() != null) {
            getView().onSaveRefundFailure(failure);
        }
    }
}
