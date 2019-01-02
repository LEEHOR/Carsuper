package com.carsuper.coahr.mvp.model.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.CommodityForMaintanceContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ReplaceableCommodityBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityForMaintanceModel extends BaseModel<CommodityForMaintanceContract.Presenter> implements CommodityForMaintanceContract.Model {
    @Inject
    public CommodityForMaintanceModel(){
        super();
    }

    @Override
    public void getReplaceableCommoditys(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ReplaceableCommodityBean>(getApiservice().getReplayceCommodity(map.get("service"),map.get("cs_id"),map.get("page"),map.get("length"))))
        .subscribeWith(new SimpleDisposableSubscriber<ReplaceableCommodityBean>() {
            @Override
            public void _onNext(ReplaceableCommodityBean bean) {
                if (getPresenter() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onGetReplaceableCommoditysSuccess(bean);
                    }else {
                        getPresenter().onGetReplaceableCommoditysFailure(bean.getMsg());
                    }
                }
            }
        }));
    }

    @Override
    public void loadMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ReplaceableCommodityBean>(getApiservice().getReplayceCommodity(map.get("service"),map.get("cs_id"),map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<ReplaceableCommodityBean>() {
                    @Override
                    public void _onNext(ReplaceableCommodityBean bean) {
                        if (getPresenter() != null) {
                            getPresenter().onLoadMoreSuccess(bean);
                        }else {
                            getPresenter().onLoadMoreFailure(bean.getMsg());
                        }
                    }
                }));
    }
}
