package com.carsuper.coahr.mvp.presenter.store;

import com.carsuper.coahr.mvp.contract.store.StoreEvaluateDetailContract;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.EvaluateBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateDetailBean;
import com.carsuper.coahr.mvp.model.store.StoreEvaluateDetailModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.store.StoreEvaluateDetailFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 门店评价列表
 */
public class StoreEvaluateDetailPresenter extends BasePresenter<StoreEvaluateDetailContract.View, StoreEvaluateDetailContract.Model> implements StoreEvaluateDetailContract.Presenter {

    @Inject
    public StoreEvaluateDetailPresenter(StoreEvaluateDetailFragment mview, StoreEvaluateDetailModel mModel) {
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
    public void onGetEvaluateDetailSuccess(StoreEvaluateDetailBean bean) {
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
        if (mModle != null) {
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
    public void storeSecondEvaluate(Map<String, String> map) {
        if (mModle != null) {
            mModle.storeSecondEvaluate(map);
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
