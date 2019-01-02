package com.carsuper.coahr.mvp.model.myData;

import android.widget.Toast;

import com.carsuper.coahr.mvp.contract.myData.LoginContract;
import com.carsuper.coahr.mvp.model.ApiService;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.model.bean.PhoneMessageBean;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.socks.library.KLog;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author： hengzwd on 2018/7/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class LoginModel extends BaseModel<LoginContract.Presenter> implements LoginContract.Model {

    @Inject
    public LoginModel() {
        super();
    }

    @Override
    public void bindPhone(Map<String, String> map) {

    }

    @Override
    public void getVerifyCode( Map<String,String> map) {

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
        }));

    }

    @Override
    public void phoneLogin(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<LoginBean>(getApiservice().phoneLogin(map.get("phone"),map.get("verify_code"),map.get("device_token"))))
                .subscribeWith(new SimpleDisposableSubscriber<LoginBean>() {
            @Override
            public void _onNext(LoginBean loginBean) {
                if (getPresenter() != null) {
                    if (loginBean.getCode()!=0) {
                        getPresenter().phoneLoginFail(loginBean.getMsg());
                    }else {
                        getPresenter().phoneLoginSuccess(loginBean);
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

    @Override
    public void wxLogin(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<LoginBean>(getApiservice().wxLogin(map.get("phone"),map.get("verify_code"),map.get("openid"),map.get("unionid"),map.get("nickname"),map.get("headimgurl"),map.get("device_token"))))
                .subscribeWith(new SimpleDisposableSubscriber<LoginBean>() {
                    @Override
                    public void _onNext(LoginBean loginBean) {
                        if (getPresenter() != null) {
                            if (loginBean.getCode()!=0) {
                                getPresenter().onWXloginFailure(loginBean );
                            }else {
                                getPresenter().onWXloginSuccess(loginBean);
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
