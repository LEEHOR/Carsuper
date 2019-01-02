package com.carsuper.coahr.mvp.contract;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.base.BaseContract;


/**
 * Author： hengzwd on 2018/7/27.
 * Email：hengzwdhengzwd@qq.com
 */
public interface MainActivityContract {

    interface View extends BaseContract.View {
        void locationFailure(int failureCode);

        void locationSuccess(BDLocation bdLocation);
    }

    interface Presenter extends BaseContract.Presenter {

        void  startLocation();

        void locationSuccess(BDLocation bdLocation);

        void locationFailure(int failureCode);


    }

    interface Model extends BaseContract.Model {

        void  startLocation();

    }
}
