package com.carsuper.coahr.mvp.model.myData;

import com.carsuper.coahr.mvp.contract.myData.MyLovelyCarContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.MyLovelyCarBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;

import java.util.Map;

import javax.inject.Inject;
import javax.xml.transform.Result;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyLoveLyCarModel extends BaseModel<MyLovelyCarContract.Presenter> implements MyLovelyCarContract.Model {
    @Inject
    public MyLoveLyCarModel(){
        super();
    }

    @Override
    public void getLovelyCarList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MyLovelyCarBean>(getApiservice().getMyLoveLyCarList(map.get("token"))))
        .subscribeWith(new SimpleDisposableSubscriber<MyLovelyCarBean>() {
            @Override
            public void _onNext(MyLovelyCarBean myLovelyCarBean) {
                if (getPresenter() != null) {
                    if (myLovelyCarBean.getCode()==0) {
                        getPresenter().onGetLovelyCarListSuccess(myLovelyCarBean);
                    }else {
                        getPresenter().onGetLovelyCarListFailure(myLovelyCarBean.getMsg());
                    }
                }
            }
        }));
    }

    @Override
    public void deleteLovelyCar(Map<String, String> map, final MyLovelyCarBean.JdataBean.MycarBean mycarEntity) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().deleteCar(map.get("token"),map.get("car_id"))))
            .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                @Override
                public void _onNext(ResultBean bean) {
                    if (getPresenter() != null) {
                        if (bean.getCode()==0) {
                            getPresenter().onDeleteSuccess(mycarEntity,bean);
                        }else {
                            getPresenter().onDeleteFailure(bean.getMsg());
                        }
                    }
                }
            }));
    }

    @Override
    public void setPrimary(Map<String, String> map, final MyLovelyCarBean.JdataBean.MycarBean mycarEntity) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().setCarPrimary(map.get("token"),map.get("car_id"))))
            .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                @Override
                public void _onNext(ResultBean bean) {
                    if (getPresenter() != null) {
                        if (bean.getCode() == 0) {
                            getPresenter().onSetPrimarySuccess(mycarEntity,bean);
                        } else {
                            getPresenter().onSetPrimaryFailure(bean.getMsg());
                        }
                    }
                }
                }));
    }

    /**
     * 本页面不用
     * @param map
     */
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
