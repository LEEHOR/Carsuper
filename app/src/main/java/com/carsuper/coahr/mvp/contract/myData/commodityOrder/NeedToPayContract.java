package com.carsuper.coahr.mvp.contract.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;
import com.carsuper.coahr.mvp.model.bean.QuickPayBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/9.
 * Email：hengzwdhengzwd@qq.com
 */
public interface NeedToPayContract {

    interface View extends BaseContract.View {
        void onGetCommodityOrderDetialSuccess(CommodityOrderDetailBean bean);

        void onGetCommodityOrderDetailFailure(String failure);

        void onCancelOrderSuccess(ResultBean resultBean);

        void onCancelOrderFailure(String failure);

        void onQuickPaySuccess(QuickPayBean bean );

        void onQuickPayFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCommodityOrderDetail(Map<String,String> map);

        void onGetCommodityOrderDetialSuccess(CommodityOrderDetailBean bean);

        void onGetCommodityOrderDetailFailure(String failure);

        void  cancelCommodityOrder(Map<String,String> map);

        void onCancelOrderSuccess(ResultBean resultBean);

        void onCancelOrderFailure(String failure);


        void quickPay(Map<String,String> map );

        void onQuickPaySuccess(QuickPayBean bean );

        void onQuickPayFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void getCommodityOrderDetail(Map<String,String> map);

        void  cancelCommodityOrder(Map<String,String> map);

        void quickPay(Map<String,String> map );


    }
}
