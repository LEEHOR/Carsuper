package com.carsuper.coahr.mvp.view.shoppingMall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.shoppingMall.ConfirmOrderContract;
import com.carsuper.coahr.mvp.model.bean.ConfirmCommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.MessageEvent_shoppingOrder;
import com.carsuper.coahr.mvp.presenter.shoppingMall.ConfirmCommodityOrderPresenter;
import com.carsuper.coahr.mvp.view.adapter.commodityDetail.ConfirmCommodityOrderAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.MyAddressFragment;
import com.carsuper.coahr.mvp.view.myData.MyCoupon.CouponViewPagerFragment;
import com.carsuper.coahr.mvp.view.myData.ThankForEvaluateFragment;
import com.carsuper.coahr.mvp.view.myData.ToEvaluateFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.TwoDecimal;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 订单确认页面
 */
public class ConfirmCommodityOrderFragment extends BaseFragment<ConfirmOrderContract.Presenter> implements ConfirmOrderContract.View {

    @Inject
    ConfirmCommodityOrderPresenter confirmOrderPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_receiver_name)
    TextView tvReceiverName;
    @BindView(R.id.tv_receiver_phone)
    TextView tvReceiverPhone;
    @BindView(R.id.tv_receiver_address)
    TextView tvReceiverAddress;
    @BindView(R.id.iv_select_receiver_info)
    ImageView ivSelectReceiverInfo;
    @BindView(R.id.rv_order_list)
    RecyclerView rvOrderList;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.tv_submit_order)
    TextView tvSubmitOrder;
    /**
     * 优惠券
     *
     */
    @BindView(R.id.rl_coupon)
    RelativeLayout rl_coupon;
    @BindView(R.id.tv_coupon_name)
    TextView tv_coupon_name;
    @BindView(R.id.tv_coupon_money)
    TextView tv_coupon_money;


    private ConfirmCommodityOrderAdapter confirmCommodityOrderAdapter;
    private LinearLayoutManager linearLayoutManager;

    private String ua_id;//地址id

    private String fee;//物流费

    private float totalPrice;//总价

    private String orderConfrim;//商品订单中，商品id与购买数量信息拼接的 字符串，用于订单提交接口

    private String order_string;//支付宝支付请求字符串

    private String mPayType;

    private  String paypage;  //判断是从购物车还是商城过来的

    private String coupon_id="0";

    private double coupon_discount;

    private double Last_price;

    private double Last_old_price; //记录总价用于选择优惠券

    public static ConfirmCommodityOrderFragment newInstance(String orderConfrim, @Nullable String ua_id,String payPage) {
        ConfirmCommodityOrderFragment fragment = new ConfirmCommodityOrderFragment();
        Bundle arg = new Bundle();
        arg.putString("orderConfirm", orderConfrim);
        arg.putString("ua_id", ua_id);
        arg.putString("paypage",payPage);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public ConfirmOrderContract.Presenter getPresenter() {
        return confirmOrderPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_confirm_commodity_order;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        tvSubmitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (tvReceiverPhone.getText() != null && !tvReceiverPhone.getText().equals("")) {
                    //弹出支付方式选择
                    PayTypeSelectDialogFragment payTypeSelectDialogFragment = new PayTypeSelectDialogFragment();
                    payTypeSelectDialogFragment.setOnpayTypeSelectListener(new PayTypeSelectDialogFragment.OnPayTypeSelectListener() {
                        @Override
                        public void onItemSlect(String payType) {
                            mPayType = payType;
                            Map map = new HashMap();
                            map.put("token", Constants.token);
                            map.put("commodity", orderConfrim);
                            map.put("ua_id", ua_id);
                            map.put("fee", fee);
                            map.put("total", Last_price);
                            map.put("payment", payType);
                            map.put("coupon_id", coupon_id);
                            getPresenter().saveCommodityOrder(map);
                        }
                    });
                    payTypeSelectDialogFragment.show(getFragmentManager(), TAG);
               } else {
                    Toast.makeText(getActivity(),"请选择配送信息",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivSelectReceiverInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // start(ThankForEvaluateFragment.newInstance("","",""));
               // start(ToEvaluateFragment.newInstance("", ToEvaluateFragment.TYPE_COMMODITY));
               // start(PaymentSuccessFragment.newInstance("1","ew"));
                startForResult(MyAddressFragment.newInstance(Constants.CONFIRMCOMMODITYORDERFRAGMENT),2);
            }
        });

        rl_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForResult(CouponViewPagerFragment.newInstance(Constants.CONFIRMCOMMODITYORDERFRAGMENT,null,Last_old_price),1);
            }
        });
    }



    @Override
    public void initData() {
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        confirmCommodityOrderAdapter = new ConfirmCommodityOrderAdapter();
        rvOrderList.setLayoutManager(linearLayoutManager);
        rvOrderList.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,2),getResources().getColor(R.color.material_grey_200)));
        rvOrderList.setAdapter(confirmCommodityOrderAdapter);
        orderConfrim = getArguments().getString("orderConfirm");
         paypage = getArguments().getString("paypage");
        ua_id = getArguments().getString("ua_id");
        KLog.e(getArguments().get("orderConfirm"));
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("commodity", orderConfrim);
        map.put("ua_id", ua_id);
        getPresenter().confirmCommodityOrder(map);

    }



    @Override
    public void onConfirmOrderSuccess(ConfirmCommodityOrderBean bean) {
        confirmCommodityOrderAdapter.setNewData(bean.getJdata().getCommodity());

        tvReceiverName.setText(bean.getJdata().getUserinfo().getUsername());
        tvReceiverPhone.setText(bean.getJdata().getUserinfo().getTelephone());
        tvReceiverAddress.setText(bean.getJdata().getUserinfo().getAddress());

        ua_id = bean.getJdata().getUserinfo().getUa_id();
        fee = bean.getJdata().getFee() + "";
        totalPrice = bean.getJdata().getTotal();
        Last_price=TwoDecimal.getTwoDecimal(totalPrice);
        Last_old_price=Last_price;
        tvTotalprice.setText("¥" +Last_price);
    }

    @Override
    public void onConfirmOrderFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSaveCommodityOrderSuccess(ConfirmOrderBean bean) {

        //最后支付的订单
        Constants.LAST_PAYING_ORDERID = bean.getJdata().getOrder();
        if (mPayType.equals("ali")) {
            order_string = bean.getJdata().getOrder_string();
            //最后支付的页面
            if (paypage.equals("购物车")){
                Constants.LAST_PAYING_PAGER="购物车";
            } else if (paypage.equals("商城")){
                Constants.LAST_PAYING_PAGER="商城";
            } else {
                Constants.LAST_PAYING_PAGER="我的商品订单";
            }
            toAliPay(order_string);
        } else if (mPayType.equals("wx")) {
            if (bean.getJdata().getOrder_json().getReturn_code().equals("SUCCESS")) {
                if (paypage.equals("购物车")){
                    Constants.LAST_PAYING_PAGER="购物车";
                } else if (paypage.equals("商城")){
                    Constants.LAST_PAYING_PAGER="商城";
                } else {
                    Constants.LAST_PAYING_PAGER="我的商品订单";
                }
                toWXPay(bean.getJdata().getOrder_json());
            }else {
                Toast.makeText(BaseApplication.mContext,bean.getJdata().getOrder_json().getReturn_msg(),Toast.LENGTH_SHORT).show();
            }
        }

    }

//    @Override
//    public void onAliPaySuccess() {
//        super.onAliPaySuccess();
//        Intent intent = new Intent(_mActivity, WXPayEntryActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void onSaveCommodityOrderFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (resultCode==2&&requestCode==2) {
            if (data != null) {
                ua_id = data.getString("ua_id");
                tvReceiverName.setText(data.getString("username"));
                tvReceiverPhone.setText(data.getString("telephone"));
                tvReceiverAddress.setText(data.getString("address"));
            }
        }
        //被Eventbus代替
      /*  if (requestCode ==1 && resultCode==1){
           *//* bundle.putString("id", coupon_used.getJdata().getCoupon().getId());
            bundle.putString("coupon_name", coupon_used.getJdata().getCoupon().getName());
            bundle.putString("discount", coupon_used.getJdata().getCoupon().getDiscount());*//*
            tv_coupon_name.setText(data.getString("coupon_name"));
            coupon_discount = TwoDecimal.getTwoDecimal(Double.parseDouble(data.getString("discount")));
            tv_coupon_money.setText("-¥"+String.valueOf(coupon_discount));
            coupon_id=data.getString("coupon_id");
            Last_price=totalPrice-coupon_discount;
            double twoDecimal = TwoDecimal.getTwoDecimal(Last_price);
            tvTotalprice.setText("¥"+twoDecimal);
        }*/
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent_shoppingOrder messageEvent) {
        tv_coupon_name.setText(messageEvent.getCoupon_name());
        coupon_discount = TwoDecimal.getTwoDecimal(Double.parseDouble(messageEvent.getDiscount()));
        tv_coupon_money.setText("-¥"+String.valueOf(coupon_discount));
        coupon_id=messageEvent.getCoupon_id();
        Last_price=totalPrice-coupon_discount;
        double twoDecimal = TwoDecimal.getTwoDecimal(Last_price);
        tvTotalprice.setText("¥"+twoDecimal);
    }
}
