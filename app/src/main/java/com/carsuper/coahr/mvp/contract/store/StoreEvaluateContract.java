package com.carsuper.coahr.mvp.contract.store;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface StoreEvaluateContract {
    interface View extends BaseContract.View {

        void onGetCommentsSuccess(StoreEvaluateBean bean);

        void onGetCommentsFailure(String failure);

        void onDianZanSuccess(DianZanBean dianZanBean);

        void onDianZanFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCommentList(Map<String, String> map);
        void dianZan(Map<String,String> map);

        void onDianZanSuccess(DianZanBean dianZanBean);

        void onDianZanFailure(String failure);

        void onGetCommentsSuccess(StoreEvaluateBean bean);

        void onGetCommentsFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void getCommentList(Map<String, String> map);

        void dianZan(Map<String,String> map);
    }


}
