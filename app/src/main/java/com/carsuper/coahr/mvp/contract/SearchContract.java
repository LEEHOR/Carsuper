package com.carsuper.coahr.mvp.contract;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.SearchBean;

import java.util.List;
import java.util.Map;

/**
 * Author： hengzwd on 2018/7/23.
 * Email：hengzwdhengzwd@qq.com
 */
public interface SearchContract {

    interface View extends BaseContract.View {
        void onSearchSuccess(SearchBean searchBean);

        void onsearchFailure(String failure);

        void onGetHistorySuccess(List<SearchBean.JdataEntity.SearchEntity> list);

        void  onGetHistoryFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void searchAll(Map<String, String> map);

        void getSearchHistory();

        void onGetHistorySuccess(List<SearchBean.JdataEntity.SearchEntity> list);

        void  onGetHistoryFailure(String failure);

        void onSearchSuccess(SearchBean searchBean);

        void onsearchFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void searchAll(Map<String, String> map);

        void getSearchHistory();
    }

}
