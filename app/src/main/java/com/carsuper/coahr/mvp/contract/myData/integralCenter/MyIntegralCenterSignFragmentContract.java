package com.carsuper.coahr.mvp.contract.myData.integralCenter;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import java.util.Map;

public interface MyIntegralCenterSignFragmentContract {
    interface View extends BaseContract.View {
        //签到
        void  onSigninSuccess(ResultBean bean);

        void onSignInFailure(String failure);
        //兑换商品
        void  getExchangeListSuccess(ExchangeMallList exchangeMallList);
        void  getExchangeListFail(String failure);


    }

    interface Presenter extends BaseContract.Presenter {

        void startSign(Map<String,String> map);

        void  onSigninSuccess(ResultBean bean);

        void onSignInFailure(String failure);
        //兑换商品
        void  getExchangeList(Map<String,String> map);
        void  getExchangeListSuccess(ExchangeMallList exchangeMallList);
        void  getExchangeListFail(String failure);

    }

    interface Model extends BaseContract.Model {
        void startSign(Map<String,String> map);
        void  getExchangeList(Map<String,String> map);

    }
}
