package com.carsuper.coahr.mvp.contract.myData;

import android.net.Uri;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.SaveCommentBean;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface ToEvaluateContract {
    interface View extends BaseContract.View {
        void onSaveCommentSuccess(SaveCommentBean saveCommentBean);

        void onSaveCommentFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void  saveCommodityComment(Map<String, RequestBody> map,List<Uri> uris);

        void onSaveCommentSuccess(SaveCommentBean saveCommentBean);

        void onSaveCommentFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void  saveCommodityComment(Map<String, RequestBody> map,List<Uri> uris);

    }


}
