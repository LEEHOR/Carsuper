package com.carsuper.coahr.mvp.presenter.myData;

import com.carsuper.coahr.mvp.contract.myData.ThankForEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.TrankForEvaluateBean;
import com.carsuper.coahr.mvp.model.myData.ThankForEvaluateModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.ThankForEvaluateFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/27.
 * Email：hengzwdhengzwd@qq.com
 */
public class ThankForEvaluatePresenter extends BasePresenter<ThankForEvaluateContract.View,ThankForEvaluateContract.Model> implements ThankForEvaluateContract.Presenter {

    @Inject
    public ThankForEvaluatePresenter(ThankForEvaluateFragment mview, ThankForEvaluateModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getCommodityList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCommodityList(map);
        }
    }

    @Override
    public void onGetCommodityListSuccess(TrankForEvaluateBean bean) {
        if (getView() != null) {
            getView().onGetCommodityListSuccess(bean);
        }
    }

    @Override
    public void onGetCommodityListFailure(String failure) {
        if (getView() != null) {
            getView().onGetCommodityListFailure(failure);
        }
    }
}
