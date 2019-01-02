package com.carsuper.coahr.mvp.model;

import com.carsuper.coahr.mvp.contract.SearchContract;
import com.carsuper.coahr.mvp.model.base.BaseModel;
import com.carsuper.coahr.mvp.model.bean.SearchBean;
import com.carsuper.coahr.mvp.model.bean.SearchBean.JdataEntity.SearchEntity;
import com.carsuper.coahr.utils.SearchHistoryStackUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author： hengzwd on 2018/7/23.
 * Email：hengzwdhengzwd@qq.com
 */
public class SearchModel extends BaseModel<SearchContract.Presenter> implements SearchContract.Model {

    @Inject
    public SearchModel(){
        super();
    }


    @Override
    public void searchAll(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<SearchBean>(getApiservice().searchAll(map.get("search_key"))))
                    .subscribeWith(new SimpleDisposableSubscriber<SearchBean>() {
                        @Override
                        public void _onNext(SearchBean searchBean) {
                            if (getPresenter() != null) {
                                if (searchBean.getCode()==0) {
                                    getPresenter().onSearchSuccess(searchBean);
                                }else {
                                    getPresenter().onsearchFailure(searchBean.getMsg());
                                }
                            }

                        }
                    }));
    }

    @Override
    public void getSearchHistory() {
        mRxManager.add(Flowable.create(new FlowableOnSubscribe<List<SearchEntity>>() {
            @Override
            public void subscribe(FlowableEmitter<List<SearchEntity>> e) {
              List<SearchEntity> list  =   SearchHistoryStackUtils.getInstance().getsearchEntities();
                if (list != null&&list.size()>0) {
                    e.onNext(list);
                }
            }
        }, BackpressureStrategy.BUFFER).subscribeWith(new SimpleDisposableSubscriber<List<SearchEntity>>() {
            @Override
            public void _onNext(List<SearchEntity> list) {
                if (getPresenter() != null) {
                    getPresenter().onGetHistorySuccess(list);
                }
            }
        }));

    }
}
