package com.carsuper.coahr.mvp.view.adapter.citypicker;

import com.carsuper.coahr.mvp.model.bean.CityInfoBean;


public interface OnPickListener {
    void onPick(int position, CityInfoBean.JdataEntity.CityListEntity.CityEntity data);
    void onLocate();
}
