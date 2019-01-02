package com.carsuper.coahr.mvp.presenter.myData.InvitesCourtesy;

import com.carsuper.coahr.mvp.contract.myData.InvitesCourtesy.Fragment_InvitesCourtesy_contract;
import com.carsuper.coahr.mvp.model.myData.InvitesCourtesy.Fragment_InvitesCourtesy_Model;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.InvitesCourtesy.Fragment_InvitesCourtesy;

import java.util.Map;

import javax.inject.Inject;

public class Fragment_InvitesCourtesy_Presenter extends BasePresenter<Fragment_InvitesCourtesy_contract.View, Fragment_InvitesCourtesy_contract.Model> implements Fragment_InvitesCourtesy_contract.Presenter  {


   @Inject
    public Fragment_InvitesCourtesy_Presenter(Fragment_InvitesCourtesy mview, Fragment_InvitesCourtesy_Model mModel) {
        super(mview, mModel);
    }

    @Override
    public void OnRequestMain(Map<String,String> map) {
        if (mView != null) {
            mModle.OnRequestMain(map);
        }
    }

    @Override
    public void OnRequestMainSuccess() {
        if (getView() != null) {
            getView().OnRequestMainSuccess();
        }
    }

    @Override
    public void OnRequestMainFailure(String failure) {
        if (getView() != null) {
            getView().OnRequestMainFailure(failure);
        }
    }

    @Override
    public void OnRequestShareWXData(Map<String,String> map) {
        if (mModle != null) {
            mModle.OnRequestShareWXData(map);
        }
    }

    @Override
    public void OnRequestShareWXDataSuccess() {
        if (getView() != null) {
            getView().OnRequestShareWXDataSuccess();
        }
    }

    @Override
    public void OnRequestShareWXDataFailure(String failure) {
        if (getView() != null) {
            getView().OnRequestShareWXDataFailure(failure);
        }
    }

    @Override
    public void OnRequestEwm(Map<String, String> map) {
        if (mModle != null) {
            mModle.OnRequestEwm(map);
        }
    }

    @Override
    public void OnRequestEwmSuccess() {
        if (getView() != null) {
            getView().OnRequestEwmSuccess();
        }
    }

    @Override
    public void OnRequestEwmFailure(String failure) {
        if (getView() != null) {
            getView().OnRequestEwmFailure(failure);
        }
    }
}
