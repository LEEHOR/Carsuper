package com.carsuper.coahr.mvp.view.myData.commodityOrder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToRecieveContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.myData.commodityOrder.NeedToRecievePresenter;
import com.carsuper.coahr.mvp.view.adapter.myCommodityOrder.CommodityOrderDetailCommodityAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.returnorchange.ReturnChangeSelectFragement;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.TwoDecimal;
import com.carsuper.coahr.widgets.logisticsRecyclerView.LogisticsRecyclerView;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 * 我的商品订单待收货
 */
public class NeedToRecieveFragment extends BaseFragment<NeedToRecieveContract.Presenter> implements NeedToRecieveContract.View {


    @Inject
    NeedToRecievePresenter needToRecievePresenter;

    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_copy_order_number)
    TextView tvCopyOrderNumber;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_receiver)
    TextView tvReceiver;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tv_recieve_address)
    TextView tvRecieveAddress;
    @BindView(R.id.rv_logistics)
    LogisticsRecyclerView rvLogistics;
    @BindView(R.id.tv_see_all)
    TextView tvSeeAll;
    @BindView(R.id.rv_commodity_list)
    RecyclerView rvCommodityList;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.tv_money_for_send)
    TextView tvMoneyForSend;
    @BindView(R.id.tv_money_to_pay)
    TextView tvMoneyToPay;
    @BindView(R.id.tv_return_change)
    TextView tvReturnChange;
    @BindView(R.id.tv_ensure_recieve)
    TextView tvEnsureRecieve;
    @BindView(R.id.fl_logistics)
    FrameLayout flLogistics;

    private LinearLayoutManager linearLayoutManager;
    private CommodityOrderDetailCommodityAdapter commodityOrderDetailCommodityAdapter;
    private String order_Id;
    private String order_status;

    public static NeedToRecieveFragment newInstance(String order_Id) {
        NeedToRecieveFragment fragment = new NeedToRecieveFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_Id);
        fragment.setArguments(arg);
        return fragment;
    }



    @Override
    public NeedToRecieveContract.Presenter getPresenter() {
        return needToRecievePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_order_need_to_revieve;
    }

    @Override
    public void initView() {
        tvReturnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(ReturnChangeSelectFragement.newInstance(order_Id,order_status));
            }
        });

        tvEnsureRecieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = new HashMap();
                map.put("token",Constants.token);
                map.put("order_id",order_Id);
                getPresenter().ensureRecieve(map);
            }
        });
        tvSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(LogisticsFragment.newInstance(order_Id));
            }
        });
        tvCopyOrderNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) _mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("text", tvOrderNumber.getText()));
               // cm.setText(tvOrderNumber.getText());
                Toast.makeText(BaseApplication.mContext,"已复制到剪切板",Toast.LENGTH_SHORT).show();
            }
        });
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void initData() {
        order_Id = getArguments().getString("order_id");
        linearLayoutManager= new LinearLayoutManager(BaseApplication.mContext);
        rvCommodityList.setLayoutManager(linearLayoutManager);
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id",order_Id);
        getPresenter().getCommodityOrderDetail(map);
    }


    @Override
    public void onGetCommodityOrderDetialSuccess(CommodityOrderDetailBean bean) {

        order_status = bean.getJdata().getOrder().getO_status();

        if (bean.getJdata().getLogistics() != null&&bean.getJdata().getLogistics().size()>0) {
            commodityOrderDetailCommodityAdapter = new CommodityOrderDetailCommodityAdapter(bean.getJdata().getCommodity());
            rvCommodityList.setAdapter(commodityOrderDetailCommodityAdapter);
            rvCommodityList.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.material_grey_200)));

            rvLogistics.setNewData(bean.getJdata().getLogistics());
        }else {
            flLogistics.setVisibility(View.GONE);
        }

        tvOrderNumber.setText(bean.getJdata().getOrder().getOrder_id());
        tvOrderStatus.setText("待收货");
        tvOrderTime.setText(bean.getJdata().getOrder().getCreate_time());

        tvReceiver.setText(bean.getJdata().getAddress().getUsername());
        tvPhoneNumber.setText(bean.getJdata().getAddress().getTelephone());
        tvRecieveAddress.setText(bean.getJdata().getAddress().getAddress());
        double total = Double.parseDouble(bean.getJdata().getOrder().getTotal() != null && !bean.getJdata().getOrder().getTotal().equals("") ? bean.getJdata().getOrder().getTotal() : "0");
        double twoDecimal = TwoDecimal.getTwoDecimal(total);
        tvTotalprice.setText("¥"+twoDecimal);
        int fee = bean.getJdata().getOrder().getFee();
                tvMoneyForSend.setText("¥" + fee);
        tvMoneyToPay.setText("¥"+(twoDecimal+fee));
//        tvSendPrice.setText("￥"+bean.getJdata().getOrder().getFee());
//        tvNeedToPay.setText("￥"+(bean.getJdata().getOrder().getTotal()+bean.getJdata().getOrder().getFee()));

    }

    @Override
    public void onGetCommodityOrderDetailFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onEnsureRecieveSuccess(ResultBean resultBean) {
        if (resultBean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext, resultBean.getJdata().getJmsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BaseApplication.mContext, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onEnsureRecieveFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }

}
