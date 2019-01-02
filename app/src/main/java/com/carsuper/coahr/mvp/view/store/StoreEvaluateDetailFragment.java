package com.carsuper.coahr.mvp.view.store;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.store.StoreEvaluateDetailContract;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.EvaluateBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateDetailBean;
import com.carsuper.coahr.mvp.presenter.store.StoreEvaluateDetailPresenter;
import com.carsuper.coahr.mvp.view.EvaluateInputDialogFragment;
import com.carsuper.coahr.mvp.view.adapter.storeevaluate.StoreEvaluatePhotoItemClickListener;
import com.carsuper.coahr.mvp.view.adapter.storeevaluate.StoreSecondEvaluateAdapter;
import com.carsuper.coahr.mvp.view.adapter.storeevaluate.StoreEvaluatePhotoAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.shoppingMall.PhotoAlbumDialogFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.OriginalPriceTextView;
import com.carsuper.coahr.widgets.StarBar;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 门店评价详情
 */
public class StoreEvaluateDetailFragment extends BaseFragment<StoreEvaluateDetailContract.Presenter> implements StoreEvaluateDetailContract.View, View.OnClickListener {

    @Inject
    StoreEvaluateDetailPresenter storeEvaluateDetailPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.iv_user_headerimg)
    ImageView ivUserHeaderimg;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_evaluate_time)
    TextView tvEvaluateTime;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.sb_evaluate)
    StarBar sbEvaluate;
    @BindView(R.id.tv_evaluate_message)
    TextView tvEvaluateMessage;
    @BindView(R.id.rv_evaluate_pushimg)
    RecyclerView rvEvaluatePushimg;
    @BindView(R.id.text0)
    TextView text0;
    @BindView(R.id.iv_commodity_img)
    ImageView ivCommodityImg;
    @BindView(R.id.tv_commodity_info)
    TextView tvCommodityInfo;
    @BindView(R.id.opt_commodity_price)
    OriginalPriceTextView optCommodityPrice;
    @BindView(R.id.tv_now_proce)
    TextView tvNowProce;
    @BindView(R.id.tv_buy_count)
    TextView tvBuyCount;
    @BindView(R.id.tv_hole_evaluation_count)
    TextView tvHoleEvaluationCount;
    @BindView(R.id.rv_evaluation)
    RecyclerView rvEvaluation;
    @BindView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @BindView(R.id.tv_zan)
    TextView tvZan;

    @BindView(R.id.my_shopping)
    RelativeLayout  my_shopping;

    @BindView(R.id.no_shopping)
    TextView no_shopping;


    public static final int ACTION_SHOW = 0;

    public static final int ACTION_EVALUATE = 0;

    private GridLayoutManager photolinearLayoutManager;
    private LinearLayoutManager secondEvaluateLinearLayoutManager;

    private StoreEvaluatePhotoAdapter storeEvaluatePhotoAdapter;
    private StoreSecondEvaluateAdapter storeSecondEvaluateAdapter;
    private  List<StoreEvaluateDetailBean.JdataBean.ReplyBean> reply ;
    private int dianzanPosition = 0;

    private String ao_id;  //站点的id

    EvaluateInputDialogFragment dialogFragment = EvaluateInputDialogFragment.newInstance();//评论输入窗口

    public static StoreEvaluateDetailFragment newInstance(String ao_id, int action) {
        StoreEvaluateDetailFragment fragment = new StoreEvaluateDetailFragment();
        Bundle args = new Bundle();
        args.putString("ao_id", ao_id);
        args.putInt("action", action);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public StoreEvaluateDetailContract.Presenter getPresenter() {
        return storeEvaluateDetailPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_evaluation_detail;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        tvEvaluate.setOnClickListener(this);
        tvZan.setOnClickListener(this);
        dialogFragment.setOnInputCallback(new EvaluateInputDialogFragment.InputCallback() {
            @Override
            public void onInputSend(String input, AppCompatDialogFragment dialogFragment) {
                //评论
                Map map = new HashMap();
                map.put("ao_id", ao_id);
                map.put("token", Constants.token);
                map.put("comment", input);
                getPresenter().storeSecondEvaluate(map);
                dialogFragment.dismiss();
            }
        });
    }

    @Override
    public void initData() {

        ao_id = getArguments().getString("ao_id");
        photolinearLayoutManager = new GridLayoutManager(BaseApplication.mContext, 2);
        secondEvaluateLinearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        Map map = new HashMap();
        map.put("ao_id", ao_id);
        map.put("token", Constants.token);
        getPresenter().getEvaluateDetail(map);

    }


    @Override
    public void onGetEvaluateDetailSuccess(StoreEvaluateDetailBean bean) {
        StoreEvaluateDetailBean.JdataBean.CommentOneBean commentOneEntity = bean.getJdata().getComment_one();
        Imageloader.loadCircularImage(commentOneEntity.getUserheadimg(), ivUserHeaderimg);
        tvUserName.setText(commentOneEntity.getNickname());
        tvEvaluateTime.setText(commentOneEntity.getCreate_time());
        tvEvaluateMessage.setText(commentOneEntity.getComment());
        //打星
        if (!TextUtils.isEmpty(commentOneEntity.getLevel_score())) {
            sbEvaluate.setStarMark(Float.parseFloat(commentOneEntity.getLevel_score()));
        }
        tvZan.setText(bean.getJdata().getComment_one().getPraise_status() == 0 ? "点赞" : "取消点赞");

        //图片
        if (commentOneEntity.getComment_pic() != null && commentOneEntity.getComment_pic().size() > 0) {
            rvEvaluatePushimg.setVisibility(View.VISIBLE);
            if (commentOneEntity.getComment_pic().size() == 4) {
                photolinearLayoutManager.setSpanCount(2);
            } else {
                photolinearLayoutManager.setSpanCount(3);

            }
            storeEvaluatePhotoAdapter = new StoreEvaluatePhotoAdapter(commentOneEntity.getComment_pic());
            rvEvaluatePushimg.setLayoutManager(photolinearLayoutManager);
            rvEvaluatePushimg.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,4),DensityUtils.dp2px(BaseApplication.mContext,4)));
            rvEvaluatePushimg.setAdapter(storeEvaluatePhotoAdapter);
            storeEvaluatePhotoAdapter.setStoreEvaluatePhotoItemClickListener(new StoreEvaluatePhotoItemClickListener() {
                @Override
                public void onItemClick(int position, List<String> commentPicEntities) {
                    PhotoAlbumDialogFragment fragment = PhotoAlbumDialogFragment.newInstance();
                    fragment.setImgList(commentPicEntities);
                    fragment.setFirstSeePosition(position);
                    FragmentManager fragmentManager = getFragmentManager();
                    fragment.show(fragmentManager, TAG);
                }
            });
        } else {
            rvEvaluatePushimg.setVisibility(View.GONE);
        }

        /**
         * 所购买的商品
         */
        StoreEvaluateDetailBean.JdataBean.CommodityBean commodityEntity = bean.getJdata().getCommodity();
        KLog.d("购买",commodityEntity.getC_name());
        if (commodityEntity.getC_name() !=null) {
            my_shopping.setVisibility(View.VISIBLE);
            no_shopping.setVisibility(View.GONE);
            Imageloader.loadImage(commodityEntity.getC_thumbnail(), ivCommodityImg);
            tvCommodityInfo.setText(commodityEntity.getC_name());
            optCommodityPrice.setText("¥" + commodityEntity.getC_price_old());
            tvNowProce.setText("¥" + commodityEntity.getC_price());
            tvBuyCount.setText(commodityEntity.getC_sold_real() + "人已购");
        } else {
            my_shopping.setVisibility(View.GONE);
            no_shopping.setVisibility(View.VISIBLE);
        }
        /*if (commodityEntity.getC_sold_real() != null) {
            tvBuyCount.setText(commodityEntity.getC_sold_real() + "人已购");
        }*/


        /**
         * 全部评论
         */
        if (bean.getJdata().getReply_count() != null) {
            tvHoleEvaluationCount.setText("全部评论(" + bean.getJdata().getReply_count() + ")");
        }

        if (bean.getJdata().getReply() != null && bean.getJdata().getReply().size()>0) {
            reply = bean.getJdata().getReply();
            rvEvaluation.setVisibility(View.VISIBLE);
                storeSecondEvaluateAdapter = new StoreSecondEvaluateAdapter(bean.getJdata().getReply());
                storeSecondEvaluateAdapter.setOnDianzanClickListener(new StoreSecondEvaluateAdapter.OnDianzanClickListener() {
                    @Override
                    public void onDianzanClick(int position, String ap_id) {
                        dianzanPosition = position;
                        Map map = new HashMap();
                        map.put("ap_id", ap_id);
                        map.put("token", Constants.token);
                        getPresenter().replydianZan(map);
                    }
                });
                rvEvaluation.setLayoutManager(secondEvaluateLinearLayoutManager);
                rvEvaluation.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,2),getResources().getColor(R.color.material_grey_300)));
                rvEvaluation.setAdapter(storeSecondEvaluateAdapter);
        } else {
            rvEvaluation.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onGetEvaluateDetailFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onReplyDianZanSuccess(DianZanBean dianZanBean) {
        if (reply !=null && reply.size()>0) {
            if (dianZanBean.getJdata().getStatus() == 0) {
                Toast.makeText(BaseApplication.mContext, "取消点赞", Toast.LENGTH_LONG).show();
                reply.get(dianzanPosition).setPraise(Integer.parseInt(reply.get(dianzanPosition).getPraise()) - 1 + "");

            } else {
                Toast.makeText(BaseApplication.mContext, "点赞成功", Toast.LENGTH_LONG).show();
                reply.get(dianzanPosition).setPraise(Integer.parseInt(reply.get(dianzanPosition).getPraise()) + 1 + "");
            }

            reply.get(dianzanPosition).setPraise_status(dianZanBean.getJdata().getStatus());
            storeSecondEvaluateAdapter.notifyItemChanged(dianzanPosition);
        }
    }

    @Override
    public void onReplyDianZanFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEvaluateDianzanSuccess(DianZanBean dianZanBean) {
        if (dianZanBean.getJdata().getStatus() == 0) {
            tvZan.setText("点赞");
            Toast.makeText(BaseApplication.mContext, "取消点赞", Toast.LENGTH_LONG).show();
        } else {
            tvZan.setText("取消点赞");
            Toast.makeText(BaseApplication.mContext, "点赞成功", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onEvaluateDianzanFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEvaluateSuccess(EvaluateBean bean) {
        Toast.makeText(BaseApplication.mContext, "评论成功", Toast.LENGTH_LONG).show();
        Map map = new HashMap();
        map.put("ao_id", ao_id);
        map.put("token", Constants.token);
        getPresenter().getEvaluateDetail(map);
    }

    @Override
    public void onEvaluateFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_zan:
                Map map = new HashMap();
                map.put("ao_id", ao_id);
                map.put("token", Constants.token);
                getPresenter().evaluateDianzan(map);
                break;
            case R.id.tv_evaluate:
                dialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);
                break;
        }
    }
}
