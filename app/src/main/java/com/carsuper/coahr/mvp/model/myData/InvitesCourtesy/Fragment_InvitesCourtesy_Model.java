package com.carsuper.coahr.mvp.model.myData.InvitesCourtesy;

import com.carsuper.coahr.mvp.contract.myData.InvitesCourtesy.Fragment_InvitesCourtesy_contract;
import com.carsuper.coahr.mvp.model.base.BaseModel;

import java.util.Map;

import javax.inject.Inject;

public class Fragment_InvitesCourtesy_Model extends BaseModel<Fragment_InvitesCourtesy_contract.Presenter> implements Fragment_InvitesCourtesy_contract.Model {
    @Inject
    public Fragment_InvitesCourtesy_Model() {
        super();
    }




    @Override
    public void OnRequestMain(Map<String, String> map) {

    }

    @Override
    public void OnRequestShareWXData(Map<String, String> map) {

    }

    @Override
    public void OnRequestEwm(Map<String, String> map) {

    }
}


