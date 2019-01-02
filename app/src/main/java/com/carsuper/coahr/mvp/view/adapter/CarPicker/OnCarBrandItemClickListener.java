package com.carsuper.coahr.mvp.view.adapter.CarPicker;

import com.carsuper.coahr.mvp.model.bean.CarBrandBean;

/**
 * Author： hengzwd on 2018/8/6.
 * Email：hengzwdhengzwd@qq.com
 */
public interface OnCarBrandItemClickListener {

    void onItemClick(CarBrandBean.JdataEntity.BrandEntityX.BrandEntity  item);
    void onHotItemClick(CarBrandBean.JdataEntity.HotEntity item);
}
