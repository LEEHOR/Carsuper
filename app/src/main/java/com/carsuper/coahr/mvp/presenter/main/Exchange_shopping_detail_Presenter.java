package com.carsuper.coahr.mvp.presenter.main;

import com.carsuper.coahr.mvp.contract.main.Exchange_shopping_detail_Contract;
import com.carsuper.coahr.mvp.model.bean.ExchangeByStone;
import com.carsuper.coahr.mvp.model.bean.ExchangeDetail;
import com.carsuper.coahr.mvp.model.bean.ExchangeRe;
import com.carsuper.coahr.mvp.model.main.Exchange_shopping_detailModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.Exchange_mall.Fragment_exchange_shopping_detail;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/20
 * on 11:01
 */
public class Exchange_shopping_detail_Presenter extends BasePresenter<Exchange_shopping_detail_Contract.View,Exchange_shopping_detail_Contract.Model> implements Exchange_shopping_detail_Contract.Presenter {
    @Inject
    public Exchange_shopping_detail_Presenter(Fragment_exchange_shopping_detail mview, Exchange_shopping_detailModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getDetail(Map<String, String> map) {
        if (mModle != null) {
            mModle.getDetail(map);
        }
    }

    @Override
    public void getDetailSuccess(ExchangeDetail detail) {
        if (getView() != null) {
            getView().getDetailSuccess(detail);
        }
    }

    @Override
    public void getDetailFailure(String failure) {
        if (getView() != null) {
            getView().getDetailFailure(failure);
        }
    }

    @Override
    public void getDetailNoLogin(Map<String, String> map) {
        if (mModle != null) {
            mModle.getDetailNoLogin(map);
        }
    }

    @Override
    public void getDetailNoLoginSuccess(ExchangeDetail detail) {
        if (getView() != null) {
            getView().getDetailNoLoginSuccess(detail);
        }
    }

    @Override
    public void getDetailNoLoginFailure(String failure) {
        if (getView() != null) {
            getView().getDetailNoLoginFailure(failure);
        }
    }

    @Override
    public void exChangeByStone(Map<String, String> map) {
        if (mModle != null) {
            mModle.exChangeByStone(map);
        }
    }

    @Override
    public void exChangeByStoneSuccess(ExchangeByStone stone) {
        if (getView() != null) {
            getView().exChangeByStoneSuccess(stone);
        }
    }

    @Override
    public void exChangeByStoneFailure(String failure) {
        if (getView() != null) {
            getView().exChangeByStoneFailure(failure);
        }
    }

    @Override
    public void exChangeRe(Map<String, String> map) {
        if (mModle != null) {
            mModle.exChangeRe(map);
        }
    }

    @Override
    public void exChangeReSuccess(ExchangeRe exchangeRe) {
        if (getView() != null) {
            getView().exChangeReSuccess(exchangeRe);
        }
    }

    @Override
    public void exChangeReFailure(String failure) {
        if (getView() != null) {
            getView().exChangeReFailure(failure);
        }
    }
}
