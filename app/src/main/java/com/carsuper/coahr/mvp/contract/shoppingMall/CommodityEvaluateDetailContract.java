package com.carsuper.coahr.mvp.contract.shoppingMall;

import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailBeans;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailbean;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.EvaluateBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateDetailBean;

import java.util.Map;

/**
 * Author： hengzwd on 2018/6/20.
 * Email：hengzwdhengzwd@qq.com
 */
public interface CommodityEvaluateDetailContract {

    interface View extends BaseContract.View {
        void onGetEvaluateDetailSuccess(CommodityEvaluateDetailBeans bean);
        void onGetEvaluateDetailFailure(String failure);

        void onReplyDianZanSuccess(DianZanBean dianZanBean);

        void onReplyDianZanFailure(String failure);

        void onEvaluateDianzanSuccess(DianZanBean dianZanBean);

        void onEvaluateDianzanFailure(String failure);

        void onEvaluateSuccess(EvaluateBean bean);

        void onEvaluateFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {


        void getEvaluateDetail(Map<String,String> map);
        void onGetEvaluateDetailSuccess(CommodityEvaluateDetailBeans bean);
        void onGetEvaluateDetailFailure(String failure);

        void replydianZan(Map<String,String> map);

        void onReplyDianZanSuccess(DianZanBean dianZanBean);

        void onReplyDianZanFailure(String failure);

        void  evaluateDianzan(Map<String,String> map);

        void onEvaluateDianzanSuccess(DianZanBean dianZanBean);

        void onEvaluateDianzanFailure(String failure);

        void commoditySecondEvaluate(Map<String,String> map);

        void onEvaluateSuccess(EvaluateBean bean);

        void onEvaluateFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void getEvaluateDetail(Map<String,String> map);

        void replydianZan(Map<String,String> map);
        void  evaluateDianzan(Map<String,String> map);

        void commoditySecondEvaluate(Map<String,String> map);
    }

}
