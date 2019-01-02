package com.carsuper.coahr.mvp.view.main;


import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.utils.StringUtils;
import com.carsuper.coahr.widgets.SideBar;
import com.github.promeg.pinyinhelper.Pinyin;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.main.CityPickerContract;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean;
import com.carsuper.coahr.mvp.presenter.main.CityPickPresenter;
import com.carsuper.coahr.mvp.view.adapter.citypicker.CityListAdapter;
import com.carsuper.coahr.mvp.view.adapter.citypicker.InnerListener;
import com.carsuper.coahr.mvp.view.adapter.DividerItemDecoration;
import com.carsuper.coahr.mvp.view.adapter.citypicker.decoration.CitySectionItemDecoration;
import com.carsuper.coahr.mvp.view.base.BaseDialogFragment;
import com.carsuper.coahr.widgets.SideIndexBar;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.carsuper.coahr.mvp.model.bean.CityInfoBean.JdataEntity.CityListEntity.CityEntity;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Author： hengzwd on 2018/7/18.
 * Email：hengzwdhengzwd@qq.com
 */
public class CityPickerDialogFragment extends BaseDialogFragment<CityPickerContract.Presenter> implements CityPickerContract.View, View.OnClickListener, InnerListener, TextWatcher {


    @Inject
    CityPickPresenter cityPickPresenter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.cp_city_recyclerview)
    RecyclerView cpCityRecyclerview;
    @BindView(R.id.cp_side_index_bar)
    SideBar cpSideIndexBar;
    @BindView(R.id.tv_dialog)
    TextView tv_dialog;



    private onCityPickListener onCityPickListener;

    private CityListAdapter mAdapter;
    private List<CityEntity> mAllCities = new ArrayList<CityEntity>();
    private List<CityEntity> mHotCities = new ArrayList<CityEntity>();
    private List<CityEntity> mResults = new ArrayList<CityEntity>();
    private CityEntity mLocateCity = new CityEntity("0", "未知", "当前定位城市");
    private int locateState = Constants.LOCATION_ING;

    public static CityPickerDialogFragment newInstance(int animate) {
        CityPickerDialogFragment cityPickerDialogFragment = new CityPickerDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("animate", animate);
        cityPickerDialogFragment.setArguments(bundle);
        return cityPickerDialogFragment;
    }


    @Override
    public CityPickerContract.Presenter getPresenter() {
        return cityPickPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragmentdialog_citypicker;
    }

    public void setLocateState(int state) {
        locateState = state;
    }


    @Override
    public void initView() {

        mAllCities.add(0, mLocateCity);
        mAllCities.add(1, new CityEntity("0", "未知", "热门城市"));
        mResults.addAll(mAllCities);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        cpCityRecyclerview.setLayoutManager(linearLayoutManager);
        cpCityRecyclerview.setHasFixedSize(true);//设置为true，调用recyclerview的onitemchange（增删改插入）等方法，不会调用requestlayout，重测recyclerview的宽高
        cpCityRecyclerview.addItemDecoration(new CitySectionItemDecoration(getActivity(), mResults), 0);
        cpCityRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity()), 1);
        mAdapter = new CityListAdapter(getActivity(), mResults, mHotCities, locateState);
        mAdapter.setInnerListener(this);
        cpCityRecyclerview.setAdapter(mAdapter);
        cpSideIndexBar.setType(2);
        cpSideIndexBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onHit(String letter) {
                tv_dialog.setVisibility(View.VISIBLE);
                tv_dialog.setText(letter);
                if (mResults == null || mResults.isEmpty()) return;
                if (TextUtils.isEmpty(letter)) return;
                int size = mResults.size();
                for (int i = 0; i < size; i++) {
                    if (TextUtils.equals(letter.substring(0, 1), mResults.get(i).getC_code().substring(0, 1).toUpperCase())) {
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
        tbTittle.getLeftIcon().setOnClickListener(this);
    }

    @Override
    public void initData() {
        getPresenter().getCityInfo();
    }

    @Override
    public void initAnimate() {
        Bundle bundle = getArguments();
        animate_style = bundle.getInt("animate");
    }

    @Override
    public void iniWidow(Window window) {
             // Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawableResource(R.drawable.bg_fff_background);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setWindowAnimations(animate_style);
        }
    }

    @Override
    public void getCitySuccess(CityInfoBean cityInfoBean) {
        //得到排序后的结果
        mAllCities.addAll(2, cityInfoBean.getJdata().getSortEntity());
        mResults.clear();
        mResults.addAll(mAllCities);
        mHotCities = cityInfoBean.getJdata().getCity_hot();
        mAdapter.setNewData(mResults, mHotCities, locateState);
        ((CitySectionItemDecoration) cpCityRecyclerview.getItemDecorationAt(0)).setData(mResults);
        mAdapter.notifyDataSetChanged();
        getPresenter().startLocation();

    }

    @Override
    public void getCityFailure(String failure) {
        getPresenter().startLocation();
        Toast.makeText(getActivity(), failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationSuccess(BDLocation location) {


        mAdapter.updateLocateState(new CityEntity("0", StringUtils.getCityName(location.getCity()),"当前定位城市"),Constants.LOCATION_SUCCESS);
    }

    @Override
    public void onLocationFailure(int failure) {
        mAdapter.updateLocateState(new CityEntity("0","定位失败","当前定位城市"),Constants.LOCATION_FAILURE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                dismiss();
                break;
        }

    }

    @Override
    public void dismiss(int position, CityEntity data) {
        //fragment  dismiss，根据data数据回首页做网络访问
        onCityPickListener.onCitypick(data.getC_name());
        dismiss();

    }

    @Override
    public void locate() {
        //启动定位
        getPresenter().startLocation();
        mAdapter.updateLocateState(new CityEntity("0","未知","当前定位城市"),Constants.LOCATION_ING);
    }

  /*  @Override
    public void onIndexChanged(String index, int position) {
        //滚动RecyclerView到索引位置
        if (mResults == null || mResults.isEmpty()) return;
        if (TextUtils.isEmpty(index)) return;
        int size = mResults.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(index.substring(0, 1), mResults.get(i).getC_code().substring(0, 1))) {
                if (linearLayoutManager != null) {
                    linearLayoutManager.scrollToPositionWithOffset(i, 0);
                    return;
                }
            }
        }
    }*/


    //搜索文字监听
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
            mResults.addAll(mAllCities);
            ((CitySectionItemDecoration) (cpCityRecyclerview.getItemDecorationAt(0))).setData(mResults);
            mAdapter.updateData(mResults);
        } else {
            mResults.clear();
            boolean isChinese = Pinyin.isChinese(s.charAt(0));
            KLog.e("ischinese" + isChinese);
            KLog.e("s.charAt(0)" + s.charAt(0));
            for (int i = 2; i < mAllCities.size(); i++) {//从第二个数据开始，错开当前城市和热门城市
                if (isChinese) {
                    KLog.e("ischinese" + isChinese);
                    if (mAllCities.get(i).getC_name().contains(s.toString())) {
                        KLog.e("mAllCities.get(i).getC_name()" + mAllCities.get(i).getC_name());
                        mResults.add(mAllCities.get(i));
                    }
                } else {
                    KLog.e(Pinyin.toPinyin(mAllCities.get(i).getC_name(), ""));
                    if (Pinyin.toPinyin(mAllCities.get(i).getC_name(), "").toLowerCase().trim().contains(s.toString().toLowerCase())) {
                        mResults.add(mAllCities.get(i));
                    }
                }

            }
            ((CitySectionItemDecoration) (cpCityRecyclerview.getItemDecorationAt(0))).setData(mResults);
            mAdapter.updateData(mResults);

        }
        cpCityRecyclerview.scrollToPosition(0);
    }

    public void setOnCityPickListener(CityPickerDialogFragment.onCityPickListener onCityPickListener) {
        this.onCityPickListener = onCityPickListener;
    }


    public interface onCityPickListener{
        void onCitypick(String city);
    }
}
