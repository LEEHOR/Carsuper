package com.carsuper.coahr.mvp.model.shoppingMall;

import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityRecommendContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityRecommendModel extends BaseModel<CommodityRecommendContract.Presenter> implements CommodityRecommendContract.Model {
    @Inject
    public CommodityRecommendModel(){
        super();
    }
}
