package com.carsuper.coahr.mvp.view.adapter.search;

import com.carsuper.coahr.mvp.model.bean.SearchBean;

/**
 * Author： hengzwd on 2018/7/23.
 * Email：hengzwdhengzwd@qq.com
 */
public interface OnSearchItemClickListener {

    void onClick(SearchBean.JdataEntity.SearchEntity item,int type);
}
