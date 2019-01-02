package com.carsuper.coahr.mvp.contract.main;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.MainBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */
public interface MainContract {


    interface View extends BaseContract.View {

        void onRequestMainSuccess(MainBean mainBean);

        void onRequestMainFailure(String failure);

        void onChangeSuccess(MainBean mainBean);

        void  onChangeFailure(String failure);
        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);

    }

    interface Presenter extends BaseContract.Presenter {

        void requestMain(Map<String,String> map);

        void changeSomeFitting(Map<String,String> map);

        void onRequestMainSuccess(MainBean mainBean);

        void onRequestMainFailure(String failure);

        void onChangeSuccess(MainBean mainBean);

        void  onChangeFailure(String failure);


        void startLocation();

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);
    }

    interface Model extends BaseContract.Model {
        void requestMain(Map<String,String> map);

        void changeSomeFitting(Map<String,String> map);

        void startLocation();

    }
}
