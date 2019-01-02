package com.carsuper.coahr.mvp.view.adapter.storeevaluate;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/10/19
 * on 16:02
 * 门店评论列表/ 回复评论adapter
 */
public class StoreEvaluateReplayAdapter extends BaseQuickAdapter<StoreEvaluateBean.JdataEntity.CommentEntity.ReplyEntity,BaseViewHolder> {
    public StoreEvaluateReplayAdapter(int layoutResId, List<StoreEvaluateBean.JdataEntity.CommentEntity.ReplyEntity> data) {
        super(R.layout.recyclerview_item_store_replay, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreEvaluateBean.JdataEntity.CommentEntity.ReplyEntity item) {
                    helper.setText(R.id.replay_nickname,item.getNickname())
                            .setText(R.id.replay_discuss,item.getComment());
    }
}
