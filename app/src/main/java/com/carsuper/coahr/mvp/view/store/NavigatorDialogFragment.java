package com.carsuper.coahr.mvp.view.store;

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
import android.widget.TextView;

import com.carsuper.coahr.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class NavigatorDialogFragment extends AppCompatDialogFragment {


    @BindView(R.id.tv_baidu_navigator)
    TextView tvBaiduNavigator;
    @BindView(R.id.tv_gaode_navigator)
    TextView tvGaodeNavigator;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    Unbinder unbinder;
    private NavigatorItemSelectListener navigatorItemSelectListener;


    public static NavigatorDialogFragment newInstance() {
        NavigatorDialogFragment fragment = new NavigatorDialogFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentdialog_navigator, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fl_navigator_container, R.id.tv_baidu_navigator, R.id.tv_gaode_navigator, R.id.tv_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_navigator_container:
                break;
            case R.id.tv_baidu_navigator:
                navigatorItemSelectListener.onItemSelct("baidu");
                break;
            case R.id.tv_gaode_navigator:
                navigatorItemSelectListener.onItemSelct("gaode");
                break;
            case R.id.tv_cancle:
                break;
        }
        dismiss();
    }

    public void setOnNavigatiorItemSelectListener(NavigatorItemSelectListener navigatiorItemSelectListener) {
        this.navigatorItemSelectListener = navigatiorItemSelectListener;
    }

    public interface NavigatorItemSelectListener {
        void onItemSelct(String mapName);
    }
}
