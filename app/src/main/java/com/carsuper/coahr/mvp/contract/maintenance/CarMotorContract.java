package com.carsuper.coahr.mvp.contract.maintenance;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CarMotorBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public interface CarMotorContract {
    interface View extends BaseContract.View {
        void onGetCarDisplaceMentSuccess(CarMotorBean bean);

        void onGetCarDisplaceMentFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCarDisplaceMent(Map<String,String> map);
        void onGetCarDisplaceMentSuccess(CarMotorBean bean);

        void onGetCarDisplaceMentFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void getCarDisplaceMent(Map<String,String> map);



    }


}
