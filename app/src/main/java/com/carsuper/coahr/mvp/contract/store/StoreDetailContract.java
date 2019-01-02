package com.carsuper.coahr.mvp.contract.store;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface StoreDetailContract {
    interface View extends BaseContract.View {
        void onGetStoreDetailSuccess(StoreDetailBean storeDetailBean);

        void onGetStoreDetailFailure(String failure);

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);
    }

    interface Presenter extends BaseContract.Presenter {


        void getStoreDetail(Map<String, String> map);

        void onGetStoreDetailSuccess(StoreDetailBean storeDetailBean);

        void onGetStoreDetailFailure(String failure);

        void startLocation();

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);
    }

    interface Model extends BaseContract.Model {
        void getStoreDetail(Map<String, String> map);

        void startLocation();
    }


}
