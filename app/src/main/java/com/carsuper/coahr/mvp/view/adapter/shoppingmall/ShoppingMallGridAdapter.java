package com.carsuper.coahr.mvp.view.adapter.shoppingmall;

import android.view.View;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： hengzwd on 2018/7/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingMallGridAdapter extends BaseQuickAdapter<ShoppingMallBean.JdataEntity.CommodityEntity, BaseViewHolder> {

    private ShoppingMallItemClickListener itemClickListener;

    public ShoppingMallGridAdapter() {
        super(R.layout.recyclerview_item_shoppingmal_gridcomposition, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final ShoppingMallBean.JdataEntity.CommodityEntity item) {
        helper.setText(R.id.tv_commodity_price,"¥"+ item.getC_price())
                .setText(R.id.tv_payment_count, item.getC_sold_real()+"人已购")
                .setText(R.id.tv_commodity_info, item.getC_name());
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
        helper.getView(R.id.rl_commodity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(item);
            }
        });

    }

    public void setItemClickListener(ShoppingMallItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}