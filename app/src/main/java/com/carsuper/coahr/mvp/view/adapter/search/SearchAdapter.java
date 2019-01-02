package com.carsuper.coahr.mvp.view.adapter.search;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.SearchBean;

import java.util.List;

/**
 * Author： hengzwd on 2018/7/23.
 * Email：hengzwdhengzwd@qq.com
 */
public class SearchAdapter extends BaseQuickAdapter<SearchBean.JdataEntity.SearchEntity,BaseViewHolder> {

    private OnSearchItemClickListener onSearchItemClickListener;
    public SearchAdapter() {
        super(R.layout.recyclerview_item_search, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final SearchBean.JdataEntity.SearchEntity item) {
        helper.setText(R.id.tv_search_item,item.getName());
        helper.getView(R.id.tv_search_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchItemClickListener.onClick(item,1);
            }
        });
    }

    public void setOnItemClickListener(OnSearchItemClickListener onSearchItemClickListener){
        this.onSearchItemClickListener=onSearchItemClickListener;
    }
}
