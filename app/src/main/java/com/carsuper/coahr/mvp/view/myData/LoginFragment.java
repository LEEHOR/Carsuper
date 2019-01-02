package com.carsuper.coahr.mvp.view.myData;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.LoginContract;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.EvenBus_Mall;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.presenter.myData.LoginPresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.myData.setting.PhoneNumberFragment;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.widgets.BlockTextView;
import com.carsuper.coahr.widgets.MobilePhoneEditText;
import com.socks.library.KLog;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Author： hengzwd on 2018/7/13.
 * Email：hengzwdhengzwd@qq.com
 * 登陆页面
 */
public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {

    @Inject
    LoginPresenter loginPresenter;
    @BindView(R.id.et_mobilephone)
    MobilePhoneEditText etMobilephone;
    @BindView(R.id.btv_verification)
    BlockTextView btvVerification;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.login_btn_wx)
    ImageView loginBtnWx;
    @BindView(R.id.login_back)
    ImageView login_back;

    private int fromfragment;
    private boolean isShow = false;

    public static LoginFragment newInstance(int fromfragment) {
        LoginFragment fragment = new LoginFragment();
        Bundle arg = new Bundle();
        arg.putInt("fromfragment", fromfragment);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public LoginContract.Presenter getPresenter() {
        return loginPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView() {
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        loginBtnWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WxLogin();
               /* UMShareAPI mShareAPI = UMShareAPI.get(_mActivity);
                mShareAPI.getPlatformInfo(_mActivity, SHARE_MEDIA.WEIXIN, umAuthListener);*/
            }
        });
        btvVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMobilephone.getPhoneNumber().length() == 11) {
                    btvVerification.startGetCount();
                    HashMap map = new HashMap();
                    map.put("phone", etMobilephone.getPhoneNumber());
                    Toast.makeText(_mActivity, etMobilephone.getPhoneNumber() + "", Toast.LENGTH_LONG).show();
                    getPresenter().getVerifyCode(map);
                } else {
                    Toast.makeText(_mActivity, "请输入正确手机号码", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMobilephone.getPhoneNumber().length() != 11) {
                    Toast.makeText(_mActivity, "请输入十一位手机号", Toast.LENGTH_LONG).show();
                    return;
                }
                if (etVerificationCode.getText().length() != 6) {
                    Toast.makeText(_mActivity, "请输入六位验证码", Toast.LENGTH_LONG).show();
                    return;
                }

                Map map = new HashMap();
                map.put("phone", etMobilephone.getPhoneNumber());
                map.put("verify_code", etVerificationCode.getText().toString());
                map.put("device_token", Constants.devicestoken);
                Log.d("token",Constants.devicestoken);
                getPresenter().phoneLogin(map);
            }

        });

    }

    @Override
    public void initData() {

            fromfragment = getArguments().getInt("fromfragment");

    }


    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            KLog.e("onstart");
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, final Map<String, String> data) {

            for (Map.Entry<String, String> entry : data.entrySet()) {

                KLog.d("微信登录","key= " + entry.getKey() + " and value= " + entry.getValue());
            }
            KLog.e("onComplete");
            if (SHARE_MEDIA.WEIXIN.equals(platform)) {
                if (TextUtils.isEmpty(Constants.devicestoken)) {
                    Constants.devicestoken = PushAgent.getInstance(_mActivity).getRegistrationId();
                }
                PreferenceUtils.setPrefString(_mActivity, "devicetoken", Constants.devicestoken);
                Map map = new HashMap();
                map.put("openid", data.get("openid"));
                map.put("unionid", data.get("unionid"));
                map.put("nickname", data.get("screen_name"));
                map.put("headimgurl", data.get("profile_image_url"));
                map.put("device_token", Constants.devicestoken);
                // KLog.e("onComplete");
                getPresenter().wxLogin(map);

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            KLog.e("shareonError:" + t.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            KLog.e("shareononCancel");

        }
    };


    @Override
    public void phoneLoginFail(String throwable) {
        Toast.makeText(_mActivity, throwable, Toast.LENGTH_LONG).show();
    }

    @Override
    public void phoneLoginSuccess(LoginBean loginBean) {
        PreferenceUtils.setPrefString(_mActivity, "phone", loginBean.getJdata().getPhone());
        PreferenceUtils.setPrefString(_mActivity, "token", loginBean.getJdata().getToken());
        PreferenceUtils.setPrefString(_mActivity, "uid", loginBean.getJdata().getUid() + "");
        Constants.phone = loginBean.getJdata().getPhone();
        Constants.token = loginBean.getJdata().getToken();
        KLog.d("token",loginBean.getJdata().getToken());
        Constants.uid = loginBean.getJdata().getUid() + "";
        if (fromfragment == Constants.MYDATAFRAGMENT) {
            ((ContainerActiivty) _mActivity).onLoginSuccessResult(1);
        } else if (fromfragment ==Constants.FragmentExchange){
            EventBus.getDefault().postSticky(new EvenBus_Mall(1));
            _mActivity.onBackPressed();
        } else {
            _mActivity.onBackPressed();
        }
        Toast.makeText(_mActivity, "登录成功", Toast.LENGTH_LONG).show();

    }

    @Override
    public void getVerifyCodeFail(String throwable) {
        Toast.makeText(_mActivity, throwable, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getVerifyCodeSuccess(String success) {
        Toast.makeText(_mActivity, success, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onWXloginSuccess(LoginBean loginBean) {
        PreferenceUtils.setPrefString(_mActivity, "phone", loginBean.getJdata().getPhone());
        PreferenceUtils.setPrefString(_mActivity, "token", loginBean.getJdata().getToken());
        PreferenceUtils.setPrefString(_mActivity, "uid", loginBean.getJdata().getUid() + "");
        Constants.phone = loginBean.getJdata().getPhone();
        Constants.token = loginBean.getJdata().getToken();
        Constants.uid = loginBean.getJdata().getUid() + "";
        KLog.d("token",loginBean.getJdata().getToken());
        if (loginBean.getJdata().getPhone() == null || loginBean.getJdata().getPhone().equals("")) {
            VerifyPhoneDialogFragment verifyPhoneDialogFragment = VerifyPhoneDialogFragment.newInstance(loginBean.getJdata().getToken(), loginBean.getJdata().getUid() + "");
            verifyPhoneDialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);
        } else {
            if (fromfragment == Constants.MYDATAFRAGMENT) {
                ((ContainerActiivty) _mActivity).onLoginSuccessResult(1);
            } else if (fromfragment ==Constants.FragmentExchange){
                EventBus.getDefault().postSticky(new EvenBus_Mall(1));
                _mActivity.onBackPressed();
            }else {
                _mActivity.onBackPressed();
            }
            Toast.makeText(_mActivity, "登录成功", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onWXloginFailure(LoginBean loginBean) {
        Toast.makeText(_mActivity, loginBean.getMsg(), Toast.LENGTH_LONG).show();
        if (loginBean.getCode() == 10012) { //未绑定手机号，跳转到手机绑定页面
            //start(PhoneNumberFragment.newInstance("登录"));
            Toast.makeText(_mActivity, "未绑定手机号", Toast.LENGTH_LONG).show();
           // VerifyPhoneDialogFragment verifyPhoneDialogFragment = VerifyPhoneDialogFragment.newInstance(loginBean.getJdata().getToken(), loginBean.getJdata().getUid() + "");
           // verifyPhoneDialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);
            start(PhoneNumberFragment.newInstance("微信登录"));
        }


    }

    @Override
    public void bindPhoneSuccess(BindPhoneBean bindPhoneBean) {

    }

    @Override
    public void bindPhoneFail(BindPhoneBean bindPhoneBean) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

/*    @Override
    public void onHiddenChanged(boolean hidden) {//被其他fragment 覆盖 ，之后show出来都会调用
        super.onHiddenChanged(hidden);
        isShow = !hidden;
        if (isShow) {
            if (haslogin()) {

            } else {   //没有登录过
                WxLogin();
            }
        }
    }*/

    private void WxLogin(){
        UMShareAPI mShareAPI = UMShareAPI.get(_mActivity);
        mShareAPI.getPlatformInfo(_mActivity, SHARE_MEDIA.WEIXIN, umAuthListener);
    }
}
