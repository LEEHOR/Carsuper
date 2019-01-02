package com.carsuper.coahr.mvp.view.Exchange_mall.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/12/29
 * on 10:33
 */
public class ExchangeMallAdapter extends BaseQuickAdapter<ExchangeMallList.JdataBean.CommodityBean,BaseViewHolder> {
    private ExchangeClick exchangeClick;
    public ExchangeMallAdapter() {

        super(R.layout.recyclerview_item_exchange_shopping,null);

    }

    @Override
    protected void convert(BaseViewHolder helper, final ExchangeMallList.JdataBean.CommodityBean item) {
        if (item != null) {
            helper.setText(R.id.exchange_shopping_describe,item.getC_name())
                    .setText(R.id.exchange_shopping_price,item.getC_price());
            ImageView view = helper.getView(R.id.exchange_shopping_ims);
            Imageloader.setViewSize(view,((ScreenUtils.getScreenWidth(BaseApplication.mContext)-DensityUtils.dp2px(BaseApplication.mContext,30))/2),LinearLayout.LayoutParams.WRAP_CONTENT,ImageView.ScaleType.FIT_CENTER);
            Imageloader.loadImage(item.getC_thumbnail(),view);
            helper.getView(R.id.exchange_re).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (exchangeClick != null) {
                        exchangeClick.onClick(item);
                    }
                }
            });
        }
    }

    public void setExchangeClick(ExchangeClick exchangeClick) {
        this.exchangeClick = exchangeClick;
    }

    public interface ExchangeClick{
        void onClick(ExchangeMallList.JdataBean.CommodityBean commodityBean);
    }

}
