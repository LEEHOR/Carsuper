package com.carsuper.coahr.mvp.presenter.myData;

import com.carsuper.coahr.mvp.contract.myData.MyLovelyCarContract;
import com.carsuper.coahr.mvp.model.bean.MyLovelyCarBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserCarBean;
import com.carsuper.coahr.mvp.model.myData.MyLoveLyCarModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.MyLovelyCarFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyLoveLyCarPresenter extends BasePresenter<MyLovelyCarContract.View, MyLovelyCarContract.Model> implements MyLovelyCarContract.Presenter {

    @Inject
    public MyLoveLyCarPresenter(MyLovelyCarFragment mview, MyLoveLyCarModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getLovelyCarList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getLovelyCarList(map);
        }
    }

    @Override
    public void onGetLovelyCarListSuccess(MyLovelyCarBean bean) {
        if (getView() != null) {
            getView().onGetLovelyCarListSuccess(bean);
        }
    }

    @Override
    public void onGetLovelyCarListFailure(String failure) {
        if (getView() != null) {
            getView().onGetLovelyCarListFailure(failure);
        }
    }

    @Override
    public void deleteLovelyCar(Map<String, String> map, MyLovelyCarBean.JdataBean.MycarBean mycarEntity) {
        if (mModle != null) {
            mModle.deleteLovelyCar(map, mycarEntity);
        }
    }

    @Override
    public void onDeleteSuccess( MyLovelyCarBean.JdataBean.MycarBean mycarEntity, ResultBean bean) {
        if (getView() != null) {
            getView().onDeleteSuccess(mycarEntity,bean);
        }
    }

    @Override
    public void onDeleteFailure(String failure) {
        if (getView() != null) {
            getView().onDeleteFailure(failure);
        }
    }

    @Override
    public void setPrimary(Map<String, String> map,  MyLovelyCarBean.JdataBean.MycarBean mycarEntity) {
        if (mModle != null) {
            mModle.setPrimary(map, mycarEntity);
        }
    }

    @Override
    public void onSetPrimarySuccess( MyLovelyCarBean.JdataBean.MycarBean mycarEntity,ResultBean bean) {
        if (getView() != null) {
            getView().onSetPrimarySuccess(mycarEntity,bean);
        }
    }

    @Override
    public void onSetPrimaryFailure(String failure) {
        if (getView() != null) {
            getView().onSetPrimaryFailure(failure);
        }
    }

    @Override
    public void saveUserCarInfo(Map<String, String> map) {
        if (mModle != null) {
            mModle.saveUserCarInfo(map);
        }
    }


    @Override
    public void onSaveUserCarInfoSuccess(SaveUserCarBean bean) {
        if (getView() != null) {
            getView().onSaveUserCarInfoSuccess(bean);
        }
    }

    @Override
    public void onSaveUserCarInfoFailure(String failure) {
        if (getView() != null) {
            getView().onSaveUserCarInfoFailure(failure);
        }
    }
}
