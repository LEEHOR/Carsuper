package com.carsuper.coahr.mvp.contract.myData;

import android.net.Uri;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.ResultBean;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface OpinionContract {
    interface View extends BaseContract.View {

        void onSaveSuccess(ResultBean resultBean);

        void onSaveFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {


        void saveSuggest(Map<String ,String> map , List<Uri> uris);

        void onSaveSuccess(ResultBean resultBean);

        void onSaveFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void saveSuggest(Map<String ,String> map , List<Uri> uris);

    }


}
