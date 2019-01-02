package com.carsuper.coahr.mvp.presenter.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityEvaluateDetailContract;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailBeans;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailbean;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.EvaluateBean;
import com.carsuper.coahr.mvp.model.shoppingMall.CommodityEvaluateDetailModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityEvaluateDetailFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityEvaluateDetailPresenter extends BasePresenter<CommodityEvaluateDetailContract.View,CommodityEvaluateDetailContract.Model> implements CommodityEvaluateDetailContract.Presenter {
    @Inject
    public CommodityEvaluateDetailPresenter(CommodityEvaluateDetailFragment mview, CommodityEvaluateDetailModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getEvaluateDetail(Map<String, String> map) {
        if (mModle != null) {
            mModle.getEvaluateDetail(map);
        }
    }

    @Override
    public void onGetEvaluateDetailSuccess(CommodityEvaluateDetailBeans bean) {
        if (getView() != null) {
            getView().onGetEvaluateDetailSuccess(bean);
        }
    }

    @Override
    public void onGetEvaluateDetailFailure(String failure) {
        if (getView() != null) {
            getView().onGetEvaluateDetailFailure(failure);
        }
    }

    @Override
    public void replydianZan(Map<String, String> map) {
        if (mModle!= null) {
            mModle.replydianZan(map);
        }
    }

    @Override
    public void onReplyDianZanSuccess(DianZanBean dianZanBean) {
        if (getView() != null) {
            getView().onReplyDianZanSuccess(dianZanBean);
        }
    }

    @Override
    public void onReplyDianZanFailure(String failure) {
        if (getView() != null) {
            getView().onReplyDianZanFailure(failure);

        }
    }

    @Override
    public void evaluateDianzan(Map<String, String> map) {
        if (mModle != null) {
            mModle.evaluateDianzan(map);
        }
    }

    @Override
    public void onEvaluateDianzanSuccess(DianZanBean dianZanBean) {
        if (getView() != null) {
            getView().onEvaluateDianzanSuccess(dianZanBean);
        }
    }

    @Override
    public void onEvaluateDianzanFailure(String failure) {
        if (getView() != null) {
            getView().onEvaluateDianzanFailure(failure);
        }
    }

    @Override
    public void commoditySecondEvaluate(Map<String, String> map) {
        if (mModle != null) {
            mModle.commoditySecondEvaluate(map);
        }
    }

    @Override
    public void onEvaluateSuccess(EvaluateBean bean) {
        if (getView() != null) {
            getView().onEvaluateSuccess(bean);
        }
    }

    @Override
    public void onEvaluateFailure(String failure) {
        if (getView() != null) {
            getView().onEvaluateFailure(failure);
        }
    }
}
