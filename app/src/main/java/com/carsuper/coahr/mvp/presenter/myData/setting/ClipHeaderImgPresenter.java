package com.carsuper.coahr.mvp.presenter.myData.setting;

import com.carsuper.coahr.mvp.contract.myData.setting.ClipHeaderImgContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserInfoBean;
import com.carsuper.coahr.mvp.model.myData.setting.ClipHeaderImgModel;
import com.carsuper.coahr.mvp.presenter.base.BasePresenter;
import com.carsuper.coahr.mvp.view.myData.setting.ClipHeaderImgFragment;

import java.util.Map;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class ClipHeaderImgPresenter extends BasePresenter<ClipHeaderImgContract.View,ClipHeaderImgContract.Model> implements ClipHeaderImgContract.Presenter {
    @Inject
    public ClipHeaderImgPresenter(ClipHeaderImgFragment mview, ClipHeaderImgModel mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void onUpdateSuccess(SaveUserInfoBean bean) {
        if (getView() != null) {
            getView().onUpdateSuccess(bean);
        }
    }

    @Override
    public void onUpdateFailue(String throwable) {
        if (getView() != null) {
            getView().onUpdateFailue(throwable);
        }
    }

    @Override
    public void updateHeadPic(Map<String, RequestBody> map, MultipartBody.Part part) {
        if (mModle != null) {
            mModle.updateHeadPic(map,part);
        }
    }
}
