package com.carsuper.coahr.mvp.model.store;

import com.carsuper.coahr.mvp.contract.store.StoreEvaluateContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreEvaluateModel extends BaseModel<StoreEvaluateContract.Presenter> implements StoreEvaluateContract.Model {
    @Inject
    public StoreEvaluateModel(){
        super();
    }

    @Override
    public void getCommentList(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<StoreEvaluateBean>(getApiservice().getStoreComments(map.get("s_id"),map.get("filter"),map.get("token"))))
        .subscribeWith(new SimpleDisposableSubscriber<StoreEvaluateBean>() {
            @Override
            public void _onNext(StoreEvaluateBean bean) {
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
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<DianZanBean>(getApiservice().storeEvaluatedianZan(map.get("ao_id"),map.get("token"))))
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
