package com.carsuper.coahr.mvp.contract.myData;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ConfirmOrderBean;
import com.carsuper.coahr.mvp.model.bean.DeleteCarBean;
import com.carsuper.coahr.mvp.model.bean.ShoppingCartBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ShoppingCartContract {
    interface View extends BaseContract.View {

        void onGetShoppingCartSuccess(ShoppingCartBean bean);

        void onGetShoppingCartFailure(String failure);

        void nDeleteCarSuccess(DeleteCarBean bean);

        void nDeleteCarFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {

        void getShoppingCart(Map<String, String> map);

        void onGetShoppingCartSuccess(ShoppingCartBean bean);

        void onGetShoppingCartFailure(String failure);

        void onDeleteCar(Map<String,String> map);

        void nDeleteCarSuccess(DeleteCarBean bean);

        void nDeleteCarFailure(String failure);



    }

    interface Model extends BaseContract.Model {
        void getShoppingCart(Map<String, String> map);

        void onDeleteCar(Map<String,String> map);


    }


}
