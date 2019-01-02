package com.carsuper.coahr.mvp.view.myData.commodityOrder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToPayContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.myData.commodityOrder.NeedToPayPresenter;
import com.carsuper.coahr.mvp.view.adapter.myCommodityOrder.CommodityOrderDetailCommodityAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.CancleReasonInputDialogFragment;
import com.carsuper.coahr.mvp.view.myData.ThankForEvaluateFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.ConfirmCommodityOrderFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.PayTypeSelectDialogFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.TwoDecimal;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 * 我的商品订单待支付
 */
public class NeedToPayFragment extends BaseFragment<NeedToPayContract.Presenter> implements NeedToPayContract.View {


    @Inject
    NeedToPayPresenter needToPayPresenter;

    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.tv_receiver)
    TextView tvReceiver;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tv_recieve_address)
    TextView tvRecieveAddress;
    @BindView(R.id.rv_commodity_list)
    RecyclerView rvCommodityList;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_copy_order_number)
    TextView tvCopyOrderNumber;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_need_to_pay)
    TextView tvNeedToPay;
    @BindView(R.id.tv_goto_pay)
    TextView tvGotoPay;
    @BindView(R.id.tv_cancel_order)
    TextView tvCancelOrder;


    private LinearLayoutManager linearLayoutManager;
    private CommodityOrderDetailCommodityAdapter commodityOrderDetailCommodityAdapter;
    private String order_Id;

    private String mPayType;
    private String order_status;

    private List<CommodityOrderDetailBean.JdataEntity.CommodityEntity> commodityEntityList = new ArrayList<>();

    public static NeedToPayFragment newInstance(String order_Id) {
        NeedToPayFragment fragment = new NeedToPayFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_Id);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public NeedToPayContract.Presenter getPresenter() {
        return needToPayPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_order_need_to_pay;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        tvGotoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出支付方式选择
                PayTypeSelectDialogFragment payTypeSelectDialogFragment = new PayTypeSelectDialogFragment();
                payTypeSelectDialogFragment.setOnpayTypeSelectListener(new PayTypeSelectDialogFragment.OnPayTypeSelectListener() {
                    @Override
                    public void onItemSlect(String payType) {
                        mPayType = payType;
                        Map map = new HashMap();
                        map.put("token",Constants.token);
                        map.put("order_id",order_Id);
                        map.put("order_type","service");
                        map.put("payment",mPayType);
                        Constants.LAST_PAYING_ORDERID=order_Id;
                        Constants.LAST_PAYING_PAGER="我的商品订单";
                        getPresenter().quickPay(map);
                    }
                });
                payTypeSelectDialogFragment.show(getFragmentManager(), TAG);

            }
        });

        tvCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancleReasonInputDialogFragment dialogFragment = CancleReasonInputDialogFragment.newInstance(CancleReasonInputDialogFragment.TYPE_COMMODIITY_ORDER);
                dialogFragment.setOnInputCallback(new CancleReasonInputDialogFragment.InputCallback() {
                    @Override
                    public void onInputSend(String input) {
                        Map map = new HashMap();
                        map.put("token", Constants.token);
                        map.put("order_id", order_Id);
                        map.put("reason", input);
                        getPresenter().cancelCommodityOrder(map);
                    }
                });
                dialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);
            }
        });
        tvCopyOrderNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) _mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("text", tvOrderNumber.getText()));
                //cm.setText(tvOrderNumber.getText());
                Toast.makeText(BaseApplication.mContext,"已复制到剪切板",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        order_Id = getArguments().getString("order_id");
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        rvCommodityList.setLayoutManager(linearLayoutManager);
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id", order_Id);
        getPresenter().getCommodityOrderDetail(map);

    }


    @Override
    public void onGetCommodityOrderDetialSuccess(CommodityOrderDetailBean bean) {
        order_status= bean.getJdata().getOrder().getO_status();
        commodityEntityList = bean.getJdata().getCommodity();
        commodityOrderDetailCommodityAdapter = new CommodityOrderDetailCommodityAdapter(bean.getJdata().getCommodity());
        rvCommodityList.setAdapter(commodityOrderDetailCommodityAdapter);
        rvCommodityList.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.material_grey_200)));
        tvOrderNumber.setText(bean.getJdata().getOrder().getOrder_id());
        tvOrderStatus.setText("待付款");
        tvOrderTime.setText(bean.getJdata().getOrder().getCreate_time());

        tvReceiver.setText(bean.getJdata().getAddress().getUsername());
        tvPhoneNumber.setText(bean.getJdata().getAddress().getTelephone());
        tvRecieveAddress.setText(bean.getJdata().getAddress().getAddress());
        double total = Double.parseDouble(bean.getJdata().getOrder().getTotal() != null && !bean.getJdata().getOrder().getTotal().equals("") ? bean.getJdata().getOrder().getTotal() : "0");
        int fee=bean.getJdata().getOrder().getFee();
        double fee_d=(double)fee;
        double topay=fee_d+total;
        double twoDecimal = TwoDecimal.getTwoDecimal(topay);
        tvNeedToPay.setText("¥" + twoDecimal);

    }

    @Override
    public void onGetCommodityOrderDetailFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelOrderSuccess(ResultBean resultBean) {
        if (resultBean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext, resultBean.getJdata().getJmsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BaseApplication.mContext, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelOrderFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onQuickPaySuccess(QuickPayBean bean) {
        if (mPayType.equals("ali")) {
            toAliPay(bean.getJdata().getOrder_string());
        } else if (mPayType.equals("wx")) {
            if (bean.getJdata().getOrder_json().getReturn_code().equals("SUCCESS")) {
                toWXPay(bean.getJdata().getOrder_json());
            }else {
                Toast.makeText(BaseApplication.mContext,bean.getJdata().getOrder_json().getReturn_msg(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onQuickPayFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }
}
