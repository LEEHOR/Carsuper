package com.carsuper.coahr.mvp.model.myData.integralCenter;

import com.carsuper.coahr.mvp.contract.myData.integralCenter.IntegralCenterContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.IntegralCenterBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class IntegralCenterModel extends BaseModel<IntegralCenterContract.Presenter> implements IntegralCenterContract.Model {


    @Inject
    public IntegralCenterModel() {
        super();
    }


    @Override
    public void getPointList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<IntegralCenterBean>(getApiservice().getPointList(map.get("token"), map.get("page"), map.get("length"), map.get("filter"))))
                .subscribeWith(new SimpleDisposableSubscriber<IntegralCenterBean>() {
                    @Override
                    public void _onNext(IntegralCenterBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onGetPointListSuccess(bean);
                            }else {
                                getPresenter().onGetPointListFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
