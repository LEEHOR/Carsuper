package com.carsuper.coahr.mvp.contract.maintenance;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CarHorsePowerBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public interface CarHorsePowerContract {

    interface View extends BaseContract.View {

        void onGetCarTypeSuccess(CarHorsePowerBean bean);

        void onGetCarTypeFailure(String failure);

        void onSaveUserCarInfoSuccess(SaveUserCarBean bean);

        void onSaveUserCarInfoFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCarType(Map<String,String> map );

        void onGetCarTypeSuccess(CarHorsePowerBean bean);

        void onGetCarTypeFailure(String failure);


        void saveUserCarInfo(Map<String, String> map);

        void onSaveUserCarInfoSuccess(SaveUserCarBean bean);

        void onSaveUserCarInfoFailure(String failure);

    }

    interface Model extends BaseContract.Model {

        void getCarType(Map<String,String> map );

        void saveUserCarInfo(Map<String, String> map);

    }
}
