package com.carsuper.coahr.mvp.model.myData.myPoints;

import com.carsuper.coahr.mvp.contract.myData.myPoints.PointCenterContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class PointCenterModel extends BaseModel<PointCenterContract.Presenter> implements PointCenterContract.Model {
    @Inject
    public PointCenterModel(){
        super();
    }
}
