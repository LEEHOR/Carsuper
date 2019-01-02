package com.carsuper.coahr.mvp.view.store;

import android.annotation.SuppressLint;
import android.inputmethodservice.AbstractInputMethodService;
import android.os.Bundle;
import android.os.TokenWatcher;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.store.StoreEvaluateContract;
import com.carsuper.coahr.mvp.model.bean.CommodityEvaluateDetailBeans;
import com.carsuper.coahr.mvp.model.bean.DianZanBean;
import com.carsuper.coahr.mvp.model.bean.StoreEvaluateBean;
import com.carsuper.coahr.mvp.presenter.store.StoreEvaluatePresenter;
import com.carsuper.coahr.mvp.view.adapter.storeevaluate.StoreEvaluateAdapter;
import com.carsuper.coahr.mvp.view.adapter.storeevaluate.StoreEvaluateItemClickListener;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.shoppingMall.PhotoAlbumDialogFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.SimplePaddingOfCommodityEvaluateFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.carsuper.coahr.widgets.radioGroup.CustomRadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 门店评价列表
 */
public class StoreEvaluateFragment extends BaseFragment<StoreEvaluateContract.Presenter> implements StoreEvaluateContract.View {

    @Inject
    StoreEvaluatePresenter storeEvaluatePresenter;
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
    private String storeId;
    private LinearLayoutManager linearLayoutManager;
    private StoreEvaluateAdapter storeEvaluateAdapter;

    private List<StoreEvaluateBean.JdataEntity.CommentEntity> commentEntities = new ArrayList<>();

    public static StoreEvaluateFragment newInstance(String s_id) {
        StoreEvaluateFragment fragment = new StoreEvaluateFragment();
        Bundle args = new Bundle();
        args.putString("s_id", s_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public StoreEvaluateContract.Presenter getPresenter() {
        return storeEvaluatePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_evaluate;
    }

    @Override
    public void initView() {
        tbTittle.getTvTittle().setText("门店评价");


    }

    @Override
    public void initData() {
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        storeEvaluateAdapter = new StoreEvaluateAdapter();
        rvEvaluation.setLayoutManager(linearLayoutManager);
        rvEvaluation.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.decoration_f5f5f8)));
        rvEvaluation.setAdapter(storeEvaluateAdapter);
        storeId = getArguments().getString("s_id");
        rgEvaluteFilter.setOnRadioChangeLisener(new CustomRadioGroup.OnRadioChangeLisener() {
            @Override
            public void Onchange(View changeTo, int positon) {
                Map map = new HashMap();
                map.put("s_id", storeId);
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
                getPresenter().getCommentList(map);
            }
        });
        storeEvaluateAdapter.setOnStoreEvaluateItemClickListener(new StoreEvaluateItemClickListener() {
            @Override
            public void onItemClick(StoreEvaluateBean.JdataEntity.CommentEntity entity) {
                start(StoreEvaluateDetailFragment.newInstance(entity.getAo_id(), StoreEvaluateDetailFragment.ACTION_SHOW));
            }

            @Override
            public void onPhotoItemClick(int position, List<String> commentPicEntities) {
                PhotoAlbumDialogFragment fragment = PhotoAlbumDialogFragment.newInstance();
                fragment.setImgList(commentPicEntities);
                fragment.setFirstSeePosition(position);
                FragmentManager fragmentManager = getFragmentManager();
                fragment.show(fragmentManager, TAG);
            }

            /**
             * 查看评论详情
             * @param entity
             */
            @Override
            public void onGoToEvaluateClick(StoreEvaluateBean.JdataEntity.CommentEntity entity) {
                start(StoreEvaluateDetailFragment.newInstance(entity.getAo_id(), StoreEvaluateDetailFragment.ACTION_EVALUATE));

            }

            @Override
            public void onZanClick(int position, StoreEvaluateBean.JdataEntity.CommentEntity entity) {
                dianzanPosition = position;
                Map map = new HashMap();
                map.put("ao_id", entity.getAo_id());
                map.put("token", Constants.token);
                getPresenter().dianZan(map);
            }

            /**
             * 查看评论详情
             * @param position
             * @param entity
             */
            @Override
            public void onOpenReplay(int position, StoreEvaluateBean.JdataEntity.CommentEntity entity) {
                start(StoreEvaluateDetailFragment.newInstance(entity.getAo_id(), StoreEvaluateDetailFragment.ACTION_EVALUATE));
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
    public void onGetCommentsSuccess(StoreEvaluateBean bean) {
        rgEvaluteFilter.setVisibility(View.VISIBLE);
        commentEntities = bean.getJdata().getComment();
        storeEvaluateAdapter.setNewData(commentEntities);
        tvHoleCount.setText("全部(" + bean.getJdata().getCount_all() + ")");
        tvLowCount.setText("低分(" + bean.getJdata().getCount_low_score() + ")");
        tvPushimgCount.setText("晒图(" + bean.getJdata().getCount_pic() + ")");
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

        } else {
            Toast.makeText(BaseApplication.mContext, "点赞成功", Toast.LENGTH_LONG).show();

            commentEntities.get(dianzanPosition).setPraise(Integer.parseInt(commentEntities.get(dianzanPosition).getPraise()) + 1 + "");

        }
        storeEvaluateAdapter.notifyItemChanged(dianzanPosition);
    }

    @Override
    public void onDianZanFailure(String failure) {

        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

}
