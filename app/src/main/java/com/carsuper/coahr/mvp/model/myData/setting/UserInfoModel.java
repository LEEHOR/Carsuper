package com.carsuper.coahr.mvp.model.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.UserInfoContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/17.
 * Email：hengzwdhengzwd@qq.com
 */
public class UserInfoModel extends BaseModel<UserInfoContract.Presenter> implements UserInfoContract.Model {


    @Inject
    public UserInfoModel(){
        super();
    }
    @Override
    public void getUserInfo(Map<String, String> map) {

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
    public void unSetWx(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().unSet(map.get("token"),map.get("uid"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean resultBean) {
                        if (getPresenter() != null) {
                            if (resultBean.getCode()!=0) {
                                getPresenter().onUnsetWxFailure(resultBean.getMsg());
                            }else {
                                getPresenter().onUnsetWxSuccess(resultBean);
                            }
                        }
                    }

                }));
    }

    @Override
    public void bindWx(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().bindWx(map.get("token"),map.get("uid"),map.get("openid"),map.get("nickname"),map.get("headimgurl"),map.get("unionid"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean loginBean) {
                        if (getPresenter() != null) {
                            if (loginBean.getCode()!=0) {
                                getPresenter().onBindWxFailure(loginBean.getMsg());
                            }else {
                                getPresenter().onBindWxSuccess(loginBean);
                            }
                        }
                    }

                }));
    }
}
