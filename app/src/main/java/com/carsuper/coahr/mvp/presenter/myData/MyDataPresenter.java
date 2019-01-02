package com.carsuper.coahr.mvp.presenter.myData;

import com.carsuper.coahr.mvp.contract.myData.MyDataContract;
import com.carsuper.coahr.mvp.model.bean.MyDataAdList;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.VerifyTokenBean;
import com.carsuper.coahr.mvp.model.myData.MyDataModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.MyDataFragment;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyDataPresenter extends BasePresenter<MyDataContract.View,MyDataContract.Model> implements MyDataContract.Presenter {


    @Inject
    public MyDataPresenter(MyDataFragment mview,MyDataModel myDataModel) {
        super(mview,myDataModel);
    }



    @Override
    public void getUserInfo(Map<String,String> map) {
        if (mModle != null) {
            mModle.getUserInfo(map);
        }
    }

    @Override
    public void onGetInfoSuccess(PersonInfoBean personInfoBean) {
        if (getView() != null) {
            getView().onGetInfoSuccess(personInfoBean);
        }
    }

    @Override
    public void onGetInfoFailure(String throwle) {
        if (getView() != null) {
            getView().onGetInfoFailure(throwle);
        }

    }

    @Override
    public void verifyToken(String token) {
        if (mModle != null) {
            mModle.verifyToken(token);
        }
    }

    @Override
    public void verifySuccess(VerifyTokenBean verifyTokenBean) {
        if (getView() != null) {
            getView().verifySuccess(verifyTokenBean);
        }
    }

    @Override
    public void verifyFailure(VerifyTokenBean bean) {
        if (getView() != null) {
            getView().verifyFailure(bean);
        }
    }

    @Override
    public void signin(Map<String, String> map) {
        if (mModle != null) {
            mModle.signin(map);
        }
    }

    @Override
    public void onSigninSuccess(ResultBean bean) {
        if (getView() != null) {
            getView().onSigninSuccess(bean);
        }
    }

    @Override
    public void onSignInFailure(String failure) {
        if (getView() != null) {
            getView().onSignInFailure(failure);
        }
    }

    @Override
    public void getAdList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getAdList(map);
        }
    }

    @Override
    public void getAdListSuccess(MyDataAdList myDataAdList) {
        if (getView() != null) {
            getView().getAdListSuccess(myDataAdList);
        }
    }

    @Override
    public void getAdListFailure(String failure) {
        if (getView() != null) {
            getView().getAdListFailure(failure);
        }
    }
}
