package com.carsuper.coahr.mvp.view;

import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.SearchContract;
import com.carsuper.coahr.mvp.model.bean.SearchBean;
import com.carsuper.coahr.mvp.presenter.SearchPresenter;
import com.carsuper.coahr.mvp.view.adapter.search.OnSearchItemClickListener;
import com.carsuper.coahr.mvp.view.adapter.search.SearchAdapter;
import com.carsuper.coahr.mvp.view.adapter.search.SearchHistoryAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityDetailFragment;
import com.carsuper.coahr.mvp.view.store.StoreDetailFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.KeyBoardUtils;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.utils.SearchHistoryStackUtils;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnTextChanged;

/**
 * Author： hengzwd on 2018/7/23.
 * Email：hengzwdhengzwd@qq.com
 * 搜索页面
 */
public class SearchFragment extends BaseFragment<SearchContract.Presenter> implements SearchContract.View, TabLayout.OnTabSelectedListener, View.OnClickListener, TextView.OnEditorActionListener, OnSearchItemClickListener {

    @Inject
    SearchPresenter searchPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tbl_search)
    TabLayout tblSearch;
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    @BindView(R.id.rv_search_history)  //搜索历史recycleView
            RecyclerView rvSearchHistory;
    @BindView(R.id.search_history_line)  //搜索历史父布局
            LinearLayout linearLayout;
    private RelativeLayout relativeLayout;


    private int tabPosition = 0;
    private boolean isSearch = false;
    private SearchBean mData;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManagerHistory;
    private SearchAdapter searchAdapter;
    private SearchHistoryAdapter searchHistoryAdapter;
    private TextView imageView_clear;


    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onSearchSuccess(SearchBean searchBean) {
        rvSearch.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        isSearch = false;
        mData = searchBean;
        switch (tabPosition) {
            case 0:
                searchAdapter.setNewData(searchBean.getJdata().getResult());
                break;
            case 1:
                searchAdapter.setNewData(searchBean.getJdata().getStation());
                break;
            case 2:
                searchAdapter.setNewData(searchBean.getJdata().getCommodity());
                break;
        }


    }

    @Override
    public void onsearchFailure(String failure) {
        isSearch = false;
        rvSearch.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetHistorySuccess(List<SearchBean.JdataEntity.SearchEntity> list) {

        //searchHistoryAdapter.setNewData(list);
        //  searchHistoryAdapter.addHeaderView(getLayoutInflater().inflate(R.layout.recyclerview_header_searchhistory,null,false));
    }

    @Override
    public void onGetHistoryFailure(String failure) {
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public SearchContract.Presenter getPresenter() {
        return searchPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView() {
        relativeLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.recyclerview_header_searchhistory, null, false);
        imageView_clear = relativeLayout.findViewById(R.id.tv_clean);
        tblSearch.addOnTabSelectedListener(this);
        tbTittle.getLeftIcon().setOnClickListener(this);
        etSearch.addTextChangedListener(new EtSearch());
        etSearch.setOnEditorActionListener(this);
        imageView_clear.setOnClickListener(this);

    }

    @Override
    public void initData() {
        linearLayoutManager = new LinearLayoutManager(_mActivity);
        linearLayoutManagerHistory = new LinearLayoutManager(_mActivity);
        rvSearch.setLayoutManager(linearLayoutManager);
        rvSearchHistory.setLayoutManager(linearLayoutManagerHistory);

        searchAdapter = new SearchAdapter();
        searchHistoryAdapter = new SearchHistoryAdapter(SearchHistoryStackUtils.getInstance().getsearchEntities());

        rvSearch.setAdapter(searchAdapter);
        rvSearchHistory.setAdapter(searchHistoryAdapter);
        searchAdapter.setOnItemClickListener(this);
        searchHistoryAdapter.setOnItemClickListener(this);
        // getPresenter().getSearchHistory();
        rvSearch.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        //添加搜索历史头部
        searchHistoryAdapter.addHeaderView(relativeLayout);
        rvSearchHistory.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.decoration_f5f5f8)));
        rvSearch.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.decoration_f5f5f8)));
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tabPosition = tab.getPosition();
        if (mData != null) {
            switch (tab.getPosition()) {
                case 0:
                    searchAdapter.setNewData(mData.getJdata().getResult());
                    break;
                case 1:
                    searchAdapter.setNewData(mData.getJdata().getStation());
                    break;
                case 2:
                    searchAdapter.setNewData(mData.getJdata().getCommodity());
                    break;
            }
        }

    }

    /**
     * 文字变化监听
     */
    class EtSearch implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            linearLayout.setVisibility(View.VISIBLE);
            rvSearch.setVisibility(View.GONE);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
           /* if (!charSequence.toString().equals("")){
                if (!isSearch) {
                    Map map = new HashMap();
                    map.put("search_key", charSequence.toString());
                    getPresenter().searchAll(map);
                    isSearch = true;
                }
            }*/
        }

        @Override
        public void afterTextChanged(Editable editable) {
           /* if (!editable.toString().equals("")){
                if (!isSearch) {
                    Map map = new HashMap();
                    map.put("search_key", editable.toString());
                    getPresenter().searchAll(map);
                    isSearch = true;
                }
            }*/
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                _mActivity.onBackPressed();
                break;
            case R.id.tv_clean:
                SearchHistoryStackUtils.getInstance().clearstack();
                searchHistoryAdapter.setNewData(SearchHistoryStackUtils.getInstance().getsearchEntities());
                break;
        }
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            KeyBoardUtils.hideKeybord(v, getActivity());
            if (!isSearch) {
                if (etSearch.getText() != null &&  !etSearch.getText().equals("")) {
                    Map map = new HashMap();
                    map.put("search_key", etSearch.getText().toString());
                    getPresenter().searchAll(map);
                    isSearch = true;
                } else {
                    Toast.makeText(_mActivity, "请输入有效字段", Toast.LENGTH_LONG).show();
                }
            }
            return true;
        }
        return false;
    }


    /**
     * 搜索结果或历史item点击
     *
     * @param item
     * @param type 判断是搜索历史还是搜索结果 1:搜索结果。2:搜索历史。
     */
    @Override
    public void onClick(SearchBean.JdataEntity.SearchEntity item, int type) {
        if (type == 1) {
            SearchHistoryStackUtils.getInstance().add(item);
            searchHistoryAdapter.setNewData(SearchHistoryStackUtils.getInstance().getsearchEntities());
        }
        //搜索结果点击跳转
        if (item.getType().equals("commodity")) {
            start(CommodityDetailFragment.newInstance(item.getId()));
        } else if (item.getType().equals("station")) {
            start(StoreDetailFragment.newInstance(item.getId()));
        }


    }
}
