package com.carsuper.coahr.mvp.view.myData.MyCoupon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.contract.myData.MyCoupon.MyCouponMainFragmentContract;
import com.carsuper.coahr.mvp.model.bean.CouponBean;
import com.carsuper.coahr.mvp.model.bean.Coupon_Select;
import com.carsuper.coahr.mvp.model.bean.Coupon_Used;
import com.carsuper.coahr.mvp.model.bean.MessageEvent_coupon;
import com.carsuper.coahr.mvp.model.bean.MessageEvent_shoppingOrder;
import com.carsuper.coahr.mvp.presenter.myData.MyCoupon.MyMainCouponFragmentPresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.MainActivity;
import com.carsuper.coahr.mvp.view.adapter.CouponAdapter_test;
import com.carsuper.coahr.mvp.view.adapter.MyCouponOverdueAdapter;
import com.carsuper.coahr.mvp.view.adapter.MyCouponStandbyAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseChildFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.maintenance.ConfirmMaintanceOrderFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.widgets.MyPtrFrameLayout;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

public class MyCouponMainFragment extends BaseChildFragment<MyCouponMainFragmentContract.Presenter> implements MyCouponMainFragmentContract.View {

    @Inject
    MyMainCouponFragmentPresenter myMainCouponFragmentPresenter;
    private int order_status;
    @BindView(R.id.mycoupon_PTF)
    MyPtrFrameLayout ptrFrameLayout;
    @BindView(R.id.mycoupon_recyclerView)
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FragmentCouponCallBack fragmentCouponCallBack;
    private int currentPage = 0;  //加载的页数
    private int length = 10; //每一页数据数目
    private int from;  //来自哪个页面

    private String distance; //预约订单页面
    private boolean isLoading; //是否加载
    private CouponBean couponBean;
    private CouponAdapter_test couponAdapterTest;
    private int lastVisibleItemPosition;//最近展示的itemposition
    private  double orderPrice;
    private int left=0,right=0;

    public static MyCouponMainFragment newInstance(int order_status, int fromFragment, String distance,double orderPrice) {
        MyCouponMainFragment fragment = new MyCouponMainFragment();
        Bundle arg = new Bundle();
        arg.putInt("order_status", order_status);
        arg.putInt("from", fromFragment);
        arg.putString("distance", distance);
        arg.putDouble("orderPrice",orderPrice);
        fragment.setArguments(arg);
        KLog.d("from", fromFragment);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentCouponCallBack = (FragmentCouponCallBack) context;
    }



    @Override
    public BaseContract.Presenter getPresenter() {
        return myMainCouponFragmentPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_coupon_main;
    }

    @Override
    public void initView() {
        initPtrFrameLayout(ptrFrameLayout);
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
       // standbyAdapter = new MyCouponStandbyAdapter();
       // overdueAdapter = new MyCouponOverdueAdapter();
         couponAdapterTest=new CouponAdapter_test(BaseApplication.mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(couponAdapterTest);
        couponAdapterTest.setListener(new CouponAdapter_test.onItemClickListener() {
            @Override
            public void StandbyCouponListener(CouponBean.JdataBean.CouponEnableBean enableBean) {
                if (from == Constants.ConfirmMaintanceOrderFragment || from == Constants.CONFIRMCOMMODITYORDERFRAGMENT) {
                        Map map = new HashMap();
                        map.put("token", Constants.token);
                        map.put("coupon_id", String.valueOf(enableBean.getId()));
                        myMainCouponFragmentPresenter.onUsedCoupon(map);
                        KLog.d("使用", map.get("coupon_id"));

                } else {
                    Toast.makeText(BaseApplication.mContext, "当前页面无法使用", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void OverdueCouponListener(CouponBean.JdataBean.CouponDisableBean disableBean) {

            }
        });

        /**
         * 暂不支持加载更多
         */
       /* recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == couponAdapterTest.getItemCount()){
                    if (!isLoading) {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Map map = new HashMap();
                                currentPage++;
                                map.put("token", Constants.token);
                                map.put("order_status", order_status);
                                map.put("page", String.valueOf(currentPage));
                                map.put("length", String.valueOf(length));
                               // getPresenter().loadMore(map);
                                isLoading = true;
                            }
                        }, 500);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });*/

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 1), getResources().getColor(R.color.material_grey_300)));
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            order_status = getArguments().getInt("order_status");
            from = getArguments().getInt("from");
            distance = getArguments().getString("distance");
             orderPrice = getArguments().getDouble("orderPrice");
        }
        isLoading=true;
        Map map = new HashMap();
        map.put("page", String.valueOf(currentPage));
        map.put("length", String.valueOf(length));
        map.put("token", Constants.token);
        myMainCouponFragmentPresenter.getCouponOrderList(map);


    }


    @Override
    public void RefreshBegin() {
        currentPage = 0;
        isLoading = true;
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("page", String.valueOf(currentPage));
        map.put("length", String.valueOf(length));
        myMainCouponFragmentPresenter.getCouponOrderList(map);
    }

    @Override
    public boolean isCanDoRefresh() {
        int position;
        if (linearLayoutManager.getChildCount() == 0) {
            return true;
        }
        position = linearLayoutManager.findFirstVisibleItemPosition();
        return position == 0 && linearLayoutManager.findViewByPosition(position).getTop() >= 0;
    }

    @Override
    public void onGetCouponOrderListSuccess(CouponBean couponBean) {
       this.couponBean=couponBean;
        couponAdapterTest.setCouponBean(couponBean);
        isLoading=false;
        List<CouponBean.JdataBean.CouponEnableBean> coupon_enable = couponBean.getJdata().getCoupon_enable();
        List<CouponBean.JdataBean.CouponDisableBean> coupon_disable = couponBean.getJdata().getCoupon_disable();
        if (coupon_disable != null && coupon_disable.size() > 0) {
            right=coupon_disable.size();
        } else {
            right=0;
        }
        if (coupon_enable != null && coupon_enable.size() > 0) {
            left=coupon_enable.size();
        } else {
            left=0;
        }
            EventBus.getDefault().post(new MessageEvent_coupon(left,right));

        if (order_status == 0) {

            couponAdapterTest.setType(order_status);
        }
        if (order_status == 1) {

            couponAdapterTest.setType(order_status);
        }
            couponAdapterTest.notifyDataSetChanged();
    }

    @Override
    public void onGetCouponOrderListFailure(String failure) {
        isLoading=false;
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUsedCouponSuccess(Coupon_Used coupon_used) {
        Bundle bundle = new Bundle();
        bundle.putString("coupon_id", coupon_used.getJdata().getCoupon().getId());
        bundle.putString("coupon_name", coupon_used.getJdata().getCoupon().getName());
        bundle.putString("discount", coupon_used.getJdata().getCoupon().getDiscount());
        bundle.putString("amount",coupon_used.getJdata().getCoupon().getAmount());
        bundle.putString("type",coupon_used.getJdata().getCoupon().getType());
        if (from ==Constants.ConfirmMaintanceOrderFragment) {   //预约订单
            if (coupon_used.getJdata().getCoupon().getCoupon_type().equals("2")) {
                ((ContainerActiivty) _mActivity).jumpToSupportFragment(ConfirmMaintanceOrderFragment.class, bundle);
            } else {
                Toast.makeText(BaseApplication.mContext, "只限于商品购买使用", Toast.LENGTH_LONG).show();
            }
        }

        if (from==Constants.CONFIRMCOMMODITYORDERFRAGMENT){      //商品订单
            if (coupon_used.getJdata().getCoupon().getCoupon_type().equals("1")){
                if (orderPrice>=Double.parseDouble(coupon_used.getJdata().getCoupon().getAmount())){
                    EventBus.getDefault().post(new MessageEvent_shoppingOrder(coupon_used.getJdata().getCoupon().getId(),coupon_used.getJdata().getCoupon().getName()
                            ,coupon_used.getJdata().getCoupon().getDiscount(),coupon_used.getJdata().getCoupon().getAmount()
                    ,coupon_used.getJdata().getCoupon().getType()));
                    _mActivity.onBackPressed();
                } else {
                    Toast.makeText(BaseApplication.mContext, "条件不满足无法使用", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(BaseApplication.mContext, "只限于预约保养使用", Toast.LENGTH_LONG).show();
            }

        }

    }


    @Override
    public void onUsedCouponFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void LoadMoreSuccess(CouponBean couponBean) {
        isLoading=false;


    }

    @Override
    public void LoadMoreFailure(String failure) {
        isLoading=false;
    }

    /**
     * 接口回调
     * 暂不使用
     */
    public interface FragmentCouponCallBack {
        void btn_LeftCallback(int count); //待使用

        void btn_rightCallback(int count);//已过期
        //使用商品订单页
        void used_couponByConfirmCommodity(Bundle bundle);
    }


}
