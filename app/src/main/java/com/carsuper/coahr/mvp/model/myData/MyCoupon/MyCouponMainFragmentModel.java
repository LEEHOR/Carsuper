package com.carsuper.coahr.mvp.model.myData.MyCoupon;

import com.carsuper.coahr.mvp.contract.myData.MyCoupon.MyCouponMainFragmentContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CouponBean;
import com.carsuper.coahr.mvp.model.bean.Coupon_Select;
import com.carsuper.coahr.mvp.model.bean.Coupon_Used;

import java.util.Map;

import javax.inject.Inject;

public class MyCouponMainFragmentModel extends BaseModel<MyCouponMainFragmentContract.Presenter> implements MyCouponMainFragmentContract.Model {

    @Inject
    public MyCouponMainFragmentModel() {
        super();
    }



    @Override
    public void getCouponOrderList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CouponBean>(getApiservice().get_coupon_list(map.get("token"),map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<CouponBean>() {
                    @Override
                    public void _onNext(CouponBean couponBean) {
                        if (getPresenter() != null) {
                            if (couponBean.getCode()!=0) {
                                getPresenter().onGetCouponOrderListFailure(couponBean.getMsg());
                            }else {
                                getPresenter().onGetCouponOrderListSuccess(couponBean);
                            }
                        }
                    }
                }));
    }

    /**
     * 选择优惠券
     * @param map
     */
    @Override
    public void onUsedCoupon(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<Coupon_Used>(getApiservice().get_coupon_selected(map.get("coupon_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<Coupon_Used>() {
                    @Override
                    public void _onNext(Coupon_Used coupon_used) {
                        if (getPresenter() != null) {
                            if (coupon_used.getCode()!=0) {
                                getPresenter().onUsedCouponFailure(coupon_used.getMsg());
                            }else {
                                getPresenter().onUsedCouponSuccess(coupon_used);
                            }
                        }
                    }
                }));
    }


    /**
     * 加载更多
     * @param map
     */
    @Override
    public void LoadMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CouponBean>(getApiservice().get_coupon_list(map.get("token"),map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<CouponBean>() {
                    @Override
                    public void _onNext(CouponBean couponBean) {
                        if (getPresenter() != null) {
                            if (couponBean.getCode()!=0) {
                                getPresenter().onGetCouponOrderListFailure(couponBean.getMsg());
                            }else {
                                getPresenter().onGetCouponOrderListSuccess(couponBean);
                            }
                        }
                    }
                }));
    }
}
