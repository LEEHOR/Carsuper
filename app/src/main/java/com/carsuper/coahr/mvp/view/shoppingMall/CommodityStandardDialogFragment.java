package com.carsuper.coahr.mvp.view.shoppingMall;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.CommodityCountView;
import com.carsuper.coahr.widgets.radioGroup.CustomRadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/7/4.
 * Email：hengzwdhengzwd@qq.com
 * 购买商品时弹窗
 */
public class CommodityStandardDialogFragment extends AppCompatDialogFragment {



    @BindView(R.id.fl_commodity_standard)
    FrameLayout flCommodityStandard;
    @BindView(R.id.iv_commodity_img)
    ImageView ivCommodityImg;
    @BindView(R.id.iv_close)
    ImageView ivClose;

    @BindView(R.id.tv_commodity_price)
    TextView tvCommodityPrice;
    @BindView(R.id.ccv_commodity_count)
    CommodityCountView ccvCommodityCount;
    @BindView(R.id.bt_ensure)
    Button btEnsure;
    Unbinder unbinder;


    public static final int  TO_BUY = 0;
    public static final int  TO_ADD_CART = 1;

    private onEnsureDissmissListener onDissmissListener;

    private int dissmissTo;
    private float price;
    private String commodityIMG;
    private int count ;
    public static CommodityStandardDialogFragment newInstance(float price,String commodityIMG,int count,int dissmissTo) {
        CommodityStandardDialogFragment fragment = new CommodityStandardDialogFragment();
        Bundle args = new Bundle();
        args.putFloat("price",price);
        args.putString("commodityIMG",commodityIMG);
        args.putInt("count",count);
        args.putInt("dissmissTo",dissmissTo);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragmentdialog_selectcommoditystandard, container, false);
        unbinder = ButterKnife.bind(this, contentView);
        initData();
        return contentView;
    }

    private void initData() {
        price = getArguments().getFloat("price");
        commodityIMG = getArguments().getString("commodityIMG");
        count = getArguments().getInt("count");
        dissmissTo= getArguments().getInt("dissmissTo");

        tvCommodityPrice.setText("￥"+price);
        Imageloader.loadImage(commodityIMG,ivCommodityImg);
        ccvCommodityCount.setCount(count);
        btEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDissmissListener.onDissMiss(dissmissTo,ccvCommodityCount.getCount());
                dismiss();
            }
        });
        flCommodityStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss();
            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setWindowAnimations(R.style.bottom_in_out_animation);
        }
        return dialog;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void setdissmissListener(onEnsureDissmissListener onDissmissListener){
            this.onDissmissListener = onDissmissListener;
    }

    public interface onEnsureDissmissListener{
        void onDissMiss(int to,int count);
    }
}
