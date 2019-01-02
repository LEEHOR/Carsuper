package com.carsuper.coahr.mvp.model.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.SettingContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class SettingModel extends BaseModel<SettingContract.Presenter> implements SettingContract.Model {
    @Inject
    public SettingModel(){
        super();
    }

    @Override
    public void loginOut(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiservice().loginOut(map.get("token"))))
            .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                @Override
                public void _onNext(ResultBean bean) {
                    if (getPresenter() != null) {
                        if (bean.getCode() == 0) {
                            getPresenter().onLoginOutSuccess(bean);
                        }else {
                            getPresenter().onLoginOutFailure(bean.getJdata().getJmsg());
                        }
                    }
                }
            }));
    }
}
