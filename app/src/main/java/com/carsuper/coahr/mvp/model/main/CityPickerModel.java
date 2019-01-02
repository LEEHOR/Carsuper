package com.carsuper.coahr.mvp.model.main;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.main.CityPickerContract;
import com.carsuper.coahr.mvp.model.BaiduLocationHelper;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Author： hengzwd on 2018/7/18.
 * Email：hengzwdhengzwd@qq.com
 */
public class CityPickerModel extends BaseModel<CityPickerContract.Presenter> implements CityPickerContract.Model {


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
    public CityPickerModel() {
        super();
    }

    @Override
    public void getCityInfo() {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CityInfoBean>(getApiservice().getCity(Constants.token)))
                .subscribeWith(new SimpleDisposableSubscriber<CityInfoBean>() {
                    @Override
                    public void _onNext(CityInfoBean s) {
                        if (getPresenter() != null) {
                            if (s.getCode()==0) {
                                getPresenter().getCitySuccess(s);
                            } else {
                                getPresenter().getCityFailure(s.getMsg());
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
