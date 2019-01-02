package com.carsuper.coahr.mvp.model.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.OrderSuccessContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class OrderSuccessModel extends BaseModel<OrderSuccessContract.Presenter> implements OrderSuccessContract.Model {
    @Inject
    public OrderSuccessModel(){
        super();
    }
}
