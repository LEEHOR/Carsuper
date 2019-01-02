package com.carsuper.coahr.mvp.model.myData.myPoints;

import com.carsuper.coahr.mvp.contract.myData.myPoints.MyPointsContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyPointsModel extends BaseModel<MyPointsContract.Presenter> implements MyPointsContract.Model {
    @Inject
    public MyPointsModel(){
        super();
    }
}
