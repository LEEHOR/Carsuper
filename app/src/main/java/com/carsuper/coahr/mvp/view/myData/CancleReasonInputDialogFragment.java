package com.carsuper.coahr.mvp.view.myData;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.KeyBoardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 * 取消预约订单或取消商品购买
 */
public class CancleReasonInputDialogFragment extends AppCompatDialogFragment {


    Unbinder unbinder;
    @BindView(R.id.rdg_pay)
    RadioGroup rdgPay;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    private InputCallback inputCallback;

    private String reason;

    public static final int TYPE_COMMODIITY_ORDER = 1;
    public static final int TYPE_MAINTANCE_ORDER = 2;

    public static CancleReasonInputDialogFragment newInstance(int type) {
        CancleReasonInputDialogFragment fragment = new CancleReasonInputDialogFragment();
        Bundle arg = new Bundle();
        arg.putInt("type", type);
        fragment.setArguments(arg);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if (getArguments().getInt("type") == TYPE_COMMODIITY_ORDER) {
            view = inflater.inflate(R.layout.fragmentdialog_cancle_commodityorder_reason_input, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_dialog_cancle_maintanceorder_reason_input, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        KeyBoardUtils.UpdateUI(view.getRootView(), getActivity());
        init();
        return view;
    }


    private void init() {
        flContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        rdgPay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                reason = ((RadioButton) group.findViewById(checkedId)).getText().toString();
            }
        });
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reason != null) {
                    inputCallback.onInputSend(reason);
                    dismiss();
                } else {
                    Toast.makeText(BaseApplication.mContext, "请选择取消原因", Toast.LENGTH_SHORT).show();
                }
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
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setWindowAnimations(R.style.bottom_in_out_animation);
        }
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void setOnInputCallback(InputCallback inputCallback) {
        this.inputCallback = inputCallback;
    }

    public interface InputCallback {
        void onInputSend(String input);
    }

}
