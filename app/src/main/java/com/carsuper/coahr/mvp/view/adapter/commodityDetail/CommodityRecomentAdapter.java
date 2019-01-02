package com.carsuper.coahr.mvp.view.adapter.commodityDetail;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/25.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityRecomentAdapter extends BaseQuickAdapter<CommodityDetailBean.JdataEntity.RecommendCommodityEntity,BaseViewHolder> {

    private RecomentItemClickListener itemClickListener;
    public CommodityRecomentAdapter() {
        super(R.layout.recyclerview_item_shoppingmal_gridcomposition, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final CommodityDetailBean.JdataEntity.RecommendCommodityEntity item) {
        helper.setText(R.id.tv_commodity_price, "¥"+item.getC_price())
                .setText(R.id.tv_payment_count, item.getC_sold_real()+"人已购买")
                .setText(R.id.tv_commodity_info, item.getC_name());
        ImageView view = helper.getView(R.id.iv_commodity_img);
        Imageloader.setViewSize(view,((ScreenUtils.getScreenWidth(BaseApplication.mContext)-DensityUtils.dp2px(BaseApplication.mContext,30))/2),LinearLayout.LayoutParams.WRAP_CONTENT,ImageView.ScaleType.FIT_CENTER);
        Imageloader.loadImage(item.getC_thumbnail(), view );
        helper.getView(R.id.rl_commodity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(item);
            }
        });
    }

    public void setItemClickListener(RecomentItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

}
