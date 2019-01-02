package com.carsuper.coahr.mvp.view.shoppingMall;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateBean;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.presenter.shoppingMall.CommodityEvaluatePresenter;
import com.carsuper.coahr.mvp.presenter.store.StoreEvaluatePresenter;
import com.carsuper.coahr.mvp.view.adapter.CommodityEvaluata.CommodityEvaluateAdapter;
import com.carsuper.coahr.mvp.view.adapter.CommodityEvaluata.CommodityEvaluateItemClickListener;
import com.carsuper.coahr.mvp.view.base.BaseActivity;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.carsuper.coahr.widgets.radioGroup.CustomRadioGroup;
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
 * 商品评价列表
 */
public class CommodityEvaluateFragment extends BaseFragment<CommodityEvaluateContract.Presenter> implements CommodityEvaluateContract.View {

    @Inject
    CommodityEvaluatePresenter commodityEvaluatePresenter;

    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_hole_count)
    TextView tvHoleCount;
    @BindView(R.id.tv_pushimg_count)
    TextView tvPushimgCount;
    @BindView(R.id.tv_low_count)
    TextView tvLowCount;
    @BindView(R.id.rv_evaluation)
    RecyclerView rvEvaluation;
    @BindView(R.id.rg_evalute_filter)
    CustomRadioGroup rgEvaluteFilter;

    private int dianzanPosition = 0;
    private String commodityid;
    private LinearLayoutManager linearLayoutManager;

    private CommodityEvaluateAdapter commodityEvaluateAdapter;

    private List<CommodityEvaluateBean.JdataEntity.CommentEntity> commentEntities = new ArrayList<>();

    public static CommodityEvaluateFragment newInstance(String c_id) {
        CommodityEvaluateFragment fragment = new CommodityEvaluateFragment();
        Bundle args = new Bundle();
        args.putString("c_id", c_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public CommodityEvaluateContract.Presenter getPresenter() {
        return commodityEvaluatePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_evaluate;
    }

    @Override
    public void initView() {
        tbTittle.getTvTittle().setText("商品评价");
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void initData() {
        commodityid = getArguments().getString("c_id");
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        commodityEvaluateAdapter = new CommodityEvaluateAdapter();
        rvEvaluation.setLayoutManager(linearLayoutManager);
        rvEvaluation.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.decoration_f5f5f8)));
        rvEvaluation.setAdapter(commodityEvaluateAdapter);

        rgEvaluteFilter.setOnRadioChangeLisener(new CustomRadioGroup.OnRadioChangeLisener() {
            @Override
            public void Onchange(View changeTo, int positon) {
                Map map = new HashMap();
                map.put("c_id", commodityid);
                map.put("token", Constants.token);
                switch (positon) {
                    case 0:
                        map.put("filter", "default");
                        filterViewBeenSelect(tvHoleCount);
                        filterViewUnSelect(tvLowCount);
                        filterViewUnSelect(tvPushimgCount);
                        break;
                    case 1:
                        map.put("filter", "pic");
                        filterViewBeenSelect(tvPushimgCount);
                        filterViewUnSelect(tvLowCount);
                        filterViewUnSelect(tvHoleCount);
                        break;
                    case 2:
                        map.put("filter", "low_score");
                        filterViewBeenSelect(tvLowCount);
                        filterViewUnSelect(tvPushimgCount);
                        filterViewUnSelect(tvHoleCount);
                        break;

                }

              /*  map.put("filter", "default");
                filterViewBeenSelect(tvHoleCount);
                filterViewUnSelect(tvLowCount);
                filterViewUnSelect(tvPushimgCount);*/
                getPresenter().getCommentList(map);
            }
        });
        commodityEvaluateAdapter.setCommodityEvaluateItemClickListener(new CommodityEvaluateItemClickListener() {
            @Override
            public void onItemClick(CommodityEvaluateBean.JdataEntity.CommentEntity entity) {
                start(CommodityEvaluateDetailFragment.newInstance(entity.getSo_id(), CommodityEvaluateDetailFragment.ACTION_SHOW));
            }

            @Override
            public void onPhotoItemClick(int position, List<String> commentPicEntities) {
                PhotoAlbumDialogFragment fragment = PhotoAlbumDialogFragment.newInstance();
                fragment.setImgList(commentPicEntities);
                fragment.setFirstSeePosition(position);
                FragmentManager fragmentManager = getFragmentManager();
                fragment.show(fragmentManager, TAG);
            }

            @Override
            public void onGoToEvaluateClick(CommodityEvaluateBean.JdataEntity.CommentEntity entity) {
                start(CommodityEvaluateDetailFragment.newInstance(entity.getSo_id(), CommodityEvaluateDetailFragment.ACTION_EVALUATE));

            }

            @Override
            public void onZanClick(int position, CommodityEvaluateBean.JdataEntity.CommentEntity entity) {
                dianzanPosition = position;
                Map map = new HashMap();
                map.put("so_id", entity.getSo_id());
                map.put("token", Constants.token);
                getPresenter().dianZan(map);
            }

            @Override
            public void onOpenReplay(int position, CommodityEvaluateBean.JdataEntity.CommentEntity entity) {
                start(CommodityEvaluateDetailFragment.newInstance(entity.getSo_id(), CommodityEvaluateDetailFragment.ACTION_EVALUATE));
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void filterViewBeenSelect(TextView view) {
        view.setTextColor(getResources().getColor(R.color.material_white));
        view.setBackgroundResource(R.drawable.bg_evaluate_filter_raido_beenselected);
    }

    @SuppressLint("ResourceAsColor")
    private void filterViewUnSelect(TextView view) {
        view.setTextColor(getResources().getColor(R.color.material_blue_700));
        view.setBackgroundResource(R.drawable.bg_evaluate_filter_radio_unselected);
    }



    @Override
    public void onGetCommentsSuccess(CommodityEvaluateBean bean) {
        if (bean !=null && bean.getJdata() !=null) {
            rvEvaluation.setVisibility(View.VISIBLE);
        rgEvaluteFilter.setVisibility(View.VISIBLE);
        commentEntities = bean.getJdata().getComment();
        if (commentEntities!=null && commentEntities.size()>0){

            commodityEvaluateAdapter.setNewData(commentEntities);
        }
            if (bean.getJdata().getCount_all() != null) {
                tvHoleCount.setText("全部(" + bean.getJdata().getCount_all() + ")");
            } else {
                tvHoleCount.setText("全部(" + 0 + ")");
            }

            if (bean.getJdata().getCount_low_score() != null) {
                tvLowCount.setText("低分(" + bean.getJdata().getCount_low_score() + ")");
            } else {
                tvLowCount.setText("低分(" + 0 + ")");
            }
            if (bean.getJdata().getCount_pic() != null) {
                tvPushimgCount.setText("晒图(" + bean.getJdata().getCount_pic() + ")");
            } else {
                tvPushimgCount.setText("晒图(" + 0 + ")");
            }
            //回复的评论



        } else {
            rvEvaluation.setVisibility(View.GONE);
           Toast.makeText(BaseApplication.mContext,"没有数据",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onGetCommentsFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDianZanSuccess(DianZanBean dianZanBean) {
        commentEntities.get(dianzanPosition).setPraise_status(dianZanBean.getJdata().getStatus());

        if (dianZanBean.getJdata().getStatus() == 0) {
            Toast.makeText(BaseApplication.mContext, "取消点赞", Toast.LENGTH_LONG).show();
            commentEntities.get(dianzanPosition).setPraise(Integer.parseInt(commentEntities.get(dianzanPosition).getPraise()) - 1 + "");

        } else if (dianZanBean.getJdata().getStatus() == 1){
            Toast.makeText(BaseApplication.mContext, "点赞成功", Toast.LENGTH_LONG).show();

            commentEntities.get(dianzanPosition).setPraise(Integer.parseInt(commentEntities.get(dianzanPosition).getPraise()) + 1 + "");

        }else if (dianZanBean.getJdata().getStatus() == 2){
//            commentEntities.get(dianzanPosition).setPraise(Integer.parseInt(commentEntities.get(dianzanPosition).getPraise()) + 1 + "");

        }
        commodityEvaluateAdapter.notifyItemChanged(dianzanPosition);
    }

    @Override
    public void onDianZanFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){


        }
    }
}
