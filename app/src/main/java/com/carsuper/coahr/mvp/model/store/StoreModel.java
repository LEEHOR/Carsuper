package com.carsuper.coahr.mvp.model.store;

import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.store.StoreContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean;
import com.carsuper.coahr.mvp.model.bean.StoreBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreModel extends BaseModel<StoreContract.Presenter> implements StoreContract.Model {
    @Inject
    public StoreModel() {
        super();
    }

    @Override
    public void getStoreList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<StoreBean>(getApiservice().getStoreList(map.get("order"), map.get("page"), map.get("length"), map.get("city"), map.get("longitude"), map.get("latitude"))))
                .subscribeWith(new SimpleDisposableSubscriber<StoreBean>() {
                    @Override
                    public void _onNext(StoreBean storeBean) {
                        if (getPresenter() != null) {
                            if (storeBean.getCode()==0) {
                                getPresenter().onGetStoreListSucess(storeBean);
                            }else {
                                getPresenter().onGetStoreListFailure(storeBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void loadMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<StoreBean>(getApiservice().getStoreList(map.get("order"), map.get("page"), map.get("length"), map.get("city"), map.get("longitude"), map.get("latitude"))))
                .subscribeWith(new SimpleDisposableSubscriber<StoreBean>() {
                    @Override
                    public void _onNext(StoreBean storeBean) {
                        if (getPresenter() != null) {
                            if (storeBean.getCode()==0) {
                                getPresenter().loadMoreSuccess(storeBean);
                            }else {
                                getPresenter().loadMoreFailure(storeBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getCityInfo() {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CityInfoBean>(getApiservice().getCity(Constants.token)))
                .subscribeWith(new SimpleDisposableSubscriber<CityInfoBean>() {
                    @Override
                    public void _onNext(CityInfoBean s) {
                        if (getPresenter() != null) {
                            if (s.getCode() == 0) {
                                getPresenter().getCitySuccess(s);
                            } else {
                                getPresenter().getCityFailure(s.getMsg());
                            }
                        }

                    }
                }));
    }
}
