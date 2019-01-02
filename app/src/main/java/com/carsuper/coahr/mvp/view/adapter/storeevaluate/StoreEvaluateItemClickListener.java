package com.carsuper.coahr.mvp.view.adapter.storeevaluate;

import com.carsuper.coahr.mvp.model.bean.StoreEvaluateBean;

import java.util.List;

/**
 * Author： hengzwd on 2018/8/1.
 * Email：hengzwdhengzwd@qq.com
 */
public interface StoreEvaluateItemClickListener {

    void onItemClick(StoreEvaluateBean.JdataEntity.CommentEntity entity);

    void onPhotoItemClick(int position, List<String> commentPicEntities);


    void onGoToEvaluateClick(StoreEvaluateBean.JdataEntity.CommentEntity entity);

    void onZanClick(int position,StoreEvaluateBean.JdataEntity.CommentEntity entity);

    void onOpenReplay(int position,StoreEvaluateBean.JdataEntity.CommentEntity entity);
}
