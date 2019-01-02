package com.carsuper.coahr.mvp.presenter.myData;

import android.net.Uri;

import com.carsuper.coahr.mvp.contract.myData.ToEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveCommentBean;
import com.carsuper.coahr.mvp.model.myData.ToEvaluateModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.ToEvaluateFragment;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ToEvaluatePresenter extends BasePresenter<ToEvaluateContract.View,ToEvaluateContract.Model> implements ToEvaluateContract.Presenter {
    @Inject
    public ToEvaluatePresenter(ToEvaluateFragment mview, ToEvaluateModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void saveCommodityComment(Map<String, RequestBody> map, List<Uri> uris) {
        if (mModle != null) {
            mModle.saveCommodityComment(map,uris);
        }
    }

    @Override
    public void onSaveCommentSuccess(SaveCommentBean resultBean) {
        if (getView() != null) {
            getView().onSaveCommentSuccess(resultBean);
        }
    }

    @Override
    public void onSaveCommentFailure(String failure) {
        if (getView() != null) {
            getView().onSaveCommentFailure(failure);
        }
    }
}
