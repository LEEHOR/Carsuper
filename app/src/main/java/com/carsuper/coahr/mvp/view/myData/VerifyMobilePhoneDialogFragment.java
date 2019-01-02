package com.carsuper.coahr.mvp.view.myData;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.model.ApiService;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.PhoneMessageBean;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.utils.KeyBoardUtils;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.widgets.BlockTextView;
import com.carsuper.coahr.widgets.MobilePhoneEditText;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Author： hengzwd on 2018/7/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class VerifyMobilePhoneDialogFragment extends AppCompatDialogFragment {
    @Inject
    Retrofit retrofit;

    @BindView(R.id.et_mobilephone)
    MobilePhoneEditText etMobilephone;
    @BindView(R.id.btv_verification)
    BlockTextView btvVerification;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    Unbinder unbinder;


    private String token;

    private String uid;


    public static VerifyMobilePhoneDialogFragment newInstance(String token, String uid) {
        VerifyMobilePhoneDialogFragment verifyMobilePhoneDialogFragment = new VerifyMobilePhoneDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("token", token);
        bundle.putString("uid", uid);
        verifyMobilePhoneDialogFragment.setArguments(bundle);
        return verifyMobilePhoneDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentdialog_verify_mobilephone, container, false);
        unbinder = ButterKnife.bind(this, view);
        KeyBoardUtils.UpdateUI(view.getRootView(),getActivity());
        init();
        return view;
    }

    private void init() {
        token  = getArguments().getString("token");
        uid = getArguments().getString("uid");
        btvVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etMobilephone.getPhoneNumber().length() == 11) {
                    btvVerification.startGetCount();
                    HashMap map = new HashMap();
                    map.put("phone", etMobilephone.getPhoneNumber());
                    Toast.makeText(getActivity(), etMobilephone.getPhoneNumber() + "", Toast.LENGTH_LONG).show();
                    getVerifyCode(map);

                } else {
                    Toast.makeText(getActivity(), "请输入正确手机号码", Toast.LENGTH_LONG).show();
                }
            }
        });


//        etVerificationCode.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString().length() == 6 && etMobilephone.getPhoneNumber().length() == 11) {
//                    tvLogin.setClickable(true);
//                    tvLogin.setBackgroundColor(Color.parseColor("#2fc1ff"));
//                } else {
//                    tvLogin.setClickable(false);
//                    tvLogin.setBackgroundColor(Color.parseColor("#969798"));
//                }
//            }
//        });

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
                map.put("token",token);
                map.put("uid",uid);
                map.put("phone", etMobilephone.getPhoneNumber());
                map.put("vcode", etVerificationCode.getText().toString());
                bindLogin(map);
            }
        });

    }


    private void getVerifyCode(final Map<String, String> map) {

        retrofit.create(ApiService.class).getVerifyCode(map.get("phone")).enqueue(new Callback<PhoneMessageBean>() {
            @Override
            public void onResponse(Call<PhoneMessageBean> call, Response<PhoneMessageBean> response) {
                PhoneMessageBean phoneMessageBean = response.body();
                if (phoneMessageBean.getCode() == 0) {

                }
            }

            @Override
            public void onFailure(Call<PhoneMessageBean> call, Throwable t) {
                KLog.e(t.getMessage());
            }
        });

    }

    private void bindLogin(final Map<String, String> map) {
        retrofit.create(ApiService.class).bindPhone(map.get("phone"), map.get("vcode"), map.get("uid"), map.get("token")).enqueue(new Callback<BindPhoneBean>() {
            @Override
            public void onResponse(Call<BindPhoneBean> call, Response<BindPhoneBean> response) {
                BindPhoneBean bindPhoneBean = response.body();
                if (bindPhoneBean.getCode()==0) {
                    Toast.makeText(getActivity(), bindPhoneBean.getJdata().getJmsg(), Toast.LENGTH_LONG).show();
                    ((ContainerActiivty)getActivity()).onLoginSuccessResult(1);
                    PreferenceUtils.setPrefString(getActivity(),"token",token);
                    PreferenceUtils.setPrefString(getActivity(),"uid",uid);
                    Constants.token= token;
                    Constants.uid = uid;
                }else {
                    Toast.makeText(getActivity(), bindPhoneBean.getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BindPhoneBean> call, Throwable t) {
                KLog.e(t.getMessage());
            }
        });

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawableResource(R.drawable.bg_fff_background);
            window.setWindowAnimations(R.style.top_in_out_animation);
        }
        return dialog;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }
}
