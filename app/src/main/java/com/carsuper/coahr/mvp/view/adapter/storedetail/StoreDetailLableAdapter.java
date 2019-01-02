package com.carsuper.coahr.mvp.view.adapter.storedetail;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/30.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreDetailLableAdapter extends BaseQuickAdapter<StoreDetailBean.JdataEntity.StationEntity.SServiceTagEntity,BaseViewHolder> {

    public StoreDetailLableAdapter() {
        super(R.layout.recyclerview_item_storedetail_lable, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, StoreDetailBean.JdataEntity.StationEntity.SServiceTagEntity item) {

        if (item != null) {
            helper.setText(R.id.tv_lable,item.getTag());
        }
    }
}
