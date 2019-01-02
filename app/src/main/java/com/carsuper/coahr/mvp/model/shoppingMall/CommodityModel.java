package com.carsuper.coahr.mvp.model.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityModel extends BaseModel<CommodityContract.Presenter>  implements CommodityContract.Model{
    @Inject
    public CommodityModel(){
        super();
    }
}
