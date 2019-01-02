package com.carsuper.coahr.mvp.view.Exchange_mall;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.main.Exchange_mall_Contract;
import com.carsuper.coahr.mvp.model.bean.EvenBus_Mall;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.presenter.main.Exchange_mall_Presenter;
import com.carsuper.coahr.mvp.view.Exchange_mall.adapter.ExchangeMallAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.mvp.view.myData.LoginFragment;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/20
 * on 10:58
 */
public class Fragment_Exchange_mall extends BaseFragment<Exchange_mall_Contract.Presenter> implements Exchange_mall_Contract.View {
    @Inject
    Exchange_mall_Presenter p;
    @BindView(R.id.exchange_myTitle)
    NormalTittleBar exchange_myTitle;
    @BindView(R.id.user_img)
    ImageView user_img;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_stone)
    TextView user_stone;
    @BindView(R.id.exchange_shopping_recycler)
    RecyclerView exchange_shopping_recycler;
    @BindView(R.id.mall_swipe)
    SwipeRefreshLayout mall_swipe;
    private int currentPage = 0;//当前页码
    private int pageLength = 10;//每页数据条数
    private boolean isLoading;
    private boolean isLogin;

    private ExchangeMallAdapter adapter;
    private GridLayoutManager linearLayoutManager;
    private int firstVisibleItemPosition;
    private List<ExchangeMallList.JdataBean.CommodityBean> commodity = new ArrayList<>();

    public static Fragment_Exchange_mall newInstance() {
        Fragment_Exchange_mall mall=new Fragment_Exchange_mall();
        return mall;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public Exchange_mall_Contract.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_exchange_mall;
    }

    @Override
    public void initView() {
        exchange_myTitle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        linearLayoutManager = new GridLayoutManager(BaseApplication.mContext, 2);
        adapter = new ExchangeMallAdapter();
        exchange_shopping_recycler.setLayoutManager(linearLayoutManager);
        exchange_shopping_recycler.setAdapter(adapter);
        exchange_shopping_recycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext, 4), DensityUtils.dp2px(BaseApplication.mContext, 4), getResources().getColor(R.color.material_grey_200)));
        for (int i = 0; i < exchange_shopping_recycler.getItemDecorationCount(); i++) {
            if (i != 0) {
                exchange_shopping_recycler.removeItemDecorationAt(i);
            }
        }
        mall_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                    getMallList("0", "10");
                } else {
                    mall_swipe.setRefreshing(false);
                }
            }
        });

        exchange_shopping_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (adapter.getFooterLayoutCount() == 0 && adapter.getData().size() >= pageLength) {
                    adapter.addFooterView(addFooterView);
                }
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                //屏幕中最后一个可见子项的position
                // int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                //当前屏幕所看到的子项个数
                int visibleItemCount = layoutManager.getChildCount();
                //当前RecyclerView的所有子项个数
                int totalItemCount = layoutManager.getItemCount();
                //RecyclerView的滑动状态
                int state = recyclerView.getScrollState();
                if (visibleItemCount > 0 && firstVisibleItemPosition == totalItemCount - 1 && state == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isLoading) {
                        exchange_shopping_recycler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isLoading = true;
                                currentPage++;
                                getMallListMore(String.valueOf(currentPage), String.valueOf(pageLength));
                            }
                        }, 500);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            }
        });
        adapter.setExchangeClick(new ExchangeMallAdapter.ExchangeClick() {
            @Override
            public void onClick(ExchangeMallList.JdataBean.CommodityBean commodityBean) {

                start(Fragment_exchange_shopping_detail.newInstance(commodityBean.getC_id()));
            }
        });

    }

    @Override
    public void initData() {
        if (haslogin()){  //登录中
            isLoading=true;
            getUserInfo();
        } else {
            isLoading=false;
            getMallList("0", "10");
        }
        user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin) {
                    start(LoginFragment.newInstance(Constants.FragmentExchange));
                }
            }
        });
    }

    @Override
    public void getExchangeListSuccess(ExchangeMallList exchangeMallList) {
        commodity.clear();
        commodity = exchangeMallList.getJdata().getCommodity();
        adapter.setNewData(commodity);
        isLoading = true;
        mall_swipe.setRefreshing(false);
    }

    @Override
    public void getExchangeListFail(String failure) {
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
        isLoading = false;
        mall_swipe.setRefreshing(false);
    }

    @Override
    public void getExchangeListMoreSuccess(ExchangeMallList exchangeMallList) {
        isLoading = false;
        if (adapter.getFooterLayoutCount() > 0) {
            adapter.removeAllFooterView();
        }
        if (exchangeMallList.getJdata().getCommodity() != null && exchangeMallList.getJdata().getCommodity().size() == 0) {
            currentPage--;
            Toast.makeText(_mActivity, "没有更多门店了", Toast.LENGTH_LONG).show();
        }
        commodity.addAll(exchangeMallList.getJdata().getCommodity());
        adapter.setNewData(commodity);
    }

    @Override
    public void getExchangeListMoreFail(String failure) {
        currentPage--;
        isLoading = false;
        if (adapter.getFooterLayoutCount() > 0) {
            adapter.removeAllFooterView();
        }
        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetInfoSuccess(PersonInfoBean personInfoBean) {
        if (personInfoBean != null) {
            PersonInfoBean.JdataBean.UserBean user = personInfoBean.getJdata().getUser();
            user_name.setText(user.getNickname());
            Imageloader.loadCircularImage(user.getUserheadimg(),user_img);
            user_stone.setText(user.getPoints());
        }
        getMallList("0", "10");
    }

    @Override
    public void onGetInfoFailure(String throwle) {

    }

    private void getMallList(String page, String length) {
        Map map = new HashMap();
        map.put("page", page);
        map.put("length", length);
        p.getExchangeList(map);

    }

    private void getMallListMore(String page, String length) {
        Map map = new HashMap();
        map.put("page", page);
        map.put("length", length);
        p.getExchangeListMore(map);
    }

    private void getUserInfo(){
        Map map = new HashMap();
        map.put("token",Constants.token);
        map.put("uid",Constants.uid + "");
        p.getUserInfo(map);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EvenBus_Mall mall) {
        if (mall.getStatue()==1) {
            getUserInfo();
            isLogin=true;
        }
    }
}
