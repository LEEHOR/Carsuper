package com.carsuper.coahr.widgets.logisticsRecyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.LogisticsEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.carsuper.coahr.R;

import java.util.Arrays;
import java.util.List;

/**
 * Author： hengzwd on 2018/6/1.
 * Email：hengzwdhengzwd@qq.com
 */
public class LogisticsRecyclerView extends RecyclerView {
    public LogisticsRecyclerView(Context context) {
        this(context, null);
    }

    public LogisticsRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LogisticsRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    LogisticsAdapter logisticsAdapter;

    private void init(Context context) {
//        String[] strings = {"1","2","3","4","653613131","52131313","251321331","wwjfljdlfj","lfjdlsfjlsjd"};
        logisticsAdapter = new LogisticsAdapter();
//        logisticsAdapter.setNewData(Arrays.asList(strings));
        this.setLayoutManager(new LinearLayoutManager(context));
        this.setAdapter(logisticsAdapter);
        this.addItemDecoration(new LogisticsItemDecoration(context));

    }


    //设置物流信息
    public void setNewData(List<LogisticsEntity> logisticsEntities) {
        logisticsAdapter.setNewData(logisticsEntities);
    }


    private class LogisticsAdapter extends BaseQuickAdapter<LogisticsEntity, BaseViewHolder> {


        public LogisticsAdapter() {
            super(R.layout.layout_logistics, null);
        }

        @Override
        protected void convert(BaseViewHolder helper,LogisticsEntity item) {

            helper.setText(R.id.tv_logistics, item.getMessage())
                    .setText(R.id.tv_time, item.getFtime());

        }
    }

}
