package com.carsuper.coahr.mvp.contract.myData.integralCenter;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.IntegralCenterBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/24.
 * Email：hengzwdhengzwd@qq.com
 */
public interface IntegralCenterContract {

    interface View extends BaseContract.View {
        void onGetPointListSuccess(IntegralCenterBean bean);

        void onGetPointListFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {


        void getPointList(Map<String, String> map);

        void onGetPointListSuccess(IntegralCenterBean bean);

        void onGetPointListFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void getPointList(Map<String, String> map);

    }
}
