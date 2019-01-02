package com.carsuper.coahr.mvp.view.myData.returnorchange;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.returnorchange.ReturnChangeSelectContract;
import com.carsuper.coahr.mvp.model.bean.RefundApplyBean;
import com.carsuper.coahr.mvp.presenter.myData.returnorchange.ReturnChangeSelectPresenter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 */
public class ReturnChangeSelectFragement extends BaseFragment<ReturnChangeSelectContract.Presenter> implements ReturnChangeSelectContract.View {


    @Inject
    ReturnChangeSelectPresenter returnChangeSelectPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.iv_commodity_img)
    ImageView ivCommodityImg;
    @BindView(R.id.tv_commodity_info)
    TextView tvCommodityInfo;
    @BindView(R.id.tv_commodity_price)
    TextView tvCommodityPrice;
    @BindView(R.id.tv_payment_count)
    TextView tvPaymentCount;
    @BindView(R.id.rl_commodity)
    RelativeLayout rlCommodity;
    @BindView(R.id.rl_only_return_money)
    RelativeLayout rlOnlyReturnMoney;
    @BindView(R.id.rl_return_good_money)
    RelativeLayout rlReturnGoodMoney;
    @BindView(R.id.rl_exchange_good)
    RelativeLayout rlExchangeGood;

    public static final int TYPE_ONLY_RETURN_MONEY = 1;
    public static final int TYPE_RETURN_MONEY_AND_GOODS = 2;
    public static final int TYPE_ONLY_EXCHANGE_GOODS = 3;

    private String order_id;
    private String order_status;

    public static ReturnChangeSelectFragement newInstance(String order_Id,String order_status) {
        ReturnChangeSelectFragement fragement = new ReturnChangeSelectFragement();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_Id);
        arg.putString("order_status",order_status);
        fragement.setArguments(arg);
        return fragement;
    }

    @Override
    public ReturnChangeSelectContract.Presenter getPresenter() {
        return returnChangeSelectPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_returnchange_select;
    }

    @Override
    public void initView() {

        rlOnlyReturnMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               start(ApplyReturnChangeFragment.newInstance(order_id,TYPE_ONLY_RETURN_MONEY,order_status));
            }
        });

        rlReturnGoodMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(ApplyReturnChangeFragment.newInstance(order_id,TYPE_RETURN_MONEY_AND_GOODS,order_status));
            }
        });

        rlExchangeGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(ApplyReturnChangeFragment.newInstance(order_id,TYPE_ONLY_EXCHANGE_GOODS,order_status));
            }
        });
    }

    @Override
    public void initData() {
        order_id = getArguments().getString("order_id");
        order_status = getArguments().getString("order_status");
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id",order_id);
        getPresenter().refundApply(map);

    }


    @Override
    public void onRefundApplySuccess(RefundApplyBean bean) {
        tvCommodityPrice.setText("￥"+bean.getJdata().getCommodity().getC_price());
        tvCommodityInfo.setText(bean.getJdata().getCommodity().getC_name());
        tvPaymentCount.setText("x"+bean.getJdata().getCommodity().getC_num());
        Imageloader.loadImage(bean.getJdata().getCommodity().getC_thumbnail(),ivCommodityImg);
    }

    @Override
    public void onRefundApplyFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();
    }

}
