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
import android.widget.FrameLayout;
import android.widget.TextView;

import com.carsuper.coahr.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/16.
 * Email：hengzwdhengzwd@qq.com
 */
public class PhotoSelectDialogFragment extends AppCompatDialogFragment {


    @BindView(R.id.tv_take_photo)
    TextView tvTakePhoto;
    @BindView(R.id.tv_goto_album)
    TextView tvGotoAlbum;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.fl_navigator_container)
    FrameLayout flNavigatorContainer;
    Unbinder unbinder;
    private ItemSelectListener itemSelectListener;


    public static PhotoSelectDialogFragment newInstance() {
        PhotoSelectDialogFragment fragment = new PhotoSelectDialogFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentdialog_photoselect, container, false);
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


    public void setOnPhotoItemSelectListener(ItemSelectListener itemSelectListener) {
        this.itemSelectListener = itemSelectListener;
    }

    @OnClick({R.id.tv_take_photo, R.id.tv_goto_album, R.id.tv_cancle, R.id.fl_navigator_container})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_take_photo:
                itemSelectListener.onItemSelct("takephoto");
                break;
            case R.id.tv_goto_album:
                itemSelectListener.onItemSelct("album");
                break;
            case R.id.tv_cancle:
                break;
            case R.id.fl_navigator_container:
                break;
        }
        dismiss();
    }

    public interface ItemSelectListener {
        void onItemSelct(String selectStyle);
    }
}
