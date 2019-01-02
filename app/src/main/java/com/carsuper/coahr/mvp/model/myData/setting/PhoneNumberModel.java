package com.carsuper.coahr.mvp.model.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.PhoneNumberContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.model.bean.PhoneMessageBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class  PhoneNumberModel extends BaseModel<PhoneNumberContract.Presenter> implements PhoneNumberContract.Model {

    @Inject
    public PhoneNumberModel(){
        super();
    }

    @Override
    public void getVerifyCode(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<PhoneMessageBean>(getApiservice().getVerifyCode(map.get("phone")))).subscribeWith(new SimpleDisposableSubscriber<PhoneMessageBean>() {
            @Override
            public void _onNext(PhoneMessageBean phoneMessageBean) {
                if (getPresenter() != null) {
                    if (phoneMessageBean.getCode()!=0) {
                        getPresenter().getVerifyCodeFail(phoneMessageBean.getMsg());
                    }else {
                        getPresenter().getVerifyCodeSuccess(phoneMessageBean.getMsg());
                    }
                }
            }
//
//            @Override
//            public void _onError(Throwable t) {
//                Toast.makeText(BaseApplication.mContext, "获取验证码失败", Toast.LENGTH_LONG).show();
//                KLog.e("onFailure", t.getMessage());
//            }
        }));

    }

    @Override
    public void binPhoneNumber(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<BindPhoneBean>(getApiservice().bindPhone(map.get("phone"),map.get("vcode"),map.get("uid"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<BindPhoneBean>() {
                    @Override
                    public void _onNext(BindPhoneBean loginBean) {
                        if (getPresenter() != null) {
                            if (loginBean.getCode()==0) {
                                getPresenter().onBindPhoneSuccess(loginBean);
                            }else {
                                getPresenter().onBindPhoneFail(loginBean.getMsg());
                            }
                        }
                    }
                }));
    }

    /**
     * 微信登陆为空时，绑定手机号一并登录
     * @param map
     */
    @Override
    public void wxLogin(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<LoginBean>(getApiservice().wxLogin(map.get("phone"),map.get("verify_code"),map.get("openid"),map.get("unionid"),map.get("nickname"),map.get("headimgurl"),map.get("device_token"))))
                .subscribeWith(new SimpleDisposableSubscriber<LoginBean>() {
                    @Override
                    public void _onNext(LoginBean loginBean) {
                        if (getPresenter() != null) {
                            if (loginBean.getCode()!=0) {
                                getPresenter().wxLoginFail(loginBean);
                            }else {
                                getPresenter().wxLoginSuccess(loginBean);
                            }
                        }
                    }
//            @Override
//            public void _onError(Throwable t) {
//                Toast.makeText(BaseApplication.mContext, "登录失败", Toast.LENGTH_LONG).show();
//                KLog.e("onFailure", t.getMessage());
//            }
                }));
    }
}
