package com.carsuper.coahr.mvp.contract.store;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean;
import com.carsuper.coahr.mvp.model.bean.StoreBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface StoreContract {
    interface View extends BaseContract.View {
        void onGetStoreListSucess(StoreBean storeBean);
        void onGetStoreListFailure(String failure);
        void loadMoreSuccess(StoreBean bean);
        void loadMoreFailure(String failure);
        void getCitySuccess(CityInfoBean cityInfoBean);
        void  getCityFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void getStoreList(Map<String,String> map);
        void onGetStoreListSucess(StoreBean storeBean);
        void onGetStoreListFailure(String failure);

        void loadMore(Map<String,String> map);
        void loadMoreSuccess(StoreBean bean);
        void loadMoreFailure(String failure);

        void getCityInfo();
        void getCitySuccess(CityInfoBean cityInfoBean);
        void  getCityFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void getStoreList(Map<String,String> map);
        void loadMore(Map<String,String> map);

        void getCityInfo();

    }


}
