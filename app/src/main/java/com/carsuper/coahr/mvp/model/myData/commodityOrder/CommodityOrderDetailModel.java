package com.carsuper.coahr.mvp.model.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.myData.commodityOrder.CommodityOrderDetailContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityOrderDetailModel extends BaseModel<CommodityOrderDetailContract.Presenter> implements CommodityOrderDetailContract.Model {
    @Inject
    public CommodityOrderDetailModel(){
        super();
    }
}
