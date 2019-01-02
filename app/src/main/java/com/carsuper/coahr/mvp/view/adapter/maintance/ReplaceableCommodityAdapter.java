package com.carsuper.coahr.mvp.view.adapter.maintance;

import android.view.View;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.ReplaceableCommodityBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/6.
 * Email：hengzwdhengzwd@qq.com
 */
public class ReplaceableCommodityAdapter extends BaseQuickAdapter<ReplaceableCommodityBean.JdataEntity.CommodityEntity,BaseViewHolder>{


    private ReplayceCommodityItemClickListener replayceCommodityItemClickListener;

    public ReplaceableCommodityAdapter() {
        super(R.layout.recyclerview_item_replayceable_commodity, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final ReplaceableCommodityBean.JdataEntity.CommodityEntity item) {
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
        helper.setText(R.id.tv_commodity_name,item.getC_name())
                .setText(R.id.tv_commodity_price,"¥"+item.getC_price());
        helper.getView(R.id.rl_replacebale_commodity_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replayceCommodityItemClickListener.onItemClick(item);
            }
        });
    }


    public void  setReplayceCommodityItemClickListener(ReplayceCommodityItemClickListener replayceCommodityItemClickListener){
        this.replayceCommodityItemClickListener=replayceCommodityItemClickListener;
    }
    public interface   ReplayceCommodityItemClickListener{
        void onItemClick( ReplaceableCommodityBean.JdataEntity.CommodityEntity commodityEntity);
    }
}
