package com.carsuper.coahr.mvp.view.adapter;

import android.view.View;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CouponBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Date：2018/10/11
 * Time：17:31
 * Created by Leehor
 * 可使用的优惠券
 */
public class MyCouponStandbyAdapter extends BaseQuickAdapter<CouponBean.JdataBean.CouponEnableBean, BaseViewHolder> {

    private StandbyListener standbyListener;
    public MyCouponStandbyAdapter() {
        super(R.layout.recyclerview_item_coupon_standby, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CouponBean.JdataBean.CouponEnableBean item) {
        if (item !=null) {
            helper.setText(R.id.coupon_tv_price, item.getDiscount())
                    .setText(R.id.coupon_tv_used, "去使用")
                    .setText(R.id.coupon_tv_name, item.getName())
                    .setText(R.id.coupon_tv_expiretime, item.getExpiretime())
                    .setText(R.id.coupon_tv_discription, item.getCoupon_des());
            TextView tv_used = helper.getView(R.id.coupon_tv_used);
            tv_used.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (standbyListener != null) {
                        standbyListener.toUseCoupon(item);
                    }
                }
            });
        }
    }

    public void setStandbyListener(StandbyListener standbyListener) {
        this.standbyListener = standbyListener;
    }

    public  interface StandbyListener {
        void toUseCoupon(CouponBean.JdataBean.CouponEnableBean item);
    }
}
