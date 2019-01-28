package com.carsuper.coahr.mvp.view.Exchange_mall;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.contract.main.FragmentPayPass_C;
import com.carsuper.coahr.mvp.model.bean.ExchangeRe;
import com.carsuper.coahr.mvp.presenter.main.FragmentPayPass_Presenter;
import com.carsuper.coahr.mvp.view.adapter.KeyboardChangeListener;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseDialogFragment;
import com.carsuper.coahr.utils.KeyBoardUtils;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.SoftKeyboardStateHelper;
import com.carsuper.coahr.widgets.PayPsdInputView;
import com.socks.library.KLog;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/29
 * on 12:50
 */
public class FragmentPayPass extends BaseDialogFragment<FragmentPayPass_C.Presenter> implements FragmentPayPass_C.View {
    @Inject
    FragmentPayPass_Presenter p;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.inputPass)
    PayPsdInputView inputPass;
    @BindView(R.id.root_view)
    FrameLayout root_view;
    private ReceiveListener listener;
    private String c_id;
    private String o_status;
    private KeyboardChangeListener keyboardChangeListener;
    private SoftKeyboardStateHelper softKeyboardStateHelper;

    public static FragmentPayPass newInstance(String c_ic,String o_status) {
        FragmentPayPass payPass=new FragmentPayPass();
        Bundle bundle=new Bundle();
        bundle.putString("c_id",c_ic);
        bundle.putString("o_status",o_status);
        payPass.setArguments(bundle);
        return  payPass;
    }

    @Override
    public FragmentPayPass_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.dialog_fragment_pay;
    }

    @Override
    public void initView() {
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
     /*   keyboardChangeListener= new KeyboardChangeListener(this.getDialog().getWindow());
        keyboardChangeListener.setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if (isShow){
                    KLog.d("是否显示1",isShow);
                } else {
                    KLog.d("是否显示2",isShow);
                 //   KeyBoardUtils.hideKeybord(inputPass,getActivity());
                    dismiss();
                }
                KLog.d("是否显示3",isShow);
            }
        });*/

        softKeyboardStateHelper = new SoftKeyboardStateHelper(root_view);
        softKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {

            }

            @Override
            public void onSoftKeyboardClosed() {
                    dismiss();
            }
        });

        inputPass.setComparePassword(new PayPsdInputView.onPasswordListener() {
            @Override
            public void onDifference(String oldPsd, String newPsd) {

            }

            @Override
            public void onEqual(String psd) {

            }

            @Override
            public void inputFinished(String inputPsd) {
                ExchangeRe(inputPsd);
            }
        });
        inputPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i==KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN){  //删除键
                    inputPass.removePW();  //删除最后一个
                    return true;
                }
                if (i==KeyEvent.KEYCODE_BACK && keyEvent.getAction()==KeyEvent.ACTION_DOWN){  //返回键
                    dismiss();         //关闭当前页面
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void initData() {
        c_id = getArguments().getString("c_id");
        o_status = getArguments().getString("o_status");
        KeyBoardUtils.showKeybord(inputPass, getActivity());
    }

    @Override
    public void initAnimate() {

    }

    @Override
    public void iniWidow(Window window) {
        if (window != null) {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setWindowAnimations(R.style.bottom_in_out_animation);
        }
    }

    @Override
    public void exChangeReSuccess(ExchangeRe exchangeRe) {
        KeyBoardUtils.hideKeybord(inputPass,getActivity());
        Toast.makeText(BaseApplication.mContext,exchangeRe.getMsg(),Toast.LENGTH_LONG).show();
        if (listener != null) {
            listener.ReceiveSuccess(this);
        }
    }

    @Override
    public void exChangeReFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_LONG).show();
        if (listener != null) {
            listener.ReceiveFailure();
        }

    }


    /**
     * 领取
     */
    private void ExchangeRe(String code){
        Map map=new HashMap();
        map.put("c_id",c_id);
        map.put("token",Constants.token);
        map.put("o_status",o_status);
        map.put("code",code);
        p.exChangeRe(map);
    }

    public interface ReceiveListener{
        void  ReceiveSuccess(AppCompatDialogFragment dialogFragment);
        void  ReceiveFailure();
    }

    public void setListener(ReceiveListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (inputPass != null) {
            KeyBoardUtils.showKeybord(inputPass, getActivity());
        }
    }
}
