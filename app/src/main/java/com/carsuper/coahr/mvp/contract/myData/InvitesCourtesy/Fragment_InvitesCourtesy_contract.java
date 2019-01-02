package com.carsuper.coahr.mvp.contract.myData.InvitesCourtesy;

import com.carsuper.coahr.mvp.contract.base.BaseContract;

import java.util.Map;

public interface Fragment_InvitesCourtesy_contract extends BaseContract {

    interface View extends BaseContract.View{

        void OnRequestMainSuccess();

        void OnRequestMainFailure(String failure);

        void OnRequestShareWXDataSuccess();

        void OnRequestShareWXDataFailure(String failure);

        void OnRequestEwmSuccess();

        void OnRequestEwmFailure(String failure);
    }


    interface Presenter extends BaseContract.Presenter {

            void OnRequestMain(Map<String,String> map);

            void OnRequestMainSuccess();

            void OnRequestMainFailure(String failure);

            void OnRequestShareWXData(Map<String,String> map);

            void OnRequestShareWXDataSuccess();

            void OnRequestShareWXDataFailure(String failure);

            void OnRequestEwm(Map<String,String> map);

            void OnRequestEwmSuccess();

            void OnRequestEwmFailure(String failure);
    }


    interface Model extends BaseContract.Model {

        void OnRequestMain(Map<String,String> map);

        void OnRequestShareWXData(Map<String,String> map);

        void OnRequestEwm(Map<String,String> map);
    }

}
