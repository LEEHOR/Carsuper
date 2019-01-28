package com.carsuper.coahr.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CouponBean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/10/18
 * on 15:10
 */
public class CouponAdapter_test extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CouponBean couponBean;
    private Context context;
    private int type;
    private onItemClickListener listener;
    public CouponAdapter_test(Context context) {
        this.context = context;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCouponBean(CouponBean couponBean) {
        this.couponBean = couponBean;

        getItemCount();
    }

//    private List<CouponBean.JdataBean.CouponDisableBean> getDisableList(){
//        if (couponBean !=null){
//            coupon_disable_list = couponBean.getJdata().getCoupon_disable();
//        }
//        return coupon_disable_list;
//    }

//    private List<CouponBean.JdataBean.CouponEnableBean> getEnableList(){
//        if (couponBean != null) {
//            coupon_enable_List=couponBean.getJdata().getCoupon_enable();
//        }
//        return coupon_enable_List;
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new StandbyCouponHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_coupon_standby, parent, false));

            case 1:
                return new OverdueCouponHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_coupon_overdue, parent, false));


        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof StandbyCouponHolder) {
            ((StandbyCouponHolder) holder).coupon_tv_price.setText("¥"+couponBean.getJdata().getCoupon_enable().get(position).getDiscount());
            ((StandbyCouponHolder) holder).coupon_tv_used.setText("去使用");
            ((StandbyCouponHolder) holder).coupon_tv_name.setText(couponBean.getJdata().getCoupon_enable().get(position).getName());
            ((StandbyCouponHolder) holder).coupon_tv_expiretime.setText(couponBean.getJdata().getCoupon_enable().get(position).getExpiretime());
            ((StandbyCouponHolder) holder).coupon_tv_discription.setText(couponBean.getJdata().getCoupon_enable().get(position).getCoupon_des());
            ((StandbyCouponHolder) holder).coupon_tv_used.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.StandbyCouponListener(couponBean.getJdata().getCoupon_enable().get(position));
                    }
                }
            });
        }
        if (holder instanceof OverdueCouponHolder) {
            ((OverdueCouponHolder) holder).coupon_tv_price.setText("¥"+couponBean.getJdata().getCoupon_disable().get(position).getDiscount());
            ((OverdueCouponHolder) holder).coupon_tv_used.setText("已过期");
            ((OverdueCouponHolder) holder).coupon_overdue_tv_title.setText(couponBean.getJdata().getCoupon_disable().get(position).getName());
            ((OverdueCouponHolder) holder).coupon_tv_expiretime.setText(couponBean.getJdata().getCoupon_disable().get(position).getExpiretime());
            ((OverdueCouponHolder) holder).coupon_tv_discription.setText(couponBean.getJdata().getCoupon_disable().get(position).getCoupon_des());
            ((OverdueCouponHolder) holder).coupon_tv_used.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.OverdueCouponListener(couponBean.getJdata().getCoupon_disable().get(position));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (couponBean != null) {
            if (type == 0) {
                return couponBean.getJdata().getCoupon_enable().size();
            } else if (type == 1) {
                return couponBean.getJdata().getCoupon_disable().size();
            } else {
                return 0;
            }
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (type == 0) {
            return 0;
        } else if (type == 1) {
            return 1;
        } else {
            return 2;
        }
    }


    public class StandbyCouponHolder extends RecyclerView.ViewHolder {
        private TextView coupon_tv_price;
        private TextView coupon_tv_used;
        private TextView coupon_tv_name;
        private TextView coupon_tv_expiretime;
        private TextView coupon_tv_discription;

        public StandbyCouponHolder(View itemView) {
            super(itemView);
            coupon_tv_price = itemView.findViewById(R.id.coupon_tv_price);
            coupon_tv_used = itemView.findViewById(R.id.coupon_tv_used);
            coupon_tv_name = itemView.findViewById(R.id.coupon_tv_name);
            coupon_tv_expiretime = itemView.findViewById(R.id.coupon_tv_expiretime);
            coupon_tv_discription = itemView.findViewById(R.id.coupon_tv_discription);
        }
    }

    public class OverdueCouponHolder extends RecyclerView.ViewHolder {
        private TextView coupon_tv_price;
        private TextView coupon_tv_used;
        private TextView coupon_overdue_tv_title;
        private TextView coupon_tv_expiretime;
        private TextView coupon_tv_discription;

        public OverdueCouponHolder(View itemView) {
            super(itemView);
            coupon_tv_price = itemView.findViewById(R.id.coupon_overdue_tv_price);
            coupon_tv_used = itemView.findViewById(R.id.coupon_overdue_tv_used);
            coupon_overdue_tv_title = itemView.findViewById(R.id.coupon_overdue_tv_title);
            coupon_tv_expiretime = itemView.findViewById(R.id.coupon_overdue_tv_expiretime);
            coupon_tv_discription = itemView.findViewById(R.id.coupon_overdue_tv_discription);
        }
    }

    public interface onItemClickListener {
        void StandbyCouponListener(CouponBean.JdataBean.CouponEnableBean enableBean);

        void OverdueCouponListener(CouponBean.JdataBean.CouponDisableBean disableBean);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }
}