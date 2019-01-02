package com.carsuper.coahr.mvp.view.adapter.commodityDetail;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.ImageVideoDataLoadProvider;
import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/25.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityDetailAdapter extends BaseQuickAdapter<CommodityDetailBean.JdataEntity.CommodityDescriptionEntity.DesPicEntity, BaseViewHolder> {

    public CommodityDetailAdapter() {
        super(R.layout.recyclerview_item_commodity_detail_img, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, CommodityDetailBean.JdataEntity.CommodityDescriptionEntity.DesPicEntity item) {
        ImageView view = helper.getView(R.id.iv_commodity_detail_img);
        Imageloader.setViewSize(view,ScreenUtils.getScreenWidth(BaseApplication.mContext),ViewGroup.LayoutParams.WRAP_CONTENT,ImageView.ScaleType.FIT_CENTER);
        Imageloader.loadImage_larger(item.getPic(), view);
    }
}
