package com.carsuper.coahr.mvp.contract.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderBean;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public interface MNeedToEvaluateContract {

    interface View extends BaseContract.View {
        void onGetMaintanceOrderDetialSuccess(MaintanceOrderDetailBean bean);

        void onGetMaintanceOrderDetailFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getMaintanceOrderDetail(Map<String,String> map);

        void onGetMaintanceOrderDetialSuccess(MaintanceOrderDetailBean bean);

        void onGetMaintanceOrderDetailFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void getMaintanceOrderDetail(Map<String,String> map);
    }
}
