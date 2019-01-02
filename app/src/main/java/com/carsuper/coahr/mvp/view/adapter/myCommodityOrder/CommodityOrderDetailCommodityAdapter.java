package com.carsuper.coahr.mvp.view.adapter.myCommodityOrder;

import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityOrderDetailCommodityAdapter extends BaseQuickAdapter<CommodityOrderDetailBean.JdataEntity.CommodityEntity,BaseViewHolder> {

    public CommodityOrderDetailCommodityAdapter(List<CommodityOrderDetailBean.JdataEntity.CommodityEntity> data) {
        super(R.layout.recyclerview_item_commodityorder_commodity, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CommodityOrderDetailBean.JdataEntity.CommodityEntity item) {
        helper.setText(R.id.tv_commodity_info,item.getC_name())
                .setText(R.id.tv_commodity_price,"¥"+item.getC_price())
                .setText(R.id.tv_payment_count,"x"+item.getC_num());
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
    }
}
