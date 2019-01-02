package com.carsuper.coahr.mvp.model.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.ClipHeaderImgContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserInfoBean;
import com.github.promeg.pinyinhelper.Pinyin;

import java.util.Map;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ClipHeaderImgModel extends BaseModel<ClipHeaderImgContract.Presenter> implements ClipHeaderImgContract.Model {
    @Inject
    public ClipHeaderImgModel(){
        super();
    }

    @Override
    public void updateHeadPic(Map<String, RequestBody> map, MultipartBody.Part  part) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<SaveUserInfoBean>(getApiservice().saveUserInfo(map,part)))
        .subscribeWith(new SimpleDisposableSubscriber<SaveUserInfoBean>() {
            @Override
            public void _onNext(SaveUserInfoBean bean) {
                if (getApiservice() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onUpdateSuccess(bean);
                    }else {
                        getPresenter().onUpdateFailue(bean.getMsg());
                    }
                }
            }
        }));
    }
}
