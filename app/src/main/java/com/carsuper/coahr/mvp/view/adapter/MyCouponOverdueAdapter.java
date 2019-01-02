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
public class MyCouponOverdueAdapter extends BaseQuickAdapter<CouponBean.JdataBean.CouponDisableBean, BaseViewHolder> {

    private OverdueListener overdueListener;
    public MyCouponOverdueAdapter() {
        super(R.layout.recyclerview_item_coupon_overdue, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CouponBean.JdataBean.CouponDisableBean item) {
        if (item !=null) {
            helper.setText(R.id.coupon_overdue_tv_price, item.getDiscount())
                    .setText(R.id.coupon_overdue_tv_used,"已过期")
                    .setText(R.id.coupon_overdue_tv_title, item.getName())
                    .setText(R.id.coupon_overdue_tv_expiretime, item.getExpiretime())
                    .setText(R.id.coupon_overdue_tv_discription, item.getCoupon_des());
            TextView tv_used = helper.getView(R.id.coupon_overdue_tv_used);
            tv_used.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (overdueListener != null) {
                        //standbyListener.toUseCoupon(item.getId());
                    }
                }
            });
        }
    }


    public  interface OverdueListener {

    }
}
