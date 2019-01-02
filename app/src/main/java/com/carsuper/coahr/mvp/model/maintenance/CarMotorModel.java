package com.carsuper.coahr.mvp.model.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.CarMotorContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CarMotorBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarMotorModel extends BaseModel<CarMotorContract.Presenter> implements CarMotorContract.Model {


    @Inject
    public CarMotorModel(){
        super();
    }
    @Override
    public void getCarDisplaceMent(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CarMotorBean>(getApiservice().getCarMotor(map.get("cs_id"))))
        .subscribeWith(new SimpleDisposableSubscriber<CarMotorBean>() {
            @Override
            public void _onNext(CarMotorBean carMotorBean) {
                if (getPresenter() != null) {
                    if (carMotorBean.getCode()==0) {
                        getPresenter().onGetCarDisplaceMentSuccess(carMotorBean);
                    }else {
                        getPresenter().onGetCarDisplaceMentFailure(carMotorBean.getMsg());
                    }
                }
            }
        }));
    }
}
