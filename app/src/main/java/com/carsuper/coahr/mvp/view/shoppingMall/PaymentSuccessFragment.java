package com.carsuper.coahr.mvp.view.shoppingMall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.shoppingMall.PaymentSuccessContract;
import com.carsuper.coahr.mvp.model.bean.PaymentSuccessBean;
import com.carsuper.coahr.mvp.model.bean.RecommendEntity;
import com.carsuper.coahr.mvp.presenter.shoppingMall.PaymentSuccessPresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.MainActivity;
import com.carsuper.coahr.mvp.view.adapter.shoppingCart.GuessYouLoveAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.MyCommodityOrderFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.NeedToPayFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.NeedToSendFragment;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MNeedToServeFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 支付成功页面
 */
public class PaymentSuccessFragment extends BaseFragment<PaymentSuccessContract.Presenter> implements PaymentSuccessContract.View {

    @Inject
    PaymentSuccessPresenter paymentSuccessPresenter;

    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_see_detial)
    TextView tvSeeDetial;
    @BindView(R.id.tv_backto_shoppingmall)
    TextView tvBacktoShoppingmall;
    @BindView(R.id.rv_other_commodity)
    RecyclerView rvOtherCommodity;

    private String order_id;

    private String paypage; //支付的页面

    private GuessYouLoveAdapter guessYouLoveAdapter;
    private GridLayoutManager gridLayoutManager;



    public static PaymentSuccessFragment newInstance(String order_id,String payPage){
        PaymentSuccessFragment fragment = new PaymentSuccessFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id",order_id);
        arg.putString("paypage",payPage);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public PaymentSuccessContract.Presenter getPresenter() {
        return paymentSuccessPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_paymentsuccess;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
                pop();
               // popTo(ShoppingMallFragment.class,);
            }
        });
        tvBacktoShoppingmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.d("点击返回商城"+paypage);
                if (paypage.equals("购物车")||paypage.equals("商城")||paypage.equals("我的商品订单")){
                    Intent intent = new Intent(_mActivity, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("showPosition",1);
                    intent.putExtra("type","1");
                    startActivity(intent);
                }

                if (paypage.equals("预约保养")||paypage.equals("我的上门保养")){
                    Intent intent = new Intent(_mActivity, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("showPosition",1);
                    intent.putExtra("type","2");
                    startActivity(intent);
                }

            }
        });
        tvSeeDetial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.d("点击详情"+paypage);
                if (paypage.equals("购物车")||paypage.equals("商城")){
                    pop();
                    startMyCommodityOrder();
                } else if (paypage.equals("我的商品订单")){
                   // popTo(PaymentSuccessFragment.class,true);
                    start(NeedToSendFragment.newInstance(order_id));
                   // pop();
                } else if (paypage.equals("预约保养")){
                    pop();
                    startMyMainTance();
                } else if (paypage.equals("我的上门保养")){
                    //popTo(PaymentSuccessFragment.class,true);
                    start(MNeedToServeFragment.newInstance(order_id));
                   // pop();
                }
            }
        });
    }

    /**
     * 商品详情
     *
     */
    private void startMyCommodityOrder() {
        /**
         * 跳转到我的
         */
        Intent intent = new Intent(_mActivity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("showPosition",4);
        intent.putExtra("detailType","1");
        intent.putExtra("order_id",order_id);
        startActivity(intent);

    }

    private void startMyMainTance() {
        /**
         * 跳转到我的
         */
        Intent intent = new Intent(_mActivity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("showPosition",4);
        intent.putExtra("detailType","2");
        intent.putExtra("order_id",order_id);
        startActivity(intent);

    }

    @Override
    public void initData() {
        order_id = getArguments().getString("order_id");
         paypage = getArguments().getString("paypage");
         if (paypage.equals("购物车")||paypage.equals("商城")||paypage.equals("我的商品订单")){
             tvBacktoShoppingmall.setText("返回商城");
        } else if(paypage.equals("预约保养")||paypage.equals("我的预约保养")){
             tvBacktoShoppingmall.setText("预约保养");
         }
        guessYouLoveAdapter = new GuessYouLoveAdapter();
        gridLayoutManager = new GridLayoutManager(BaseApplication.mContext, 2);
        rvOtherCommodity.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext, 4), DensityUtils.dp2px(BaseApplication.mContext, 4), getResources().getColor(R.color.material_grey_300)));
        rvOtherCommodity.setLayoutManager(gridLayoutManager);
        rvOtherCommodity.setAdapter(guessYouLoveAdapter);
        guessYouLoveAdapter.setItemClickListener(new GuessYouLoveAdapter.OnGuessYouLoveItemClickListener() {
            @Override
            public void onItemClick(RecommendEntity entity) {
                start(CommodityDetailFragment.newInstance(entity.getC_id()));
            }
        });

        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id",order_id);
        getPresenter().getEndingRecommend(map);
    }


    @Override
    public void onGetRecommendSuccess(PaymentSuccessBean bean) {
        guessYouLoveAdapter.setNewData(bean.getJdata().getCommodity());
    }

    @Override
    public void onGetRecommendFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }
}
