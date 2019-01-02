package com.carsuper.coahr.mvp.contract.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public interface MNeedToServeContract {

    interface View extends BaseContract.View {
        void onGetMaintanceOrderDetialSuccess(MaintanceOrderDetailBean bean);

        void onGetMaintanceOrderDetailFailure(String failure);

        void onCancelOrderSuccess(ResultBean resultBean);

        void onCancelOrderFailure(String failure);

        void onConfirmSuccess(ResultBean bean);

        void onConfirmFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {

        void getMaintanceOrderDetail(Map<String,String> map);

        void onGetMaintanceOrderDetialSuccess(MaintanceOrderDetailBean bean);

        void onGetMaintanceOrderDetailFailure(String failure);



        void  cancelMaintanceOrder(Map<String,String> map);

        void onCancelOrderSuccess(ResultBean resultBean);

        void onCancelOrderFailure(String failure);

        void confirmServiceFinish(Map<String,String> map );

        void onConfirmSuccess(ResultBean bean);

        void onConfirmFailure(String failure);



    }

    interface Model extends BaseContract.Model {
        void getMaintanceOrderDetail(Map<String,String> map);

        void  cancelMaintanceOrder(Map<String,String> map);

        void confirmServiceFinish(Map<String,String> map );

    }
}
