package com.carsuper.coahr.mvp.view.adapter.storeevaluate;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateBean;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.CircleImageView;
import com.carsuper.coahr.widgets.StarBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： hengzwd on 2018/8/1.
 * Email：hengzwdhengzwd@qq.com
 */
public class StoreEvaluateAdapter extends BaseQuickAdapter<StoreEvaluateBean.JdataEntity.CommentEntity,BaseViewHolder> {


    private List<StoreEvaluateBean.JdataEntity.CommentEntity> commentEntities = new ArrayList<>();
//    private List<StoreEvaluateBean.JdataEntity.CommentEntity.CommentPicEntity> commentPicEntities =new ArrayList<>();
    private StoreEvaluateItemClickListener storeEvaluateItemClickListener;
    public StoreEvaluateAdapter() {
        super(R.layout.recyclerview_item_evaluatioin, null);
    }


    @Override
    protected void convert(final BaseViewHolder helper, final StoreEvaluateBean.JdataEntity.CommentEntity item) {
        if (commentEntities != null&&commentEntities.size()>0) {
            helper.setText(R.id.tv_user_name,item.getNickname())
                    .setText(R.id.tv_evaluate_time,item.getCreate_time())
                    .setText(R.id.tv_evaluate_message,item.getComment())
                    .setText(R.id.tv_message_count,item.getComment_count())
                    .setText(R.id.tv_zan_count,item.getPraise())
                    .setText(R.id.tv_someone_evaluate,item.getReply().getNickname())
                    .setText(R.id.tv_someone_evaluate_message,item.getReply().getComment());
            /**
             * 回复的评论
             */
            if (item.getReply().getNickname() !=null && !item.getReply().getNickname().equals("")){
                helper.getView(R.id.replay_part).setVisibility(View.GONE);
                helper.setText(R.id.open_replay_message,"查看更多("+item.getComment_count()+")");
            } else {
                helper.getView(R.id.replay_part).setVisibility(View.GONE);
            }

            Imageloader.loadCircularImage(item.getUserheadimg(),((ImageView)(helper.getView(R.id.iv_user_headerimg))));
            /**
             * 大星
             */
            if (!TextUtils.isEmpty(item.getLevel_score())){
                ((StarBar)helper.getView(R.id.sb_evaluate)).setStarMark(Float.parseFloat(item.getLevel_score()));

            } else {
                ((StarBar)helper.getView(R.id.sb_evaluate)).setStarMark(Float.parseFloat(String.valueOf(0)));
            }

            /**
             * 评论图片
             */
            if (item.getComment_pic() != null&&item.getComment_pic().size()>0) {
                GridLayoutManager linearLayoutManager;
                if (item.getComment_pic().size()==4) {
                    linearLayoutManager  = new GridLayoutManager(BaseApplication.mContext,2);
                }else {
                    linearLayoutManager  = new GridLayoutManager(BaseApplication.mContext,3);
                }
                StoreEvaluatePhotoAdapter adapter = new StoreEvaluatePhotoAdapter(item.getComment_pic());
                adapter.setStoreEvaluatePhotoItemClickListener(new StoreEvaluatePhotoItemClickListener() {
                    @Override
                    public void onItemClick(int position, List<String> commentPicEntities) {
                        storeEvaluateItemClickListener.onPhotoItemClick(position,commentPicEntities);
                    }
                });
                ((RecyclerView)helper.getView(R.id.rv_evaluate_pushimg)).setLayoutManager(linearLayoutManager);
                ((RecyclerView)helper.getView(R.id.rv_evaluate_pushimg)).addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,4),DensityUtils.dp2px(BaseApplication.mContext,4)));
                ((RecyclerView)helper.getView(R.id.rv_evaluate_pushimg)).setAdapter(adapter);
            }
            /**
             * 查看回复评论
             */


            if (item.getPraise_status()==0) {
                Drawable drawable =helper.getView(R.id.tv_zan_count).getResources().getDrawable(R.mipmap.zan_no);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                ((TextView)helper.getView(R.id.tv_zan_count)).setCompoundDrawables(drawable,null,null,null);
            }else {
                Drawable drawable =helper.getView(R.id.tv_zan_count).getResources().getDrawable(R.mipmap.zan);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                ((TextView)helper.getView(R.id.tv_zan_count)).setCompoundDrawables(drawable,null,null,null);

            }
          /*  helper.getView(R.id.tv_zan_count).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (item.getPraise_status()==0) {
//                        commentEntities.get(helper.getAdapterPosition()).setPraise(Integer.parseInt(item.getPraise())+1+"");
//                        helper.setText(R.id.tv_zan_count,Integer.parseInt(item.getPraise())+1+"");
//                    }else {
//                        commentEntities.get(helper.getAdapterPosition()).setPraise(Integer.parseInt(item.getPraise())-1+"");
//                        helper.setText(R.id.tv_zan_count,Integer.parseInt(item.getPraise())-1+"");
//                    }
//                    notifyItemChanged(helper.getAdapterPosition());
                    storeEvaluateItemClickListener.onZanClick(helper.getAdapterPosition(),item);
                }
            });*/
            helper.getView(R.id.rl_evaluation).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storeEvaluateItemClickListener.onItemClick(item);
                }
            });

            helper.getView(R.id.tv_message_count).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storeEvaluateItemClickListener.onGoToEvaluateClick(item);
                }
            });

            helper.getView(R.id.tv_zan_count).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storeEvaluateItemClickListener.onZanClick(helper.getAdapterPosition(),item);
                }
            });

            helper.getView(R.id.open_replay_message).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    storeEvaluateItemClickListener.onOpenReplay(helper.getAdapterPosition(),item);
                }
            });
        }
    }


    @Override
    public void setNewData(List<StoreEvaluateBean.JdataEntity.CommentEntity> data) {
        commentEntities=data;
        super.setNewData(data);
    }

    public void setOnStoreEvaluateItemClickListener(StoreEvaluateItemClickListener storeEvaluateItemClickListener){
        this.storeEvaluateItemClickListener=storeEvaluateItemClickListener;
    }
}
