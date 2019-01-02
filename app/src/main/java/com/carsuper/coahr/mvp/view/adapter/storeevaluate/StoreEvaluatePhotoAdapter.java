package com.carsuper.coahr.mvp.view.adapter.storeevaluate;

import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： hengzwd on 2018/8/1.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreEvaluatePhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<String> commentPicEntities = new ArrayList<>();
    private StoreEvaluatePhotoItemClickListener storeEvaluatePhotoItemClickListener;
    public StoreEvaluatePhotoAdapter(List<String> commentPicEntities) {
        super(R.layout.recyclerview_item_comment_img, commentPicEntities);
        this.commentPicEntities = commentPicEntities;
    }


    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        Imageloader.loadImage(item, (ImageView) helper.getView(R.id.iv_comment_img));
        helper.getView(R.id.iv_comment_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeEvaluatePhotoItemClickListener.onItemClick(helper.getAdapterPosition(),commentPicEntities);
            }
        });
    }


    public  void setStoreEvaluatePhotoItemClickListener(StoreEvaluatePhotoItemClickListener StoreEvaluatePhotoItemClickListener ){
        this.storeEvaluatePhotoItemClickListener= StoreEvaluatePhotoItemClickListener;
    }

}
