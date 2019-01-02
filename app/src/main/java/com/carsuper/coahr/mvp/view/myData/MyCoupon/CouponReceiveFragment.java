package com.carsuper.coahr.mvp.view.myData.MyCoupon;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.MyCoupon.CouponReceiveFragmentContract;
import com.carsuper.coahr.mvp.model.bean.GetCoupon;
import com.carsuper.coahr.mvp.model.bean.GetCouponDown;
import com.carsuper.coahr.mvp.presenter.myData.MyCoupon.CouponReceiveFragmentPresenter;
import com.carsuper.coahr.mvp.view.adapter.MyCouponRecevieAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 优惠券领取页面
 */
public class CouponReceiveFragment extends BaseFragment<CouponReceiveFragmentContract.Presenter> implements CouponReceiveFragmentContract.View {

    @Inject
    CouponReceiveFragmentPresenter couponReceiveFragmentPresenter;

    @BindView(R.id.coupon_Receive_title)
    NormalTittleBar tittleBar;
    @BindView(R.id.coupon_Receive_recycler)
    RecyclerView coupon_Receive_recycler;
    @BindView(R.id.PtrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.go_receive_coupon)
    Button go_receive_coupon;

    private  LinearLayoutManager linearLayoutManager;
    private MyCouponRecevieAdapter adapter;
    private  String toform;

    public static CouponReceiveFragment newInstance(String toform) {
        CouponReceiveFragment fragment = new CouponReceiveFragment();
        Bundle arg = new Bundle();
        arg.putString("toform",toform);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public void getCouponListSuccess(GetCoupon getCoupon) {
        adapter.setNewData(getCoupon.getJdata().getCoupon());
    }

    @Override
    public void getCouponListFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,"加载失败",Toast.LENGTH_LONG).show();
    }

    @Override
    public void getReceiveCouponSuccess(GetCouponDown getCouponDown) {

                Toast.makeText(BaseApplication.mContext,"领取成功",Toast.LENGTH_LONG).show();

                RefreshBegin();

    }

    @Override
    public void getReceiveCouponFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_LONG).show();
    }

    @Override
    public CouponReceiveFragmentContract.Presenter getPresenter() {
        return couponReceiveFragmentPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_coupon_receive;
    }

    @Override
    public void initView() {
        adapter=new MyCouponRecevieAdapter();
        linearLayoutManager=new LinearLayoutManager(BaseApplication.mContext);
        coupon_Receive_recycler.setLayoutManager(linearLayoutManager);
        coupon_Receive_recycler.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext, 1),getResources().getColor(R.color.material_grey_300)));
        coupon_Receive_recycler.setAdapter(adapter);
        initPtrFrameLayout(ptrFrameLayout);

        tittleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });

        go_receive_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        adapter.setReceiverListener(new MyCouponRecevieAdapter.ReceiverListener() {
            @Override
            public void pull_down(GetCoupon.JdataBean.CouponBean item) {
                Map map=new HashMap();
                map.put("token",Constants.token);
                map.put("coupon_id",item.getId());
                getPresenter().getReceiveCoupon(map);
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
             toform = getArguments().getString("toform");
        }
        if (toform.equals("首页")){
            go_receive_coupon.setVisibility(View.GONE);
        } else {
            go_receive_coupon.setVisibility(View.VISIBLE);
        }
        getCouponList();

    }

    @Override
    public void RefreshBegin() {
        Map map=new HashMap();
        map.put("token",Constants.token);
        getPresenter().getCouponList(map);
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
    private void getCouponList(){
        Map map=new HashMap();
        map.put("token",Constants.token);
        getPresenter().getCouponList(map);
    }
}
