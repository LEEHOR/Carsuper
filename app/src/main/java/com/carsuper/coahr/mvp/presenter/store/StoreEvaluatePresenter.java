package com.carsuper.coahr.mvp.presenter.store;

import com.carsuper.coahr.mvp.contract.store.StoreEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateBean;
import com.carsuper.coahr.mvp.model.store.StoreEvaluateModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.store.StoreEvaluateFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreEvaluatePresenter extends BasePresenter<StoreEvaluateContract.View,StoreEvaluateContract.Model> implements StoreEvaluateContract.Presenter {

    @Inject
    public StoreEvaluatePresenter(StoreEvaluateFragment mview, StoreEvaluateModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getCommentList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCommentList(map);
        }
    }

    @Override
    public void dianZan(Map<String, String> map) {
        if (mModle != null) {
            mModle.dianZan(map);
        }
    }

    @Override
    public void onDianZanSuccess(DianZanBean dianZanBean) {
        if (getView() != null) {
            getView().onDianZanSuccess(dianZanBean);
        }
    }


    @Override
    public void onDianZanFailure(String failure) {
        if (getView() != null) {
            getView().onDianZanFailure(failure);
        }
    }

    @Override
    public void onGetCommentsSuccess(StoreEvaluateBean bean) {
        if (getView() != null) {
            getView().onGetCommentsSuccess(bean);
        }
    }

    @Override
    public void onGetCommentsFailure(String failure) {
        if (getView() != null) {
            getView().onGetCommentsFailure(failure);
        }
    }
}
