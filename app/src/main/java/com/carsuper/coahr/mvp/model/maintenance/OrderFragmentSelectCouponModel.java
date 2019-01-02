package com.carsuper.coahr.mvp.model.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.OrderFragmentSelectCouponContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/10/16
 * on 9:17
 */
public class OrderFragmentSelectCouponModel extends BaseModel<OrderFragmentSelectCouponContract.Presenter> implements OrderFragmentSelectCouponContract.Model {

    @Inject
    public OrderFragmentSelectCouponModel() {
        super();
    }

    @Override
    public void getSelectCouponList(Map<String, String> map) {

    }
}
