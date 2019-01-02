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
import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToSendContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.presenter.myData.commodityOrder.NeedToSendPresenter;
import com.carsuper.coahr.mvp.view.adapter.myCommodityOrder.CommodityOrderDetailCommodityAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.returnorchange.ReturnChangeSelectFragement;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.TwoDecimal;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 * 我的商品订单待发货
 */
public class NeedToSendFragment extends BaseFragment<NeedToSendContract.Presenter> implements NeedToSendContract.View {


    @Inject
    NeedToSendPresenter needToSendPresenter;
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
    @BindView(R.id.rv_commodity_list)
    RecyclerView rvCommodityList;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.tv_money_for_send)
    TextView tvMoneyForSend;
    @BindView(R.id.tv_money_to_pay)
    TextView tvMoneyToPay;
    @BindView(R.id.tv_return_money)
    TextView tvReturnMoney;

    private LinearLayoutManager linearLayoutManager;
    private CommodityOrderDetailCommodityAdapter commodityOrderDetailCommodityAdapter;
    private String order_Id;
    private String order_status;

    public static NeedToSendFragment newInstance(String order_Id) {
        NeedToSendFragment  fragment = new NeedToSendFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_Id);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public NeedToSendContract.Presenter getPresenter() {
        return needToSendPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_order_need_to_send;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _mActivity.onBackPressed();
            }
        });
        tvReturnMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(ReturnChangeSelectFragement.newInstance(order_Id,order_status));
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
        commodityOrderDetailCommodityAdapter = new CommodityOrderDetailCommodityAdapter(bean.getJdata().getCommodity());
        rvCommodityList.setAdapter(commodityOrderDetailCommodityAdapter);
//        rvLogistics.setNewData(bean.getJdata().getLogistics());
        rvCommodityList.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.material_grey_200)));

        tvOrderNumber.setText(bean.getJdata().getOrder().getOrder_id());
        tvOrderStatus.setText("待发货");
        tvOrderTime.setText(bean.getJdata().getOrder().getCreate_time());

        if (bean.getJdata().getAddress() != null) {
            tvReceiver.setText(bean.getJdata().getAddress().getUsername());
            tvPhoneNumber.setText(bean.getJdata().getAddress().getTelephone());
            tvRecieveAddress.setText(bean.getJdata().getAddress().getAddress());
        }
        double total = Double.parseDouble(bean.getJdata().getOrder().getTotal() != null && !bean.getJdata().getOrder().getTotal().equals("") ? bean.getJdata().getOrder().getTotal() : "0");
        double twoDecimal = TwoDecimal.getTwoDecimal(total);

        int fee = bean.getJdata().getOrder().getFee();
        tvTotalprice.setText("¥"+(twoDecimal));  //合计
        tvMoneyForSend.setText("¥"+fee);
        tvMoneyToPay.setText("¥"+(twoDecimal+fee));


    }

    @Override
    public void onGetCommodityOrderDetailFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }


}
