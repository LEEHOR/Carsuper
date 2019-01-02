package com.carsuper.coahr.mvp.view.adapter;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/16.
 * Email：hengzwdhengzwd@qq.com
 */
public class OpinionIMGAdapter extends BaseQuickAdapter<Uri,BaseViewHolder> {

    public OpinionIMGAdapter() {
        super(R.layout.recyclerview_item_opinion_img, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Uri item) {
        Imageloader.loadImage(item, (ImageView) helper.getView(R.id.iv_opinion_img));
        helper.getView(R.id.iv_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData().remove(helper.getAdapterPosition());
                notifyItemRemoved(helper.getAdapterPosition());
            }
        });
    }
}
