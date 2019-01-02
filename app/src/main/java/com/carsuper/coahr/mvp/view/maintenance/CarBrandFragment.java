package com.carsuper.coahr.mvp.view.maintenance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.main.MainContract;
import com.carsuper.coahr.mvp.contract.maintenance.CarBrandContract;
import com.carsuper.coahr.mvp.model.bean.CarBrandBean;
import com.carsuper.coahr.mvp.presenter.maintenance.CarBrandPresenter;
import com.carsuper.coahr.mvp.view.adapter.CarPicker.CarBrandListAdapter;
import com.carsuper.coahr.mvp.view.adapter.CarPicker.HotCarBrandAdapter;
import com.carsuper.coahr.mvp.view.adapter.CarPicker.OnCarBrandItemClickListener;
import com.carsuper.coahr.mvp.view.adapter.CarPicker.decoration.CarBrandSectionItemDecoration;
import com.carsuper.coahr.mvp.view.adapter.DividerItemDecoration;
import com.carsuper.coahr.mvp.view.adapter.citypicker.decoration.CitySectionItemDecoration;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.SideBar;
import com.carsuper.coahr.widgets.SideIndexBar;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.github.promeg.pinyinhelper.Pinyin;
import com.socks.library.KLog;

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
 * 汽车品牌选择
 */
public class CarBrandFragment extends BaseFragment<CarBrandContract.Presenter> implements CarBrandContract.View, TextWatcher {

    @Inject
    CarBrandPresenter carBrandPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_carbrand)
    RecyclerView rvCarbrand;
    @BindView(R.id.side_index_bar_carbrand)
    SideBar sideIndexBarCarbrand;
    @BindView(R.id.ptrframelayout)
    PtrFrameLayout ptrframelayout;
    @BindView(R.id.tv_dialog)
    TextView tv_dialog;


    private LinearLayoutManager linearLayoutManager ;

    private CarBrandListAdapter carBrandListAdapter;

    private List<CarBrandBean.JdataEntity.HotEntity> hotEntities= new ArrayList<>();                  //hotList
    private List<CarBrandBean.JdataEntity.BrandEntityX.BrandEntity> brandEntities = new ArrayList<>(); //normaList
    private List<CarBrandBean.JdataEntity.BrandEntityX.BrandEntity> searchList =new ArrayList<>(); //搜索List
    private List<CarBrandBean.JdataEntity.BrandEntityX.BrandEntity>   mResults =new ArrayList<>(); //结果

    private int fromF;//来自哪个车型选择模块，服务订单确认  或者是  我的爱车页面  选择完成根据此返回fromF
    public static CarBrandFragment newInstance(int fromF) {
        CarBrandFragment fragment  = new CarBrandFragment();
        Bundle arg =new Bundle();
        arg.putInt("fromF",fromF);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public CarBrandContract.Presenter getPresenter() {
        return carBrandPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_carbrand;
    }

    @Override
    public void initView() {
        initPtrFrameLayout(ptrframelayout);
       // sideIndexBarCarbrand.setOnIndexChangedListener(this);
        sideIndexBarCarbrand.setType(2);
        sideIndexBarCarbrand.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onHit(String letter) {
                tv_dialog.setVisibility(View.VISIBLE);
                tv_dialog.setText(letter);
                if (mResults == null || mResults.isEmpty()) return;
                if (TextUtils.isEmpty(letter)) return;
                int size = mResults.size();
                for (int i = 0; i < size; i++) {
                    if (TextUtils.equals(letter.substring(0, 1), mResults.get(i).getFirst_letter().substring(0, 1).toUpperCase())) {
                        if (linearLayoutManager != null) {
                            linearLayoutManager.scrollToPositionWithOffset(i, 0);
                            return;
                        }
                    }
                }
            }

            @Override
            public void onCancel() {
                tv_dialog.setVisibility(View.GONE);
            }
        });
        etSearch.addTextChangedListener(this);
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

    }
    @Override
    public void RefreshBegin() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        getPresenter().getCarBrand(map);
    }

    @Override
    public boolean isCanDoRefresh() {
        int position;
        if (linearLayoutManager.getChildCount() == 0) {
            return true;
        }
        position = linearLayoutManager.findFirstVisibleItemPosition();
        return position == 0 && linearLayoutManager.findViewByPosition(position).getTop() >= _mActivity.getResources().getDimension(R.dimen.cp_section_height);
    }
    @Override
    public void initData() {
        fromF = getArguments().getInt("fromF");
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        carBrandListAdapter=new CarBrandListAdapter(_mActivity,mResults,hotEntities);
        rvCarbrand.addItemDecoration(new CarBrandSectionItemDecoration(_mActivity,mResults),0);
        rvCarbrand.addItemDecoration(new DividerItemDecoration(_mActivity),1);
        rvCarbrand.setLayoutManager(linearLayoutManager);
        rvCarbrand.setAdapter(carBrandListAdapter);
        carBrandListAdapter.setBrandItemClicklistener(new OnCarBrandItemClickListener() {
            @Override
            public void onItemClick(CarBrandBean.JdataEntity.BrandEntityX.BrandEntity item) {
                //普通品牌点击
                start(CarSerialFragment.newInstance(fromF,item.getCb_id(),item.getPic(),item.getB_name()));
            }

            @Override
            public void onHotItemClick(CarBrandBean.JdataEntity.HotEntity item) {
                //热门品牌点击
                start(CarSerialFragment.newInstance(fromF,item.getCb_id(),item.getPic(),item.getB_name()));
            }
        });
    }


    @Override
    public void onGetCarBrandSuccess(CarBrandBean brandBean) {
        hotEntities.clear();
        hotEntities.addAll(brandBean.getJdata().getHot());
        brandEntities.clear();
        brandEntities.add(0,new CarBrandBean.JdataEntity.BrandEntityX.BrandEntity("热门品牌"));
        brandEntities.addAll(brandBean.getJdata().getSortEntity());
        mResults.clear();
        mResults.addAll(brandEntities);
        rvCarbrand.getItemDecorationAt(0);
        ((CarBrandSectionItemDecoration)rvCarbrand.getItemDecorationAt(0)).setData(mResults);
        carBrandListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onGetCarBrandFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_LONG).show();
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        String keyword = s.toString();
        if (TextUtils.isEmpty(keyword)) {
            mResults.clear();
            mResults.addAll(brandEntities);
            ((CarBrandSectionItemDecoration) (rvCarbrand.getItemDecorationAt(0))).setData(mResults);
            carBrandListAdapter.notifyDataSetChanged();
        } else {
            mResults.clear();
            searchList.clear();
            searchList.add(0,new CarBrandBean.JdataEntity.BrandEntityX.BrandEntity("热门品牌"));
            boolean isChinese = Pinyin.isChinese(s.charAt(0));
            KLog.e("ischinese" + isChinese);
            KLog.e("s.charAt(0)" + s.charAt(0));
            for (int i = 1; i < brandEntities.size(); i++) {//从第1个数据开始，错开热门品牌
                if (isChinese) {
                    if (brandEntities.get(i).getB_name().contains(s.toString())) {
                        KLog.e("mAllCities.get(i).getC_name()" + brandEntities.get(i).getB_name());
                        searchList.add(brandEntities.get(i));
                    }
                } else {
                    KLog.e(Pinyin.toPinyin(brandEntities.get(i).getFirst_letter(), ""));
                    if (Pinyin.toPinyin(brandEntities.get(i).getFirst_letter(), "").toLowerCase().trim().contains(s.toString().toLowerCase())) {
                        searchList.add(brandEntities.get(i));
                    }
                }

            }
            mResults.addAll(searchList);
            KLog.d("搜索",mResults.size());
            ((CarBrandSectionItemDecoration) (rvCarbrand.getItemDecorationAt(0))).setData(mResults);
            carBrandListAdapter.notifyDataSetChanged();

        }
        rvCarbrand.scrollToPosition(0);
    }

 /*   @Override
    public void onIndexChanged(String index, int position) {
        //滚动RecyclerView到索引位置
        if (brandEntities == null || brandEntities.isEmpty()) return;
        if (TextUtils.isEmpty(index)) return;
        int size = brandEntities.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(index.substring(0, 1), brandEntities.get(i).getFirst_letter().substring(0, 1))) {
                if (linearLayoutManager != null) {
                    linearLayoutManager.scrollToPositionWithOffset(i, 0);
                    return;
                }
            }
        }
    }*/



}
