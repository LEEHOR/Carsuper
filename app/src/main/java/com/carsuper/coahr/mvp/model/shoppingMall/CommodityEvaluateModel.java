package com.carsuper.coahr.mvp.model.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityEvaluateContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateBean;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityEvaluateModel extends BaseModel<CommodityEvaluateContract.Presenter> implements CommodityEvaluateContract.Model {
    @Inject
    public CommodityEvaluateModel(){
        super();
    }

    @Override
    public void getCommentList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CommodityEvaluateBean>(getApiservice().getCommodityComments(map.get("c_id"),map.get("filter"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<CommodityEvaluateBean>() {
                    @Override
                    public void _onNext(CommodityEvaluateBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onGetCommentsSuccess(bean);
                            }else {
                                getPresenter().onGetCommentsFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void dianZan(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<DianZanBean>(getApiservice().commodityEvaluatedianZan(map.get("so_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<DianZanBean>() {
                    @Override
                    public void _onNext(DianZanBean dianZanBean) {
                        if (getPresenter() != null) {
                            if (dianZanBean.getCode()==0) {
                                getPresenter().onDianZanSuccess(dianZanBean);
                            }else {
                                getPresenter().onDianZanFailure(dianZanBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
