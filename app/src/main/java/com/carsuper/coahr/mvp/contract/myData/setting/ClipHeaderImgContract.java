package com.carsuper.coahr.mvp.contract.myData.setting;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveUserInfoBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ClipHeaderImgContract {
    interface View extends BaseContract.View {
        void onUpdateSuccess(SaveUserInfoBean bean);

        void onUpdateFailue(String throwable);


    }

    interface Presenter extends BaseContract.Presenter {
        void onUpdateSuccess(SaveUserInfoBean bean);

        void onUpdateFailue(String throwable);

        void updateHeadPic(Map<String,RequestBody> map, MultipartBody.Part part);
    }

    interface Model extends BaseContract.Model {
        void updateHeadPic(Map<String, RequestBody> map, MultipartBody.Part part);
    }

}
