package com.carsuper.coahr.mvp.contract.myData.setting;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.LoginBean;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.view.MainActivity;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/17.
 * Email：hengzwdhengzwd@qq.com
 */
public interface UserInfoContract {

    interface View extends BaseContract.View {
        void onGetInfoSuccess(PersonInfoBean personInfoBean);
        void onGetInfoFailure(String throwle);

        void onUnsetWxSuccess(ResultBean resultBean);

        void onUnsetWxFailure(String failure);

        void  onBindWxSuccess(ResultBean resultBean);

        void onBindWxFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getUserInfo(Map<String,String> map);
        void onGetInfoSuccess(PersonInfoBean personInfoBean);
        void onGetInfoFailure(String throwle);

        void  unSetWx(Map<String,String> map );

        void onUnsetWxSuccess(ResultBean resultBean);

        void onUnsetWxFailure(String failure);

        void  bindWx(Map<String,String> map );

        void  onBindWxSuccess(ResultBean resultBean);

        void onBindWxFailure(String failure);
    }

    interface Model extends BaseContract.Model {

        void getUserInfo(Map<String,String> map);

        void  unSetWx(Map<String,String> map );
        void  bindWx(Map<String,String> map );

    }
}
