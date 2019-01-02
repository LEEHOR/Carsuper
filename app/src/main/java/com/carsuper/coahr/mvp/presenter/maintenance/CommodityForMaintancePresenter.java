package com.carsuper.coahr.mvp.presenter.maintenance;

import com.carsuper.coahr.mvp.contract.maintenance.CommodityForMaintanceContract;
import com.carsuper.coahr.mvp.model.bean.RecommendServiceBean;
import com.carsuper.coahr.mvp.model.bean.ReplaceableCommodityBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;
import com.carsuper.coahr.mvp.model.maintenance.CommodityForMaintanceModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.maintenance.CommodityForMaintanceFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityForMaintancePresenter extends BasePresenter<CommodityForMaintanceContract.View,CommodityForMaintanceContract.Model> implements CommodityForMaintanceContract.Presenter {

    @Inject
    public CommodityForMaintancePresenter(CommodityForMaintanceFragment mview, CommodityForMaintanceModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }


    @Override
    public void getReplaceableCommoditys(Map<String, String> map) {
        if (mModle != null) {
            mModle.getReplaceableCommoditys(map);
        }
    }

    @Override
    public void onGetReplaceableCommoditysSuccess(ReplaceableCommodityBean bean) {
        if (getView() != null) {
            getView().onGetReplaceableCommoditysSuccess(bean);
        }
    }

    @Override
    public void onGetReplaceableCommoditysFailure(String failure) {
        if (getView() != null) {
            getView().onGetReplaceableCommoditysFailure(failure);
        }
    }

    @Override
    public void loadMore(Map<String, String> map) {
        if (mModle != null) {
            mModle.loadMore(map);
        }
    }

    @Override
    public void onLoadMoreSuccess(ReplaceableCommodityBean bean) {
        if (getView() != null) {
            getView().onLoadMoreSuccess(bean);

        }
    }

    @Override
    public void onLoadMoreFailure(String failure) {
        if (getView() != null) {
            getView().onLoadMoreFailure(failure);
        }
    }
}
