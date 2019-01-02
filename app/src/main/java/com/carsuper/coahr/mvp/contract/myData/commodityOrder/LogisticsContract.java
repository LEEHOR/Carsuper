package com.carsuper.coahr.mvp.contract.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.LogisticsBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/23.
 * Email：hengzwdhengzwd@qq.com
 */
public interface LogisticsContract {

    interface View extends BaseContract.View {

        void  onGetLogisticsSuccess(LogisticsBean bean);
        void onGetLogisticsFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {


        void getLogistics(Map<String,String> map);

        void  onGetLogisticsSuccess(LogisticsBean bean);

        void onGetLogisticsFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void getLogistics(Map<String,String> map);

    }
}
