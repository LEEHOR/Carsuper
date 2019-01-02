package com.carsuper.coahr.mvp.view.adapter.shoppingmall;
import android.os.Environment;

import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean.JdataEntity.CommodityEntity;
/**
 * Author： hengzwd on 2018/7/24.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ShoppingMallItemClickListener {

    void  onClick(CommodityEntity  entity);
}
