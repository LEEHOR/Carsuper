package com.carsuper.coahr.mvp.contract.myData;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.LoginBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/7/13.
 * Email：hengzwdhengzwd@qq.com
 */
public interface LoginContract extends BaseContract {
    interface View extends BaseContract.View {

        void phoneLoginFail(String throwable);

        void phoneLoginSuccess(LoginBean loginBean);

        void getVerifyCodeFail(String throwable);

        void getVerifyCodeSuccess(String success);

        void onWXloginSuccess(LoginBean loginBean);

        void onWXloginFailure(LoginBean LoginBean);

        void  bindPhoneSuccess(BindPhoneBean bindPhoneBean);

        void  bindPhoneFail(BindPhoneBean bindPhoneBean);
    }

    interface Presenter extends BaseContract.Presenter {

        void getVerifyCode(Map<String,String> map);

        void phoneLogin(Map<String,String> map);

        void wxLogin(Map<String,String> map);

        void phoneLoginFail(String throwable);

        void phoneLoginSuccess(LoginBean loginBean);

        void getVerifyCodeFail(String throwable);

        void getVerifyCodeSuccess(String success);

        void onWXloginSuccess(LoginBean LoginBean);

        void onWXloginFailure(LoginBean LoginBean);

        void  bindPhone(Map<String,String> map);

        void  bindPhoneSuccess(BindPhoneBean bindPhoneBean);

        void  bindPhoneFail(BindPhoneBean bindPhoneBean);


    }

    interface Model extends BaseContract.Model {

        void  bindPhone(Map<String,String> map);

        void getVerifyCode(Map<String,String> map);

        void phoneLogin(Map<String,String> map);

        void wxLogin(Map<String,String> map);
    }

}
