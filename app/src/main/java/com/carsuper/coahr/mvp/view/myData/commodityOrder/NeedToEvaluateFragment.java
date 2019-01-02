package com.carsuper.coahr.mvp.view.myData.commodityOrder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.commodityOrder.NeedToEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.presenter.myData.commodityOrder.NeedToEvaluatePresenter;
import com.carsuper.coahr.mvp.view.adapter.myCommodityOrder.CommodityOrderDetailCommodityAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.ToEvaluateFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.TwoDecimal;
import com.carsuper.coahr.widgets.logisticsRecyclerView.LogisticsRecyclerView;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/9.
 * Email：hengzwdhengzwd@qq.com
 * 我的商品订单待评价
 */
public class NeedToEvaluateFragment extends BaseFragment<NeedToEvaluateContract.Presenter> implements NeedToEvaluateContract.View {


    @Inject
    NeedToEvaluatePresenter needToEvaluatePresenter;
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
    @BindView(R.id.text0)
    TextView text0;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.tv_send_price)
    TextView tvSendPrice;
    @BindView(R.id.tv_need_to_pay)
    TextView tvNeedToPay;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.tv_goto_evaluate)
    TextView tvGotoEvaluate;
    @BindView(R.id.fl_logistics)
    FrameLayout flLogistics;
    @BindView(R.id.text3)
    TextView text3;


    private LinearLayoutManager linearLayoutManager;
    private CommodityOrderDetailCommodityAdapter commodityOrderDetailCommodityAdapter;
    private String order_Id;
    private int evaluateType=4;

    public static NeedToEvaluateFragment newInstance(String order_Id,int evaluateType) {
        NeedToEvaluateFragment fragment = new NeedToEvaluateFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_Id);
        arg.putInt("evaluateType",evaluateType);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public NeedToEvaluateContract.Presenter getPresenter() {
        return needToEvaluatePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_order_need_to_evaluate;
    }

    @Override
    public void initView() {

        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        tvCopyOrderNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) _mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("text", tvOrderNumber.getText()));
                //cm.setText(tvOrderNumber.getText());
                Toast.makeText(BaseApplication.mContext, "已复制到剪切板", Toast.LENGTH_SHORT).show();
            }
        });

        tvGotoEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start(ToEvaluateFragment.newInstance(order_Id, ToEvaluateFragment.TYPE_COMMODITY));
            }
        });

    }

    @Override
    public void initData() {
        order_Id = getArguments().getString("order_id");
        evaluateType = getArguments().getInt("evaluateType");
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        rvCommodityList.setLayoutManager(linearLayoutManager);
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id", order_Id);
        getPresenter().getCommodityOrderDetail(map);
        if (evaluateType==4){  //待评价
            tvGotoEvaluate.setVisibility(View.VISIBLE);
            text3.setText("等待您的评价");
        } else  {  //已评价
            tbTittle.getTvTittle().setText("已评价");
            tvGotoEvaluate.setVisibility(View.GONE);
            text3.setText("已评价");
        }
    }


    @Override
    public void onGetCommodityOrderDetialSuccess(CommodityOrderDetailBean bean) {
        if (bean.getJdata().getLogistics() != null && bean.getJdata().getLogistics().size() > 0) {
            commodityOrderDetailCommodityAdapter = new CommodityOrderDetailCommodityAdapter(bean.getJdata().getCommodity());
            rvCommodityList.setAdapter(commodityOrderDetailCommodityAdapter);
            rvCommodityList.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.material_grey_200)));

            rvLogistics.setNewData(bean.getJdata().getLogistics());
        } else {
            flLogistics.setVisibility(View.GONE);
        }

        tvOrderNumber.setText(bean.getJdata().getOrder().getOrder_id());
        if (evaluateType ==4){
            tvOrderStatus.setText("待评价");
        }
        if (evaluateType==6){
            tvOrderStatus.setText("已评价");
        }

        tvOrderTime.setText(bean.getJdata().getOrder().getCreate_time());

        tvReceiver.setText(bean.getJdata().getAddress().getUsername());
        tvPhoneNumber.setText(bean.getJdata().getAddress().getTelephone());
        tvRecieveAddress.setText(bean.getJdata().getAddress().getAddress());
        double total = Double.parseDouble(bean.getJdata().getOrder().getTotal() != null && !bean.getJdata().getOrder().getTotal().equals("") ? bean.getJdata().getOrder().getTotal() : "0");
        double twoDecimal_a = TwoDecimal.getTwoDecimal(total);
        tvTotalprice.setText("¥" + twoDecimal_a);
        double twoDecimal_b = TwoDecimal.getTwoDecimal(bean.getJdata().getOrder().getFee());
        tvSendPrice.setText("¥" + twoDecimal_b);
        int fee = bean.getJdata().getOrder().getFee();
        double fee_d = (double) fee;
        double topay = fee_d + total;
        double twoDecimal = TwoDecimal.getTwoDecimal(topay);
        tvNeedToPay.setText("¥" + twoDecimal);

    }

    @Override
    public void onGetCommodityOrderDetailFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }

}
