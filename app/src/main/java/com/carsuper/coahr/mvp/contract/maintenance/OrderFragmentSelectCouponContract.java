package com.carsuper.coahr.mvp.contract.maintenance;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.Coupon_Select;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/10/16
 * on 9:14
 */
public interface OrderFragmentSelectCouponContract {
    interface View extends BaseContract.View {
        void getSelectCouponListSuccess(Coupon_Select coupon_select);

        void getSelectCouponListFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getSelectCouponList(Map<String,String > map);

        void getSelectCouponListSuccess(Coupon_Select coupon_select);

        void getSelectCouponListFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void getSelectCouponList(Map<String,String > map);
    }
}
