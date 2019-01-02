package com.carsuper.coahr.mvp.view.Exchange_mall.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean;
import com.carsuper.coahr.mvp.model.bean.ExchangeDetail;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Author： hengzwd on 2018/7/25.
 * Email：hengzwdhengzwd@qq.com
 */
public class ExchangeDetailAdapter extends BaseQuickAdapter<ExchangeDetail.JdataBean.CommodityDescriptionBean.DesPicBean, BaseViewHolder> {

    public ExchangeDetailAdapter() {
        super(R.layout.recyclerview_item_commodity_detail_img, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, ExchangeDetail.JdataBean.CommodityDescriptionBean.DesPicBean item) {
        ImageView view = helper.getView(R.id.iv_commodity_detail_img);
        Imageloader.setViewSize(view,ScreenUtils.getScreenWidth(BaseApplication.mContext),ViewGroup.LayoutParams.WRAP_CONTENT,ImageView.ScaleType.FIT_CENTER);
        Imageloader.loadImage_larger(item.getPic(), view);
    }
}
