package com.carsuper.coahr.mvp.view.adapter.CommodityEvaluata;

import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateBean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/8.
 * Email：hengzwdhengzwd@qq.com
 */
public interface CommodityEvaluateItemClickListener {

    void onItemClick(CommodityEvaluateBean.JdataEntity.CommentEntity entity);

    void onPhotoItemClick(int position, List<String> commentPicEntities);


    void onGoToEvaluateClick(CommodityEvaluateBean.JdataEntity.CommentEntity entity);

    void onZanClick(int position,CommodityEvaluateBean.JdataEntity.CommentEntity entity);

    void onOpenReplay(int position,CommodityEvaluateBean.JdataEntity.CommentEntity entity);
}
