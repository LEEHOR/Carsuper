package com.carsuper.coahr.mvp.model;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.MainActivityContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.socks.library.KLog;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/7/27.
 * Email：hengzwdhengzwd@qq.com
 */
public class MainActivityModel extends BaseModel<MainActivityContract.Presenter> implements MainActivityContract.Model {



    @Inject
    BaiduLocationHelper baiduLocationHelper;

    // 百度地图获取经纬度
    private LocationClient locationClient ;
    private static final int UPDATE_TIME = 3000;
    private static int LOCATION_COUTNS = 0;

    private BaiduLocationHelper.OnLocationCallBack onLocationCallBack = new BaiduLocationHelper.OnLocationCallBack() {
        @Override
        public void onLocationSuccess(BDLocation location) {
            if (getPresenter() != null) {
                getPresenter().locationSuccess(location);
                baiduLocationHelper.stopLocation();
            }
        }

        @Override
        public void onLocationFailure(int locType) {
            if (getPresenter() != null) {
                getPresenter().locationFailure(locType);
            }
        }
    };

    @Inject
    public MainActivityModel(){
        super();

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
