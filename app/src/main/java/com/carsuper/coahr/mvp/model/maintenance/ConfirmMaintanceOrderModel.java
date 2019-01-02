package com.carsuper.coahr.mvp.model.maintenance;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.maintenance.ConfirmMaintanceOrderContract;
import com.carsuper.coahr.mvp.model.BaiduLocationHelper;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.StationRecommend;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ConfirmMaintanceOrderModel extends BaseModel<ConfirmMaintanceOrderContract.Presenter> implements ConfirmMaintanceOrderContract.Model {


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
    public ConfirmMaintanceOrderModel(){
        super();
    }

    @Override
    public void confirmMaintanceOrder(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ConfirmOrderBean>(getApiservice().confirmMaintanceOrder(map.get("token"),map.get("c_id"),map.get("num"),map.get("car_id"),map.get("province"),map.get("city"),map.get("address")
        ,map.get("longitude"),map.get("latitude"),map.get("phone"),map.get("data"),map.get("time"),map.get("total"),map.get("payment"),map.get("filter_c_id"),map.get("filter_num"),map.get("s_id"),map.get("coupon_id")))).subscribeWith(new SimpleDisposableSubscriber<ConfirmOrderBean>() {
            @Override
            public void _onNext(ConfirmOrderBean bean) {
                if (getPresenter() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onConfirmOrderSuccess(bean);
                    }else {
                        getPresenter().onConfirmOrderFailure(bean.getMsg());
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

    /**
     * 获取门店信息
     * @param map
     */
    @Override
    public void onGetStationRecommend(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<StationRecommend>(getApiservice().get_station_recommend(map.get("token"),map.get("longitude"),map.get("latitude")))).subscribeWith(new SimpleDisposableSubscriber<StationRecommend>() {
            @Override
            public void _onNext(StationRecommend bean) {
                if (getPresenter() != null) {
                    if (bean.getCode()==0) {
                        getPresenter().onGetStationRecommendSuccess(bean);
                    }else {
                        getPresenter().onGetStationRecommendFailure(bean.getMsg());
                    }
                }
            }
        }));
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
