package com.carsuper.coahr.mvp.contract.myData.returnorchange;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.RefundApplyBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ReturnChangeSelectContract {

    interface View extends BaseContract.View {
        void onRefundApplySuccess(RefundApplyBean bean);

        void onRefundApplyFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void refundApply(Map<String, String> map);

        void onRefundApplySuccess(RefundApplyBean bean);

        void onRefundApplyFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void refundApply(Map<String, String> map);

    }

}
