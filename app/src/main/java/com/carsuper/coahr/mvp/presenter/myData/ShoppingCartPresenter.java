package com.carsuper.coahr.mvp.presenter.myData;

import com.carsuper.coahr.mvp.contract.myData.ShoppingCartContract;
import com.carsuper.coahr.mvp.model.bean.DeleteCarBean;
import com.carsuper.coahr.mvp.model.bean.ShoppingCartBean;
import com.carsuper.coahr.mvp.model.myData.ShoppingCartModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.ShoppingCartFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingCartPresenter extends BasePresenter<ShoppingCartContract.View,ShoppingCartContract.Model> implements ShoppingCartContract.Presenter {
    @Inject
    public ShoppingCartPresenter(ShoppingCartFragment mview, ShoppingCartModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getShoppingCart(Map<String, String> map) {
        if (mModle != null) {
            mModle.getShoppingCart(map);
        }
    }

    @Override
    public void onGetShoppingCartSuccess(ShoppingCartBean bean) {
        if (getView() != null) {
            getView().onGetShoppingCartSuccess(bean);
        }
    }

    @Override
    public void onGetShoppingCartFailure(String failure) {
        if (getView() != null) {
            getView().onGetShoppingCartFailure(failure);
        }
    }

    @Override
    public void onDeleteCar(Map<String, String> map) {
        if (mModle != null) {
            mModle.onDeleteCar(map);
        }
    }

    @Override
    public void nDeleteCarSuccess(DeleteCarBean bean) {
        if (getView() != null) {
            getView().nDeleteCarSuccess(bean);
        }
    }

    @Override
    public void nDeleteCarFailure(String failure) {
        if (getView() != null) {
            getView().nDeleteCarFailure(failure);
        }
    }
}
