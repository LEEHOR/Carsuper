package com.carsuper.coahr.mvp.presenter.myData;

import android.net.Uri;

import com.carsuper.coahr.mvp.contract.myData.OpinionContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.myData.OpinionModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.OpinionFragment;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class OpinionPresenter extends BasePresenter<OpinionContract.View,OpinionContract.Model> implements OpinionContract.Presenter {
    @Inject
    public OpinionPresenter(OpinionFragment mview, OpinionModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void saveSuggest(Map<String, String> map, List<Uri> uris) {
        if (mModle != null) {
            mModle.saveSuggest(map,uris);
        }
    }

    @Override
    public void onSaveSuccess(ResultBean resultBean) {
        if (getView() != null) {
            getView().onSaveSuccess(resultBean);
        }
    }

    @Override
    public void onSaveFailure(String failure) {
        if (getView() != null) {
            getView().onSaveFailure(failure);
        }
    }
}
