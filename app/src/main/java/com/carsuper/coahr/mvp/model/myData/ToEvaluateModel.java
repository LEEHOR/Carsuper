package com.carsuper.coahr.mvp.model.myData;

import android.arch.core.internal.FastSafeIterableMap;
import android.net.Uri;

import com.carsuper.coahr.mvp.contract.myData.ToEvaluateContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveCommentBean;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.UriUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.transform.Result;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ToEvaluateModel extends BaseModel<ToEvaluateContract.Presenter> implements ToEvaluateContract.Model {
    @Inject
    public ToEvaluateModel(){
        super();
    }

    @Override
    public void saveCommodityComment(Map<String, RequestBody> map, List<Uri> uris) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < uris.size(); i++) {
            String path = UriUtils.getFilePathFromContentUri(uris.get(i), BaseApplication.mContext.getContentResolver());
            File file = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("pic" + (i+1), file.getName(), requestFile);
            parts.add(part);
        }

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<SaveCommentBean>(getApiservice().saveComment(map,parts)))
        .subscribeWith(new SimpleDisposableSubscriber<SaveCommentBean>() {
            @Override
            public void _onNext(SaveCommentBean resultBean) {
                if (getPresenter() != null) {
                    if (resultBean.getCode()==0) {
                        getPresenter().onSaveCommentSuccess(resultBean);
                    }else {
                        getPresenter().onSaveCommentFailure(resultBean.getMsg());
                    }
                }
            }
        }));
    }
}
