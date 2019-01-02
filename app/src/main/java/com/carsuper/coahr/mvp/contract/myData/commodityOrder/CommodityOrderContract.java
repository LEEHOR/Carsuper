package com.carsuper.coahr.mvp.contract.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface CommodityOrderContract {
    interface View extends BaseContract.View {

        void onGetCommodityOrderListSuccess(CommodityOrderBean bean);

        void onGetCommodityOrderListFailure(String failure);

        void loadMoreSuccess(CommodityOrderBean bean);

        void loadMoreFailure(String failure);


        void onReminderSuccess(ResultBean resultBean);

        void onReminderFailure(String failure);


        void onEnsureRecieveSuccess(ResultBean resultBean);

        void onEnsureRecieveFailure(String failure);

        void onQuickPaySuccess(QuickPayBean bean );

        void onQuickPayFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {


        void getCommodityOrderList(Map<String, String> map);

        void onGetCommodityOrderListSuccess(CommodityOrderBean bean);

        void onGetCommodityOrderListFailure(String failure);

        void loadMore(Map<String, String> map);

        void loadMoreSuccess(CommodityOrderBean bean);

        void loadMoreFailure(String failure);

        void ensureRecieve(Map<String, String> map);

        void onEnsureRecieveSuccess(ResultBean resultBean);

        void onEnsureRecieveFailure(String failure);


        void reminderOrder(Map<String, String> map);

        void onReminderSuccess(ResultBean resultBean);

        void onReminderFailure(String failure);



        void quickPay(Map<String,String> map );

        void onQuickPaySuccess(QuickPayBean bean );

        void onQuickPayFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void getCommodityOrderList(Map<String, String> map);

        void loadMore(Map<String, String> map);

        void ensureRecieve(Map<String, String> map);

        void reminderOrder(Map<String, String> map);

        void quickPay(Map<String,String> map );


    }

}
