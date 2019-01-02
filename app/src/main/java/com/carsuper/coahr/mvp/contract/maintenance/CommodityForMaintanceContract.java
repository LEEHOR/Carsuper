package com.carsuper.coahr.mvp.contract.maintenance;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.RecommendServiceBean;
import com.carsuper.coahr.mvp.model.bean.ReplaceableCommodityBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface CommodityForMaintanceContract {
    interface View extends BaseContract.View {
        void onGetReplaceableCommoditysSuccess(ReplaceableCommodityBean bean);

        void onGetReplaceableCommoditysFailure(String failure);

        void onLoadMoreSuccess(ReplaceableCommodityBean bean);

        void onLoadMoreFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getReplaceableCommoditys(Map<String, String> map);

        void onGetReplaceableCommoditysSuccess(ReplaceableCommodityBean bean);

        void onGetReplaceableCommoditysFailure(String failure);


        void loadMore(Map<String ,String> map);


        void onLoadMoreSuccess(ReplaceableCommodityBean bean);

        void onLoadMoreFailure(String failure);
    }

    interface Model extends BaseContract.Model {

        void getReplaceableCommoditys(Map<String, String> map);
        void loadMore(Map<String ,String> map);

    }

}
