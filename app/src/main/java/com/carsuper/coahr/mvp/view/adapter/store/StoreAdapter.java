package com.carsuper.coahr.mvp.view.adapter.store;

import android.view.View;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.StoreBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.StarBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/26.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreAdapter extends BaseQuickAdapter<StoreBean.JdataEntity.StationEntity, BaseViewHolder> {

    private StoreItemClickListener itemClickListener;

    public StoreAdapter() {
        super(R.layout.recyclerview_item_store, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final StoreBean.JdataEntity.StationEntity item) {
        helper.setText(R.id.tv_store_name, item.getS_name())
                .setText(R.id.tv_store_locatioon, item.getS_address())
                .setText(R.id.tv_store_distance, item.getDistance() + "km");
        ((StarBar) helper.getView(R.id.sb_evaluate)).setStarMark(Float.parseFloat(item.getLevel_score()));
        if (!item.getS_type().equals("0")) {
            ((ImageView) helper.getView(R.id.iv_store_repair_union)).setImageResource(R.mipmap.maintenance_alliance);
        }
        Imageloader.loadImage(item.getPic1(), (ImageView) helper.getView(R.id.iv_store_img));

        helper.getView(R.id.rl_store_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(item);
            }
        });
    }

    public void setItemClickListener(StoreItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
