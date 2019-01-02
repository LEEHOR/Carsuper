package com.carsuper.coahr.mvp.view.maintenance;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.maintenance.OrderFragmentSelectCouponContract;
import com.carsuper.coahr.mvp.model.bean.Coupon_Select;
import com.carsuper.coahr.mvp.presenter.maintenance.OrderFragmentSelectCouponPresenter;
import com.carsuper.coahr.mvp.view.adapter.MyCouponStandbyAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Leehor
 * on 2018/10/16
 * on 9:23
 */
public class OrderFragmentSelectCouponFragment extends BaseFragment<OrderFragmentSelectCouponContract.Presenter> implements OrderFragmentSelectCouponContract.View {
    @Inject
    OrderFragmentSelectCouponPresenter presenter;
    @BindView(R.id.title)
    NormalTittleBar tittleBar;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.rv_recyclerView)
    RecyclerView rv_recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private  MyCouponStandbyAdapter standbyAdapter;
    @Override
    public void getSelectCouponListSuccess(Coupon_Select coupon_select) {

    }

    @Override
    public void getSelectCouponListFailure(String failure) {

    }

    @Override
    public OrderFragmentSelectCouponContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_coupon_select;
    }

    @Override
    public void initView() {
        initPtrFrameLayout(ptrFrameLayout);
        tittleBar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
         standbyAdapter =new MyCouponStandbyAdapter();
        rv_recyclerView.setLayoutManager(linearLayoutManager);
        rv_recyclerView.setAdapter(standbyAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void RefreshBegin() {

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
}
