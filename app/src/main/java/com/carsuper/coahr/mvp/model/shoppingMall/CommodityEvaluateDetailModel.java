package com.carsuper.coahr.mvp.model.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityEvaluateContract;
import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityEvaluateDetailContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailBeans;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailbean;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.EvaluateBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityEvaluateDetailModel extends BaseModel<CommodityEvaluateDetailContract.Presenter> implements CommodityEvaluateDetailContract.Model {
    @Inject
    public CommodityEvaluateDetailModel(){
        super();
    }


    //评论详情
    @Override
    public void getEvaluateDetail(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CommodityEvaluateDetailBeans>(getApiservice().getCommodityCommentDetail(map.get("so_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<CommodityEvaluateDetailBeans>() {
                    @Override
                    public void _onNext(CommodityEvaluateDetailBeans storeEvaluateDetailBean) {
                        if (getPresenter() != null) {
                            if (storeEvaluateDetailBean.getCode()==0) {
                                getPresenter().onGetEvaluateDetailSuccess(storeEvaluateDetailBean);
                            }else {
                                getPresenter().onGetEvaluateDetailFailure(storeEvaluateDetailBean.getMsg());
                            }
                        }
                    }
                }));
    }

    //取消点赞
    @Override
    public void replydianZan(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<DianZanBean>(getApiservice().commoditySecondEvaluatedianZan(map.get("sp_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<DianZanBean>() {
                    @Override
                    public void _onNext(DianZanBean dianZanBean) {
                        if (getPresenter() != null) {
                            if (dianZanBean.getCode()==0) {
                                getPresenter().onReplyDianZanSuccess(dianZanBean);
                            }else {
                                getPresenter().onReplyDianZanFailure(dianZanBean.getMsg());
                            }
                        }
                    }
                }));


    }
        //点赞
    @Override
    public void evaluateDianzan(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<DianZanBean>(getApiservice().commodityEvaluatedianZan(map.get("so_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<DianZanBean>() {
                    @Override
                    public void _onNext(DianZanBean dianZanBean) {
                        if (getPresenter() != null) {
                            if (dianZanBean.getCode()==0) {
                                getPresenter().onEvaluateDianzanSuccess(dianZanBean);
                            }else {
                                getPresenter().onEvaluateDianzanFailure(dianZanBean.getMsg());
                            }
                        }
                    }
                }));
    }

    //评论
    @Override
    public void commoditySecondEvaluate(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<EvaluateBean>(getApiservice().commoditySecondEvaluate(map.get("so_id"),map.get("token"),map.get("comment"))))
                .subscribeWith(new SimpleDisposableSubscriber<EvaluateBean>() {
                    @Override
                    public void _onNext(EvaluateBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onEvaluateSuccess(bean);
                            }else {
                                getPresenter().onEvaluateFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
