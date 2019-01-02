package com.carsuper.coahr.mvp.view.adapter.myMaintanceOrder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderBean;
import com.carsuper.coahr.mvp.view.base.BaseApplication;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MaintanceOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MaintanceOrderBean.JdataEntity.OrderEntity> orderListEntities;

    private onMaintanceOrderHandleListener onMaintanceOrderHandleListener;
    private onItemClickListener onItemClickListener;
    private static final int HAVE_BEEN_CANCLE = -1;
    private static final int NEED_TO_PAY = 0;
    private static final int HAVE_BEEN_COMPLETE = 3;
    private static final int NEED_TO_EVALUATE = 2;
    private static final int NEED_TO_SERVE = 1;

    public MaintanceOrderAdapter(Context context, List<MaintanceOrderBean.JdataEntity.OrderEntity> orderListEntities) {
        this.context = context;
        this.orderListEntities = orderListEntities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HAVE_BEEN_CANCLE:
                return new HaveBeenCancleViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_maintanceorder_have_been_cancle, parent, false));
            case NEED_TO_PAY:
                return new NeedToPayViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_maintanceorder_need_to_pay, parent, false));
            case NEED_TO_SERVE:
                return new NeedToServeViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_maintanceorder_need_to_serve, parent, false));
            case NEED_TO_EVALUATE:
                return new NeedToEvaluateViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_maintanceorder_need_to_evaluate, parent, false));
            case HAVE_BEEN_COMPLETE:
                return new HaveBeanCompleteViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_maintanceorder_have_been_complete,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HaveBeenCancleViewholder) {
            ((HaveBeenCancleViewholder) holder).tv_number.setText(orderListEntities.get(position).getOrder_id());
            ((HaveBeenCancleViewholder) holder).tv_detail_address.setText(orderListEntities.get(position).getAddress());
            ((HaveBeenCancleViewholder) holder).tv_order_status.setText("已取消");
            ((HaveBeenCancleViewholder) holder).tv_order_address.setText(orderListEntities.get(position).getProvince() + orderListEntities.get(position).getCity() + orderListEntities.get(position).getDistrict());
            ((HaveBeenCancleViewholder) holder).tv_maintance_time.setText(orderListEntities.get(position).getAppoint_date() + orderListEntities.get(position).getAppoint_time());
            ((HaveBeenCancleViewholder) holder).tv_restore_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMaintanceOrderHandleListener.restoreToOrder(orderListEntities.get(position));
                }
            });
        }
        if (holder instanceof NeedToPayViewholder) {
            ((NeedToPayViewholder) holder).tv_number.setText(orderListEntities.get(position).getOrder_id());
            ((NeedToPayViewholder) holder).tv_detail_address.setText(orderListEntities.get(position).getAddress());
            ((NeedToPayViewholder) holder).tv_order_status.setText("待付款");
            ((NeedToPayViewholder) holder).tv_order_address.setText(orderListEntities.get(position).getProvince() + orderListEntities.get(position).getCity() + orderListEntities.get(position).getDistrict());
            ((NeedToPayViewholder) holder).tv_maintance_time.setText(orderListEntities.get(position).getAppoint_date() + orderListEntities.get(position).getAppoint_time());
                ((NeedToPayViewholder) holder).tv_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onMaintanceOrderHandleListener.payImmediately(orderListEntities.get(position));
                    }
                });

        }

        if (holder instanceof NeedToEvaluateViewholder) {
            ((NeedToEvaluateViewholder) holder).tv_number.setText(orderListEntities.get(position).getOrder_id());
            ((NeedToEvaluateViewholder) holder).tv_detail_address.setText(orderListEntities.get(position).getAddress());
            ((NeedToEvaluateViewholder) holder).tv_order_status.setText("待评论");
            ((NeedToEvaluateViewholder) holder).tv_order_address.setText(orderListEntities.get(position).getProvince() + orderListEntities.get(position).getCity() + orderListEntities.get(position).getDistrict());
            ((NeedToEvaluateViewholder) holder).tv_maintance_time.setText(orderListEntities.get(position).getAppoint_date() + orderListEntities.get(position).getAppoint_time());
            if (orderListEntities.get(position).getO_status().equals("6"))
            ((NeedToEvaluateViewholder) holder).tv_evaluate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMaintanceOrderHandleListener.evaluateImmediately(orderListEntities.get(position));
                }
            });

        }
        if (holder instanceof HaveBeanCompleteViewholder) {
            ((HaveBeanCompleteViewholder) holder).tv_number.setText(orderListEntities.get(position).getOrder_id());
            ((HaveBeanCompleteViewholder) holder).tv_detail_address.setText(orderListEntities.get(position).getAddress());
            ((HaveBeanCompleteViewholder) holder).tv_order_status.setText("已完成");
            ((HaveBeanCompleteViewholder) holder).tv_order_address.setText(orderListEntities.get(position).getProvince() + orderListEntities.get(position).getCity() + orderListEntities.get(position).getDistrict());
            ((HaveBeanCompleteViewholder) holder).tv_maintance_time.setText(orderListEntities.get(position).getAppoint_date() + orderListEntities.get(position).getAppoint_time());

        }

        if (holder instanceof NeedToServeViewholder) {
            ((NeedToServeViewholder) holder).tv_number.setText(orderListEntities.get(position).getOrder_id());
            ((NeedToServeViewholder) holder).tv_detail_address.setText(orderListEntities.get(position).getAddress());
            ((NeedToServeViewholder) holder).tv_order_status.setText("待服务");
            ((NeedToServeViewholder) holder).tv_order_address.setText(orderListEntities.get(position).getProvince() + orderListEntities.get(position).getCity() + orderListEntities.get(position).getDistrict());
            ((NeedToServeViewholder) holder).tv_maintance_time.setText(orderListEntities.get(position).getAppoint_date() + orderListEntities.get(position).getAppoint_time());
            ((NeedToServeViewholder) holder).tv_contact_someone.setText(orderListEntities.get(position).getAssignment()==1?"联系客服":orderListEntities.get(position).getAssignment()==2?"联系门店":"确认完成服务");
            ((NeedToServeViewholder) holder).tv_contact_someone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (orderListEntities.get(position).getAssignment()==3) {
                        onMaintanceOrderHandleListener.ensureServiceComplete(orderListEntities.get(position));
                    }else {
                        onMaintanceOrderHandleListener.contractSomeone(orderListEntities.get(position));
                    }
                }
            });
            ((NeedToServeViewholder) holder).tv_contact_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onMaintanceOrderHandleListener.ensureServiceToCancel(orderListEntities.get(position));
                }
            });

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemCLick(orderListEntities.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (orderListEntities != null && orderListEntities.size() > 0) {
            return orderListEntities.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(orderListEntities.get(position).getO_status());
    }

    public void setOnMaintanceOrderHandleListener(onMaintanceOrderHandleListener onMaintanceOrderHandleListener) {
        this.onMaintanceOrderHandleListener = onMaintanceOrderHandleListener;
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public class HaveBeenCancleViewholder extends RecyclerView.ViewHolder {


        private LinearLayout rl_maintance_order;
        private TextView tv_number;
        private TextView tv_order_status;
        private TextView tv_order_address;
        private TextView tv_detail_address;
        private TextView tv_maintance_time;
        private TextView tv_restore_order;


        public HaveBeenCancleViewholder(final View itemView) {
            super(itemView);
            rl_maintance_order = itemView.findViewById(R.id.ll_maintance_order);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_order_address = itemView.findViewById(R.id.tv_order_address);
            tv_detail_address = itemView.findViewById(R.id.tv_detail_address);
            tv_maintance_time = itemView.findViewById(R.id.tv_maintance_time);
            tv_restore_order = itemView.findViewById(R.id.tv_restore_order);
        }
    }

    public class NeedToPayViewholder extends RecyclerView.ViewHolder {

        private LinearLayout rl_maintance_order;
        private TextView tv_number;
        private TextView tv_order_status;
        private TextView tv_order_address;
        private TextView tv_detail_address;
        private TextView tv_maintance_time;
        private TextView tv_pay;


        public NeedToPayViewholder(final View itemView) {
            super(itemView);
            rl_maintance_order = itemView.findViewById(R.id.ll_maintance_order);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_order_address = itemView.findViewById(R.id.tv_order_address);
            tv_detail_address = itemView.findViewById(R.id.tv_detail_address);
            tv_maintance_time = itemView.findViewById(R.id.tv_maintance_time);
            tv_pay = itemView.findViewById(R.id.tv_immediately_pay);
        }
    }


    public class NeedToEvaluateViewholder extends RecyclerView.ViewHolder {
        private LinearLayout rl_maintance_order;
        private TextView tv_number;
        private TextView tv_order_status;
        private TextView tv_order_address;
        private TextView tv_detail_address;
        private TextView tv_maintance_time;
        private TextView tv_evaluate;


        public NeedToEvaluateViewholder(final View itemView) {
            super(itemView);
            rl_maintance_order = itemView.findViewById(R.id.ll_maintance_order);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_order_address = itemView.findViewById(R.id.tv_order_address);
            tv_detail_address = itemView.findViewById(R.id.tv_detail_address);
            tv_maintance_time = itemView.findViewById(R.id.tv_maintance_time);
            tv_evaluate = itemView.findViewById(R.id.tv_evaluate);

        }
    }

    public class HaveBeanCompleteViewholder extends RecyclerView.ViewHolder {
        private LinearLayout rl_maintance_order;
        private TextView tv_number;
        private TextView tv_order_status;
        private TextView tv_order_address;
        private TextView tv_detail_address;
        private TextView tv_maintance_time;
//        private TextView tv_contact_staff;
//        private TextView tv_ensure;


        public HaveBeanCompleteViewholder(final View itemView) {
            super(itemView);
            rl_maintance_order = itemView.findViewById(R.id.ll_maintance_order);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_order_address = itemView.findViewById(R.id.tv_order_address);
            tv_detail_address = itemView.findViewById(R.id.tv_detail_address);
            tv_maintance_time = itemView.findViewById(R.id.tv_maintance_time);
//            tv_contact_staff = itemView.findViewById(R.id.tv_contact_staff);
//            tv_ensure = itemView.findViewById(R.id.tv_ensure);

        }
    }


    public class NeedToServeViewholder extends RecyclerView.ViewHolder {
        private LinearLayout rl_maintance_order;
        private TextView tv_number;
        private TextView tv_order_status;
        private TextView tv_order_address;
        private TextView tv_detail_address;
        private TextView tv_maintance_time;
        private TextView tv_contact_someone;
        private TextView tv_contact_cancel;


        public NeedToServeViewholder(final View itemView) {
            super(itemView);
            rl_maintance_order = itemView.findViewById(R.id.ll_maintance_order);
            tv_number = itemView.findViewById(R.id.tv_number);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_order_address = itemView.findViewById(R.id.tv_order_address);
            tv_detail_address = itemView.findViewById(R.id.tv_detail_address);
            tv_maintance_time = itemView.findViewById(R.id.tv_maintance_time);
            tv_contact_someone = itemView.findViewById(R.id.tv_contact_someone);
            tv_contact_cancel=itemView.findViewById(R.id.tv_contact_cancel);
        }
    }

    public interface onMaintanceOrderHandleListener {

        void payImmediately(MaintanceOrderBean.JdataEntity.OrderEntity orderEntity);

        void contractSomeone(MaintanceOrderBean.JdataEntity.OrderEntity orderEntity);

        void ensureServiceComplete(MaintanceOrderBean.JdataEntity.OrderEntity orderEntity);

        void evaluateImmediately(MaintanceOrderBean.JdataEntity.OrderEntity orderEntity);

        void restoreToOrder(MaintanceOrderBean.JdataEntity.OrderEntity orderEntity);

        void ensureServiceToCancel(MaintanceOrderBean.JdataEntity.OrderEntity orderEntity);


    }

    public interface onItemClickListener {
        void onItemCLick(MaintanceOrderBean.JdataEntity.OrderEntity orderListEntity);
    }
}
