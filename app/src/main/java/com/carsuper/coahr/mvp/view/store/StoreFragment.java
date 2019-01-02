package com.carsuper.coahr.mvp.view.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.store.StoreContract;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean;
import com.carsuper.coahr.mvp.model.bean.StoreBean;
import com.carsuper.coahr.mvp.presenter.store.StorePresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.adapter.citypicker.InnerListener;
import com.carsuper.coahr.mvp.view.adapter.store.StoreAdapter;
import com.carsuper.coahr.mvp.view.adapter.store.StoreItemClickListener;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseLazyFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.widgets.ConditionSelectView;
import com.carsuper.coahr.widgets.conditionMenu.StoreConditionMenu;
import com.carsuper.coahr.widgets.conditionMenu.OnMenuItemClickListener;
import com.carsuper.coahr.widgets.conditionMenu.OnStateChangeListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 * 门店页
 */
public class StoreFragment extends BaseLazyFragment<StoreContract.Presenter> implements StoreContract.View {

    @Inject
    WindowManager windowManager;

    @Inject
    StoreConditionMenu storeConditionMenu;
    @Inject
    StorePresenter storePresenter;

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rl_store_condition)
    ConditionSelectView rlStoreCondition;
    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrframelayout;

    @BindView(R.id.store_sort)
    TextView store_sort;
    @BindView(R.id.store_city_select)
    TextView  store_city_select;


    private LinearLayoutManager linearLayoutManager;
    private StoreAdapter storeAdapter;
    private List<CityInfoBean.JdataEntity.CityListEntity.CityEntity> cityEntities = new ArrayList<>();
    private List<StoreBean.JdataEntity.StationEntity> stationEntities = new ArrayList<>();


    private List<String> citylist = new ArrayList<>();//城市
    private List<String> sortList = new ArrayList<>();

    private static String[] sortIdArray = new String[3];//排序
    private static String[] sortStringArray = new String[3];//排序

    private int currentPage = 0;//当前页码
    private int pageLength = 10;//每页数据条数
    //    private boolean isLoading = false;
    private int lastVisibleItemPosition;//rvStore最近展示的itemposition

    private int conditionItemShow = 100;//条件选择器，哪一个正在显示
    private int selectCityPositon = 0xffffff;
    private int selectedSortPosition = 0xffffff;

    private String selectedCity = "";
    private String selectedSort = "";
    private boolean isLoading; //判断是否在加载

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        if (storeConditionMenu.isMenuOpen()) {
            storeConditionMenu.dismiss();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public StoreContract.Presenter getPresenter() {
        return storePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_store;
    }

    @Override
    public void initView() {
        initPtrFrameLayout(ptrframelayout);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_mActivity, ContainerActiivty.class);
                intent.putExtra("tofragment", Constants.SEARCHFRAGMENT);
                startActivity(intent);
            }
        });
        storeConditionMenu.setAnchor(rlStoreCondition);
        store_city_select.setText("全国");
    }

    @Override
    public void initData() {
        sortIdArray = _mActivity.getResources().getStringArray(R.array.store_sortIdArray);
        sortStringArray = _mActivity.getResources().getStringArray(R.array.store_ssortStringArray);
        sortList = Arrays.asList(sortStringArray);
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        storeAdapter = new StoreAdapter();
        rvStore.setLayoutManager(linearLayoutManager);
        rvStore.setAdapter(storeAdapter);
        storeAdapter.setItemClickListener(new StoreItemClickListener() {
            @Override
            public void onItemClick(StoreBean.JdataEntity.StationEntity entity) {
                Intent intent = new Intent(_mActivity, ContainerActiivty.class);
                intent.putExtra("tofragment", Constants.STOREDETAILFRAGMENT);
                intent.putExtra("s_id", entity.getS_id());
                intent.putExtra("s_distance", entity.getDistance());
                startActivity(intent);
            }
        });
        rvStore.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (storeAdapter.getFooterLayoutCount() == 0&&storeAdapter.getData().size()>=pageLength) {
                    storeAdapter.addFooterView(addFooterView);
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == storeAdapter.getItemCount() && stationEntities.size() >= 1) {
                    if (!isLoading) {

                    rvStore.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentPage++;
                            Map map = new HashMap();
                            map.put("page", String.valueOf(currentPage));
                            map.put("length", String.valueOf(pageLength));
                            map.put("order", selectedSort);
                            map.put("city", selectedCity);
                            map.put("longitude", String.valueOf(Constants.longitude ));
                            map.put("latitude", String.valueOf(Constants.latitude));
                            getPresenter().loadMore(map);
                                isLoading = true;
                        }
                    }, 500);
                }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

            }
        });
        rlStoreCondition.setItemShowHidenListener(new ConditionSelectView.onItemShowHidenListener() {
            @Override
            public void onItemShow(int position) {
                switch (position) {
                    case 0:
                        if (cityEntities.size() > 0) {
                            storeConditionMenu.setCityList(cityEntities, selectCityPositon).showAsDropDown();
                        } else {
                            storeConditionMenu.setCityList(cityEntities, selectCityPositon).showAsDropDown();
                            getPresenter().getCityInfo();
                        }
                        conditionItemShow = 0;
                        break;
                    case 1:
                        storeConditionMenu.setSortList(sortList, selectedSortPosition).showAsDropDown();
                        conditionItemShow = 1;
                        break;
                }
            }

            @Override
            public void onAllHiden() {
                storeConditionMenu.dismiss();
            }
        });
        storeConditionMenu.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void ondismiss() {
//                removeDarkView(windowManager);
                rlStoreCondition.hidenAll();
            }

            @Override
            public void onSpread() {
//                showDarkViewBelow(rlStoreCondition, windowManager, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        storeConditionMenu.dismiss();
//                    }
//                });
            }
        });
        storeConditionMenu.setInnerListener(new InnerListener() {//点击城市
            @Override
            public void dismiss(int position, CityInfoBean.JdataEntity.CityListEntity.CityEntity data) {
                currentPage = 0;
                selectedCity = data.getC_name();
                store_city_select.setText(selectedCity);
                Map map = new HashMap();
                map.put("page", String.valueOf(currentPage));
                map.put("length", String.valueOf(pageLength));
                map.put("order", "");
                map.put("city", selectedCity);
                map.put("longitude", String.valueOf(Constants.longitude));
                map.put("latitude", String .valueOf(Constants.latitude));
                getPresenter().getStoreList(map);
                storeConditionMenu.dismiss();
            }

            @Override
            public void locate() {

            }
        });
        storeConditionMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {//点击排序
            @Override
            public void onMenuItemClick(int position) {
                switch (conditionItemShow) {
                    case 0:
                       // selectedCity = cityEntities.get(position).getC_name();
                       // selectCityPositon = position;
                    //    store_city_select.setText(cityEntities.get(position).getC_name());
                      //  KLog.d("选择",cityEntities.get(position).getC_name());
                        break;
                    case 1:
                        selectedSort = sortStringArray[position];
                        selectedSortPosition = position;
                        store_sort.setText(sortStringArray[position]);
                        break;
                }
                currentPage=0;
                Map map = new HashMap();
                map.put("page", String.valueOf(currentPage));
                map.put("length", pageLength + "");
                map.put("order", selectedSort);
                map.put("city", selectedCity);
                map.put("longitude", Constants.longitude + "");
                map.put("latitude", Constants.latitude + "");
                getPresenter().getStoreList(map);
                storeConditionMenu.dismiss();
            }
        });

        //默认请求
        Map map = new HashMap();
        map.put("page", String.valueOf(currentPage));
        map.put("length", String.valueOf(pageLength));
        map.put("order", null);
        map.put("city", null);
        map.put("longitude", String.valueOf(Constants.longitude));
        map.put("latitude", String.valueOf(Constants.latitude));
        getPresenter().getStoreList(map);
    }


    @Override
    public void RefreshBegin() {
        currentPage = 0;
        Map map = new HashMap();
        map.put("page", String.valueOf(currentPage));
        map.put("length", String.valueOf(pageLength));
        map.put("order", selectedSort);
        map.put("city", selectedCity);
        map.put("longitude", String.valueOf(Constants.longitude ));
        map.put("latitude", String.valueOf(Constants.latitude));
        getPresenter().getStoreList(map);
        storeConditionMenu.dismiss();
    }

    @Override
    public boolean isCanDoRefresh() {
        int position;
        if (linearLayoutManager.getChildCount() == 0) {
            return true;
        }
        position = linearLayoutManager.findFirstVisibleItemPosition();
        return position == 0 && linearLayoutManager.findViewByPosition(position).getTop() >= 0;
    }

    @Override
    public void onGetStoreListSucess(StoreBean storeBean) {
        stationEntities.clear();
        stationEntities.addAll(storeBean.getJdata().getStation());
        storeAdapter.setNewData(stationEntities);
    }

    @Override
    public void onGetStoreListFailure(String failure) {
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadMoreSuccess(StoreBean bean) {
        isLoading=false;
        if (storeAdapter.getFooterLayoutCount() > 0) {
            storeAdapter.removeAllFooterView();
        }
        if (bean.getJdata().getStation() != null && bean.getJdata().getStation().size() == 0) {
            currentPage--;
            Toast.makeText(_mActivity, "没有更多门店了", Toast.LENGTH_LONG).show();
        }
        stationEntities.addAll(bean.getJdata().getStation());
        storeAdapter.setNewData(stationEntities);
    }

    @Override
    public void loadMoreFailure(String failure) {
       isLoading=false;
        currentPage--;
        if (storeAdapter.getFooterLayoutCount() > 0) {
            storeAdapter.removeAllFooterView();
        }
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();

    }

    @Override
    public void getCitySuccess(CityInfoBean cityInfoBean) {
        cityEntities.clear();
        cityEntities = cityInfoBean.getJdata().getSortEntity();
        storeConditionMenu.setCityList(cityEntities, selectCityPositon);
    }

    @Override
    public void getCityFailure(String failure) {
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (storeConditionMenu.isMenuOpen()) {
            storeConditionMenu.dismiss();
            return true;
        } else {
            return super.onBackPressedSupport();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvStore.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext, 1),getResources().getColor(R.color.material_grey_300)));
    }
}
