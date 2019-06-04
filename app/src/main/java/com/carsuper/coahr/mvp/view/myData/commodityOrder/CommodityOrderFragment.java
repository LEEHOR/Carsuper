package com.carsuper.coahr.mvp.view.myData.commodityOrder;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.commodityOrder.CommodityOrderContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.myData.commodityOrder.CommodityOrderPresenter;
import com.carsuper.coahr.mvp.view.adapter.myCommodityOrder.CommodityOrderAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseChildFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.ToEvaluateFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.ConfirmCommodityOrderFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.PayTypeSelectDialogFragment;
import com.carsuper.coahr.utils.DensityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 我的商品订单
 */
public class CommodityOrderFragment extends BaseChildFragment<CommodityOrderContract.Presenter> implements CommodityOrderContract.View {
    @Inject
    CommodityOrderPresenter commodityOrderPresenter;
    @BindView(R.id.rv_commodity_order)
    RecyclerView rvCommodityOrder;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrFrameLayout;


    private LinearLayoutManager linearLayoutManager;
    private CommodityOrderAdapter adapter;

    private List<CommodityOrderBean.JdataEntity.OrderListEntity> orderListEntities = new ArrayList<>();
    private String order_status;
    private int current_page = 0;
    private int length = 10;
    private int lastVisibleItemPosition;//最近展示的itemposition
    private boolean isLoading = false;

    private String mPayType;


    public static CommodityOrderFragment newInstance(String order_status) {
        CommodityOrderFragment fragment = new CommodityOrderFragment();
        Bundle arg = new Bundle();
        arg.putString("order_status", order_status);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public CommodityOrderContract.Presenter getPresenter() {
        return commodityOrderPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_commodity_order;
    }

    @Override
    public void initView() {
        initPtrFrameLayout(ptrFrameLayout);
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        adapter = new CommodityOrderAdapter(_mActivity, orderListEntities);
        rvCommodityOrder.setLayoutManager(linearLayoutManager);
        rvCommodityOrder.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext, 5),getResources().getColor(R.color.material_grey_200)));
        rvCommodityOrder.setAdapter(adapter);
        rvCommodityOrder.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == adapter.getItemCount() && orderListEntities.size() >= 10) {
                    if (!isLoading) {

                        rvCommodityOrder.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Map map = new HashMap();
                                map.put("token", Constants.token);
                                map.put("order_status", order_status);
                                map.put("page", current_page + 1 + "");
                                map.put("length", length + "");
                                getPresenter().loadMore(map);
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
        });
    }

    @Override
    public void initData() {
        order_status = getArguments().getString("order_status");
        adapter.setOnCommodityOrderHandleListener(new CommodityOrderAdapter.onCommodityOrderHandleListener() {
            @Override
            public void payImmediately(final CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {
                //弹出支付方式选择
                PayTypeSelectDialogFragment payTypeSelectDialogFragment = new PayTypeSelectDialogFragment();
                payTypeSelectDialogFragment.setOnpayTypeSelectListener(new PayTypeSelectDialogFragment.OnPayTypeSelectListener() {
                    @Override
                    public void onItemSlect(String payType) {
                        mPayType = payType;
                        Map map = new HashMap();
                        map.put("token",Constants.token);
                        map.put("order_id",orderListEntity.getOrder_id());
                        map.put("order_type","commodity");
                        map.put("payment",mPayType);
                        Constants.LAST_PAYING_ORDERID=orderListEntity.getOrder_id();
                        Constants.LAST_PAYING_PAGER="我的商品订单";
                        getPresenter().quickPay(map);
                    }
                });
                payTypeSelectDialogFragment.show(getFragmentManager(), TAG);
            }

            @Override
            public void urgeMyOrder(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {
                    Map map = new HashMap();
                    map.put("token",Constants.token);
                    map.put("order_id",orderListEntity.getOrder_id());
                    getPresenter().reminderOrder(map);
            }

            @Override
            public void seeLogistics(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {
                ((SupportFragment)getParentFragment()).start(LogisticsFragment.newInstance(orderListEntity.getOrder_id()));
            }

            @Override
            public void ensureToRecieve(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {
                Map map = new HashMap();
                map.put("token",Constants.token);
                map.put("order_id",orderListEntity.getOrder_id());
                getPresenter().ensureRecieve(map);
            }

            //待评价
            @Override
            public void evaluateImmediately(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {
                ((SupportFragment)getParentFragment()).start(ToEvaluateFragment.newInstance(orderListEntity.getOrder_id(),ToEvaluateFragment.TYPE_COMMODITY));
            }

            @Override
            public void restoreToBuy(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {
                String orderConfirm = "";
                for (int i = 0; i < orderListEntity.getCommodity().size(); i++) {
                    if (i == 0) {
                        orderConfirm = String.format("cid=%s&num=%s", orderListEntity.getCommodity().get(i).getC_id(), orderListEntity.getCommodity().get(i).getC_num());
                    } else {
                        orderConfirm += String.format(",cid=%s&num=%s", orderListEntity.getCommodity().get(i).getC_id(), orderListEntity.getCommodity().get(i).getC_num());
                    }
                }
                ((SupportFragment)getParentFragment()).start(ConfirmCommodityOrderFragment.newInstance(orderConfirm,"","我的商品订单"));//传地址ua_id为 空字符串，后台认为使用默认地址
            }
            //已评价
            @Override
            public void haveEvaluate(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {
                ((MyCommodityOrderFragment)getParentFragment()).start(NeedToEvaluateFragment.newInstance(orderListEntity.getOrder_id(),6));
            }
        });

        adapter.setOnItemClickListener(new CommodityOrderAdapter.onItemClickListener() {
            @Override
            public void onItemCLick(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {
                if (orderListEntity.getO_status().equals("0")) {
                    ((MyCommodityOrderFragment)getParentFragment()).start(NeedToPayFragment.newInstance(orderListEntity.getOrder_id()));
                } else if (orderListEntity.getO_status().equals("1")) {
                    ((MyCommodityOrderFragment)getParentFragment()).start(NeedToSendFragment.newInstance(orderListEntity.getOrder_id()));
                } else if (orderListEntity.getO_status().equals("2")) {
                    ((MyCommodityOrderFragment)getParentFragment()).start(NeedToRecieveFragment.newInstance(orderListEntity.getOrder_id()));
                } else if (orderListEntity.getO_status().equals("4")) { //待评价
                    ((MyCommodityOrderFragment)getParentFragment()).start(NeedToEvaluateFragment.newInstance(orderListEntity.getOrder_id(),4));
                } else if (orderListEntity.getO_status().equals("5")) {
                    ((MyCommodityOrderFragment)getParentFragment()).start(ReturnOrChangeFragment.newInstance(orderListEntity.getOrder_id()));
                } else if (orderListEntity.getO_status().equals("6")){ //已评价
                    ((MyCommodityOrderFragment)getParentFragment()).start(NeedToEvaluateFragment.newInstance(orderListEntity.getOrder_id(),6));
                }
            }
        });
    }



    @Override
    public void RefreshBegin() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_status", order_status);
        map.put("page", current_page + "");
        map.put("length", length + "");
        getPresenter().getCommodityOrderList(map);
        isLoading = true;
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
    public void onGetCommodityOrderListSuccess(CommodityOrderBean bean) {
        isLoading = false;
        current_page = 0;
        orderListEntities.clear();
        orderListEntities.addAll(bean.getJdata().getOrder_list());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetCommodityOrderListFailure(String failure) {
        isLoading = false;
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadMoreSuccess(CommodityOrderBean bean) {
        isLoading = false;
        current_page++;
        orderListEntities.addAll(bean.getJdata().getOrder_list());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreFailure(String failure) {
        isLoading = false;
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onReminderSuccess(ResultBean resultBean) {
        Toast.makeText(BaseApplication.mContext,resultBean.getJdata().getJmsg(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onReminderFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();
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
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

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
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }
}
