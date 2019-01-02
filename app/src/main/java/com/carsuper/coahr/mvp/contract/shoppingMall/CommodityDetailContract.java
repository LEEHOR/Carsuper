package com.carsuper.coahr.mvp.contract.shoppingMall;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface CommodityDetailContract {
    interface View extends BaseContract.View {
        void onGetCommodityDetailSuccess(CommodityDetailBean bean);
        void onGetCommodityDetailFailure(String failure);

        void onAddSuccess(ResultBean bean);

        void onAddFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCommodityDetail(Map<String, String> map);

        void onGetCommodityDetailSuccess(CommodityDetailBean bean);

        void onGetCommodityDetailFailure(String failure);


        void addToShoppingCart(Map<String,String> map );

        void onAddSuccess(ResultBean bean);

        void onAddFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void getCommodityDetail(Map<String, String> map);

        void addToShoppingCart(Map<String,String> map );
    }
}
