package com.carsuper.coahr.mvp.model.myData;

import com.carsuper.coahr.mvp.contract.myData.InvitationContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class InvitationModel extends BaseModel<InvitationContract.Presenter>  implements InvitationContract.Model{
    @Inject
    public InvitationModel(){
        super();
    }
}
