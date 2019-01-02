package com.carsuper.coahr.mvp.model.myData.VisitMaintance;

import com.carsuper.coahr.mvp.contract.myData.visitMaintance.MyMaintanceOrderDetailContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyMaintanceOrderDetailModel extends BaseModel<MyMaintanceOrderDetailContract.Presenter> implements MyMaintanceOrderDetailContract.Model {
    @Inject
    public MyMaintanceOrderDetailModel(){
        super();
    }
}
