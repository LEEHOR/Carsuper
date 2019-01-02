package com.carsuper.coahr.mvp.model.myData;

import com.carsuper.coahr.mvp.contract.myData.MyDataContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.MyDataAdList;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.VerifyTokenBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyDataModel extends BaseModel<MyDataContract.Presenter> implements MyDataContract.Model {
    @Inject
    public MyDataModel() {
        super();
    }

    @Override
    public void verifyToken(String token) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<VerifyTokenBean>(getApiservice().verifyToken(token)))
                .subscribeWith(new SimpleDisposableSubscriber<VerifyTokenBean>() {
                    @Override
                    public void _onNext(VerifyTokenBean verifyTokenBean) {
                        if (getPresenter() != null) {
                            if (verifyTokenBean.getCode()==0) {
                                getPresenter().verifySuccess(verifyTokenBean);
                            }else {
                                getPresenter().verifyFailure(verifyTokenBean);
                            }
                        }
                    }
                }));
    }

    @Override
    public void getUserInfo(Map<String,String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<PersonInfoBean>(getApiservice().getUserInfo(map.get("token"),map.get("uid"))))
                .subscribeWith(new SimpleDisposableSubscriber<PersonInfoBean>() {
                    @Override
                    public void _onNext(PersonInfoBean personInfoBean) {

                        if (getPresenter() != null) {
                            if (personInfoBean.getCode()==0) {
                                getPresenter().onGetInfoSuccess(personInfoBean);
                            }else {
                                getPresenter().onGetInfoFailure(personInfoBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void signin(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().signin(map.get("token"))))
        .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
            @Override
            public void _onNext(ResultBean bean) {
                if (getPresenter() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onSigninSuccess(bean);
                    }else {
                        getPresenter().onSignInFailure(bean.getMsg());
                    }
                }
            }
        }));
    }

    @Override
    public void getAdList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MyDataAdList>(getApiservice().getAdList(map.get("asid"))))
                .subscribeWith(new SimpleDisposableSubscriber<MyDataAdList>() {
                    @Override
                    public void _onNext(MyDataAdList adList) {
                        if (getPresenter() != null) {
                            if (adList.getCode()==0) {
                                getPresenter().getAdListSuccess(adList);
                            }else {
                                getPresenter().getAdListFailure(adList.getMsg());
                            }
                        }
                    }
                }));
    }
}
