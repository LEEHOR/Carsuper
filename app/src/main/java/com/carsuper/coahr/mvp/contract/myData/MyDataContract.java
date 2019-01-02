package com.carsuper.coahr.mvp.contract.myData;

import android.service.voice.VoiceInteractionService;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.MyDataAdList;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.VerifyTokenBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface MyDataContract {
    interface View extends BaseContract.View {
        void onGetInfoSuccess(PersonInfoBean personInfoBean);
        void onGetInfoFailure(String throwle);
        void verifySuccess(VerifyTokenBean verifyTokenBean);
        void verifyFailure(VerifyTokenBean failure);

        void  onSigninSuccess(ResultBean bean);
        void onSignInFailure(String failure);

        void getAdListSuccess(MyDataAdList myDataAdList);
        void getAdListFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getUserInfo(Map<String,String> map);
        void onGetInfoSuccess(PersonInfoBean personInfoBean);
        void onGetInfoFailure(String throwle);
        void verifyToken(String token);
        void verifySuccess(VerifyTokenBean verifyTokenBean);
        void verifyFailure(VerifyTokenBean failure);

        void signin(Map<String,String> map );
        void  onSigninSuccess(ResultBean bean);
        void onSignInFailure(String failure);

        void getAdList(Map<String,String> map);
        void getAdListSuccess(MyDataAdList myDataAdList);
        void getAdListFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void verifyToken(String token);
        void getUserInfo(Map<String,String> map);
        void signin(Map<String,String> map );
        void getAdList(Map<String,String> map);
    }


}
