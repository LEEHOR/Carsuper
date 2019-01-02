package com.carsuper.coahr.mvp.model.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.NameContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.SaveUserInfoBean;

import java.util.Map;
import java.util.jar.Attributes;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class NameModel extends BaseModel<NameContract.Presenter> implements NameContract.Model {
    @Inject
    public NameModel(){
        super();
    }

    @Override
    public void updateName(Map<String, RequestBody> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<SaveUserInfoBean>(getApiservice().saveUserInfo(map,null)))
        .subscribeWith(new SimpleDisposableSubscriber<SaveUserInfoBean>() {
            @Override
            public void _onNext(SaveUserInfoBean bean) {
                if (getApiservice() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onUpdateSuccess(bean);
                    }else {
                        getPresenter().onUpdateFailure(bean.getMsg());
                    }
                }
            }
        }));
    }
}
