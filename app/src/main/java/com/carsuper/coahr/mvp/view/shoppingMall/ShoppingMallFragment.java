package com.carsuper.coahr.mvp.view.shoppingMall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.shoppingMall.ShoppingMalContract;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean.JdataEntity.BrandEntity;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean.JdataEntity.SortEntity;
import com.carsuper.coahr.mvp.model.bean.ShoppingMallBean.JdataEntity.CommodityEntity;

import com.carsuper.coahr.mvp.presenter.shoppingMall.ShoppingMalPresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.adapter.shoppingmall.ShoppingMallGridAdapter;
import com.carsuper.coahr.mvp.view.adapter.shoppingmall.ShoppingMallItemClickListener;
import com.carsuper.coahr.mvp.view.adapter.shoppingmall.ShoppingMallListAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.base.BaseLazyFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.ShoppingCartFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.widgets.ConditionSelectView;
import com.carsuper.coahr.widgets.conditionMenu.CommodityConditionSelectMenu;
import com.carsuper.coahr.widgets.conditionMenu.OnMenuItemClickListener;
import com.carsuper.coahr.widgets.conditionMenu.OnStateChangeListener;
import com.socks.library.KLog;

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
 * 商城页面
 */
public class ShoppingMallFragment extends BaseLazyFragment<ShoppingMalContract.Presenter> implements ShoppingMalContract.View, View.OnClickListener {


    @Inject
    WindowManager windowManager;

    @Inject
    ShoppingMalPresenter shoppingMalPresenter;
    @Inject
    CommodityConditionSelectMenu commodityConditionSelectMenu;

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.iv_composition)
    ImageView ivComposition;
    @BindView(R.id.rl_commcodity_condition)
    ConditionSelectView rlCommcodityCondition;
    @BindView(R.id.rv_shoppingmall)
    RecyclerView rvShoppingmall;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrframelayout;
    @BindView(R.id.shopping_brand)
    TextView shopping_brand;
    @BindView(R.id.shopping_classification)
    TextView shopping_classification;
    @BindView(R.id.shopping_sort)
    TextView shopping_sort;
    @BindView(R.id.tittle_left_image)
    ImageView tittle_left_image;


    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    private ShoppingMallGridAdapter gridAdapter;
    private ShoppingMallListAdapter listAdapter;

    private static final int LIST_COMPOSITON = 0;
    private static final int GRID_COMPOSITON = 1;

    private int composition = LIST_COMPOSITON;
    private int currentPage = 0;//当前页码
    private int pageLength = 10;//每页数据条数
    //    private boolean isLoading = false;
    private int lastVisibleItemPosition;//rvShoppingmall最近展示的itemposition

    private int conditionItemShow = 100;//条件选择器，哪一个正在显示
    private int selectBrandPositon = 0xffffff;
    private int selectedClassificationPosition = 0xffffff;
    private int selectedSortPosition = 0xffffff;

    private String selectedBrand = "";
    private String selectedClassification = "";
    private String selectedSort = "";
    private boolean isLoading; //判断是否正在加载
    private boolean isRefresh = false;
    private boolean isFirst = true; //是否第一次进入
    private SpacesItemDecoration spacesItemDecoration_grid, spacesItemDecoration_list;
    private List<BrandEntity> brandEntityList = new ArrayList<>();
    private List<SortEntity> classificationEntityList = new ArrayList<>();
    private List<CommodityEntity> commodityEntities = new ArrayList<>();

    private List<String> brandList = new ArrayList<>();//品牌
    private List<String> classificationlist = new ArrayList<>();//分类
    private static String[] sortIdArray = new String[4];//排序
    private static String[] sortStringArray = new String[4];//排序

    private List<String> sortlist = new ArrayList<>();


    public static ShoppingMallFragment newInstance(String selectedClassification) {
        ShoppingMallFragment fragment = new ShoppingMallFragment();
        Bundle arg = new Bundle();
        arg.putString("selectedClassification", selectedClassification);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public ShoppingMalContract.Presenter getPresenter() {
        return shoppingMalPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_shoppingmall;
    }

    @Override
    public void initView() {
        tvSearch.setOnClickListener(this);
        ivComposition.setOnClickListener(this);
        tittle_left_image.setOnClickListener(this);
        initPtrFrameLayout(ptrframelayout);
    }

    @Override
    public void initData() {
        spacesItemDecoration_grid = new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext, 2), DensityUtils.dp2px(BaseApplication.mContext, 2), getResources().getColor(R.color.material_grey_300));

        spacesItemDecoration_list = new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 1), getResources().getColor(R.color.material_grey_300));
        selectedClassification = getArguments().getString("selectedClassification");
        if (selectedClassification != null) {
            if (selectedClassification.equals("机油") && !selectedClassification.equals("")) {
                shopping_classification.setText("机油");

                tittle_left_image.setVisibility(View.VISIBLE);
            } else if (selectedClassification.equals("轮胎") && !selectedClassification.equals("")) {
                shopping_classification.setText("轮胎");
                tittle_left_image.setVisibility(View.VISIBLE);
            } else if (selectedClassification.equals("配件") && !selectedClassification.equals("")) {
                shopping_classification.setText("配件");
                tittle_left_image.setVisibility(View.VISIBLE);
            }
        }
        sortIdArray = _mActivity.getResources().getStringArray(R.array.shoppingmall_sortIdArray);
        sortStringArray = _mActivity.getResources().getStringArray(R.array.shoppingmall_ssortStringArray);
        sortlist = Arrays.asList(sortStringArray);
        commodityConditionSelectMenu.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void ondismiss() {
//                removeDarkView(windowManager);
                rlCommcodityCondition.hidenAll();
            }

            @Override
            public void onSpread() {
//                showDarkViewBelow(rlCommcodityCondition, windowManager, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        commodityConditionSelectMenu.dismiss();
//                    }
//                });
            }
        });
        commodityConditionSelectMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position) {
                switch (conditionItemShow) {
                    case 0:
                        selectedBrand = brandList.get(position);
                        selectBrandPositon = position;
                        shopping_brand.setText(brandList.get(position));
                        break;
                    case 1:
                        selectedClassification = classificationlist.get(position);
                        selectedClassificationPosition = position;
                        shopping_classification.setText(classificationlist.get(position));
                        break;
                    case 2:
                        selectedSort = sortStringArray[position];
                        selectedSortPosition = position;
                        shopping_sort.setText(sortStringArray[position]);
                        break;
                }
                Map map = new HashMap();
                if (selectedBrand.equals("全部品牌")) {
                    selectedBrand = "";
                }
                if (selectedClassification.equals("全部分类")) {
                    selectedClassification = "";
                }

                map.put("brand", selectedBrand);
                map.put("sort", selectedClassification);
                map.put("order", selectedSort);
                map.put("page", currentPage + "");
                map.put("length", pageLength + "");
                KLog.d("分类", selectedClassification);
                getPresenter().getCommodityList(map);
                commodityConditionSelectMenu.dismiss();
            }
        });
        rlCommcodityCondition.setItemShowHidenListener(new ConditionSelectView.onItemShowHidenListener() {
            @Override
            public void onItemShow(int position) {
                switch (position) {
                    case 0:
                        commodityConditionSelectMenu.setContentList(brandList, selectBrandPositon).showAsDropDown(rlCommcodityCondition);
                        conditionItemShow = 0;
                        break;
                    case 1:
                        commodityConditionSelectMenu.setContentList(classificationlist, selectedClassificationPosition).showAsDropDown(rlCommcodityCondition);
                        conditionItemShow = 1;

                        break;
                    case 2:
                        commodityConditionSelectMenu.setContentList(sortlist, selectedSortPosition).showAsDropDown(rlCommcodityCondition);
                        conditionItemShow = 2;
                        break;
                }
            }

            @Override
            public void onAllHiden() {
                commodityConditionSelectMenu.dismiss();
            }
        });
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        gridLayoutManager = new GridLayoutManager(BaseApplication.mContext, 2);
        gridAdapter = new ShoppingMallGridAdapter();
        listAdapter = new ShoppingMallListAdapter();
//        listAdapter.addFooterView(addFooterView);
        rvShoppingmall.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (listAdapter.getFooterLayoutCount() == 0 && listAdapter.getData().size() >= currentPage) {
                    listAdapter.addFooterView(addFooterView);
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == (composition == LIST_COMPOSITON ? listAdapter.getItemCount() : gridAdapter.getItemCount()) && commodityEntities.size() >= 1) {
                    if (!isLoading) {

                        rvShoppingmall.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                currentPage++;
                                Map map = new HashMap();
                                map.put("brand", selectedBrand);
                                map.put("sort", selectedClassification);
                                map.put("order", selectedSort);
                                map.put("page", String.valueOf(currentPage));
                                map.put("length", pageLength + "");
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
                if (composition == LIST_COMPOSITON) {
                    lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                } else {
                    lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
                }
            }
        });

        listAdapter.setItemClickListener(shoppingMallItemClickListener);
        gridAdapter.setItemClickListener(shoppingMallItemClickListener);
    }


    private ShoppingMallItemClickListener shoppingMallItemClickListener = new ShoppingMallItemClickListener() {
        @Override
        public void onClick(CommodityEntity entity) {
            Intent intent = new Intent(_mActivity, ContainerActiivty.class);
            intent.putExtra("tofragment", Constants.COMMODITYDETAILFRAGMENT);
            intent.putExtra("c_id", entity.getC_id());
            startActivity(intent);
        }
    };


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (commodityConditionSelectMenu.isMenuOpen()) {
            commodityConditionSelectMenu.dismiss();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void RefreshBegin() {
        if (isFirst) {

        } else {
            isRefresh = true;
        }
        isFirst = false;
        isLoading = true;
        currentPage = 0;
        Map map = new HashMap();
        map.put("brand", selectedBrand);
        map.put("sort", selectedClassification);
        map.put("order", selectedSort);
        map.put("page", String.valueOf(currentPage));
        map.put("length", pageLength + "");

        getPresenter().getCommodityList(map);
    }

    @Override
    public boolean isCanDoRefresh() {
        int position;
        if (composition == LIST_COMPOSITON) {
            if (linearLayoutManager.getChildCount() == 0) {
                return true;
            }
            position = linearLayoutManager.findFirstVisibleItemPosition();//获取当前第一个可见的item

            return position == 0 && linearLayoutManager.findViewByPosition(position).getTop() >= 0;
        } else {
            if (gridLayoutManager.getChildCount() == 0) {
                return true;
            }
            position = gridLayoutManager.findFirstVisibleItemPosition();
            return position == 0 && gridLayoutManager.findViewByPosition(position).getTop() >= 0;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Intent intent = new Intent(_mActivity, ContainerActiivty.class);
                intent.putExtra("tofragment", Constants.SEARCHFRAGMENT);
                startActivity(intent);
                break;
            case R.id.iv_composition:
                changeComposition();
                break;
            case R.id.tittle_left_image:
                _mActivity.onBackPressed();
                break;
        }

    }


    private void setListCompositonNotify() {
        rvShoppingmall.setLayoutManager(linearLayoutManager);
        if (isRefresh) {  //防止间距越来越大
        } else {
            rvShoppingmall.addItemDecoration(spacesItemDecoration_list);
        }
        isRefresh = false;
        rvShoppingmall.setAdapter(listAdapter);
        listAdapter.setNewData(commodityEntities);
        composition = LIST_COMPOSITON;
        ivComposition.setImageResource(R.mipmap.list_compostion);
    }

    private void setGridCompositonNotify() {
        rvShoppingmall.setLayoutManager(gridLayoutManager);
        if (isRefresh) {
        } else {
            rvShoppingmall.addItemDecoration(spacesItemDecoration_grid);

        }
        isRefresh = false;
        rvShoppingmall.setAdapter(gridAdapter);
        gridAdapter.setNewData(commodityEntities);
        composition = GRID_COMPOSITON;
        ivComposition.setImageResource(R.mipmap.grid_composition);
    }

    private void changeComposition() {

        if (composition == LIST_COMPOSITON) {
            rvShoppingmall.removeItemDecoration(spacesItemDecoration_list);
            setGridCompositonNotify();
        } else {
            rvShoppingmall.removeItemDecoration(spacesItemDecoration_grid);
            setListCompositonNotify();
        }
    }


    @Override
    public void onGetCommodityListSuccess(ShoppingMallBean bean) {
        isLoading = false;
        // currentPage = 0;
        classificationEntityList.clear();
        brandEntityList.clear();
        classificationlist.clear();
        brandList.clear();
        //品牌添加全部字段
        brandList.add(0, "全部品牌");
        //分类添加全部字段
        classificationlist.add(0, "全部分类");
        classificationEntityList = bean.getJdata().getSort();
        brandEntityList = bean.getJdata().getBrand();
        commodityEntities = bean.getJdata().getCommodity();
        for (SortEntity entity : classificationEntityList) {
            classificationlist.add(entity.getName());
        }
        for (BrandEntity entity : brandEntityList) {
            brandList.add(entity.getName());
        }
        if (composition == LIST_COMPOSITON) {
            setListCompositonNotify();
        } else {
            setGridCompositonNotify();
        }
    }

    @Override
    public void onGtCommodityListFailure(String failure) {
        isLoading = false;
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadMoreSuccess(ShoppingMallBean bean) {
        isLoading = false;

        if (listAdapter.getFooterLayoutCount() > 0) {
            listAdapter.removeAllFooterView();
        }
        if (bean.getJdata().getCommodity() != null && bean.getJdata().getCommodity().size() == 0) {
            Toast.makeText(_mActivity, "没有更多商品", Toast.LENGTH_LONG).show();
        }
        commodityEntities.addAll(bean.getJdata().getCommodity());

        if (composition == LIST_COMPOSITON) {
            listAdapter.setNewData(commodityEntities);
        } else {
            gridAdapter.setNewData(commodityEntities);
        }
    }

    @Override
    public void loadMoreFailure(String failure) {
        isLoading = false;
        currentPage--;
        if (listAdapter.getFooterLayoutCount() > 0) {
            listAdapter.removeAllFooterView();
        }
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (commodityConditionSelectMenu.isMenuOpen()) {
            commodityConditionSelectMenu.dismiss();
            return true;
        } else {
            return super.onBackPressedSupport();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
