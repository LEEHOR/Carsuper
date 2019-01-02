package com.carsuper.coahr.mvp.view.adapter.myCommodityOrder;

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
import com.carsuper.coahr.mvp.model.bean.CommodityOrderBean;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/9.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CommodityOrderBean.JdataEntity.OrderListEntity> orderListEntities;

    private onCommodityOrderHandleListener onCommodityOrderHandleListener;
    private onItemClickListener onItemClickListener;
    private static final int HAVE_BEEN_CANCLE = -1;
    private static final int NEED_TO_PAY = 0;
    private static final int NEED_TO_SEND = 1;
    private static final int NEED_TO_RECIEVE = 2;
    private static final int NEED_TO_EVALUATE = 4;
    private static final int RETURN_OR_CHANGE = 5;
    private static final int HAVE_EVALUATE=6;

    public CommodityOrderAdapter(Context context, List<CommodityOrderBean.JdataEntity.OrderListEntity> orderListEntities) {
        this.context = context;
        this.orderListEntities = orderListEntities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HAVE_BEEN_CANCLE:
                return new HaveBeenCancleViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_have_been_cancle, parent, false));
            case NEED_TO_PAY:
                return new NeedToPayViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_need_to_pay, parent, false));
            case NEED_TO_SEND:
                return new NeedToSendViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_need_to_send, parent, false));
            case NEED_TO_RECIEVE:
                return new NeedToRecieveViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_need_to_recieve, parent, false));
            case NEED_TO_EVALUATE:
                return new NeedToEvaluateViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_need_to_evaluate, parent, false));
            case RETURN_OR_CHANGE:
                return new ReturnOrChangeViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_return_or_change, parent, false));
            case HAVE_EVALUATE:
                return new HaveToEvaluateViewholder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_have_to_evaluate, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder!=null&&holder.itemView!=null) {
        if (holder instanceof HaveBeenCancleViewholder) {
            ((HaveBeenCancleViewholder) holder).tv_time.setText(orderListEntities.get(position).getCreate_time());
            ((HaveBeenCancleViewholder) holder).tv_total_price.setText("¥" + orderListEntities.get(position).getTotal());
            ((HaveBeenCancleViewholder) holder).tv_order_status.setText("待付款");
            CommodityOrderCommodityAdapter adapter = new CommodityOrderCommodityAdapter(orderListEntities.get(position).getCommodity());
            ((HaveBeenCancleViewholder) holder).rv_commodity.setLayoutManager(new LinearLayoutManager(BaseApplication.mContext));
            ((HaveBeenCancleViewholder) holder).rv_commodity.setAdapter(adapter);
            ((HaveBeenCancleViewholder) holder).tv_restore_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCommodityOrderHandleListener.restoreToBuy(orderListEntities.get(position));
                }
            });

        }
        if (holder instanceof NeedToPayViewholder) {
            ((NeedToPayViewholder) holder).tv_time.setText(orderListEntities.get(position).getCreate_time());
            ((NeedToPayViewholder) holder).tv_total_price.setText("¥" + orderListEntities.get(position).getTotal());
            ((NeedToPayViewholder) holder).tv_order_status.setText("待付款");
            CommodityOrderCommodityAdapter adapter = new CommodityOrderCommodityAdapter(orderListEntities.get(position).getCommodity());
            ((NeedToPayViewholder) holder).rv_commodity.setLayoutManager(new LinearLayoutManager(BaseApplication.mContext));
            ((NeedToPayViewholder) holder).rv_commodity.setAdapter(adapter);
            ((NeedToPayViewholder) holder).tv_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCommodityOrderHandleListener.payImmediately(orderListEntities.get(position));
                }
            });

        }
        if (holder instanceof NeedToSendViewholder) {
            ((NeedToSendViewholder) holder).tv_time.setText(orderListEntities.get(position).getCreate_time());
            ((NeedToSendViewholder) holder).tv_total_price.setText("¥" + orderListEntities.get(position).getTotal());
            ((NeedToSendViewholder) holder).tv_order_status.setText("待发货");
            CommodityOrderCommodityAdapter adapter = new CommodityOrderCommodityAdapter(orderListEntities.get(position).getCommodity());
            ((NeedToSendViewholder) holder).rv_commodity.setLayoutManager(new LinearLayoutManager(BaseApplication.mContext));
            ((NeedToSendViewholder) holder).rv_commodity.setAdapter(adapter);
            ((NeedToSendViewholder) holder).tv_urge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCommodityOrderHandleListener.urgeMyOrder(orderListEntities.get(position));
                }
            });
        }
        if (holder instanceof NeedToEvaluateViewholder) {
            ((NeedToEvaluateViewholder) holder).tv_time.setText(orderListEntities.get(position).getCreate_time());
            ((NeedToEvaluateViewholder) holder).tv_total_price.setText("¥" + orderListEntities.get(position).getTotal());
            ((NeedToEvaluateViewholder) holder).tv_order_status.setText("待评价");
            CommodityOrderCommodityAdapter adapter = new CommodityOrderCommodityAdapter(orderListEntities.get(position).getCommodity());
            ((NeedToEvaluateViewholder) holder).rv_commodity.setLayoutManager(new LinearLayoutManager(BaseApplication.mContext));
            ((NeedToEvaluateViewholder) holder).rv_commodity.setAdapter(adapter);
            ((NeedToEvaluateViewholder) holder).tv_evaluate_immediately.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCommodityOrderHandleListener.evaluateImmediately(orderListEntities.get(position));
                }
            });
        }

            if (holder instanceof HaveToEvaluateViewholder) {
                ((HaveToEvaluateViewholder) holder).tv_time.setText(orderListEntities.get(position).getCreate_time());
                ((HaveToEvaluateViewholder) holder).tv_total_price.setText("¥" + orderListEntities.get(position).getTotal());
                ((HaveToEvaluateViewholder) holder).tv_order_status.setText("已评价");
                CommodityOrderCommodityAdapter adapter = new CommodityOrderCommodityAdapter(orderListEntities.get(position).getCommodity());
                ((HaveToEvaluateViewholder) holder).rv_commodity.setLayoutManager(new LinearLayoutManager(BaseApplication.mContext));
                ((HaveToEvaluateViewholder) holder).rv_commodity.setAdapter(adapter);
                ((HaveToEvaluateViewholder) holder).tv_evaluate_immediately.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCommodityOrderHandleListener.haveEvaluate(orderListEntities.get(position));
                    }
                });
            }
        if (holder instanceof NeedToRecieveViewholder) {
            ((NeedToRecieveViewholder) holder).tv_time.setText(orderListEntities.get(position).getCreate_time());
            ((NeedToRecieveViewholder) holder).tv_total_price.setText("¥" + orderListEntities.get(position).getTotal());
            ((NeedToRecieveViewholder) holder).tv_order_status.setText("待收货");
            CommodityOrderCommodityAdapter adapter = new CommodityOrderCommodityAdapter(orderListEntities.get(position).getCommodity());
            ((NeedToRecieveViewholder) holder).rv_commodity.setLayoutManager(new LinearLayoutManager(BaseApplication.mContext));
            ((NeedToRecieveViewholder) holder).rv_commodity.setAdapter(adapter);
            ((NeedToRecieveViewholder) holder).tv_see_logistics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCommodityOrderHandleListener.seeLogistics(orderListEntities.get(position));
                }
            });
            ((NeedToRecieveViewholder) holder).tv_ensure_recieve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCommodityOrderHandleListener.ensureToRecieve(orderListEntities.get(position));
                }
            });
        }
        if (holder instanceof ReturnOrChangeViewholder) {
            ((ReturnOrChangeViewholder) holder).tv_time.setText(orderListEntities.get(position).getCreate_time());
            ((ReturnOrChangeViewholder) holder).tv_total_price.setText("¥" + orderListEntities.get(position).getTotal());
            ((ReturnOrChangeViewholder) holder).tv_order_status.setText("退换货");
            CommodityOrderCommodityAdapter adapter = new CommodityOrderCommodityAdapter(orderListEntities.get(position).getCommodity());
            ((ReturnOrChangeViewholder) holder).rv_commodity.setLayoutManager(new LinearLayoutManager(BaseApplication.mContext));
            ((ReturnOrChangeViewholder) holder).rv_commodity.setAdapter(adapter);
            ((ReturnOrChangeViewholder) holder).tv_see_logistics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCommodityOrderHandleListener.seeLogistics(orderListEntities.get(position));
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

    public void setOnCommodityOrderHandleListener(onCommodityOrderHandleListener onCommodityOrderHandleListener) {
        this.onCommodityOrderHandleListener = onCommodityOrderHandleListener;
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public class HaveBeenCancleViewholder extends RecyclerView.ViewHolder {

        private LinearLayout ll_commodity_order;
        private TextView tv_time;

        private TextView tv_order_status;

        private TextView tv_total_price;

        private TextView tv_restore_buy;

        private RecyclerView rv_commodity;

        public HaveBeenCancleViewholder(final View itemView) {
            super(itemView);
            ll_commodity_order = itemView.findViewById(R.id.ll_commodity_order);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_total_price = itemView.findViewById(R.id.tv_totalprice);
            tv_restore_buy = itemView.findViewById(R.id.tv_restore_to_buy);
            rv_commodity = itemView.findViewById(R.id.rv_commodity);

            rv_commodity.setOnTouchListener(new View.OnTouchListener() {//解决recyclerview事件不向上传递的问题
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return itemView.onTouchEvent(event);
                }
            });
        }
    }

    public class NeedToPayViewholder extends RecyclerView.ViewHolder {

        private LinearLayout ll_commodity_order;
        private TextView tv_time;

        private TextView tv_order_status;

        private TextView tv_total_price;

        private TextView tv_pay;

        private RecyclerView rv_commodity;

        public NeedToPayViewholder(final View itemView) {
            super(itemView);
            ll_commodity_order = itemView.findViewById(R.id.ll_commodity_order);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_total_price = itemView.findViewById(R.id.tv_totalprice);
            tv_pay = itemView.findViewById(R.id.tv_immediately_pay);
            rv_commodity = itemView.findViewById(R.id.rv_commodity);


            rv_commodity.setOnTouchListener(new View.OnTouchListener() {//解决recyclerview事件不向上传递的问题
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return itemView.onTouchEvent(event);
                }
            });
        }
    }

    public class NeedToSendViewholder extends RecyclerView.ViewHolder {
        private LinearLayout ll_commodity_order;
        private TextView tv_time;

        private TextView tv_order_status;

        private TextView tv_total_price;

        private TextView tv_urge;

        private RecyclerView rv_commodity;

        public NeedToSendViewholder(final View itemView) {
            super(itemView);
            ll_commodity_order = itemView.findViewById(R.id.ll_commodity_order);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_total_price = itemView.findViewById(R.id.tv_totalprice);
            tv_urge = itemView.findViewById(R.id.tv_urge);
            rv_commodity = itemView.findViewById(R.id.rv_commodity);

            rv_commodity.setOnTouchListener(new View.OnTouchListener() {//解决recyclerview事件不向上传递的问题
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return itemView.onTouchEvent(event);
                }
            });
        }
    }

    public class NeedToRecieveViewholder extends RecyclerView.ViewHolder {
        private LinearLayout ll_commodity_order;
        private TextView tv_time;

        private TextView tv_order_status;

        private TextView tv_total_price;

        private TextView tv_see_logistics;
        private TextView tv_ensure_recieve;

        private RecyclerView rv_commodity;

        public NeedToRecieveViewholder(final View itemView) {
            super(itemView);
            ll_commodity_order = itemView.findViewById(R.id.ll_commodity_order);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_total_price = itemView.findViewById(R.id.tv_totalprice);
            tv_see_logistics = itemView.findViewById(R.id.tv_see_logistics);
            tv_ensure_recieve = itemView.findViewById(R.id.tv_ensure_recieve);
            rv_commodity = itemView.findViewById(R.id.rv_commodity);

            rv_commodity.setOnTouchListener(new View.OnTouchListener() {//解决recyclerview事件不向上传递的问题
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return itemView.onTouchEvent(event);
                }
            });
        }
    }

    /**
     * 待评价
     */
    public class NeedToEvaluateViewholder extends RecyclerView.ViewHolder {
        private LinearLayout ll_commodity_order;
        private TextView tv_time;

        private TextView tv_order_status;

        private TextView tv_total_price;

        private TextView tv_evaluate_immediately;
        private RecyclerView rv_commodity;

        public NeedToEvaluateViewholder(final View itemView) {
            super(itemView);
            ll_commodity_order = itemView.findViewById(R.id.ll_commodity_order);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_total_price = itemView.findViewById(R.id.tv_totalprice);
            tv_evaluate_immediately = itemView.findViewById(R.id.tv_evaluate_immediately);
            rv_commodity = itemView.findViewById(R.id.rv_commodity);

            rv_commodity.setOnTouchListener(new View.OnTouchListener() {//解决recyclerview事件不向上传递的问题
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return itemView.onTouchEvent(event);
                }
            });
        }
    }

    /**
     * 已评价
     */
    public class HaveToEvaluateViewholder extends RecyclerView.ViewHolder {
        private LinearLayout ll_commodity_order;
        private TextView tv_time;

        private TextView tv_order_status;

        private TextView tv_total_price;

        private TextView tv_evaluate_immediately;
        private RecyclerView rv_commodity;

        public HaveToEvaluateViewholder(final View itemView) {
            super(itemView);
            ll_commodity_order = itemView.findViewById(R.id.ll_commodity_order);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_total_price = itemView.findViewById(R.id.tv_totalprice);
            tv_evaluate_immediately = itemView.findViewById(R.id.tv_evaluate_immediately);
            rv_commodity = itemView.findViewById(R.id.rv_commodity);

            rv_commodity.setOnTouchListener(new View.OnTouchListener() {//解决recyclerview事件不向上传递的问题
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return itemView.onTouchEvent(event);
                }
            });
        }
    }

    public class ReturnOrChangeViewholder extends RecyclerView.ViewHolder {
        private LinearLayout ll_commodity_order;
        private TextView tv_time;
        private TextView tv_order_status;
        private TextView tv_total_price;
        private TextView tv_see_logistics;
        private RecyclerView rv_commodity;

        public ReturnOrChangeViewholder(final View itemView) {
            super(itemView);
            ll_commodity_order = itemView.findViewById(R.id.ll_commodity_order);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_total_price = itemView.findViewById(R.id.tv_totalprice);
            tv_see_logistics = itemView.findViewById(R.id.tv_see_logistics);
            rv_commodity = itemView.findViewById(R.id.rv_commodity);

            rv_commodity.setOnTouchListener(new View.OnTouchListener() {//解决recyclerview事件不向上传递的问题
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return itemView.onTouchEvent(event);
                }
            });
        }
    }


    public interface onCommodityOrderHandleListener {

        void payImmediately(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity);

        void urgeMyOrder(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity);

        void seeLogistics(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity);

        void ensureToRecieve(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity);

        void evaluateImmediately(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity);

        void restoreToBuy(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity);

        void haveEvaluate(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity);
    }

    public interface onItemClickListener {
        void onItemCLick(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity);
    }
}
