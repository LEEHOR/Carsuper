package com.carsuper.coahr.mvp.view.store;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.store.StoreDetailContract;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean.JdataEntity.CommentOneEntity;
import com.carsuper.coahr.mvp.model.bean.StoreDetailBean.JdataEntity.StationEntity;


import com.carsuper.coahr.mvp.presenter.store.StoreDetailPresenter;
import com.carsuper.coahr.mvp.view.MainActivity;
import com.carsuper.coahr.mvp.view.adapter.storedetail.ExplainItemClickListener;
import com.carsuper.coahr.mvp.view.adapter.storedetail.RepairServiceAdapter;
import com.carsuper.coahr.mvp.view.adapter.storedetail.StoreDetailEvaluateIMGadapter;
import com.carsuper.coahr.mvp.view.adapter.storedetail.StoreDetailLableAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.MapUtils;
import com.carsuper.coahr.utils.SetCustomBannerUtils;
import com.carsuper.coahr.widgets.Banner.BannerItemClickListener;
import com.carsuper.coahr.widgets.TipView;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.Banner.BannerView;
import com.carsuper.coahr.widgets.CircleImageView;
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
import butterknife.OnFocusChange;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 门店详情
 */
public class StoreDetailFragment extends BaseFragment<StoreDetailContract.Presenter> implements StoreDetailContract.View, View.OnClickListener {


    @Inject
    StoreDetailPresenter storeDetailPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_store_info)
    TextView tvStoreInfo;
    @BindView(R.id.tv_store_business_time)
    TextView tvStoreBusinessTime;
    @BindView(R.id.rv_store_lable)
    RecyclerView rvStoreLable;
    @BindView(R.id.iv_store_repair_union)
    ImageView ivStoreRepairUnion;
    @BindView(R.id.tv_user_receiving_address)
    TextView tvUserReceivingAddress;
    @BindView(R.id.tv_store_distance)
    TextView tvStoreDistance;
    @BindView(R.id.rl_transport)
    RelativeLayout rlTransport;
    @BindView(R.id.ll_store_evaluate)
    LinearLayout llStoreEvaluate;
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
    @BindView(R.id.ll_store_detail)
    LinearLayout llStoreDetail;
    @BindView(R.id.rv_range_for_repair)
    RecyclerView rvRangeForRepair;
    @BindView(R.id.scv_store_detail)
    NestedScrollView scvStoreDetail;
    @BindView(R.id.tv_contact_store)
    TextView tvContactStore;
    @BindView(R.id.tv_goto_store)
    TextView tvGotoStore;
    @BindView(R.id.rl_evaluation)
    RelativeLayout rlEvaluation;
    @BindView(R.id.tv_no_evaluation)
    TextView tvNoEvaluation;

    /**
     * 轮播图
     */
    @BindView(R.id.customBanner)
    CustomBanner customBanner;
    private List<String> storeImgList = new ArrayList<>();
    private List<StationEntity.TurnPicEntity> turnPicEntities = new ArrayList<>();
    private List<StationEntity.SServiceTagEntity> sServiceTagEntities = new ArrayList<>();
    private List<StoreDetailBean.JdataEntity.ServiceEntity> serviceEntities = new ArrayList<>();
    private CommentOneEntity commentEntity;
    private StationEntity stationEntity;


    private StaggeredGridLayoutManager lablegridLayoutManager;
    private LinearLayoutManager evaluateImgGridLayoutManager;
    private LinearLayoutManager servicelinearLayoutManager;

    private StoreDetailEvaluateIMGadapter evaluataImgAdapter;
    private RepairServiceAdapter repairServiceAdapter;
    private StoreDetailLableAdapter storeDetailLableAdapter;
    private float disatance;
    private String id;


    private TipView tipView;
    private int tipX;
    private int tipY;
    private TextView tipText;
    private ImageView closeIMG;

    private int lastScrollPosition;

    private double s_longitude;
    private double s_latitude;

    private String phone;//门店电话
    private NavigatorDialogFragment navigatorDialogFragment = NavigatorDialogFragment.newInstance();

    public static StoreDetailFragment newInstance(String id) {
        StoreDetailFragment commodityDetailFragment = new StoreDetailFragment();
        Bundle args = new Bundle();
        args.putString("s_id", id);
        commodityDetailFragment.setArguments(args);
        return commodityDetailFragment;
    }

    @Override
    public StoreDetailContract.Presenter getPresenter() {
        return storeDetailPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_storedetail;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        tvContactStore.setOnClickListener(this);
        tvGotoStore.setOnClickListener(this);
        tvMoreEvaluate.setOnClickListener(this);
        scvStoreDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (tipView != null && tipView.getContentView() != null && tipView.isShowing()) {
                    int distanceX = scrollX - oldScrollX;
                    int distanceY = scrollY - oldScrollY;
                    lastScrollPosition = scrollY;
                    tipView.scrllTo(tipView.getContentX() - distanceX, tipView.getContentY() - distanceY);
                }
            }
        });


        navigatorDialogFragment.setOnNavigatiorItemSelectListener(new NavigatorDialogFragment.NavigatorItemSelectListener() {
            @Override
            public void onItemSelct(String mapName) {
                if (mapName.equals("baidu")) {
                 // 调用百度地图客户端
                    if (MapUtils.isInstallByRead("com.baidu.BaiduMap")) {
                        MapUtils.getBaiduMapUri(_mActivity, s_latitude + "", s_longitude + "");
                    } else {
                        Toast.makeText(_mActivity, "您尚未安装百度地图", Toast.LENGTH_LONG).show();
                        Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                } else if (mapName.equals("gaode")) {
                  //调用高德地图客户端
                    double[] gd_lat_lon_des = MapUtils.bdToGaoDe(s_latitude, s_longitude);
                    if (MapUtils.isInstallByRead("com.autonavi.minimap")) {
                        MapUtils.getGaoDeMapUri(_mActivity, gd_lat_lon_des[1] + "", gd_lat_lon_des[0] + "");
                    } else {
                        Toast.makeText(_mActivity, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
                        Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
            }
        });
    }


    @Override
    public void initData() {
        evaluataImgAdapter = new StoreDetailEvaluateIMGadapter();
        repairServiceAdapter = new RepairServiceAdapter();
        storeDetailLableAdapter = new StoreDetailLableAdapter();
        evaluateImgGridLayoutManager = new GridLayoutManager(BaseApplication.mContext, 3);
        servicelinearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        lablegridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        rvEvaluatePushimg.setLayoutManager(evaluateImgGridLayoutManager);
        rvEvaluatePushimg.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,4),DensityUtils.dp2px(BaseApplication.mContext,4)));

        rvEvaluatePushimg.setAdapter(evaluataImgAdapter);
        rvRangeForRepair.setLayoutManager(servicelinearLayoutManager);
        rvRangeForRepair.setAdapter(repairServiceAdapter);
        rvStoreLable.setLayoutManager(lablegridLayoutManager);
        rvStoreLable.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,2),DensityUtils.dp2px(BaseApplication.mContext,4)));
        rvStoreLable.setAdapter(storeDetailLableAdapter);
        repairServiceAdapter.setExplainItemClickListener(new ExplainItemClickListener() {
            @Override
            public void onitemClick(TextView view, StoreDetailBean.JdataEntity.ServiceEntity serviceEntity) {
                if (tipView != null && tipView.isShowing()) {
                    tipView.dismiss();
                }
                showTip(view, serviceEntity);
            }
        });
        id = (String) getArguments().get("s_id");
        Map map = new HashMap();
        map.put("s_id", id);
        map.put("longitude",Constants.longitude+"");
        map.put("latitude",Constants.latitude+"");
        getPresenter().getStoreDetail(map);
//        getPresenter().startLocation();
    }


    private void showTip(TextView view, StoreDetailBean.JdataEntity.ServiceEntity serviceEntity) {
        View contentview = LayoutInflater.from(_mActivity).inflate(R.layout.layout_tip_for_storedetail_service, null, false);
        tipText = contentview.findViewById(R.id.tv_explain_tip);
        tipText.setText(serviceEntity.getB_content());
        closeIMG = contentview.findViewById(R.id.iv_close);
        closeIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipView.dismiss();
            }
        });
        tipView = new TipView.Builder(_mActivity)
                .anchorView(view)
                .backgroundColorResourse(R.color.material_blue_700)
                .backgroundAlpha(0.5f)
                .contentView(contentview)
                .build();
        tipView.show();
    }



    @Override
    public void onGetStoreDetailSuccess(StoreDetailBean storeDetailBean) {
        phone = storeDetailBean.getJdata().getS_phone();
        disatance = storeDetailBean.getJdata().getStation().getDistance();
        stationEntity = storeDetailBean.getJdata().getStation();
        turnPicEntities = stationEntity.getTurn_pic();
        sServiceTagEntities = stationEntity.getS_service_tag();
        serviceEntities = storeDetailBean.getJdata().getService();
        commentEntity = storeDetailBean.getJdata().getComment_one();
        s_longitude = Double.parseDouble(storeDetailBean.getJdata().getStation().getS_longitude());
        s_latitude = Double.parseDouble(storeDetailBean.getJdata().getStation().getS_latitude());

        if (turnPicEntities != null && turnPicEntities.size() > 0) {
            storeImgList.clear();
            for (StationEntity.TurnPicEntity turnPicEntity : turnPicEntities) {
                storeImgList.add(turnPicEntity.getPic());
            }
            if (storeImgList !=null && storeImgList.size()>0){

                SetCustomBannerUtils.setCustomBanner(customBanner,storeImgList,ImageView.ScaleType.FIT_XY);
            }

        }
        if (sServiceTagEntities != null && sServiceTagEntities.size() > 0) {
        /*    if (sServiceTagEntities.size() > 4) {
                lablegridLayoutManager.setSpanCount(2);
            } else {
                lablegridLayoutManager.setSpanCount(1);
            }*/
            storeDetailLableAdapter.setNewData(sServiceTagEntities);
        }

        if (!stationEntity.getS_type().equals("0")) {
            ivStoreRepairUnion.setImageResource(R.mipmap.maintenance_alliance);
        }
        tvStoreInfo.setText(stationEntity.getS_name());
        tvStoreBusinessTime.setText("营业时间" + "\t\t" + stationEntity.getS_starttime()+"-"+stationEntity.getS_endtime());
        tvStoreDistance.setText(disatance + "km");
        tvUserReceivingAddress.setText(stationEntity.getS_address());

        if (commentEntity != null &&Integer.parseInt(commentEntity.getComment_count())>0) {
            tvNoEvaluation.setVisibility(View.GONE);
            rlEvaluation.setVisibility(View.VISIBLE);
            tvMoreEvaluate.setVisibility(View.VISIBLE);
            Imageloader.loadCircularImage(commentEntity.getUserheadimg(), ivUserHeaderimg);
            tvUserName.setText(commentEntity.getNickname());
            tvEvaluateTime.setText(commentEntity.getCreate_time());
            rvEvaluatePushimg.setVisibility(View.VISIBLE);
            sbEvaluate.setStarMark(Float.parseFloat(commentEntity.getLevel_score()));
            tvEvaluateMessage.setText(commentEntity.getComment());
            tvMoreEvaluate.setText("查看全部评论(" + storeDetailBean.getJdata().getComment_one().getComment_count() + ")");
            if (commentEntity.getComment_pic().size()>0){  //有无评论
                rvEvaluatePushimg.setVisibility(View.VISIBLE);
                evaluataImgAdapter.setNewData(commentEntity.getComment_pic());
            } else {
                rvEvaluatePushimg.setVisibility(View.GONE);
            }
        }else {
            tvNoEvaluation.setVisibility(View.VISIBLE); //暂无评论显示
            rlEvaluation.setVisibility(View.GONE); //评论区隐藏
            tvMoreEvaluate.setVisibility(View.GONE);
        }
        if (serviceEntities != null && serviceEntities.size() > 0) {
            repairServiceAdapter.setNewData(serviceEntities);
        }

    }

    @Override
    public void onGetStoreDetailFailure(String failure) {
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationSuccess(BDLocation location) {
        Constants.latitude = location.getLatitude();
        Constants.longitude = location.getLongitude();
        String a[] = location.getAddrStr().split("-");
        Constants.locationDistrict = location.getDistrict();
        Constants.locationProvince = location.getProvince();
        Constants.locationAddress = a[0];
        Constants.locationcity = location.getCity();

        Map map = new HashMap();
        map.put("s_id", id);
        map.put("longitude",Constants.longitude);
        map.put("latitude",Constants.latitude);
        getPresenter().getStoreDetail(map);
    }

    @Override
    public void onLocationFailure(int failure) {
        Map map = new HashMap();
        map.put("s_id", id);
        map.put("longitude",Constants.longitude);
        map.put("latitude",Constants.latitude);
        getPresenter().getStoreDetail(map);
        Toast.makeText(BaseApplication.mContext, "定位失败，请开启权限，保持网络通畅", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_more_evaluate:
                start(StoreEvaluateFragment.newInstance(id));
                break;
            case R.id.tv_contact_store:
//                if (!TextUtils.isEmpty(phone)) {
                    call(phone);
//                }else {
//                }
                break;
            case R.id.tv_goto_store:
                navigatorDialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        if (tipView != null && tipView.isShowing()) {
            tipView.dismiss();
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void dispatchTouchEvent(MotionEvent ev) {
//        KLog.e("dispatchTouchEvent"+ev.getAction());
//        if (tipView != null&&tipView.isShowing()) {
//            tipView.dismiss();
//        }
        super.dispatchTouchEvent(ev);
    }

 /*   private void setBanner(ArrayList<String> bannerList){
        customBanner.setPages(new CustomBanner.ViewCreator<String>() {
            @Override
            public View createView(Context context, int i) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, String entity) {
                Imageloader.loadImage(entity,(ImageView) view);
            }
        }, bannerList)
                //设置指示器为普通指示器
//                .setIndicatorStyle(CustomBanner.IndicatorStyle.ORDINARY)
//                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setIndicatorRes(R.drawable.shape_point_select, R.drawable.shape_point_unselect)
//                //设置指示器的方向
//                .setIndicatorGravity(CustomBanner.IndicatorGravity.CENTER)
//                //设置指示器的指示点间隔
//                .setIndicatorInterval(20)
                //设置自动翻页
                .startTurning(5000);
    }*/
}
