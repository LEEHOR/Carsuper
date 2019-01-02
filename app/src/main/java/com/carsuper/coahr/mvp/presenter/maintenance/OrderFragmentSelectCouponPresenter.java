package com.carsuper.coahr.mvp.presenter.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.OrderFragmentSelectCouponContract;
import com.carsuper.coahr.mvp.model.bean.Coupon_Select;
import com.carsuper.coahr.mvp.model.maintenance.OrderFragmentSelectCouponModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.maintenance.OrderFragmentSelectCouponFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/10/16
 * on 9:20
 */
public class OrderFragmentSelectCouponPresenter extends BasePresenter<OrderFragmentSelectCouponContract.View,OrderFragmentSelectCouponContract.Model> implements OrderFragmentSelectCouponContract.Presenter {

    @Inject
    public OrderFragmentSelectCouponPresenter(OrderFragmentSelectCouponFragment mview, OrderFragmentSelectCouponModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getSelectCouponList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getSelectCouponList(map);
        }
    }

    @Override
    public void getSelectCouponListSuccess(Coupon_Select coupon_select) {
        if (getView() != null) {
            getView().getSelectCouponListSuccess(coupon_select);
        }
    }

    @Override
    public void getSelectCouponListFailure(String failure) {
        if (getView() != null) {
            getView().getSelectCouponListFailure(failure);
        }
    }
}
