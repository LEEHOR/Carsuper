package com.carsuper.coahr.mvp.presenter.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MHaveBeenCanceledContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.mvp.model.myData.maintanceOrder.MHavebeenCanceledModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MHaveBeenCanceledFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MHaveBeenCanceledPresenter extends BasePresenter<MHaveBeenCanceledContract.View,MHaveBeenCanceledContract.Model> implements MHaveBeenCanceledContract.Presenter {

    @Inject
    public MHaveBeenCanceledPresenter(MHaveBeenCanceledFragment mview, MHavebeenCanceledModel mModel) {
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
