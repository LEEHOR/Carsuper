package com.carsuper.coahr.mvp.view.adapter.main;

import android.view.View;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.MainBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.OriginalPriceTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： hengzwd on 2018/7/31.
 * Email：hengzwdhengzwd@qq.com
 */
public class GoodAdapter extends BaseQuickAdapter<MainBean.JdataEntity.AllDatasEntity.GoodsTypeEntity, BaseViewHolder> {

    private OnGoodItemClickListener onGoodItemClickListener;

    private List<MainBean.JdataEntity.AllDatasEntity.GoodsTypeEntity> goodsTypeEntities = new ArrayList<>();

    public GoodAdapter() {
        super(R.layout.recyclerview_item_commodityclassification, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final MainBean.JdataEntity.AllDatasEntity.GoodsTypeEntity item) {
        if (goodsTypeEntities != null && helper.getAdapterPosition() < goodsTypeEntities.size()) {
            helper.setText(R.id.tv_commodity_price, "¥" + item.getC_price())
                    .setText(R.id.tv_commodity_info, item.getC_name());
            OriginalPriceTextView old_price = helper.getView(R.id.tv_commodity_originalprice);
            if (item.getC_price_old() != null && !item.getC_price_old().equals("0.00")) {
                old_price.setText("¥" + item.getC_price_old());
            } else {
                old_price.setVisibility(View.INVISIBLE);
            }
            Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
            helper.getView(R.id.rl_commodity).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onGoodItemClickListener.onItemClick(item);
                }
            });
        }

    }


    @Override
    public void setNewData(List<MainBean.JdataEntity.AllDatasEntity.GoodsTypeEntity> data) {
        goodsTypeEntities = data;
        super.setNewData(data);
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    public void setOnGoodItemClickListener(OnGoodItemClickListener onGoodItemClickListener) {
        this.onGoodItemClickListener = onGoodItemClickListener;
    }
}
