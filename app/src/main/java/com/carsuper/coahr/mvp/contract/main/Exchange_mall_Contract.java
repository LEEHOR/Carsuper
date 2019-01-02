package com.carsuper.coahr.mvp.contract.main;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */
public interface Exchange_mall_Contract {


    interface View extends BaseContract.View {

        void  getExchangeListSuccess(ExchangeMallList exchangeMallList);
        void  getExchangeListFail(String failure);
        void  getExchangeListMoreSuccess(ExchangeMallList exchangeMallList);
        void  getExchangeListMoreFail(String failure);
        void onGetInfoSuccess(PersonInfoBean personInfoBean);
        void onGetInfoFailure(String throwle);
    }

    interface Presenter extends BaseContract.Presenter {

        void  getExchangeList(Map<String,String> map);
        void  getExchangeListSuccess(ExchangeMallList exchangeMallList);
        void  getExchangeListFail(String failure);

        void  getExchangeListMore(Map<String,String> map);
        void  getExchangeListMoreSuccess(ExchangeMallList exchangeMallList);
        void  getExchangeListMoreFail(String failure);

        void getUserInfo(Map<String,String> map);
        void onGetInfoSuccess(PersonInfoBean personInfoBean);
        void onGetInfoFailure(String throwle);


    }

    interface Model extends BaseContract.Model {

        void  getExchangeList(Map<String,String> map);
        void  getExchangeListMore(Map<String,String> map);
        void getUserInfo(Map<String,String> map);

    }
}
