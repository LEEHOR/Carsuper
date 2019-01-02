package com.carsuper.coahr.mvp.view.adapter;

import android.view.View;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.GetCoupon;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Date：2018/10/11
 * Time：17:31
 * Created by Leehor
 * 可使用的优惠券
 */
public class MyCouponRecevieAdapter extends BaseQuickAdapter<GetCoupon.JdataBean.CouponBean, BaseViewHolder> {

    private ReceiverListener receiverListener;
    public MyCouponRecevieAdapter() {
        super(R.layout.recyclerview_item_coupon_receive, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GetCoupon.JdataBean.CouponBean item) {
        if (item !=null) {
            helper.setText(R.id.coupon_Receive_Left_tv, "¥"+item.getDiscount())
                    .setText(R.id.coupon_Receive_Right_name, item.getName())
                    .setText(R.id.coupon_Right_time, item.getExpiretime())
                    .setText(R.id.coupon_Receive_Right_des, item.getCoupon_des());
            TextView tv_used = helper.getView(R.id.coupon_Receive_pullDown);
            if (item.getHas_coupon()==1){  //已领取
                tv_used.setTextColor(BaseApplication.mContext.getResources().getColor(R.color.material_grey_600));
                tv_used.setBackground(BaseApplication.mContext.getResources().getDrawable(R.drawable.bg_grey700_frame_background));
            } else {      //未领取
                tv_used.setTextColor(BaseApplication.mContext.getResources().getColor(R.color.material_red_A700));
                tv_used.setBackground(BaseApplication.mContext.getResources().getDrawable(R.drawable.bg_red700_frame_background));
            }
            tv_used.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (receiverListener != null) {
                        receiverListener.pull_down(item);
                    }
                }
            });
        }
    }

    public void setReceiverListener(ReceiverListener receiverListener) {
        this.receiverListener = receiverListener;
    }

    public  interface ReceiverListener {
        void pull_down(GetCoupon.JdataBean.CouponBean item);
    }
}
