package com.carsuper.coahr.mvp.model.shoppingMall;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.contract.shoppingMall.ShoppingMalContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingMalModel extends BaseModel<ShoppingMalContract.Presenter> implements ShoppingMalContract.Model {
    @Inject
    public ShoppingMalModel() {
        super();
    }

    @Override
    public void getCommodityList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ShoppingMallBean>(getApiservice().getCommodityList(map.get("brand")
                ,map.get("order"),map.get("sort"),map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<ShoppingMallBean>() {
                    @Override
                    public void _onNext(ShoppingMallBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onGetCommodityListSuccess(bean);
                            }else {
                                getPresenter().onGtCommodityListFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void loadMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ShoppingMallBean>(getApiservice().getCommodityList(map.get("brand")
                ,map.get("order"),map.get("sort"),map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<ShoppingMallBean>() {
                    @Override
                    public void _onNext(ShoppingMallBean bean) {
                        if (getPresenter() != null){
                            if (bean.getCode()==0) {
                                getPresenter().loadMoreSuccess(bean);
                            }else {
                                getPresenter().loadMoreFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
