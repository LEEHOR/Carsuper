package com.carsuper.coahr.mvp.contract.myData.MyCoupon;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CouponBean;
import com.carsuper.coahr.mvp.model.bean.Coupon_Select;
import com.carsuper.coahr.mvp.model.bean.Coupon_Used;

import java.util.Map;

public interface MyCouponMainFragmentContract {

    interface View extends BaseContract.View{

        void onGetCouponOrderListSuccess(CouponBean couponBean);

        void onGetCouponOrderListFailure(String failure);

        void onUsedCouponSuccess(Coupon_Used coupon_used);

        void onUsedCouponFailure(String failure);

        void LoadMoreSuccess(CouponBean coupon_used);

        void LoadMoreFailure(String failure);


    }

    interface  Presenter extends BaseContract.Presenter{

        void getCouponOrderList(Map<String,String> map);

        void onGetCouponOrderListSuccess(CouponBean couponBean);

        void onGetCouponOrderListFailure(String failure);

        void onUsedCoupon(Map<String,String> map);

        void onUsedCouponSuccess(Coupon_Used coupon_used);

        void onUsedCouponFailure(String failure);

        void LoadMore(Map<String,String> map);

        void LoadMoreSuccess(CouponBean couponBean);

        void LoadMoreFailure(String failure);


    }

    interface  Model extends BaseContract.Model{

        void getCouponOrderList(Map<String,String> map);

        void onUsedCoupon(Map<String,String> map);

        void LoadMore(Map<String,String> map);
    }

}
