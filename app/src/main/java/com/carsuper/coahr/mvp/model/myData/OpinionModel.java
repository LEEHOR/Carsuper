package com.carsuper.coahr.mvp.model.myData;

import android.net.Uri;

import com.carsuper.coahr.mvp.contract.myData.OpinionContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.utils.UriUtils;
import com.socks.library.KLog;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.transform.Result;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class OpinionModel extends BaseModel<OpinionContract.Presenter> implements OpinionContract.Model {
    @Inject
    public OpinionModel() {
        super();
    }

    @Override
    public void saveSuggest(Map<String, String> map, List<Uri> uris) {
        Map<String,RequestBody> map1 = new HashMap<>();
        map1.put("token", RequestBody.create(null,map.get("token")));
        map1.put("suggest", RequestBody.create(null,map.get("suggest")));

        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < uris.size(); i++) {
           String path = UriUtils.getFilePathFromContentUri(uris.get(i), BaseApplication.mContext.getContentResolver());
            File file = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("pic" + (i+1), file.getName(), requestFile);
            parts.add(part);
        }

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().saveSuggest(map1, parts)))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode() ==0) {
                                getPresenter().onSaveSuccess(bean);
                            }else {
                                getPresenter().onSaveFailure(bean.getMsg());
                            }
                        }
                    }
                }));

    }
}
