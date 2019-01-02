package com.carsuper.coahr.mvp.model.store;

import com.carsuper.coahr.mvp.contract.store.StoreEvaluateDetailContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.EvaluateBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateDetailBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreEvaluateDetailModel extends BaseModel<StoreEvaluateDetailContract.Presenter> implements StoreEvaluateDetailContract.Model {
    @Inject
    public StoreEvaluateDetailModel() {
        super();
    }

    @Override
    public void getEvaluateDetail(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<StoreEvaluateDetailBean>(getApiservice().getStoreCommentDetail(map.get("ao_id"), map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<StoreEvaluateDetailBean>() {
                    @Override
                    public void _onNext(StoreEvaluateDetailBean storeEvaluateDetailBean) {
                        if (getPresenter() != null) {
                            if (storeEvaluateDetailBean.getCode() == 0) {
                                getPresenter().onGetEvaluateDetailSuccess(storeEvaluateDetailBean);
                            } else {
                                getPresenter().onGetEvaluateDetailFailure(storeEvaluateDetailBean.getMsg());
                            }
                        }
                    }
                }));
    }


    @Override
    public void evaluateDianzan(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<DianZanBean>(getApiservice().storeEvaluatedianZan(map.get("ao_id"), map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<DianZanBean>() {
                    @Override
                    public void _onNext(DianZanBean dianZanBean) {
                        if (getPresenter() != null) {
                            if (dianZanBean.getCode() == 0) {
                                getPresenter().onEvaluateDianzanSuccess(dianZanBean);
                            } else {
                                getPresenter().onEvaluateDianzanFailure(dianZanBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void storeSecondEvaluate(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<EvaluateBean>(getApiservice().storeSecondEvaluate(map.get("ao_id"), map.get("token"), map.get("comment"))))
                .subscribeWith(new SimpleDisposableSubscriber<EvaluateBean>() {
                    @Override
                    public void _onNext(EvaluateBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode() == 0) {
                                getPresenter().onEvaluateSuccess(bean);
                            } else {
                                getPresenter().onEvaluateFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void replydianZan(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<DianZanBean>(getApiservice().storeSecondEvaluatedianZan(map.get("ap_id"), map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<DianZanBean>() {
                    @Override
                    public void _onNext(DianZanBean dianZanBean) {
                        if (getPresenter() != null) {
                            if (dianZanBean.getCode() == 0) {
                                getPresenter().onReplyDianZanSuccess(dianZanBean);
                            } else {
                                getPresenter().onReplyDianZanFailure(dianZanBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
