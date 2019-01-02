package com.carsuper.coahr.mvp.model.store;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.store.StoreDetailContract;
import com.carsuper.coahr.mvp.model.BaiduLocationHelper;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean;

import java.security.PublicKey;
import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreDetailModel extends BaseModel<StoreDetailContract.Presenter> implements StoreDetailContract.Model {


    @Inject
    BaiduLocationHelper baiduLocationHelper;

    private BaiduLocationHelper.OnLocationCallBack onLocationCallBack = new BaiduLocationHelper.OnLocationCallBack() {
        @Override
        public void onLocationSuccess(BDLocation location) {
            if (getPresenter() != null) {
                getPresenter().onLocationSuccess(location);
                baiduLocationHelper.stopLocation();
            }
        }

        @Override
        public void onLocationFailure(int locType) {
            if (getPresenter() != null) {
                getPresenter().onLocationFailure(locType);
            }
        }
    };
    @Inject
    public StoreDetailModel(){
        super();
    }

    @Override
    public void getStoreDetail(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<StoreDetailBean>(getApiservice().getStoreDetail(map.get("s_id"),map.get("longitude"),map.get("latitude"))))
        .subscribeWith(new SimpleDisposableSubscriber<StoreDetailBean>() {
            @Override
            public void _onNext(StoreDetailBean storeDetailBean) {
                if (getPresenter() != null) {
                    if (storeDetailBean.getCode()==0) {
                        getPresenter().onGetStoreDetailSuccess(storeDetailBean);
                    } else {
                        getPresenter().onGetStoreDetailFailure(storeDetailBean.getMsg());
                    }
                }
            }
        }));
    }

    @Override
    public void startLocation() {
        initlocation();
        baiduLocationHelper.startLocation();
    }


    private void initlocation() {
        baiduLocationHelper.registerLocationCallback(onLocationCallBack);
    }

    @Override
    public void detachPresenter() {
        super.detachPresenter();
        baiduLocationHelper.unRegisterLocationCallback(onLocationCallBack);
    }
}
