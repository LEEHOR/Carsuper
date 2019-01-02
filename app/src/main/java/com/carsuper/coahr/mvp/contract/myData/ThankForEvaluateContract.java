package com.carsuper.coahr.mvp.contract.myData;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.TrankForEvaluateBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/27.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ThankForEvaluateContract {

    interface View extends BaseContract.View {
        void onGetCommodityListSuccess(TrankForEvaluateBean bean);

        void onGetCommodityListFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {

        void  getCommodityList(Map<String,String> map);

        void onGetCommodityListSuccess(TrankForEvaluateBean bean);

        void onGetCommodityListFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void  getCommodityList(Map<String,String> map);

    }

}
