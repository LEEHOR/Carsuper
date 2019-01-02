package com.carsuper.coahr.mvp.view.adapter.CommodityEvaluata;

import android.view.View;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： hengzwd on 2018/8/8.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityEvaluatePhoneAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<String> commentPicEntities = new ArrayList<>();
    private CommodityEvaluatePhoneItemClickListener commodityEvaluatePhoneItemClickListener;
    public CommodityEvaluatePhoneAdapter(List<String> commentPicEntities) {
        super(R.layout.recyclerview_item_comment_img, commentPicEntities);
        this.commentPicEntities = commentPicEntities;
    }


    @Override
    protected void convert(final BaseViewHolder helper, String item) {
      //  KLog.d("图片",item);
        Imageloader.loadImage(item, (ImageView) helper.getView(R.id.iv_comment_img));
        helper.getView(R.id.iv_comment_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commodityEvaluatePhoneItemClickListener.onItemClick(helper.getAdapterPosition(),commentPicEntities);
            }
        });
    }


    public  void setCommodityEvaluatePhoneItemClickListener(CommodityEvaluatePhoneItemClickListener commodityEvaluatePhoneItemClickListener ){
        this.commodityEvaluatePhoneItemClickListener= commodityEvaluatePhoneItemClickListener;
    }

}
