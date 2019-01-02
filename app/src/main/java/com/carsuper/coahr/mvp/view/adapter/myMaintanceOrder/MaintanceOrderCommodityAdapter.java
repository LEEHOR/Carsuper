package com.carsuper.coahr.mvp.view.adapter.myMaintanceOrder;

import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public class MaintanceOrderCommodityAdapter extends BaseQuickAdapter<MaintanceOrderDetailBean.JdataEntity.CommodityEntity,BaseViewHolder> {

    public MaintanceOrderCommodityAdapter(List<MaintanceOrderDetailBean.JdataEntity.CommodityEntity> data) {
        super(R.layout.recyclerview_item_maintanceorder_commodity, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MaintanceOrderDetailBean.JdataEntity.CommodityEntity item) {
        helper.setText(R.id.tv_commodity_info,item.getC_name())
                .setText(R.id.tv_commodity_price,"￥"+item.getC_price())
                .setText(R.id.tv_payment_count,"x"+item.getC_num());
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
    }
}
