package com.carsuper.coahr.mvp.contract.myData;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.UserAddressBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/8/15.
 * Email：hengzwdhengzwd@qq.com
 */
public interface MyAddressContract {

    interface View extends BaseContract.View {
        void onSaveUserAddressSuccess(ResultBean bean);

        void onSaveUserAddressFailure(String failure);

        void ongetUserAddressListSuccess(UserAddressBean bean);

        void onGetUserAddressListFailure(String failure);

        void onDeleteUserAddressSuccess(ResultBean bean);

        void  onDeleteUserAddressFailure(String failure);


        void onSetPrimarySuccess(ResultBean bean);

        void onSetPrimaryFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void getUserAddressList(Map<String, String> map);

        void ongetUserAddressListSuccess(UserAddressBean bean);

        void onGetUserAddressListFailure(String failure);

        void saveUserAddress(Map<String, String> map);

        void onSaveUserAddressSuccess(ResultBean bean);

        void onSaveUserAddressFailure(String failure);


        void  deleteUserAddress(Map<String,String> map);

        void onDeleteUserAddressSuccess(ResultBean bean );

        void  onDeleteUserAddressFailure(String failure);


        void setPrimaryAddress(Map<String,String> map );

        void onSetPrimarySuccess(ResultBean bean);

        void onSetPrimaryFailure(String failure);



    }

    interface Model extends BaseContract.Model {
        void saveUserAddress(Map<String, String> map);

        void getUserAddressList(Map<String, String> map);

        void  deleteUserAddress(Map<String,String> map);
        void setPrimaryAddress(Map<String,String> map );
    }

}
