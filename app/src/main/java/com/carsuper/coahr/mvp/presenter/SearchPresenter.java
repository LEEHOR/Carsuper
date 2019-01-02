package com.carsuper.coahr.mvp.presenter;

import com.carsuper.coahr.mvp.contract.SearchContract;
import com.carsuper.coahr.mvp.model.SearchModel;
import com.carsuper.coahr.mvp.model.bean.SearchBean;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.SearchFragment;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/7/23.
 * Email：hengzwdhengzwd@qq.com
 */
public class SearchPresenter extends BasePresenter<SearchContract.View,SearchContract.Model> implements SearchContract.Presenter {



    @Inject
    public SearchPresenter(SearchFragment mview, SearchModel model){
        super(mview,model);
    }


    @Override
    public void searchAll(Map<String, String> map) {
        if (mModle != null) {
            mModle.searchAll(map);
        }
    }

    @Override
    public void getSearchHistory() {
        if (mModle != null) {
            mModle.getSearchHistory();
        }
    }

    @Override
    public void onGetHistorySuccess(List<SearchBean.JdataEntity.SearchEntity> list) {
        if (getView() != null) {
            getView().onGetHistorySuccess(list);
        }
    }

    @Override
    public void onGetHistoryFailure(String failure) {
        if (getView() != null) {
            getView().onsearchFailure(failure);
        }
    }

    @Override
    public void onSearchSuccess(SearchBean searchBean) {
        if (getView() != null) {
            getView().onSearchSuccess(searchBean);
        }
    }

    @Override
    public void onsearchFailure(String failure) {
        if (getView() != null) {
            getView().onsearchFailure(failure);
        }
    }
}
