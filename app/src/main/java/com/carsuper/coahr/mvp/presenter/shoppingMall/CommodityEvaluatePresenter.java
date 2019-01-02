package com.carsuper.coahr.mvp.presenter.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateBean;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.shoppingMall.CommodityEvaluateModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityEvaluateFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityEvaluatePresenter extends BasePresenter<CommodityEvaluateContract.View,CommodityEvaluateContract.Model> implements CommodityEvaluateContract.Presenter {

    @Inject
    public CommodityEvaluatePresenter(CommodityEvaluateFragment mview, CommodityEvaluateModel mModel) {
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
    public void onGetCommentsSuccess(CommodityEvaluateBean bean) {
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
