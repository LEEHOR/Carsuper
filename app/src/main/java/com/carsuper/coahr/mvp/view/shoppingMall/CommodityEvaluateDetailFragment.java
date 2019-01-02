package com.carsuper.coahr.mvp.view.shoppingMall;

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
import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityEvaluateDetailContract;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailBeans;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailbean;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.EvaluateBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateDetailBean;
import com.carsuper.coahr.mvp.presenter.shoppingMall.CommodityEvaluateDetailPresenter;
import com.carsuper.coahr.mvp.view.EvaluateInputDialogFragment;
import com.carsuper.coahr.mvp.view.adapter.CommodityEvaluata.CommodityEvaluatePhoneAdapter;
import com.carsuper.coahr.mvp.view.adapter.CommodityEvaluata.CommodityEvaluatePhoneItemClickListener;
import com.carsuper.coahr.mvp.view.adapter.CommodityEvaluata.CommoditySecondEvaluateAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
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
 * 商品评价详情
 */
public class CommodityEvaluateDetailFragment extends BaseFragment<CommodityEvaluateDetailContract.Presenter> implements CommodityEvaluateDetailContract.View, View.OnClickListener {

    @Inject
    CommodityEvaluateDetailPresenter commodityEvaluateDetailPresenter;
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
    RelativeLayout my_shopping;

    @BindView(R.id.no_shopping)
    TextView no_shopping;

    public static final int ACTION_SHOW = 0;

    public static final int ACTION_EVALUATE = 1;

    private GridLayoutManager photolinearLayoutManager;
    private LinearLayoutManager secondEvaluateLinearLayoutManager;

    private CommodityEvaluatePhoneAdapter commodityEvaluatePhoneAdapter;
    private CommoditySecondEvaluateAdapter commoditySecondEvaluateAdapter;

    private int dianzanPosition = 0;

    private    List<CommodityEvaluateDetailBeans.JdataBean.ReplyBean> reply; //回复评论List

    private String so_id;  //站点的id


    EvaluateInputDialogFragment dialogFragment = EvaluateInputDialogFragment.newInstance();//评论输入窗口


    public static CommodityEvaluateDetailFragment newInstance(String so_id, int action) {
        CommodityEvaluateDetailFragment fragment = new CommodityEvaluateDetailFragment();
        Bundle args = new Bundle();
        args.putString("so_id", so_id);
        args.putInt("action", action);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public CommodityEvaluateDetailContract.Presenter getPresenter() {
        return commodityEvaluateDetailPresenter;
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
                map.put("so_id", so_id);
                map.put("token", Constants.token);
                map.put("comment", input);
                getPresenter().commoditySecondEvaluate(map);
                dialogFragment.dismiss();
            }
        });
    }

    @Override
    public void initData() {

        so_id = getArguments().getString("so_id");
        photolinearLayoutManager = new GridLayoutManager(BaseApplication.mContext, 2);
        secondEvaluateLinearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        Map map = new HashMap();
        map.put("so_id", so_id);
        map.put("token", Constants.token);
        //  map.put("filter",filter);
        getPresenter().getEvaluateDetail(map);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_zan:
                Map map = new HashMap();
                map.put("so_id", so_id);
                map.put("token", Constants.token);
                getPresenter().evaluateDianzan(map);

                break;
            case R.id.tv_evaluate:
                dialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);
                break;
        }
    }

    @Override
    public void onGetEvaluateDetailSuccess(CommodityEvaluateDetailBeans bean) {
        CommodityEvaluateDetailBeans.JdataBean.CommentOneBean comment_one = bean.getJdata().getComment_one();
        Imageloader.loadCircularImage(comment_one.getUserheadimg(), ivUserHeaderimg);
       // Imageloader.loadImage();
        tvUserName.setText(comment_one.getNickname());
        tvEvaluateTime.setText(comment_one.getCreate_time());
        tvEvaluateMessage.setText(comment_one.getComment());
        if (!TextUtils.isEmpty(comment_one.getLevel_score()))
            sbEvaluate.setStarMark(Float.parseFloat(comment_one.getLevel_score()));
        tvZan.setText(comment_one.getPraise_status() == 0 ? "点赞" : "取消点赞");
        //评论图片

        List<Object> comment_pic = comment_one.getComment_pic();
        if (comment_pic != null && comment_pic.size() > 0) {
            rvEvaluatePushimg.setVisibility(View.VISIBLE);
            List<String> strs = (List<String>) (List) comment_pic;
            if (strs.size() == 4) {
                photolinearLayoutManager.setSpanCount(2);
            } else {
                photolinearLayoutManager.setSpanCount(3);

            }
            commodityEvaluatePhoneAdapter = new CommodityEvaluatePhoneAdapter(strs);
            rvEvaluatePushimg.setLayoutManager(photolinearLayoutManager);
            rvEvaluatePushimg.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,4),DensityUtils.dp2px(BaseApplication.mContext,4)));
            rvEvaluatePushimg.setAdapter(commodityEvaluatePhoneAdapter);
            commodityEvaluatePhoneAdapter.setCommodityEvaluatePhoneItemClickListener(new CommodityEvaluatePhoneItemClickListener() {
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

        //购买商品
        CommodityEvaluateDetailBeans.JdataBean.CommodityBean commodity = bean.getJdata().getCommodity();
        if (commodity != null && commodity.getC_id() != null) {
            my_shopping.setVisibility(View.VISIBLE);
            no_shopping.setVisibility(View.GONE);
            Imageloader.loadImage(commodity.getC_thumbnail(), ivCommodityImg);
            tvCommodityInfo.setText(commodity.getC_name());
            optCommodityPrice.setText("¥" + commodity.getC_price_old());
            tvNowProce.setText("¥" + commodity.getC_price());
            if (commodity.getC_sold_real() != null) {
                tvBuyCount.setText(commodity.getC_sold_real() + "人已购");
            }
        } else {
            my_shopping.setVisibility(View.GONE);
            no_shopping.setVisibility(View.VISIBLE);
        }
        //一下模块暂无
        if (bean.getJdata().getReply_count() != null) {
            tvHoleEvaluationCount.setText("全部评论(" + bean.getJdata().getReply_count() + ")");
        }

       reply = bean.getJdata().getReply();
            if (reply != null && reply.size() > 0) {
                rvEvaluation.setVisibility(View.VISIBLE);
                commoditySecondEvaluateAdapter = new CommoditySecondEvaluateAdapter(reply);
                commoditySecondEvaluateAdapter.setOnDianzanClickListener(new CommoditySecondEvaluateAdapter.OnDianzanClickListener() {
                    @Override
                    public void onDianzanClick(int position, String sp_id) {
                        dianzanPosition = position;
                        Map map = new HashMap();
                        map.put("sp_id", sp_id);
                        map.put("token", Constants.token);
                        getPresenter().replydianZan(map);
                    }
                });
                rvEvaluation.setLayoutManager(secondEvaluateLinearLayoutManager);
                rvEvaluation.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.material_grey_200)));
                rvEvaluation.setAdapter(commoditySecondEvaluateAdapter);
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
        }
        commoditySecondEvaluateAdapter.notifyItemChanged(dianzanPosition);
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

    }

    @Override
    public void onEvaluateFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();

    }
}
