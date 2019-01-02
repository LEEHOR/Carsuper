package com.carsuper.coahr.mvp.contract.myData.returnorchange;

import android.net.Uri;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.RefundFormBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ApplyReturnChangeContract {


    interface View extends BaseContract.View {
        void  onRefundFormSuccess(RefundFormBean bean);

        void onRefundFormFailure(String failure);
        void onSaveRefundSuccess(ResultBean resultBean);

        void onSaveRefundFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {


        void refundForm(Map<String,String> map );

        void  onRefundFormSuccess(RefundFormBean bean);

        void onRefundFormFailure(String failure);

        void  saveRefund(Map<String, RequestBody> map , List<Uri> uris);

        void onSaveRefundSuccess(ResultBean resultBean);

        void onSaveRefundFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void refundForm(Map<String,String> map );

        void  saveRefund(Map<String, RequestBody> map , List<Uri> uris);

    }
}
