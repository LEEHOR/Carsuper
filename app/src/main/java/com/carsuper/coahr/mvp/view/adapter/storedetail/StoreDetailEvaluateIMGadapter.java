package com.carsuper.coahr.mvp.view.adapter.storedetail;

import android.widget.ImageView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： hengzwd on 2018/7/30.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreDetailEvaluateIMGadapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private List<String> list = new ArrayList<>();
    public StoreDetailEvaluateIMGadapter() {
        super(R.layout.recyclerview_item_comment_img, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (helper.getAdapterPosition()<list.size()) {
            Imageloader.loadImage_larger(item, (ImageView) helper.getView(R.id.iv_comment_img));
        }
    }

    @Override
    public void setNewData(List<String> list){
        this.list = list;
        super.setNewData(list);
    }


    @Override
    public int getItemCount() {
        if (list.size()>0 && list.size()<3){
            return list.size();
        } else if (list.size()>0 && list.size()>=3){
            return 3;
        } else {
            return 0;
        }
    }
}
