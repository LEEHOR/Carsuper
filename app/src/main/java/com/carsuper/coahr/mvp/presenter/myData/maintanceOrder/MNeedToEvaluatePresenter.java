package com.carsuper.coahr.mvp.presenter.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MNeedToEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.mvp.model.myData.maintanceOrder.MNeedToEvaluateModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MNeedToEvaluateFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MNeedToEvaluatePresenter extends BasePresenter<MNeedToEvaluateContract.View,MNeedToEvaluateContract.Model> implements MNeedToEvaluateContract.Presenter {

    @Inject
    public MNeedToEvaluatePresenter(MNeedToEvaluateFragment mview, MNeedToEvaluateModel mModel) {
        super(mview, mModel);
    }
    @Override
    public void getMaintanceOrderDetail(Map<String, String> map) {
        if (mModle != null) {
            mModle.getMaintanceOrderDetail(map);
        }
    }

    @Override
    public void onGetMaintanceOrderDetialSuccess(MaintanceOrderDetailBean bean) {
        if (getView() != null) {
            getView().onGetMaintanceOrderDetialSuccess(bean);
        }
    }

    @Override
    public void onGetMaintanceOrderDetailFailure(String failure) {
        if (getView() != null) {
            getView().onGetMaintanceOrderDetailFailure(failure);
        }
    }
}
