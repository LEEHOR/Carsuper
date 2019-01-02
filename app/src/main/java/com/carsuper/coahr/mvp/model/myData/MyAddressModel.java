package com.carsuper.coahr.mvp.model.myData;

import com.carsuper.coahr.mvp.contract.myData.MyAddressContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.UserAddressBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/16.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyAddressModel extends BaseModel<MyAddressContract.Presenter> implements MyAddressContract.Model {

    @Inject
    public MyAddressModel(){
        super();
    }

    @Override
    public void saveUserAddress(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().saveUserAddress(map.get("token"),map.get("username"),map.get("telephone"),map.get("address"),map.get("id"))))
        .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
            @Override
            public void _onNext(ResultBean bean) {
                if (getPresenter() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onSaveUserAddressSuccess(bean);
                    }else {
                        getPresenter().onSaveUserAddressFailure(bean.getJdata().getJmsg());
                    }
                }
            }
        }));
    }

    @Override
    public void getUserAddressList(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<UserAddressBean>(getApiservice().getUserAddressList(map.get("token"))))
            .subscribeWith(new SimpleDisposableSubscriber<UserAddressBean>() {
                @Override
                public void _onNext(UserAddressBean bean) {
                    if (getPresenter() != null) {
                        if (bean.getCode()==0) {
                            getPresenter().ongetUserAddressListSuccess(bean);
                        }else {
                            getPresenter().onGetUserAddressListFailure(bean.getMsg());
                        }
                    }
                }
            }));
    }

    @Override
    public void deleteUserAddress(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().deleteUserAddress(map.get("token"),map.get("id"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onDeleteUserAddressSuccess(bean);
                            }else {
                                getPresenter().onDeleteUserAddressFailure(bean.getJdata().getJmsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void setPrimaryAddress(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().setPrimaryAddress(map.get("token"),map.get("id"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {

                    }
                }));
    }
}
