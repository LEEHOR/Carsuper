package com.carsuper.coahr.mvp.contract.maintenance;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.RecommendServiceBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface OrderToMaintenanceContract {
    interface View extends BaseContract.View {
        void onRecommendServiceSuccess(RecommendServiceBean bean);

        void onRecommendServiceFailure(String failure);

        void onSaveUserCarDistanceSuccess(ResultBean bean );

        void onSaveUserCarDistanceFailure(String failure);

        void onGetFilterSuccess(RecommendServiceBean bean);

        void onGetFilterFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {

        void recommendService(Map<String, String> map);

        void onRecommendServiceSuccess(RecommendServiceBean bean);

        void onRecommendServiceFailure(String failure);

        void  saveUserCarDistance(Map<String,String> map );

        void onSaveUserCarDistanceSuccess(ResultBean bean );

        void onSaveUserCarDistanceFailure(String failure);

        void onGetFilter(Map<String, String> map);

        void onGetFilterSuccess(RecommendServiceBean bean);

        void onGetFilterFailure(String failure);


    }

    interface Model extends BaseContract.Model {

        void recommendService(Map<String, String> map);

        void  saveUserCarDistance(Map<String,String> map );

        void onGetFilter(Map<String, String> map);

    }
}
