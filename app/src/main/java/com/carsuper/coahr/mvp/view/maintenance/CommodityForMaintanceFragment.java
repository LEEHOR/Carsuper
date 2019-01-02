package com.carsuper.coahr.mvp.view.maintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.maintenance.CommodityForMaintanceContract;
import com.carsuper.coahr.mvp.model.bean.ReplaceableCommodityBean;
import com.carsuper.coahr.mvp.presenter.maintenance.CommodityForMaintancePresenter;
import com.carsuper.coahr.mvp.view.adapter.maintance.ReplaceableCommodityAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;


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
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class CommodityForMaintanceFragment extends BaseFragment<CommodityForMaintanceContract.Presenter> implements CommodityForMaintanceContract.View {

    @Inject
    CommodityForMaintancePresenter commodityForMaintancePresenter;
    @BindView(R.id.tittle_bar)
    NormalTittleBar tittleBar;
    @BindView(R.id.rv_commodityformaintance)
    RecyclerView rvCommodityformaintance;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrframelayout;


    private String service;
    private String cs_id;
    private int type;
    private int page = 0;
    private int length = 10;

    private LinearLayoutManager linearLayoutManager;
    private ReplaceableCommodityAdapter replaceableCommodityAdapter;
    private List<ReplaceableCommodityBean.JdataEntity.CommodityEntity> commodityEntities = new ArrayList<>();

    private int lastVisibleItemPosition;
    private boolean isLoading = false;

    /**
     * @param service 服务方式（机油、轮胎、滤芯）
     * @param cs_id   车辆类型
     * @param type    区别是滤芯商品还是机油和轮胎
     * @return
     */
    public static CommodityForMaintanceFragment newInstance(String service, String cs_id, int type) {
        CommodityForMaintanceFragment forMaintanceFragment = new CommodityForMaintanceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("service", service);
        bundle.putString("cs_id", cs_id);
        bundle.putInt("type", type);
        forMaintanceFragment.setArguments(bundle);
        return forMaintanceFragment;
    }

    @Override
    public CommodityForMaintanceContract.Presenter getPresenter() {
        return commodityForMaintancePresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_commodityformaintance;
    }

    @Override
    public void initView() {
        initPtrFrameLayout(ptrframelayout);
        tittleBar.getTvTittle().setText("可更换商品");
        tittleBar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

        replaceableCommodityAdapter = new ReplaceableCommodityAdapter();
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        rvCommodityformaintance.setLayoutManager(linearLayoutManager);
        rvCommodityformaintance.setAdapter(replaceableCommodityAdapter);
        rvCommodityformaintance.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == replaceableCommodityAdapter.getItemCount() && commodityEntities.size() >= 10) {
                    if (!isLoading) {
                        rvCommodityformaintance.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Map map = new HashMap();
                                map.put("service", service);
                                map.put("cs_id", cs_id);
                                map.put("page", page + 1 + "");
                                map.put("length", length + "");
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
        replaceableCommodityAdapter.setReplayceCommodityItemClickListener(new ReplaceableCommodityAdapter.ReplayceCommodityItemClickListener() {
            @Override
            public void onItemClick(ReplaceableCommodityBean.JdataEntity.CommodityEntity commodityEntity) {
                Bundle bundle = new Bundle();
                bundle.putString("commodity_url", commodityEntity.getC_thumbnail());
                bundle.putString("name", commodityEntity.getC_name());
                bundle.putString("price", commodityEntity.getC_price());
                bundle.putString("c_id", commodityEntity.getC_id());
                bundle.putInt("type", type);
                CommodityForMaintanceFragment.this.setFragmentResult(OrderToMaintenanceFragment.RESULT_REPLACEABLECOMMODITY_ELEMENT, bundle);
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void initData() {


        service = getArguments().getString("service");
        cs_id = getArguments().getString("cs_id");
        type = getArguments().getInt("type");
        Map map = new HashMap();
        map.put("service", service);  //轮胎、机油服务、滤芯
        map.put("cs_id", cs_id);
        map.put("page", page + "");
        map.put("length", length + "");
        getPresenter().getReplaceableCommoditys(map);
    }


    @Override
    public void RefreshBegin() {
        Map map = new HashMap();
        map.put("service", service);
        map.put("cs_id", cs_id);
        map.put("page", page + "");
        map.put("length", length + "");
        getPresenter().getReplaceableCommoditys(map);
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
    public void onGetReplaceableCommoditysSuccess(ReplaceableCommodityBean bean) {
        page = 0;
        commodityEntities = bean.getJdata().getCommodity();
        replaceableCommodityAdapter.setNewData(commodityEntities);
    }

    @Override
    public void onGetReplaceableCommoditysFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoadMoreSuccess(ReplaceableCommodityBean bean) {
        page++;
        commodityEntities.addAll(bean.getJdata().getCommodity());
        replaceableCommodityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }


}
