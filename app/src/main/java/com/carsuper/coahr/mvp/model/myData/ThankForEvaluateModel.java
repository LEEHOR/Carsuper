package com.carsuper.coahr.mvp.model.myData;

import com.carsuper.coahr.mvp.contract.myData.ThankForEvaluateContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.TrankForEvaluateBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/27.
 * Email：hengzwdhengzwd@qq.com
 */
public class ThankForEvaluateModel extends BaseModel<ThankForEvaluateContract.Presenter> implements ThankForEvaluateContract.Model {


    @Inject
    public ThankForEvaluateModel() {
            super();
    }

    @Override
    public void getCommodityList(Map<String, String> map) {


        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<TrankForEvaluateBean>(getApiservice().commentEndingRecommend(map.get("token"),map.get("order_id"),map.get("type"))))
        .subscribeWith(new SimpleDisposableSubscriber<TrankForEvaluateBean>() {
            @Override
            public void _onNext(TrankForEvaluateBean trankForEvaluateBean) {
                if (getPresenter() != null) {
                    if (trankForEvaluateBean.getCode()==0) {
                        getPresenter().onGetCommodityListSuccess(trankForEvaluateBean);
                    }else {
                        getPresenter().onGetCommodityListFailure(trankForEvaluateBean.getMsg());
                    }
                }
            }
        }));

    }
}
