package com.carsuper.coahr.mvp.presenter.myData;

import com.carsuper.coahr.mvp.contract.myData.MyAddressContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.UserAddressBean;
import com.carsuper.coahr.mvp.model.myData.MyAddressModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.MyAddressFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/16.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyAddressPresenter extends BasePresenter<MyAddressContract.View,MyAddressContract.Model> implements MyAddressContract.Presenter {

    @Inject
    public MyAddressPresenter(MyAddressFragment mview, MyAddressModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void getUserAddressList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getUserAddressList(map);
        }
    }

    @Override
    public void ongetUserAddressListSuccess(UserAddressBean bean) {
        if (getView() != null) {
            getView().ongetUserAddressListSuccess(bean);
        }
    }

    @Override
    public void onGetUserAddressListFailure(String failure) {
        if (getView() != null) {
            getView().onGetUserAddressListFailure(failure);
        }
    }

    @Override
    public void saveUserAddress(Map<String, String> map) {
        if (mModle != null) {
            mModle.saveUserAddress(map);
        }
    }

    @Override
    public void onSaveUserAddressSuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onSaveUserAddressSuccess(bean);
        }
    }

    @Override
    public void onSaveUserAddressFailure(String failure) {
        if (getView() != null) {
            getView().onSaveUserAddressFailure(failure);
        }
    }

    @Override
    public void deleteUserAddress(Map<String, String> map) {
        if (mModle != null) {
            mModle.deleteUserAddress(map);
        }
    }

    @Override
    public void onDeleteUserAddressSuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onDeleteUserAddressSuccess(bean);
        }
    }


    @Override
    public void onDeleteUserAddressFailure(String failure) {
        if (getView() != null) {
            getView().onDeleteUserAddressFailure(failure);
        }
    }

    @Override
    public void setPrimaryAddress(Map<String, String> map) {
        if (mModle != null) {
            mModle.setPrimaryAddress(map);
        }
    }

    @Override
    public void onSetPrimarySuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onSetPrimarySuccess(bean);
        }
    }

    @Override
    public void onSetPrimaryFailure(String failure) {
        if (getView() != null) {
            getView().onSetPrimaryFailure(failure);
        }
    }
}
