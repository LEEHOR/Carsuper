package com.carsuper.coahr.mvp.model.maintenance;

import android.widget.Space;

import com.carsuper.coahr.mvp.contract.maintenance.OrderToMaintenanceContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.RecommendServiceBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class OrderToMaintenanceModel extends BaseModel<OrderToMaintenanceContract.Presenter> implements OrderToMaintenanceContract.Model {


    @Inject
    public OrderToMaintenanceModel(){
        super();
    }

    @Override
    public void recommendService(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<RecommendServiceBean>(getApiservice().recommendService(map.get("service"),map.get("cs_id"),map.get("token"))))
            .subscribeWith(new SimpleDisposableSubscriber<RecommendServiceBean>() {
                @Override
                public void _onNext(RecommendServiceBean bean) {
                    if (getPresenter() != null) {
                        if (bean.getCode()==0) {
                            getPresenter().onRecommendServiceSuccess(bean);
                        }else {
                            getPresenter().onRecommendServiceFailure(bean.getMsg());
                        }
                    }
                }
            }));
    }

    @Override
    public void saveUserCarDistance(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().saveUserCarDistance(map.get("token"),map.get("car_id"),map.get("distance"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onSaveUserCarDistanceSuccess(bean);
                            }else {
                                getPresenter().onSaveUserCarDistanceFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void onGetFilter(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<RecommendServiceBean>(getApiservice().recommendService(map.get("service"),map.get("cs_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<RecommendServiceBean>() {
                    @Override
                    public void _onNext(RecommendServiceBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onGetFilterSuccess(bean);
                            }else {
                                getPresenter().onGetFilterFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

}
