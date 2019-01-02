package com.carsuper.coahr.mvp.contract.maintenance;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CarSerialBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 */
public interface CarSerialContract {

    interface View extends BaseContract.View {
        void onGetCarSerialSuccess(CarSerialBean bean);

        void onGetCarSerialFailure(String  failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCarSerial(Map<String,String> map);

        void onGetCarSerialSuccess(CarSerialBean bean);

        void onGetCarSerialFailure(String  failure);

    }

    interface Model extends BaseContract.Model {

        void getCarSerial(Map<String,String> map);

    }
}
