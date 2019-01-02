package com.carsuper.coahr.mvp.contract.myData.setting;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.LoginBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface PhoneNumberContract {
    interface View extends BaseContract.View {
        void onBindPhoneFail(String throwable);

        void onBindPhoneSuccess(BindPhoneBean bindPhoneBean);

        void getVerifyCodeFail(String throwable);

        void getVerifyCodeSuccess(String success);

        void wxLoginSuccess(LoginBean loginBean);

        void wxLoginFail(LoginBean loginBean);
    }

    interface Presenter extends BaseContract.Presenter {
        void getVerifyCode(Map<String, String> map);

        void binPhoneNumber(Map<String, String> map);

        void onBindPhoneFail(String throwable);

        void onBindPhoneSuccess(BindPhoneBean bindPhoneBean);

        void getVerifyCodeFail(String throwable);

        void getVerifyCodeSuccess(String success);

        void wxLogin(Map<String,String> map);

        void wxLoginSuccess(LoginBean loginBean);

        void wxLoginFail(LoginBean loginBean);


    }

    interface Model extends BaseContract.Model {
        void getVerifyCode(Map<String, String> map);

        void binPhoneNumber(Map<String, String> map);

        void wxLogin(Map<String,String> map);
    }

}
