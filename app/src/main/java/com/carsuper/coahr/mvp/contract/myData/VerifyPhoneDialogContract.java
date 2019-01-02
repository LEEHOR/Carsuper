package com.carsuper.coahr.mvp.contract.myData;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.BindPhoneBean;
import com.carsuper.coahr.mvp.model.bean.PhoneMessageBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/2.
 * Email：hengzwdhengzwd@qq.com
 */
public interface VerifyPhoneDialogContract {

    interface View extends BaseContract.View {
        void onGetVerifyCodeSuccess(PhoneMessageBean bean );

        void onGetVerifyCodeFailure(String failure);

        void onBindLoginSuccess(BindPhoneBean bindPhoneBean);

        void onBindLoginFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void getVerifyCode(Map<String,String> map);

        void bindLogin(Map<String,String> map);

        void onGetVerifyCodeSuccess(PhoneMessageBean bean );

        void onGetVerifyCodeFailure(String failure);

        void onBindLoginSuccess(BindPhoneBean bindPhoneBean);

        void onBindLoginFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void getVerifyCode(Map<String,String> map);

        void bindLogin(Map<String,String> map);
    }
}
