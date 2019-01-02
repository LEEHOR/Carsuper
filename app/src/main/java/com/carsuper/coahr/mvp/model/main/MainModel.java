package com.carsuper.coahr.mvp.model.main;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.contract.main.MainContract;
import com.carsuper.coahr.mvp.model.ApiService;
import com.carsuper.coahr.mvp.model.BaiduLocationHelper;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.MainBean;
import com.carsuper.coahr.mvp.presenter.main.MainPresenter;
import com.socks.library.KLog;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
public class MainModel extends BaseModel<MainContract.Presenter> implements MainContract.Model {




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
    public MainModel() {
        super();
    }



    @Override
    public void requestMain(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MainBean>(getApiservice().getmain(map.get("page"))))
        .subscribeWith(new SimpleDisposableSubscriber<MainBean>() {
            @Override
            public void _onNext(MainBean mainBean) {
                if (getPresenter() != null) {
                    if (mainBean.getCode()==0) {
                        getPresenter().onRequestMainSuccess(mainBean);
                    }else {
                        getPresenter().onRequestMainFailure(mainBean.getMsg());
                    }
                }
            }
        }));
    }

    @Override
    public void changeSomeFitting(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MainBean>(getApiservice().getmain(map.get("page"))))
            .subscribeWith(new SimpleDisposableSubscriber<MainBean>() {
                @Override
                public void _onNext(MainBean mainBean) {
                    if (getPresenter() != null) {
                        if (mainBean.getCode()==0) {
                            getPresenter().onChangeSuccess(mainBean);
                        }else {
                            getPresenter().onChangeFailure(mainBean.getMsg());
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
