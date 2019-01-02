package com.carsuper.coahr.mvp.model.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.CarSerialContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CarSerialBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarSerialModel extends BaseModel<CarSerialContract.Presenter> implements CarSerialContract.Model {


    @Inject
    public CarSerialModel() {
        super();
    }

    @Override
    public void getCarSerial(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CarSerialBean>(getApiservice().getCarSerial(map.get("cb_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<CarSerialBean>() {
                    @Override
                    public void _onNext(CarSerialBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode() == 0) {
                                getPresenter().onGetCarSerialSuccess(bean);
                            } else {
                                getPresenter().onGetCarSerialFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
