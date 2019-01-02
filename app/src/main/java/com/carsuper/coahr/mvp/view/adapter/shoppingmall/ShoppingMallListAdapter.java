package com.carsuper.coahr.mvp.view.adapter.shoppingmall;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.OriginalPriceTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean.JdataEntity.CommodityEntity;
import com.chad.library.adapter.base.BaseViewHolder;
import com.socks.library.KLog;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingMallListAdapter extends BaseQuickAdapter<CommodityEntity, BaseViewHolder> {

    private ShoppingMallItemClickListener itemClickListener;

    public ShoppingMallListAdapter() {
        super(R.layout.recyclerview_item_shoppingmal_listcomposition, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final CommodityEntity item) {
        helper.setText(R.id.tv_commodity_price, "¥"+item.getC_price())
                .setText(R.id.tv_payment_count, item.getC_sold_real()+"人已购")
                .setText(R.id.tv_commodity_info, item.getC_name());
//        Glide.with(BaseApplication.mContext).load(item.getC_thumbnail()).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.mipmap.a).into((ImageView) helper.getView(R.id.iv_commodity_img));
        OriginalPriceTextView old_price = helper.getView(R.id.tv_commodity_originalprice);
        if (item.getC_price_old() != null && !item.getC_price_old().equals("0.00")) {
            old_price.setText("¥"+item.getC_price_old());
        } else {
            old_price.setVisibility(View.GONE);
        }
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
