package com.carsuper.coahr.mvp.contract.shoppingMall;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ConfirmCommodityOrderBean;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.SaveCommodityOrderBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ConfirmOrderContract {
    interface View extends BaseContract.View {

        void onConfirmOrderSuccess(ConfirmCommodityOrderBean bean);

        void onConfirmOrderFailure(String failure);

        void onSaveCommodityOrderSuccess(ConfirmOrderBean bean);

        void onSaveCommodityOrderFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {


        void confirmCommodityOrder(Map<String, String> map);

        void onConfirmOrderSuccess(ConfirmCommodityOrderBean bean);

        void onConfirmOrderFailure(String failure);

        void saveCommodityOrder(Map<String, String> map);

        void onSaveCommodityOrderSuccess(ConfirmOrderBean bean);

        void onSaveCommodityOrderFailure(String failure);
    }

    interface Model extends BaseContract.Model {

        void confirmCommodityOrder(Map<String, String> map);

        void saveCommodityOrder(Map<String, String> map);
    }

}
