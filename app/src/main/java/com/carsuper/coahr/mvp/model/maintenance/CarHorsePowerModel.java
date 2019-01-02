package com.carsuper.coahr.mvp.model.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.CarHorsePowerContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CarHorsePowerBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarHorsePowerModel extends BaseModel<CarHorsePowerContract.Presenter> implements CarHorsePowerContract.Model {


    @Inject
    public CarHorsePowerModel(){
        super();
    }

    @Override
    public void getCarType(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CarHorsePowerBean>(getApiservice().getCarHorsePower(map.get("cs_id"),map.get("motor"))))
        .subscribeWith(new SimpleDisposableSubscriber<CarHorsePowerBean>() {
            @Override
            public void _onNext(CarHorsePowerBean bean) {
                if (getPresenter() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onGetCarTypeSuccess(bean);
                    }else {
                        getPresenter().onGetCarTypeFailure(bean.getMsg());
                    }
                }
            }
        }));
    }

    @Override
    public void saveUserCarInfo(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<SaveUserCarBean>(getApiservice().saveUserCarInfo(map.get("token"),map.get("cd_id"),map.get("motor"),map.get("horsepower"))))
                .subscribeWith(new SimpleDisposableSubscriber<SaveUserCarBean>() {
                    @Override
                    public void _onNext(SaveUserCarBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onSaveUserCarInfoSuccess(bean);
                            }else {
                                getPresenter().onSaveUserCarInfoFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
