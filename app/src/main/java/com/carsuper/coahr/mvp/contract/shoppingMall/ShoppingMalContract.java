package com.carsuper.coahr.mvp.contract.shoppingMall;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ShoppingMalContract {

    interface View extends BaseContract.View {
        void onGetCommodityListSuccess(ShoppingMallBean bean);
        void onGtCommodityListFailure(String failure);
        void loadMoreSuccess(ShoppingMallBean bean);
        void loadMoreFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void  getCommodityList(Map<String,String> map);
        void onGetCommodityListSuccess(ShoppingMallBean bean);
        void onGtCommodityListFailure(String failure);

        void loadMore(Map<String,String> map);
        void loadMoreSuccess(ShoppingMallBean bean);
        void loadMoreFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void  getCommodityList(Map<String,String> map);
        void loadMore(Map<String,String> map);
    }
}
