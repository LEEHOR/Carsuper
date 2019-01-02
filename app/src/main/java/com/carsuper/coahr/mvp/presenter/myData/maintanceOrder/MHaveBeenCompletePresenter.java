package com.carsuper.coahr.mvp.presenter.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MHaveBeenCompleteContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.mvp.model.myData.maintanceOrder.MHaveBeenCompleteModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MHaveBeenCompleteFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/14.
 * Email：hengzwdhengzwd@qq.com
 */
public class MHaveBeenCompletePresenter extends BasePresenter<MHaveBeenCompleteContract.View,MHaveBeenCompleteContract.Model> implements MHaveBeenCompleteContract.Presenter {


    @Inject
    public MHaveBeenCompletePresenter(MHaveBeenCompleteFragment mview, MHaveBeenCompleteModel mModel) {
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
