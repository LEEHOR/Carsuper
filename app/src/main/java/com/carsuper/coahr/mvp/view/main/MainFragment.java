package com.carsuper.coahr.mvp.view.main;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baidu.location.BDLocation;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.main.MainContract;
import com.carsuper.coahr.mvp.model.bean.MainBean;
import com.carsuper.coahr.mvp.presenter.main.MainPresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.adapter.main.ChooseAdapter;
import com.carsuper.coahr.mvp.view.adapter.main.GoodAdapter;
import com.carsuper.coahr.mvp.view.adapter.main.OnChooseItemClickListener;
import com.carsuper.coahr.mvp.view.adapter.main.OnGoodItemClickListener;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.base.BaseLazyFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.setting.JoinUsFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.ShoppingMallFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.Permission.OnRequestPermissionListener;
import com.carsuper.coahr.utils.Permission.RequestPermissionUtils;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.SetCustomBannerUtils;
import com.carsuper.coahr.utils.StringUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.Banner.BannerItemClickListener;
import com.carsuper.coahr.widgets.Banner.BannerView;
import com.carsuper.coahr.widgets.Banner.NewsBannerView;
import com.carsuper.coahr.widgets.Banner.RecyclerviewNewSBanner;
import com.donkingliang.banner.CustomBanner;
import com.socks.library.KLog;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 */
public class MainFragment extends BaseLazyFragment<MainContract.Presenter> implements MainContract.View, View.OnClickListener {

    @Inject
    MainPresenter mPresenter;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.iv_car_wash)
    ImageView ivCarWash;
    @BindView(R.id.tv_car_wash)
    TextView tvCarWash;
    @BindView(R.id.ll_fitting)
    LinearLayout llFitting;
    @BindView(R.id.iv_engine_oil)
    ImageView ivEngineOil;
    @BindView(R.id.tv_engine_oil)
    TextView tvEngineOil;
    @BindView(R.id.ll_engine_oil)
    LinearLayout llEngineOil;
    @BindView(R.id.iv_tyre)
    ImageView ivTyre;
    @BindView(R.id.tv_tyre)
    TextView tvTyre;
    @BindView(R.id.ll_tyre)
    LinearLayout llTyre;
    @BindView(R.id.iv_shopping_cart)
    ImageView ivShoppingCart;
    @BindView(R.id.tv_shopping_cart)
    TextView tvShoppingCart;
    @BindView(R.id.ll_shopping_car)
    LinearLayout llShoppingCar;
    @BindView(R.id.iv_classification)
    ImageView ivClassification;
    @BindView(R.id.tv_classification)
    TextView tvClassification;
    @BindView(R.id.ll_coupon)
    LinearLayout llCoupon;
    @BindView(R.id.iv_activity)
    ImageView ivActivity;
    @BindView(R.id.rv_Discount)
    RecyclerView rvDiscount;
    @BindView(R.id.rv_carefully_chose_tyre)
    RecyclerView rvCarefullyChoseTyre;
    @BindView(R.id.rv_famous_engine_oil)
    RecyclerView rvFamousEngineOil;
    @BindView(R.id.rv_quality_fitting)
    RecyclerView rvQualityFitting;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrframelayout;
    @BindView(R.id.nsv_main)
    NestedScrollView nsvMain;

    @BindView(R.id.tv_more_oil)
    TextView tvMoreOil;
    @BindView(R.id.tv_more_tyre)
    TextView tvMoreTyre;
    //    @BindView(R.id.bv_news)
//    NewsBannerView bvNews;
    @BindView(R.id.tv_changesome)
    TextView tvChangeSome;
    @BindView(R.id.rv_news_banner)
    TextBannerView rvNewsBanner;

    @BindView(R.id.customBanner)
    CustomBanner<String> customBanner;

    private int page = 0;
    private int mScrollHeight = 0;


    private List<String> bannerImgList = new ArrayList<>();
    private List<String> newsList = new ArrayList<>();
    private List<MainBean.JdataEntity.HeadImagesEntity> headImagesEntities = new ArrayList<>();
    private List<MainBean.JdataEntity.AllDatasEntity.NewsEntity> newsEntities = new ArrayList<>();
    private List<MainBean.JdataEntity.AllDatasEntity.ActivityEntity> activityEntities = new ArrayList<>();
    private List<MainBean.JdataEntity.AllDatasEntity.ChooseEntity> chooseEntities = new ArrayList<>();
    private List<MainBean.JdataEntity.AllDatasEntity.GoodsTypeEntity> tyreList = new ArrayList<>();
    private List<MainBean.JdataEntity.AllDatasEntity.GoodsTypeEntity> oilList = new ArrayList<>();
    private List<MainBean.JdataEntity.AllDatasEntity.GoodsTypeEntity> fittingList = new ArrayList<>();

    private ChooseAdapter chooseAdapter;
    private GoodAdapter tyreAdapter;
    private GoodAdapter oilAdapter;
    private GoodAdapter fittingAdapter;

    private LinearLayoutManager chooseLinearlayoutmanager;
    private GridLayoutManager tyreLinearLayoutManager;
    private GridLayoutManager oilLinearLayoutManager;
    private GridLayoutManager fittingLinearLayoutManager;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private OnGoodItemClickListener onGoodItemClickListener = new OnGoodItemClickListener() {
        @Override
        public void onItemClick(MainBean.JdataEntity.AllDatasEntity.GoodsTypeEntity entity) {
            Intent intent = new Intent(_mActivity, ContainerActiivty.class);
            intent.putExtra("tofragment", Constants.COMMODITYDETAILFRAGMENT);
            intent.putExtra("c_id", entity.getC_id());
            startActivity(intent);
        }
    };
    private OnChooseItemClickListener onChooseItemClickListener = new OnChooseItemClickListener() {
        @Override
        public void onItemClick(MainBean.JdataEntity.AllDatasEntity.ChooseEntity entity) {
            Intent intent = new Intent(_mActivity, ContainerActiivty.class);
            intent.putExtra("tofragment", Constants.COMMODITYDETAILFRAGMENT);
            intent.putExtra("c_id", entity.getC_id());
            startActivity(intent);
        }
    };


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (TextUtils.isEmpty(Constants.pointCity)) {
                tvCity.setText(Constants.pointCity);
            }
        }
    }

    @Override
    public MainContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public int bindLayout() {

        return R.layout.fragment_main;
    }

    @Override
    public void initView() {

        initPtrFrameLayout(ptrframelayout);
        llCoupon.setOnClickListener(this);
        llEngineOil.setOnClickListener(this);
        llFitting.setOnClickListener(this);
        llShoppingCar.setOnClickListener(this);
        llTyre.setOnClickListener(this);
        tvMoreOil.setOnClickListener(this);
        tvMoreTyre.setOnClickListener(this);
        tvCity.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        tvChangeSome.setOnClickListener(this);
        ivActivity.setOnClickListener(this);
        nsvMain.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                mScrollHeight = scrollY;
            }
        });




    }

    @Override
    public void initData() {
        //设置轮播图大小
        Imageloader.setViewSize(customBanner,ScreenUtils.getScreenWidth(BaseApplication.mContext),ScreenUtils.getScreenWidth(BaseApplication.mContext)*9/16,null);

        //设置爆款图片大小
        Imageloader.setViewSize(ivActivity,ScreenUtils.getScreenWidth(BaseApplication.mContext),ScreenUtils.getScreenWidth(BaseApplication.mContext)/2,ImageView.ScaleType.FIT_CENTER);

        tyreLinearLayoutManager = new GridLayoutManager(BaseApplication.mContext, 3);
        tyreAdapter = new GoodAdapter();
        tyreAdapter.setOnGoodItemClickListener(onGoodItemClickListener);
        rvCarefullyChoseTyre.setLayoutManager(tyreLinearLayoutManager);
        rvCarefullyChoseTyre.setAdapter(tyreAdapter);
       //   rvCarefullyChoseTyre.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,2),0,getResources().getColor(R.color.material_grey_200)));

        oilLinearLayoutManager = new GridLayoutManager(BaseApplication.mContext, 3);
        oilAdapter = new GoodAdapter();
        oilAdapter.setOnGoodItemClickListener(onGoodItemClickListener);
        rvFamousEngineOil.setLayoutManager(oilLinearLayoutManager);
        rvFamousEngineOil.setAdapter(oilAdapter);
       // rvFamousEngineOil.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,2),0,getResources().getColor(R.color.material_grey_200)));

        fittingLinearLayoutManager = new GridLayoutManager(BaseApplication.mContext, 3);
        fittingAdapter = new GoodAdapter();
        fittingAdapter.setOnGoodItemClickListener(onGoodItemClickListener);
        rvQualityFitting.setLayoutManager(fittingLinearLayoutManager);
        rvQualityFitting.setAdapter(fittingAdapter);
       // rvQualityFitting.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,2),0,getResources().getColor(R.color.material_grey_200)));


        chooseLinearlayoutmanager = new LinearLayoutManager(BaseApplication.mContext, LinearLayoutManager.HORIZONTAL, false);
        chooseAdapter = new ChooseAdapter();
        chooseAdapter.setOnChooseItemClickListener(onChooseItemClickListener);
        rvDiscount.setLayoutManager(chooseLinearlayoutmanager);
        rvDiscount.setAdapter(chooseAdapter);
        //rvDiscount.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,2),0,getResources().getColor(R.color.material_grey_200)));


        Map map = new HashMap();
        map.put("page", page + "");
        getPresenter().requestMain(map);
        getLocationPermission();
        // getPresenter().startLocation();
    }

    @Override
    public void RefreshBegin() {
        Map map = new HashMap();
        page = 0;
        map.put("page", page + "");
        getPresenter().requestMain(map);
    }

    @Override
    public boolean isCanDoRefresh() {
        return mScrollHeight == 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_city:
                CityPickerDialogFragment cityPickerDialogFragment = CityPickerDialogFragment.newInstance(R.style.top_in_out_animation);
                cityPickerDialogFragment.setOnCityPickListener(new CityPickerDialogFragment.onCityPickListener() {
                    @Override
                    public void onCitypick(final String city) {
                        if (Constants.pointCity.equals(city)) {
                            return;
                        }
                        new MaterialDialog.Builder(getActivity())
                                .title("提示")
                                .content("您当前定位在" + Constants.pointCity + ",是否切换到" + city)
                                .negativeText("取消")
                                .positiveText("确认")
                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                    }
                                }).onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                tvCity.setText(city);
                                Constants.pointCity = city;
                            }
                        }).build().show();
                    }
                });
                FragmentManager fragmentManager = getFragmentManager();
                cityPickerDialogFragment.show(fragmentManager.beginTransaction(), TAG);
                break;
            case R.id.tv_search:
                Intent intent = new Intent(_mActivity, ContainerActiivty.class);
                intent.putExtra("tofragment", Constants.SEARCHFRAGMENT);
                startActivity(intent);
                break;
            case R.id.tv_changesome:
                Map map = new HashMap();
                map.put("page", page + "");
                getPresenter().changeSomeFitting(map);
                break;
            case R.id.tv_more_oil:
                Intent intent1 = new Intent(_mActivity, ContainerActiivty.class);
                intent1.putExtra("selectedClassification", "机油");
                intent1.putExtra("tofragment", Constants.SHOPPINGMALLFRAGMENT);
                _mActivity.startActivity(intent1);
                break;
            case R.id.tv_more_tyre:
                Intent intent2 = new Intent(_mActivity, ContainerActiivty.class);
                intent2.putExtra("selectedClassification", "轮胎");
                intent2.putExtra("tofragment", Constants.SHOPPINGMALLFRAGMENT);
                _mActivity.startActivity(intent2);
                break;

            case R.id.ll_tyre:
                Intent intent3 = new Intent(_mActivity, ContainerActiivty.class);
                intent3.putExtra("selectedClassification", "轮胎");
                intent3.putExtra("tofragment", Constants.SHOPPINGMALLFRAGMENT);
                _mActivity.startActivity(intent3);
                break;
            case R.id.ll_engine_oil:
                Intent intent4 = new Intent(_mActivity, ContainerActiivty.class);
                intent4.putExtra("selectedClassification", "机油");
                intent4.putExtra("tofragment", Constants.SHOPPINGMALLFRAGMENT);
                _mActivity.startActivity(intent4);
                break;
            case R.id.ll_fitting:
                Intent intent5 = new Intent(_mActivity, ContainerActiivty.class);
                intent5.putExtra("selectedClassification", "配件");
                intent5.putExtra("tofragment", Constants.SHOPPINGMALLFRAGMENT);
                _mActivity.startActivity(intent5);
                break;
            case R.id.ll_shopping_car:
                    Intent intent6 = new Intent(_mActivity, ContainerActiivty.class);
                    intent6.putExtra("tofragment", Constants.FragmentExchange);
                    _mActivity.startActivity(intent6);
                break;
            case R.id.ll_coupon:
                if (haslogin()){
                    Intent intent7 = new Intent(_mActivity, ContainerActiivty.class);
                    intent7.putExtra("tofragment", Constants.MYRECIVECOUPON);
                    intent7.putExtra("toform","首页");
                    startActivity(intent7);
                } else {
                    Toast.makeText(BaseApplication.mContext, "当前未登录", Toast.LENGTH_LONG).show();
                }

                break;
            case  R.id.iv_activity:
                if (activityEntities.get(0).getAdv_url() != null && ! activityEntities.get(0).getAdv_url().equals("")) {
                    Intent intent8=new Intent(_mActivity, ContainerActiivty.class);
                    intent8.putExtra("tofragment",Constants.MyMainFragment);
                    intent8.putExtra("url",activityEntities.get(0).getAdv_url());
                    intent8.putExtra("type",3);
                    intent8.putExtra("title",activityEntities.get(0).getAdv_title());
                    startActivity(intent8);
                }
                break;
        }
    }

    @Override
    public void onRequestMainSuccess(MainBean mainBean) {
        page = 0;
        headImagesEntities = mainBean.getJdata().getHeadImages();
        bannerImgList.clear();
        for (MainBean.JdataEntity.HeadImagesEntity entity : headImagesEntities) {
            bannerImgList.add(entity.getAdv_img());
        }
        if (bannerImgList != null && bannerImgList.size() > 0) {
            SetCustomBannerUtils.setCustomBanner(customBanner,bannerImgList,ImageView.ScaleType.FIT_CENTER);
            setBannerLister(headImagesEntities,customBanner);
        }
        newsEntities = mainBean.getJdata().getAllDatas().getNews();
        setTextBanner(rvNewsBanner,newsEntities);
       
       // if (newsList != null && newsList.size() > 0) {
           // rvNewsBanner.setNewsList(newsList);
           // rvNewsBanner.startLoop(true);
         //   setTextBanner(rvNewsBanner,newsList);

      //  }
        activityEntities = mainBean.getJdata().getAllDatas().getActivity();
        Imageloader.loadImage_larger(activityEntities.get(0).getAdv_img(), ivActivity);

        tyreList = mainBean.getJdata().getAllDatas().getGoodsType1();
        tyreAdapter.setNewData(tyreList);

        oilList = mainBean.getJdata().getAllDatas().getGoodsType2();
        oilAdapter.setNewData(oilList);

        fittingList = mainBean.getJdata().getAllDatas().getGoodsType3();
        fittingAdapter.setNewData(fittingList);

        chooseEntities = mainBean.getJdata().getAllDatas().getChoose();
        chooseAdapter.setNewData(chooseEntities);

    }

    @Override
    public void onRequestMainFailure(String failure) {
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onChangeSuccess(MainBean mainBean) {
        page++;
        if (fittingList != null) {
            fittingList.clear();
            fittingList.addAll(mainBean.getJdata().getAllDatas().getGoodsType3());
            fittingAdapter.setNewData(fittingList);
        }
    }

    @Override
    public void onChangeFailure(String failure) {
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
        if (!TextUtils.isEmpty(Constants.pointCity)) {
            Constants.pointCity = StringUtils.getCityName(Constants.locationcity);
            tvCity.setText(Constants.pointCity);
        }
    }

    @Override
    public void onLocationFailure(int failure) {
        Toast.makeText(BaseApplication.mContext, "定位失败，请开启权限，保持网络通畅", Toast.LENGTH_LONG).show();

    }

    /**
     * 文字轮播设置数据和点击监听
     * @param
     */
    private void setTextBanner(TextBannerView textBanner, final List<MainBean.JdataEntity.AllDatasEntity.NewsEntity> new_list){
        newsList.clear();
        for (MainBean.JdataEntity.AllDatasEntity.NewsEntity newsEntity : new_list) {
            newsList.add(newsEntity.getTitle());
        }
       
        textBanner.setDatas(newsList);
        textBanner.setItemOnClickListener(new ITextBannerItemClickListener() {
            @Override
            public void onItemClick(String data, int position) {
                String n_id = new_list.get(position).getN_id(); //新闻id
                Intent intent=new Intent(_mActivity, ContainerActiivty.class);
                intent.putExtra("tofragment",Constants.MyMainFragment);
                intent.putExtra("url",Constants.newsUrl+n_id);
                intent.putExtra("type",1);
                intent.putExtra("title",new_list.get(position).getTitle());
                KLog.d("banner链接",Constants.newsUrl+n_id);
                startActivity(intent);
            }
        });
    }

    /**
     * 防止文字轮播重影
     */
    @Override
    public void onStop() {
        super.onStop();
        rvNewsBanner.stopViewAnimator();
    }

    @Override
    public void onResume() {
        super.onResume();
        rvNewsBanner.startViewAnimator();
    }

    private void setBannerLister(final List<MainBean.JdataEntity.HeadImagesEntity> lists, CustomBanner customBanner){
        customBanner.setOnPageClickListener(new CustomBanner.OnPageClickListener() {
            @Override
            public void onPageClick(int i, Object o) {
                String n_id = lists.get(i).getId(); //新闻id
                Intent intent=new Intent(_mActivity, ContainerActiivty.class);
                intent.putExtra("tofragment",Constants.MyMainFragment);
                intent.putExtra("url",Constants.bannerUrl+n_id);
                intent.putExtra("type",3);
                intent.putExtra("title",lists.get(i).getAdv_title());
                KLog.d("新闻链接",Constants.bannerUrl+n_id);
                startActivity(intent);
            }
        });
    }

    /**
     * 动态获取权限
     */
    private void getLocationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RequestPermissionUtils.requestPermission(getActivity(), new OnRequestPermissionListener() {
                @Override
                public void PermissionSuccess(List<String> permissions) {
                    getPresenter().startLocation();
                }

                @Override
                public void PermissionFail(List<String> permissions) {
                    Toast.makeText(getActivity(), "获取权限失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void PermissionHave() {
                    getPresenter().startLocation();
                }
            }, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE);

        } else {
            getPresenter().startLocation();
        }

    }


}
