package com.carsuper.coahr.mvp.contract.myData.MyCoupon;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.GetCoupon;
import com.carsuper.coahr.mvp.model.bean.GetCouponDown;

import java.util.Map;

public interface CouponReceiveFragmentContract {
    interface  View extends BaseContract.View{

        void getCouponListSuccess(GetCoupon getCoupon);

        void getCouponListFailure(String failure);

        void  getReceiveCouponSuccess(GetCouponDown getCouponDown);

        void  getReceiveCouponFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter{

        void getCouponList(Map<String,String> map);

        void getCouponListSuccess(GetCoupon getCoupon);

        void getCouponListFailure(String failure);




        void  getReceiveCoupon(Map<String,String> map);

        void  getReceiveCouponSuccess(GetCouponDown getCouponDown);

        void  getReceiveCouponFailure(String failure);

    }

    interface Model extends BaseContract.Model{

        void getCouponList(Map<String,String> map);

        void  getReceiveCoupon(Map<String,String> map);
    }
}
