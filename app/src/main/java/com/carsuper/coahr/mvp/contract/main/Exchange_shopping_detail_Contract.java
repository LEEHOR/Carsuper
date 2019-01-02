package com.carsuper.coahr.mvp.contract.main;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ExchangeByStone;
import com.carsuper.coahr.mvp.model.bean.ExchangeDetail;
import com.carsuper.coahr.mvp.model.bean.ExchangeRe;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */
public interface Exchange_shopping_detail_Contract {


    interface View extends BaseContract.View {

        void getDetailSuccess(ExchangeDetail detail);
        void getDetailFailure(String failure);

        void exChangeByStoneSuccess(ExchangeByStone stone);
        void exChangeByStoneFailure(String failure);

        void exChangeReSuccess(ExchangeRe exchangeRe);
        void exChangeReFailure(String failure);

        void getDetailNoLoginSuccess(ExchangeDetail detail);
        void getDetailNoLoginFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        /**
         * 详情
         * @param map
         */
        void getDetail(Map<String,String> map);
        void getDetailSuccess(ExchangeDetail detail);
        void getDetailFailure(String failure);

        /**
         * meiyoutoken
         * @param map
         */
        void getDetailNoLogin(Map<String,String> map);
        void getDetailNoLoginSuccess(ExchangeDetail detail);
        void getDetailNoLoginFailure(String failure);

        //宝石兑换
        void exChangeByStone(Map<String,String> map);
        void exChangeByStoneSuccess(ExchangeByStone stone);
        void exChangeByStoneFailure(String failure);

        //领取礼品
        void exChangeRe(Map<String,String> map);
        void exChangeReSuccess(ExchangeRe exchangeRe);
        void exChangeReFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void getDetail(Map<String,String> map);
        void exChangeByStone(Map<String,String> map);
        void exChangeRe(Map<String,String> map);
        void getDetailNoLogin(Map<String,String> map);
    }
}
