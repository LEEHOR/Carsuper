package com.carsuper.coahr.mvp.presenter.store;

import com.carsuper.coahr.mvp.contract.store.StoreContract;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean;
import com.carsuper.coahr.mvp.model.bean.StoreBean;
import com.carsuper.coahr.mvp.model.store.StoreModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.store.StoreFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
public class StorePresenter extends BasePresenter<StoreContract.View,StoreContract.Model> implements StoreContract.Presenter {


    @Inject
    public StorePresenter(StoreFragment mview,StoreModel storeModel) {
        super(mview,storeModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getStoreList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getStoreList(map);
        }
    }

    @Override
    public void onGetStoreListSucess(StoreBean storeBean) {
        if (getView() != null) {
            getView().onGetStoreListSucess(storeBean);
        }
    }

    @Override
    public void onGetStoreListFailure(String failure) {
        if (getView() != null) {
            getView().onGetStoreListFailure(failure);
        }
    }

    @Override
    public void loadMore(Map<String, String> map) {
        if (mModle != null) {
            mModle.loadMore(map);
        }
    }

    @Override
    public void loadMoreSuccess(StoreBean bean) {
        if (getView() != null) {
            getView().loadMoreSuccess(bean);
        }
    }

    @Override
    public void loadMoreFailure(String failure) {
        if (getView() != null) {
            getView().loadMoreFailure(failure);
        }
    }

    @Override
    public void getCityInfo() {
        if (mModle != null) {
            mModle.getCityInfo();
        }
    }

    @Override
    public void getCitySuccess(CityInfoBean cityInfoBean) {
        if (getView() != null) {
            getView().getCitySuccess(cityInfoBean);
        }
    }

    @Override
    public void getCityFailure(String failure) {
        if (getView() != null) {
            getView().getCityFailure(failure);
        }
    }
}
