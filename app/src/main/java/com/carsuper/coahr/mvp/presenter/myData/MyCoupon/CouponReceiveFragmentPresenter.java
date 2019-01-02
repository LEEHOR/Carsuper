package com.carsuper.coahr.mvp.presenter.myData.MyCoupon;

import com.carsuper.coahr.mvp.contract.myData.MyCoupon.CouponReceiveFragmentContract;
import com.carsuper.coahr.mvp.model.bean.GetCoupon;
import com.carsuper.coahr.mvp.model.bean.GetCouponDown;
import com.carsuper.coahr.mvp.model.myData.MyCoupon.CouponReceiveFragmentModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.MyCoupon.CouponReceiveFragment;

import java.util.Map;

import javax.inject.Inject;

public class CouponReceiveFragmentPresenter extends BasePresenter<CouponReceiveFragmentContract.View,CouponReceiveFragmentContract.Model> implements CouponReceiveFragmentContract.Presenter {

    @Inject
    public CouponReceiveFragmentPresenter(CouponReceiveFragment mview, CouponReceiveFragmentModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getCouponList(Map<String,String> map) {
        if (mModle != null) {
            mModle.getCouponList(map);
        }
    }

    @Override
    public void getCouponListSuccess(GetCoupon getCoupon) {
        if (getView() != null) {
            getView().getCouponListSuccess(getCoupon);
        }
    }

    @Override
    public void getCouponListFailure(String failure) {
        if (getView() != null) {
            getView().getCouponListFailure(failure);
        }
    }

    @Override
    public void getReceiveCoupon(Map<String ,String> map) {
        if (mModle != null) {
            mModle.getReceiveCoupon(map);
        }
    }

    @Override
    public void getReceiveCouponSuccess(GetCouponDown getCouponDown) {
        if (getView() != null) {
            getView().getReceiveCouponSuccess(getCouponDown);
        }
    }

    @Override
    public void getReceiveCouponFailure(String failure) {
        if (getView() != null) {
            getView().getReceiveCouponFailure(failure);
        }
    }
}
