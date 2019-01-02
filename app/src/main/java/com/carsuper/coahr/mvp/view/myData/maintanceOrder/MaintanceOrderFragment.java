package com.carsuper.coahr.mvp.view.myData.maintanceOrder;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.maintanceOrder.MaintanceOrderContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.ServiceOrderCopyBean;
import  com.carsuper.coahr.mvp.presenter.myData.maintanceOrder.MaintanceOrderPresenter;
import com.carsuper.coahr.mvp.view.adapter.myMaintanceOrder.MaintanceOrderAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseChildFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.maintenance.ConfirmMaintanceOrderFragment;
import com.carsuper.coahr.mvp.view.myData.CancleReasonInputDialogFragment;
import com.carsuper.coahr.mvp.view.myData.ToEvaluateFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.PayTypeSelectDialogFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 * 我的上门保养
 */
public class MaintanceOrderFragment extends BaseChildFragment<MaintanceOrderContract.Presenter> implements MaintanceOrderContract.View {
    @Inject
    MaintanceOrderPresenter MaintanceOrderPresenter;
    @BindView(R.id.rv_maintance_order)
    RecyclerView rvMaintanceOrder;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrFrameLayout;


    private LinearLayoutManager linearLayoutManager;
    private MaintanceOrderAdapter adapter;

    private List<MaintanceOrderBean.JdataEntity.OrderEntity> orderListEntities = new ArrayList<>();
    private String order_status;
    private int current_page = 0;
    private int length = 10;
    private int lastVisibleItemPosition;//最近展示的itemposition
    private boolean isLoading = false;

    private String mPayType;

    public static MaintanceOrderFragment newInstance(String order_status) {
        MaintanceOrderFragment fragment = new MaintanceOrderFragment();
        Bundle arg = new Bundle();
        arg.putString("order_status", order_status);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public MaintanceOrderContract.Presenter getPresenter() {
        return MaintanceOrderPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_maintance_order;
    }

    @Override
    public void initView() {
        initPtrFrameLayout(ptrFrameLayout);
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        adapter = new MaintanceOrderAdapter(_mActivity, orderListEntities);
        rvMaintanceOrder.setLayoutManager(linearLayoutManager);
        rvMaintanceOrder.setAdapter(adapter);
        rvMaintanceOrder.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 5), getResources().getColor(R.color.material_grey_200)));
        rvMaintanceOrder.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == adapter.getItemCount() && orderListEntities.size() >= 10) {
                    if (!isLoading) {
                        rvMaintanceOrder.postDelayed(new Runnable() {
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
        adapter.setOnMaintanceOrderHandleListener(new MaintanceOrderAdapter.onMaintanceOrderHandleListener() {
            //去支付
            @Override
            public void payImmediately(final MaintanceOrderBean.JdataEntity.OrderEntity orderEntity) {
                //弹出支付方式选择
                PayTypeSelectDialogFragment payTypeSelectDialogFragment = new PayTypeSelectDialogFragment();
                payTypeSelectDialogFragment.setOnpayTypeSelectListener(new PayTypeSelectDialogFragment.OnPayTypeSelectListener() {
                    @Override
                    public void onItemSlect(String payType) {
                        mPayType = payType;
                        Map map = new HashMap();
                        map.put("token",Constants.token);
                        map.put("order_id",orderEntity.getOrder_id());
                        map.put("order_type","service");
                        map.put("payment",mPayType);
                        //支付的订单编号
                        Constants.LAST_PAYING_ORDERID=orderEntity.getOrder_id();
                        Constants.LAST_PAYING_PAGER="我的上门保养";
                        getPresenter().quickPay(map);
                    }
                });
                payTypeSelectDialogFragment.show(getFragmentManager(), TAG);

            }

            //联系店员
            @Override
            public void contractSomeone(MaintanceOrderBean.JdataEntity.OrderEntity orderEntity) {
                if (orderEntity.getAssignment()==1) {
                    call(orderEntity.getTelephone());
                }else if (orderEntity.getAssignment()==2){
                    call(orderEntity.getS_phone());
                }
            }

            //已完成
            @Override
            public void ensureServiceComplete(MaintanceOrderBean.JdataEntity.OrderEntity orderEntity) {
                        Map map = new HashMap();
                        map.put("token",Constants.token);
                        map.put("order_id",orderEntity.getOrder_id());
                        getPresenter().confirmServiceFinish(map);
            }

            //去评价
            @Override
            public void evaluateImmediately(MaintanceOrderBean.JdataEntity.OrderEntity orderEntity) {
                ((SupportFragment)getParentFragment()).start(ToEvaluateFragment.newInstance(orderEntity.getOrder_id(),ToEvaluateFragment.TYPE_SERVICE));

            }

            //复制订单
            @Override
            public void restoreToOrder(MaintanceOrderBean.JdataEntity.OrderEntity orderEntity) {
                Map map = new HashMap();
                map.put("token",Constants.token);
                map.put("order_id",orderEntity.getOrder_id());
                getPresenter().ServiceOrderCopy(map);
            }

            //取消订单
            @Override
            public void ensureServiceToCancel(final MaintanceOrderBean.JdataEntity.OrderEntity orderEntity) {
                CancleReasonInputDialogFragment dialogFragment = CancleReasonInputDialogFragment.newInstance(CancleReasonInputDialogFragment.TYPE_MAINTANCE_ORDER);
                dialogFragment.setOnInputCallback(new CancleReasonInputDialogFragment.InputCallback() {
                    @Override
                    public void onInputSend(String input) {
                        Map map = new HashMap();
                        map.put("token", Constants.token);
                        map.put("order_id", orderEntity.getOrder_id());
                        map.put("reason", input);
                        getPresenter().onCancelOder(map);
                    }
                });
                dialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);

            }
        });

        adapter.setOnItemClickListener(new MaintanceOrderAdapter.onItemClickListener() {
            @Override
            public void onItemCLick(MaintanceOrderBean.JdataEntity.OrderEntity orderListEntity) {
                if (orderListEntity.getO_status().equals("0")) {
                    ((MyMaintanceOrderFragment)getParentFragment()).start(MNeedToPayFragment.newInstance(orderListEntity.getOrder_id()));
                } else if (orderListEntity.getO_status().equals("1")) {
                    ((MyMaintanceOrderFragment)getParentFragment()).start(MNeedToServeFragment.newInstance(orderListEntity.getOrder_id()));
                } else if (orderListEntity.getO_status().equals("2")) {
                    ((MyMaintanceOrderFragment)getParentFragment()).start(MNeedToEvaluateFragment.newInstance(orderListEntity.getOrder_id()));
                } else if (orderListEntity.getO_status().equals("-1")) {
                    ((MyMaintanceOrderFragment)getParentFragment()).start(MHaveBeenCanceledFragment.newInstance(orderListEntity.getOrder_id()));
                }else if (orderListEntity.getO_status().equals("3")) {
                   // KLog.e("完成",""+orderListEntity.getOrder_id());
                    ((MyMaintanceOrderFragment)getParentFragment()).start(MHaveBeenCompleteFragment.newInstance(orderListEntity.getOrder_id()));
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
        getPresenter().getMaintanceOrderList(map);
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
    public void onGetMaintanceOrderListSuccess(MaintanceOrderBean bean) {
        isLoading = false;
        current_page = 0;
        orderListEntities.clear();
        orderListEntities.addAll(bean.getJdata().getOrder());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetMaintanceOrderListFailure(String failure) {
        isLoading = false;
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadMoreSuccess(MaintanceOrderBean bean) {
        isLoading = false;
        current_page++;
        orderListEntities.addAll(bean.getJdata().getOrder());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreFailure(String failure) {
        isLoading = false;
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onConfirmSuccess(ResultBean bean) {
        if (bean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext,bean.getJdata().getJmsg(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(BaseApplication.mContext,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConfirmFailure(String failure) {
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

    @Override
    public void onCopySuccess(ServiceOrderCopyBean bean) {
        if (bean.getJdata().getOrder() != null && bean.getJdata().getCommodity() != null) {
            ServiceOrderCopyBean.JdataBean.CommodityBeanX.CommodityBean commodity = bean.getJdata().getCommodity().getCommodity();
            ServiceOrderCopyBean.JdataBean.CommodityBeanX.CommodityFilterBean commodity_filter = bean.getJdata().getCommodity().getCommodity_filter();
            if (commodity != null) {
                String filter_id = null;
                String filter_name = null;
                String filter_price = null;
                String filter_sort = null;
                String filter_thumbnail = null;
                String filter_num = null;

                String c_id = commodity.getC_id();
                String c_name = commodity.getC_name();
                String c_price = commodity.getC_price();
                String c_sort = commodity.getC_sort();
                String c_thumbnail = commodity.getC_thumbnail();
                String num = commodity.getNum();
                if (commodity_filter != null) {
                    filter_id = commodity_filter.getC_id();
                    filter_name = commodity_filter.getC_name();
                    filter_price = commodity_filter.getC_price();
                    filter_sort = commodity_filter.getC_sort();
                    filter_thumbnail = commodity_filter.getC_thumbnail();
                    filter_num = commodity_filter.getNum();
                }

                ((SupportFragment) getParentFragment()).start(ConfirmMaintanceOrderFragment.newInstance(c_id,
                        num ==null?Integer.parseInt("0"):Integer.parseInt(num), bean.getJdata().getOrder().getCar_id(),
                        c_thumbnail, c_name,
                        c_price==null?Float.parseFloat("0"):Float.parseFloat(c_price),
                        filter_id, filter_num == null ? Integer.valueOf("0") : Integer.valueOf(filter_num), filter_price == null ? Float.parseFloat("0") : Float.parseFloat(filter_price), filter_name, filter_thumbnail));
            }

        }
        if (bean.getJdata().getCommodity() != null&&bean.getJdata().getOrder()!=null) {

        }
    }

    @Override
    public void onCopyFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancelOrderSuccess(ResultBean resultBean) {
        if (resultBean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext, resultBean.getJdata().getJmsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BaseApplication.mContext, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
        RefreshBegin();
    }

    @Override
    public void onCancelOrderFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();
    }

}
