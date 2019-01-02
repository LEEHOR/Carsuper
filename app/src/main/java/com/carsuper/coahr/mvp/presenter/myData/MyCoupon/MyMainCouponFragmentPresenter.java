package com.carsuper.coahr.mvp.presenter.myData.MyCoupon;

import com.carsuper.coahr.mvp.contract.myData.MyCoupon.MyCouponMainFragmentContract;
import com.carsuper.coahr.mvp.model.bean.CouponBean;
import com.carsuper.coahr.mvp.model.bean.Coupon_Select;
import com.carsuper.coahr.mvp.model.bean.Coupon_Used;
import com.carsuper.coahr.mvp.model.myData.MyCoupon.MyCouponMainFragmentModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.MyCoupon.MyCouponMainFragment;

import java.util.Map;

import javax.inject.Inject;

public class MyMainCouponFragmentPresenter extends BasePresenter<MyCouponMainFragmentContract.View,MyCouponMainFragmentContract.Model> implements MyCouponMainFragmentContract.Presenter {

   @Inject
    public MyMainCouponFragmentPresenter(MyCouponMainFragment mview, MyCouponMainFragmentModel mModel) {
        super(mview, mModel);
    }


    @Override
    public void getCouponOrderList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCouponOrderList(map);
        }
    }

    @Override
    public void onGetCouponOrderListSuccess(CouponBean couponBean) {
        if (getView() != null) {
            getView().onGetCouponOrderListSuccess(couponBean);
        }
    }

    @Override
    public void onGetCouponOrderListFailure(String failure) {
        if (getView() != null) {
            getView().onGetCouponOrderListFailure(failure);
        }
    }

    @Override
    public void onUsedCoupon(Map<String, String> map) {
        if (mModle != null) {
            mModle.onUsedCoupon(map);
        }
    }

    @Override
    public void onUsedCouponSuccess(Coupon_Used coupon_used) {
        if (getView() != null) {
            getView().onUsedCouponSuccess(coupon_used);
        }
    }

    @Override
    public void onUsedCouponFailure(String failure) {
        if (getView() != null) {
            getView().onUsedCouponFailure(failure);
        }
    }

    @Override
    public void LoadMore(Map<String, String> map) {
        if (mModle != null) {
            mModle.LoadMore(map);
        }
    }

    @Override
    public void LoadMoreSuccess(CouponBean couponBean) {
        if (getView() != null) {
            getView().LoadMoreSuccess(couponBean);
        }
    }

    @Override
    public void LoadMoreFailure(String failure) {
        if (getView() != null) {
            getView().LoadMoreFailure(failure);
        }
    }
}
