package com.carsuper.coahr.mvp.contract.myData.maintanceOrder;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderBean;
import com.carsuper.coahr.mvp.model.bean.MaintanceOrderBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.ServiceOrderCopyBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 */
public interface MaintanceOrderContract {

    interface View extends BaseContract.View {

        void onGetMaintanceOrderListSuccess(MaintanceOrderBean bean );

        void onGetMaintanceOrderListFailure(String failure);
        void loadMoreSuccess(MaintanceOrderBean bean);

        void loadMoreFailure(String  failure);

        void onConfirmSuccess(ResultBean bean);

        void onConfirmFailure(String failure);

        void onQuickPaySuccess(QuickPayBean bean );

        void onQuickPayFailure(String failure);

        void onCopySuccess(ServiceOrderCopyBean bean);

        void onCopyFailure(String failure);

        void onCancelOrderSuccess(ResultBean resultBean);

        void onCancelOrderFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {


        void getMaintanceOrderList(Map<String,String> map );

        void onGetMaintanceOrderListSuccess(MaintanceOrderBean bean );

        void onGetMaintanceOrderListFailure(String failure);

        void loadMore(Map<String,String> map);

        void loadMoreSuccess(MaintanceOrderBean bean);

        void loadMoreFailure(String  failure);

        void confirmServiceFinish(Map<String,String> map );

        void onConfirmSuccess(ResultBean bean);

        void onConfirmFailure(String failure);


        void quickPay(Map<String,String> map );

        void onQuickPaySuccess(QuickPayBean bean );

        void onQuickPayFailure(String failure);



        void ServiceOrderCopy(Map<String,String> map);

        void onCopySuccess(ServiceOrderCopyBean bean);

        void onCopyFailure(String failure);


        void onCancelOrderSuccess(ResultBean resultBean);

        void onCancelOrderFailure(String failure);

        void onCancelOder(Map<String,String> map);

    }

    interface Model extends BaseContract.Model {
        void getMaintanceOrderList(Map<String,String> map );

        void loadMore(Map<String,String> map);

        void confirmServiceFinish(Map<String,String> map );

        void quickPay(Map<String,String> map );

        void ServiceOrderCopy(Map<String,String> map);

        void onCancelOder(Map<String,String> map);

    }
}
