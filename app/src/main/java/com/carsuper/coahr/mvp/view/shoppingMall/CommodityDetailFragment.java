package com.carsuper.coahr.mvp.view.shoppingMall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.shoppingMall.CommodityDetailContract;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean.JdataEntity.CommentEntity;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean.JdataEntity.CommodityDescriptionEntity;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean.JdataEntity.CommodityEntity;
import com.carsuper.coahr.mvp.model.bean.CommodityDetailBean.JdataEntity.RecommendCommodityEntity;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.shoppingMall.CommodityDetailPresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.adapter.commodityDetail.CommodityDetailAdapter;
import com.carsuper.coahr.mvp.view.adapter.commodityDetail.CommodityRecomentAdapter;
import com.carsuper.coahr.mvp.view.adapter.commodityDetail.CommodityDetailEvaluataImgAdapter;
import com.carsuper.coahr.mvp.view.adapter.commodityDetail.RecomentItemClickListener;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.MyAddressFragment;
import com.carsuper.coahr.utils.ComputeUsableHeight;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.NavigationBarUtils;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.SetCustomBannerUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.Banner.BannerItemClickListener;
import com.carsuper.coahr.widgets.Banner.BannerView;
import com.carsuper.coahr.widgets.CircleImageView;
import com.carsuper.coahr.widgets.CommodityCountView;
import com.carsuper.coahr.widgets.StarBar;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.donkingliang.banner.CustomBanner;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 商品详情
 */
public class CommodityDetailFragment extends BaseFragment<CommodityDetailContract.Presenter> implements CommodityDetailContract.View, View.OnClickListener {

    @Inject
    CommodityDetailPresenter commodityDetailPresenter;

    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tbl_commodity_detail)
    TabLayout tblCommodityDetail;

    @BindView(R.id.customBanner)
    CustomBanner<String> customBanner;
    @BindView(R.id.tv_commodity_info)
    TextView tvCommodityInfo;
    @BindView(R.id.tv_commodity_price)
    TextView tvCommodityPrice;
    @BindView(R.id.ccv_commodity_count)
    CommodityCountView ccvCommodityCount;
    @BindView(R.id.tv_transport)
    TextView tvTransport;
    @BindView(R.id.tv_user_receiving_address)
    TextView tvUserReceivingAddress;
    @BindView(R.id.rl_transport)
    RelativeLayout rlTransport;
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
    @BindView(R.id.tv_more_evaluate)
    TextView tvMoreEvaluate;
    @BindView(R.id.rv_commodity_detail)
    RecyclerView rvCommodityDetail;
    @BindView(R.id.rv_recommended_collocation)
    RecyclerView rvRecommendedCollocation;
    @BindView(R.id.scv_commodity_detail)
    NestedScrollView scvCommodityDetail;
    @BindView(R.id.tv_immediately_pay)
    TextView tvImmediatelyPay;
    @BindView(R.id.tv_put_in_shoppingcart)
    TextView tvPutInShoppingcart;
    @BindView(R.id.ll_commodity_evaluate)
    LinearLayout llCommodityEvaluate;
    @BindView(R.id.ll_commodity_detail)
    LinearLayout llCommodityDetail;
    @BindView(R.id.ll_commodity_suggestion)
    LinearLayout llCommoditySuggestion;
    @BindView(R.id.rl_evaluation)
    RelativeLayout rlEvaluation;
    @BindView(R.id.tv_no_evaluation)
    TextView tvNoEvaluation;
   /* @BindView(R.id.topFragment)
    FrameLayout topFragment;*/

    @BindView(R.id.fragment_center)
    FrameLayout fragment_center;

    private String commodityId;
    private CommodityDescriptionEntity commodityDescriptionEntities;
    private CommodityEntity commodityEntities;
    private List<RecommendCommodityEntity> recommendCommodityEntities;
    private CommentEntity commentEntity;

    private LinearLayoutManager detailLinearLayoutManager;
    private LinearLayoutManager evaluateImgGridLayoutManager;
    private GridLayoutManager recommendGridLayoutManager;

    private CommodityDetailAdapter commodityDetailAdapter;
    private CommodityRecomentAdapter commodityRecomentAdapter;
    private CommodityDetailEvaluataImgAdapter evaluataImgAdapter;

    private float price;
    private String commodityIMG;
    private int count ;
    private String ua_id;

    public static CommodityDetailFragment newInstance(String id) {
        CommodityDetailFragment commodityDetailFragment = new CommodityDetailFragment();
        Bundle args = new Bundle();
        args.putString("c_id", id);
        commodityDetailFragment.setArguments(args);
        return commodityDetailFragment;
    }

    @Override
    public CommodityDetailContract.Presenter getPresenter() {
        return commodityDetailPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_commoditydetail;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       /* if (NavigationBarUtils.hasNavigationBarFun(getActivity())){
            if (NavigationBarUtils.isNavigationBarShow(getActivity())){
                NavigationBarUtils.setNavigationColor(getActivity(),getResources().getColor(R.color.material_white));
            }
        }*/
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void initView() {

        tbTittle.getLeftIcon().setOnClickListener(this);
        rlTransport.setOnClickListener(this);
        tvMoreEvaluate.setOnClickListener(this);
        tvImmediatelyPay.setOnClickListener(this);
        tvPutInShoppingcart.setOnClickListener(this);
        tblCommodityDetail.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        scvCommodityDetail.scrollTo(0, customBanner.getTop());
                        break;
                    case 1:
                        scvCommodityDetail.scrollTo(0, llCommodityEvaluate.getTop());
                        break;
                    case 2:
                        scvCommodityDetail.scrollTo(0, fragment_center.getTop());
                        break;
                    case 3:
                        scvCommodityDetail.scrollTo(0, llCommoditySuggestion.getTop());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void initData() {
        //Imageloader.setViewSize(topFragment,ScreenUtils.getScreenWidth(BaseApplication.mContext),ScreenUtils.getScreenHeight(BaseApplication.mContext)/2,null);

        Imageloader.setViewSize(customBanner,ScreenUtils.getScreenWidth(BaseApplication.mContext),ScreenUtils.getScreenWidth(BaseApplication.mContext)/16*9,3);
        commodityId =  getArguments().getString("c_id");
        commodityDetailAdapter = new CommodityDetailAdapter(); //产品详情
        commodityRecomentAdapter = new CommodityRecomentAdapter();  //推荐搭配
        evaluataImgAdapter = new CommodityDetailEvaluataImgAdapter();  //商品评价
        detailLinearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        evaluateImgGridLayoutManager = new GridLayoutManager(BaseApplication.mContext, 3);
        recommendGridLayoutManager = new GridLayoutManager(BaseApplication.mContext, 2);
        rvCommodityDetail.setLayoutManager(detailLinearLayoutManager);
        rvEvaluatePushimg.setLayoutManager(evaluateImgGridLayoutManager);
        rvRecommendedCollocation.setLayoutManager(recommendGridLayoutManager);
        rvRecommendedCollocation.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext, 2),DensityUtils.dp2px(BaseApplication.mContext, 2),getResources().getColor(R.color.material_grey_300)));
        rvCommodityDetail.setAdapter(commodityDetailAdapter);
        rvRecommendedCollocation.setAdapter(commodityRecomentAdapter);

        commodityRecomentAdapter.setItemClickListener(new RecomentItemClickListener() {
            @Override
            public void onItemClick(RecommendCommodityEntity entity) {
                start(CommodityDetailFragment.newInstance(entity.getC_id()));
            }
        });
        rvEvaluatePushimg.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,4),DensityUtils.dp2px(BaseApplication.mContext,4)));
        rvEvaluatePushimg.setAdapter(evaluataImgAdapter);

        Map map = new HashMap();
        map.put("c_id", commodityId);
        map.put("token",Constants.token);
        getPresenter().getCommodityDetail(map);
    }



    @Override
    public void onGetCommodityDetailSuccess(CommodityDetailBean bean) {

        commodityDescriptionEntities = bean.getJdata().getCommodity_description();
        commodityEntities = bean.getJdata().getCommodity();
        recommendCommodityEntities = bean.getJdata().getRecommend_commodity();
        commentEntity = bean.getJdata().getComment();
        commodityIMG =  commodityEntities.getC_thumbnail();
        List<String> cp_path = commodityEntities.getCp_path();
        if (cp_path !=null && cp_path.size()>0){
            SetCustomBannerUtils.setCustomBanner(customBanner,cp_path,ImageView.ScaleType.FIT_CENTER);
        }

        price = Float.parseFloat(commodityEntities.getC_price());

        tvCommodityInfo.setText(commodityEntities.getC_name());
        tvCommodityPrice.setText("¥"+commodityEntities.getC_price());
        if (bean.getJdata().getAddress() != null) {
            ua_id=bean.getJdata().getAddress().getUa_id();
            tvUserReceivingAddress.setText(bean.getJdata().getAddress().getAddress());
        }

        /**
         * 评论
         */
        if ( commentEntity != null &&Integer.parseInt(commentEntity.getComment_count())>0 ) {
                rlEvaluation.setVisibility(View.VISIBLE);
                tvNoEvaluation.setVisibility(View.GONE);
                tvMoreEvaluate.setVisibility(View.VISIBLE);
                tvUserName.setText(commentEntity.getNickname());
                Imageloader.loadCircularImage(commentEntity.getUserheadimg(), ivUserHeaderimg);
                tvEvaluateTime.setText(commentEntity.getCreate_time());
                if (!TextUtils.isEmpty(commentEntity.getLevel_score())) {
                    sbEvaluate.setStarMark(Float.parseFloat(commentEntity.getLevel_score()));
                }
                tvEvaluateMessage.setText(commentEntity.getComment());
                tvMoreEvaluate.setText("查看全部评论(" + commentEntity.getComment_count() + ")");
               // KLog.d("商品评论", commentEntity.getNickname(), commentEntity.getComment(), commentEntity.getComment_count(),commentEntity.getLevel_score());
                if (commentEntity.getComment_pic() != null && commentEntity.getComment_pic().size() > 0) {

                    rvEvaluatePushimg.setVisibility(View.VISIBLE);
                    evaluataImgAdapter.setNewData(commentEntity.getComment_pic());
                } else {
                    rvEvaluatePushimg.setVisibility(View.GONE);
                }
            }else {
                tvNoEvaluation.setVisibility(View.VISIBLE); ////暂无评论显示
                rlEvaluation.setVisibility(View.GONE);  //隐藏评论区
            tvMoreEvaluate.setVisibility(View.GONE);
            }


        commodityRecomentAdapter.setNewData(recommendCommodityEntities);
        commodityDetailAdapter.setNewData(commodityDescriptionEntities.getDes_pic());
        scvCommodityDetail.scrollTo(0, customBanner.getTop());
    }

    @Override
    public void onGetCommodityDetailFailure(String failure) {
        scvCommodityDetail.scrollTo(0, customBanner.getTop());
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddSuccess(ResultBean bean) {
        Toast.makeText(BaseApplication.mContext,"添加到购物车成功",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onAddFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_LONG).show();

    }

    private void login(){
            Intent intent = new Intent(_mActivity, ContainerActiivty.class);
            intent.putExtra("tofragment", Constants.LOGINFRAGMENT);
            startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_left:
                _mActivity.onBackPressed();
                break;
            case R.id.tv_more_evaluate:
                start(CommodityEvaluateFragment.newInstance(commodityId));
                break;
            case R.id.tv_immediately_pay:
                if (!haslogin()) {
                    login();
                    return;
                }
                count = ccvCommodityCount.getCount();
                CommodityStandardDialogFragment fragment0 = CommodityStandardDialogFragment.newInstance(price,commodityIMG,count,CommodityStandardDialogFragment.TO_BUY);
                fragment0.setdissmissListener(new CommodityStandardDialogFragment.onEnsureDissmissListener() {
                    @Override
                    public void onDissMiss(int to,int count) {
                        //跳转到订单确认
                        start(ConfirmCommodityOrderFragment.newInstance(String.format("cid=%s&num=%s",commodityId,count),ua_id==null?"":ua_id,"商城"));
                    }
                });
                fragment0.show(getFragmentManager(),TAG);
                break;
            case R.id.tv_put_in_shoppingcart:
                if (!haslogin()) {
                    login();
                    return;
                }
                count = ccvCommodityCount.getCount();
                CommodityStandardDialogFragment fragment1 = CommodityStandardDialogFragment.newInstance(price,commodityIMG,count,CommodityStandardDialogFragment.TO_ADD_CART);
                fragment1.setdissmissListener(new CommodityStandardDialogFragment.onEnsureDissmissListener() {
                    @Override
                    public void onDissMiss(int to,int count) {
                        //添加到购物车
                        Map map = new HashMap();
                        map.put("token", Constants.token);
                        map.put("c_id",commodityId);
                        map.put("c_num",String.valueOf(count));
                        getPresenter().addToShoppingCart(map);
                    }
                });
                fragment1.show(getFragmentManager(),TAG);
                break;

            case R.id.rl_transport:
                startForResult(MyAddressFragment.newInstance(Constants.COMMODITYDETAILFRAGMENT),1);
                break;
        }
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==1) {
            if (data != null) {
                ua_id = data.getString("ua_id");
                tvUserReceivingAddress.setText(data.getString("address"));
            }
        }

    }
}
