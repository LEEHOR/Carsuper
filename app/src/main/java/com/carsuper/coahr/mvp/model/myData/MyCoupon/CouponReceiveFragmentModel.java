package com.carsuper.coahr.mvp.model.myData.MyCoupon;

import com.carsuper.coahr.mvp.contract.myData.MyCoupon.CouponReceiveFragmentContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.GetCoupon;
import com.carsuper.coahr.mvp.model.bean.GetCouponDown;

import java.util.Map;

import javax.inject.Inject;

public class CouponReceiveFragmentModel extends BaseModel<CouponReceiveFragmentContract.Presenter> implements CouponReceiveFragmentContract.Model{

    @Inject
    public CouponReceiveFragmentModel() {
        super();
    }

    @Override
    public void getCouponList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<GetCoupon>(getApiservice().get_coupon_all(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<GetCoupon>() {
                    @Override
                    public void _onNext(GetCoupon couponBean) {
                        if (getPresenter() != null) {
                            if (couponBean.getCode()!=0) {
                                getPresenter().getCouponListFailure(couponBean.getMsg());
                            }else {
                                getPresenter().getCouponListSuccess(couponBean);
                            }
                        }
                    }
                }));
    }



    @Override
    public void getReceiveCoupon(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<GetCouponDown>(getApiservice().get_coupon_byself(map.get("token"),map.get("coupon_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<GetCouponDown>() {
                    @Override
                    public void _onNext(GetCouponDown getCouponDown) {
                        if (getPresenter() != null) {
                            if (getCouponDown.getCode()!=0) {
                               getPresenter().getReceiveCouponFailure(getCouponDown.getJdata().getJmsg());
                            }else {
                                getPresenter().getReceiveCouponSuccess(getCouponDown);
                            }
                        }
                    }
                }));
    }
}
