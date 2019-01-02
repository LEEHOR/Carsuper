package com.carsuper.coahr.mvp.contract.myData.setting;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */

public interface SettingContract {
    interface View extends BaseContract.View {

        void onLoginOutSuccess(ResultBean bean);

        void onLoginOutFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void loginOut(Map<String, String> map);

        void onLoginOutSuccess(ResultBean bean);

        void onLoginOutFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void loginOut(Map<String, String> map);
    }

}
