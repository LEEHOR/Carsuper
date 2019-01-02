package com.carsuper.coahr.mvp.presenter.store;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.mvp.contract.store.StoreDetailContract;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean;
import com.carsuper.coahr.mvp.model.store.StoreDetailModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.store.StoreDetailFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreDetailPresenter extends BasePresenter<StoreDetailContract.View,StoreDetailContract.Model> implements StoreDetailContract.Presenter {

    @Inject
    public StoreDetailPresenter(StoreDetailFragment mview, StoreDetailModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getStoreDetail(Map<String, String> map) {
        if (mModle != null) {
            mModle.getStoreDetail(map);
        }
    }

    @Override
    public void onGetStoreDetailSuccess(StoreDetailBean storeDetailBean) {
        if (getView() != null) {
            getView().onGetStoreDetailSuccess(storeDetailBean);
        }
    }

    @Override
    public void onGetStoreDetailFailure(String failure) {
        if (getView() != null) {
            getView().onGetStoreDetailFailure(failure);
        }
    }

    @Override
    public void startLocation() {
        if (mModle != null) {
            mModle.startLocation();
        }
    }

    @Override
    public void onLocationSuccess(BDLocation location) {
        if (getView() != null) {
            getView().onLocationSuccess(location);
        }
    }

    @Override
    public void onLocationFailure(int failure) {
            getView().onLocationFailure(failure);
    }
}
