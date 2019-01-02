package com.carsuper.coahr.mvp.view.adapter.commodityDetail;

import android.widget.ImageView;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.ConfirmCommodityOrderBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.CommodityCountView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/17.
 * Email：hengzwdhengzwd@qq.com
 */
public class ConfirmCommodityOrderAdapter extends BaseQuickAdapter<ConfirmCommodityOrderBean.JdataEntity.CommodityEntity,BaseViewHolder> {

    public ConfirmCommodityOrderAdapter() {
        super(R.layout.recyclerview_item_confirm_commodityorder, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, ConfirmCommodityOrderBean.JdataEntity.CommodityEntity item) {

        helper.setText(R.id.tv_commodity_info,item.getC_name())
                .setText(R.id.tv_commodity_price,"￥"+item.getC_price());
        ((TextView)helper.getView(R.id.tv_commodity_count)).setText("X"+item.getC_num());
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
    }
}
