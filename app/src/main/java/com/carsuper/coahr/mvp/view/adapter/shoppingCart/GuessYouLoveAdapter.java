package com.carsuper.coahr.mvp.view.adapter.shoppingCart;

import android.view.View;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.RecommendEntity;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/20.
 * Email：hengzwdhengzwd@qq.com
 */
public class GuessYouLoveAdapter extends BaseQuickAdapter<RecommendEntity,BaseViewHolder> {

    private OnGuessYouLoveItemClickListener itemClickListener;
    public GuessYouLoveAdapter() {
        super(R.layout.recyclerview_item_shoppingmal_gridcomposition, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final RecommendEntity item) {
        helper.setText(R.id.tv_commodity_price, "¥"+item.getC_price())
                .setText(R.id.tv_payment_count, item.getC_sold_real()+"人已购")
                .setText(R.id.tv_commodity_info, item.getC_name());
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
        helper.getView(R.id.rl_commodity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(item);
            }
        });
    }

    public void setItemClickListener(OnGuessYouLoveItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    public interface OnGuessYouLoveItemClickListener{
        void  onItemClick(RecommendEntity entity);
    }
}
