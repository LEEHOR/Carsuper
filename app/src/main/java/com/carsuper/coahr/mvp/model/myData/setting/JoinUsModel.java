package com.carsuper.coahr.mvp.model.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.JoinUsContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class JoinUsModel extends BaseModel<JoinUsContract.Presenter> implements JoinUsContract.Model {
    @Inject
    public JoinUsModel(){
        super();
    }
}
