package com.carsuper.coahr.mvp.model.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.CarBrandContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CarBrandBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CarBrandModel extends BaseModel<CarBrandContract.Presenter> implements CarBrandContract.Model {

    @Inject
    public CarBrandModel(){
        super();
    }

    @Override
    public void getCarBrand(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CarBrandBean>(getApiservice().getCarBrand(map.get("token"))))
        .subscribeWith(new SimpleDisposableSubscriber<CarBrandBean>() {
            @Override
            public void _onNext(CarBrandBean brandBean) {
                if (getPresenter() != null) {
                    if (brandBean.getCode()==0) {
                        getPresenter().onGetCarBrandSuccess(brandBean);
                    }else {
                        getPresenter().onGetCarBrandFailure(brandBean.getMsg());
                    }
                }
            }
        }));
    }
}
