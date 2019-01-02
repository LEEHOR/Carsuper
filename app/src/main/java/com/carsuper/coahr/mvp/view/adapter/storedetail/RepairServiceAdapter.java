package com.carsuper.coahr.mvp.view.adapter.storedetail;

import android.view.View;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/30.
 * Email：hengzwdhengzwd@qq.com
 */
public class RepairServiceAdapter extends BaseQuickAdapter<StoreDetailBean.JdataEntity.ServiceEntity, BaseViewHolder> {
    private List<StoreDetailBean.JdataEntity.ServiceEntity> serviceEntities;

    private ExplainItemClickListener explainItemClickListener;

    public RepairServiceAdapter() {
        super(R.layout.recyclerview_item_repairservice, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final StoreDetailBean.JdataEntity.ServiceEntity item) {
                helper.setText(R.id.tv_service,item.getB_name())
                        .setText(R.id.tv_price,"￥"+item.getB_price_min()+"~"+"￥"+item.getB_price_max());
                helper.getView(R.id.tv_explain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        explainItemClickListener.onitemClick((TextView) v,item);
                    }
                });
    }

    public void setExplainItemClickListener(ExplainItemClickListener explainItemClickListener) {
        this.explainItemClickListener = explainItemClickListener;
    }

    @Override
    public void setNewData(List<StoreDetailBean.JdataEntity.ServiceEntity> serviceEntities) {
        this.serviceEntities = serviceEntities;
        super.setNewData(serviceEntities);
    }
}
