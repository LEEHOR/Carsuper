package com.carsuper.coahr.mvp.contract.maintenance;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.StationRecommend;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ConfirmMaintanceOrderContract {
    interface View extends BaseContract.View {
        void onConfirmOrderSuccess(ConfirmOrderBean bean);

        void onConfirmOrderFailure(String failure);

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);

        void onGetStationRecommendSuccess(StationRecommend stationRecommend);

        void onGetStationRecommendFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void confirmMaintanceOrder(Map<String, String> map);

        void onConfirmOrderSuccess(ConfirmOrderBean bean);

        void onConfirmOrderFailure(String failure);

        void startLocation();

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);

        void onGetStationRecommend(Map<String, String> map);

        void onGetStationRecommendSuccess(StationRecommend stationRecommend);

        void onGetStationRecommendFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void confirmMaintanceOrder(Map<String, String> map);

        void startLocation();

        void onGetStationRecommend(Map<String, String> map);
    }

}
