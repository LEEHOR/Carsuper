package com.carsuper.coahr.mvp.model.myData;

import com.carsuper.coahr.mvp.contract.myData.VerifyPhoneDialogContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.PhoneMessageBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/2.
 * Email：hengzwdhengzwd@qq.com
 */
public class VerifyPhoneDialogModel extends BaseModel<VerifyPhoneDialogContract.Presenter> implements VerifyPhoneDialogContract.Model {


    @Inject
    public VerifyPhoneDialogModel(){
            super();
    }
    @Override
    public void getVerifyCode(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<PhoneMessageBean>(getApiservice().getVerifyCode(map.get("phone"))))
        .subscribeWith(new SimpleDisposableSubscriber<PhoneMessageBean>() {
            @Override
            public void _onNext(PhoneMessageBean bean) {
                if (getPresenter() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onGetVerifyCodeSuccess(bean);
                    } else {
                        getPresenter().onGetVerifyCodeFailure(bean.getMsg());
                    }
                }

            }
        }));
    }

    @Override
    public void bindLogin(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<BindPhoneBean>(getApiservice().bindPhone(map.get("phone"), map.get("vcode"), map.get("uid"), map.get("token"))))
            .subscribeWith(new SimpleDisposableSubscriber<BindPhoneBean>() {
                @Override
                public void _onNext(BindPhoneBean bindPhoneBean) {
                    if (getPresenter() != null) {
                        if (bindPhoneBean.getCode()==0) {
                            getPresenter().onBindLoginSuccess(bindPhoneBean);
                        } else {
                            getPresenter().onBindLoginFailure(bindPhoneBean.getMsg());
                        }
                    }

                }
            }));
    }
}
