package com.carsuper.coahr.mvp.contract.shoppingMall;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.PaymentSuccessBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface PaymentSuccessContract {
    interface View extends BaseContract.View {
        void onGetRecommendSuccess(PaymentSuccessBean bean);

        void onGetRecommendFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getEndingRecommend(Map<String, String> map);

        void onGetRecommendSuccess(PaymentSuccessBean bean);

        void onGetRecommendFailure(String failure);

    }

    interface Model extends BaseContract.Model {

        void getEndingRecommend(Map<String, String> map);

    }
}
