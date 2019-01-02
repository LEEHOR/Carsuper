package com.carsuper.coahr.mvp.view.myData.setting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.setting.PhoneNumberContract;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.presenter.myData.setting.PhoneNumberPresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.myData.VerifyPhoneDialogFragment;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.widgets.BlockTextView;
import com.carsuper.coahr.widgets.MobilePhoneEditText;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class PhoneNumberFragment extends BaseFragment<PhoneNumberContract.Presenter> implements PhoneNumberContract.View {
    @Inject
    PhoneNumberPresenter phoneNumberPresenter;

    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.et_mobilephone)
    MobilePhoneEditText etPhoneNumber;
    @BindView(R.id.btv_verification)
    BlockTextView btv_verification;
    @BindView(R.id.et_verifytoken)
    EditText etVerifytoken;
    @BindView(R.id.tv_save)
    TextView tvSave;

    private  String type;

    public static PhoneNumberFragment newInstance(String type){
        PhoneNumberFragment fragment = new PhoneNumberFragment();
        Bundle bundle=new Bundle();
        bundle.putString("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public PhoneNumberContract.Presenter getPresenter() {
        return phoneNumberPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_change_phone_number;
    }

    @Override
    public void initView() {
        tbTittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

        btv_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPhoneNumber.getPhoneNumber().length() == 11) {
                    btv_verification.startGetCount();
                    HashMap map = new HashMap();
                    map.put("phone", etPhoneNumber.getPhoneNumber());
                    Toast.makeText(_mActivity,etPhoneNumber.getPhoneNumber()+"",Toast.LENGTH_LONG).show();
                    getPresenter().getVerifyCode(map);
                } else {
                    Toast.makeText(_mActivity, "请输入正确手机号码", Toast.LENGTH_LONG).show();
                }
            }
        });


        etVerifytoken.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 6 && etPhoneNumber.getPhoneNumber().length() == 11) {
                    tvSave.setClickable(true);
                    tvSave.setBackgroundResource(R.color.prominent_text_color);
                } else {
                    tvSave.setClickable(false);
                    tvSave.setBackgroundColor(Color.parseColor("#969798"));
                }
            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPhoneNumber.getPhoneNumber().length() != 11) {
                    Toast.makeText(_mActivity, "请输入十一位手机号", Toast.LENGTH_LONG).show();
                    return;
                }
                if (etVerifytoken.getText().length() != 6) {
                    Toast.makeText(_mActivity, "请输入六位验证码", Toast.LENGTH_LONG).show();
                    return;
                }
                    if (type.equals("微信登录")){
                        WxLogin();
                    } else {
                        Map map = new HashMap();
                        map.put("phone", etPhoneNumber.getPhoneNumber());
                        map.put("verify_code", etVerifytoken.getText().toString());
                        map.put("uid", Constants.uid);
                        map.put("token", Constants.token);
                        getPresenter().binPhoneNumber(map);
                    }

            }
        });

    }

    @Override
    public void initData() {
        if (getArguments() !=null){
             type = getArguments().getString("type");
        }
        if (type.equals("微信登录")){
            tvSave.setText("绑定并登录");
        }

    }



    @Override
    public void onBindPhoneFail(String throwable) {
        Toast.makeText(_mActivity, throwable, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBindPhoneSuccess(BindPhoneBean bindPhoneBean) {
        Toast.makeText(_mActivity, bindPhoneBean.getJdata().getJmsg(), Toast.LENGTH_LONG).show();
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
    public void wxLoginSuccess(LoginBean loginBean) {
        Toast.makeText(getActivity(), loginBean.getMsg(), Toast.LENGTH_LONG).show();
        ((ContainerActiivty)getActivity()).onLoginSuccessResult(1);
        String token = loginBean.getJdata().getToken();
        int uid = loginBean.getJdata().getUid();
        PreferenceUtils.setPrefString(getActivity(),"token",token);
        PreferenceUtils.setPrefString(getActivity(),"uid",String.valueOf(uid));
        Constants.token= token;
        Constants.uid = String.valueOf(uid);
    }

    @Override
    public void wxLoginFail(LoginBean loginBean) {
        Toast.makeText(_mActivity, loginBean.getMsg(), Toast.LENGTH_LONG).show();
    }

    private void WxLogin(){
        UMShareAPI mShareAPI = UMShareAPI.get(_mActivity);
        mShareAPI.getPlatformInfo(_mActivity, SHARE_MEDIA.WEIXIN, umAuthListener);
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            KLog.e("onstart");
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, final Map<String, String> data) {
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
                map.put("phone",etPhoneNumber.getPhoneNumber());
                map.put("verify_code",etVerifytoken.getText().toString());
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
