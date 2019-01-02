package com.carsuper.coahr.mvp.contract.main;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean;

/**
 * Author： hengzwd on 2018/7/18.
 * Email：hengzwdhengzwd@qq.com
 */
public interface CityPickerContract extends BaseContract {

    interface View extends BaseContract.View {
        void getCitySuccess(CityInfoBean cityInfoBean);
        void  getCityFailure(String failure);

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCityInfo();
        void getCitySuccess(CityInfoBean cityInfoBean);
        void  getCityFailure(String failure);

        void startLocation();

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);

    }

    interface Model extends BaseContract.Model {
        void getCityInfo();

        void startLocation();

    }

}
