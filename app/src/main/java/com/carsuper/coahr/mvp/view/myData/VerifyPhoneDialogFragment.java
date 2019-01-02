package com.carsuper.coahr.mvp.view.myData;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.VerifyPhoneDialogContract;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.PhoneMessageBean;
import com.carsuper.coahr.mvp.presenter.myData.VerifyPhoneDialogPresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.base.BaseDialogFragment;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.widgets.BlockTextView;
import com.carsuper.coahr.widgets.MobilePhoneEditText;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/2.
 * Email：hengzwdhengzwd@qq.com
 */
public class VerifyPhoneDialogFragment extends BaseDialogFragment<VerifyPhoneDialogContract.Presenter> implements VerifyPhoneDialogContract.View {


    @Inject
    VerifyPhoneDialogPresenter phoneDialogPresenter;
    @BindView(R.id.image0)
    ImageView image0;
    @BindView(R.id.et_mobilephone)
    MobilePhoneEditText etMobilephone;
    @BindView(R.id.btv_verification)
    BlockTextView btvVerification;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_login)
    TextView tvLogin;


    private String token;

    private String uid;




    public static VerifyPhoneDialogFragment newInstance(String token, String uid) {
        VerifyPhoneDialogFragment verifyPhoneDialogFragment = new VerifyPhoneDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("token", token);
        bundle.putString("uid", uid);
        verifyPhoneDialogFragment.setArguments(bundle);
        return verifyPhoneDialogFragment;
    }

    @Override
    public VerifyPhoneDialogContract.Presenter getPresenter() {
        return phoneDialogPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragmentdialog_verify_mobilephone;
    }

    @Override
    public void initView() {
        token = getArguments().getString("token");
        uid = getArguments().getString("uid");
        btvVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMobilephone.getPhoneNumber().length() == 11) {
                    btvVerification.startGetCount();
                    HashMap map = new HashMap();
                    map.put("phone", etMobilephone.getPhoneNumber());
                    getPresenter().getVerifyCode(map);

                } else {
                    Toast.makeText(getActivity(), "请输入正确手机号码", Toast.LENGTH_LONG).show();
                }
            }
        });


        etVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 6 && etMobilephone.getPhoneNumber().length() == 11) {
                    tvLogin.setClickable(true);
                    tvLogin.setBackgroundColor(Color.parseColor("#2fc1ff"));
                } else {
                    tvLogin.setClickable(false);
                    tvLogin.setBackgroundColor(Color.parseColor("#969798"));
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMobilephone.getPhoneNumber().length() != 11) {
                    Toast.makeText(getActivity(), "请输入十一位手机号", Toast.LENGTH_LONG).show();
                    return;
                }
                if (etVerificationCode.getText().length() != 6) {
                    Toast.makeText(getActivity(), "请输入六位验证码", Toast.LENGTH_LONG).show();
                    return;
                }
                Map map = new HashMap();
                map.put("token", token);
                map.put("uid", uid);
                map.put("phone", etMobilephone.getPhoneNumber());
                map.put("vcode", etVerificationCode.getText().toString());
                getPresenter().bindLogin(map);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initAnimate() {

    }

    @Override
    public void iniWidow(Window window) {
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawableResource(R.drawable.bg_fff_background);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setWindowAnimations(animate_style);
        }
    }

    @Override
    public void onGetVerifyCodeSuccess(PhoneMessageBean bean) {

    }

    @Override
    public void onGetVerifyCodeFailure(String failure) {
        Toast.makeText(getActivity(), failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBindLoginSuccess(BindPhoneBean bindPhoneBean) {
        Toast.makeText(getActivity(), bindPhoneBean.getJdata().getJmsg(), Toast.LENGTH_LONG).show();
        ((ContainerActiivty)getActivity()).onLoginSuccessResult(1);
        PreferenceUtils.setPrefString(getActivity(),"token",token);
        PreferenceUtils.setPrefString(getActivity(),"uid",uid);
        Constants.token= token;
        Constants.uid = uid;
    }

    @Override
    public void onBindLoginFailure(String failure) {
        Toast.makeText(getActivity(), failure, Toast.LENGTH_LONG).show();
    }

}
