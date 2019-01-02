package com.carsuper.coahr.mvp.model.myData;

import com.carsuper.coahr.mvp.contract.myData.EvaluateSuccessContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class EvaluateSuccessModel extends BaseModel<EvaluateSuccessContract.Presenter> implements EvaluateSuccessContract.Model {
    @Inject
    public EvaluateSuccessModel(){
        super();
    }
}
