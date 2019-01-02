package com.carsuper.coahr.mvp.view.adapter.storedetail;

import android.widget.TextView;

import com.carsuper.coahr.mvp.contract.MainActivityContract;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean;

/**
 * Author： hengzwd on 2018/7/30.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ExplainItemClickListener {

    void  onitemClick(TextView view,StoreDetailBean.JdataEntity.ServiceEntity serviceEntity);
}
