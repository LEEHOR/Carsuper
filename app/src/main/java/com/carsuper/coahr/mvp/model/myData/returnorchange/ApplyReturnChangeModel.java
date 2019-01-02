package com.carsuper.coahr.mvp.model.myData.returnorchange;

import android.arch.core.internal.FastSafeIterableMap;
import android.net.Uri;

import com.carsuper.coahr.mvp.contract.myData.returnorchange.ApplyReturnChangeContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.RefundFormBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.UriUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/8/28.
 * Email：hengzwdhengzwd@qq.com
 */
public class ApplyReturnChangeModel extends BaseModel<ApplyReturnChangeContract.Presenter> implements ApplyReturnChangeContract.Model {


    @Inject
    public ApplyReturnChangeModel(){
        super();
    }
    @Override
    public void refundForm(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<RefundFormBean>(getApiservice().refundForm(map.get("token"),map.get("order_id"),map.get("refund_type"))))
        .subscribeWith(new SimpleDisposableSubscriber<RefundFormBean>() {
            @Override
            public void _onNext(RefundFormBean bean) {
                if (getPresenter() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onRefundFormSuccess(bean);
                    }else {
                        getPresenter().onRefundFormFailure(bean.getMsg());
                    }
                }
            }
        }));
    }

    @Override
    public void saveRefund(Map<String, RequestBody> map, List<Uri> uris) {

        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < uris.size(); i++) {
            String path = UriUtils.getFilePathFromContentUri(uris.get(i), BaseApplication.mContext.getContentResolver());
            File file = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("des_pic" + (i+1), file.getName(), requestFile);
            parts.add(part);
        }

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().saveRefund(map, parts)))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onSaveRefundSuccess(bean);
                            }else {
                                getPresenter().onSaveRefundFailure(bean.getMsg());
                            }
                        }
                    }
                }));


    }
}
