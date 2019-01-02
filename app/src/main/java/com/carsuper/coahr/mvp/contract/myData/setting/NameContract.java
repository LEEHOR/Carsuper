package com.carsuper.coahr.mvp.contract.myData.setting;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.SaveUserInfoBean;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface NameContract {
    interface View extends BaseContract.View {
        void  onUpdateSuccess(SaveUserInfoBean bean);

        void onUpdateFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void  updateName(Map<String, RequestBody> map);

        void  onUpdateSuccess(SaveUserInfoBean bean);

        void onUpdateFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void  updateName(Map<String, RequestBody> map);
    }

}
