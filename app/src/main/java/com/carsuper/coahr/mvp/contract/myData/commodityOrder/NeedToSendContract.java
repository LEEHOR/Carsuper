package com.carsuper.coahr.mvp.contract.myData.commodityOrder;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CommodityOrderDetailBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/9.
 * Email：hengzwdhengzwd@qq.com
 */
public interface NeedToSendContract {
    interface View extends BaseContract.View {
        void onGetCommodityOrderDetialSuccess(CommodityOrderDetailBean bean);

        void onGetCommodityOrderDetailFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCommodityOrderDetail(Map<String,String> map);

        void onGetCommodityOrderDetialSuccess(CommodityOrderDetailBean bean);

        void onGetCommodityOrderDetailFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void getCommodityOrderDetail(Map<String,String> map);
    }
}
