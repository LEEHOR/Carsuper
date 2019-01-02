package com.carsuper.coahr.mvp.view.myData.integralCenter;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.integralCenter.MyIntegralCenterSignFragmentContract;
import com.carsuper.coahr.mvp.model.bean.ExchangeMallList;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.presenter.myData.integralCenter.MyIntegralCenterSignFragmentPresenter;
import com.carsuper.coahr.mvp.view.Exchange_mall.Fragment_exchange_shopping_detail;
import com.carsuper.coahr.mvp.view.Exchange_mall.adapter.ExchangeMallAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.CircleImageView;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

public class MyIntegralCenterSignFagment extends BaseFragment<MyIntegralCenterSignFragmentContract.Presenter> implements MyIntegralCenterSignFragmentContract.View,View.OnClickListener {


    @Inject
    MyIntegralCenterSignFragmentPresenter myIntegralCenterSignFragmentPresenter;

    @BindView(R.id.iv_myhead)
    ImageView headImageView;
    @BindView(R.id.tv_myscore)
    TextView myPoints;
    @BindView(R.id.tv_signins)
    TextView tv_siginins;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.exchange_shopping_recycler)
    RecyclerView exchange_shopping_recycler;
    @BindView(R.id.p1)
    ImageView p1;
    @BindView(R.id.p2)
    ImageView p2;
    @BindView(R.id.line_score)
    LinearLayout line_score;
    private String isSign;

    private  String myPointsed;
    private GridLayoutManager gridLayoutManager;
    private ExchangeMallAdapter exchangeMallAdapter;

    public static MyIntegralCenterSignFagment newInstance(String headPath,String MyPoints,String isSign){
        MyIntegralCenterSignFagment fragment = new MyIntegralCenterSignFagment();
        Bundle arg = new Bundle();
        arg.putString("headPath", headPath);
        arg.putString("MyPoints",MyPoints);
        arg.putString("isSign",isSign);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onSigninSuccess(ResultBean bean) {
        if (bean.getJdata().getJmsg() != null) {

            Toast.makeText(BaseApplication.mContext, bean.getJdata().getJmsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BaseApplication.mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
        }
        tv_siginins.setText("已签到");
        Integer integer = Integer.valueOf(myPointsed);
        String p=String.valueOf(integer+2);

        myPoints.setText(p);

        isSign = "1";
    }

    @Override
    public void onSignInFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getExchangeListSuccess(ExchangeMallList exchangeMallList) {
        exchangeMallAdapter.setNewData(exchangeMallList.getJdata().getCommodity());
    }
    @Override
    public void getExchangeListFail(String failure) {

    }

    @Override
    public MyIntegralCenterSignFragmentContract.Presenter getPresenter() {
        return myIntegralCenterSignFragmentPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_myintegalcentersigin;
    }

    @Override
    public void initView() {
        Imageloader.setViewSize(p1,ScreenUtils.getScreenWidth(BaseApplication.mContext)-20, (int) ((ScreenUtils.getScreenWidth(BaseApplication.mContext)-20)/1.7),ImageView.ScaleType.FIT_CENTER);
        Imageloader.setViewSize(p2,ScreenUtils.getScreenWidth(BaseApplication.mContext)-20,(int) ((ScreenUtils.getScreenWidth(BaseApplication.mContext)-20)/1.7),ImageView.ScaleType.FIT_CENTER);
        p2.setImageResource(R.mipmap.p02);
        p1.setImageResource(R.mipmap.p01);
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        line_score.setOnClickListener(this);
        tv_siginins.setOnClickListener(this);
        gridLayoutManager = new GridLayoutManager(BaseApplication.mContext, 2);
        exchangeMallAdapter = new ExchangeMallAdapter();
        exchange_shopping_recycler.setLayoutManager(gridLayoutManager);
        exchange_shopping_recycler.setAdapter(exchangeMallAdapter);
        exchange_shopping_recycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext, 4), DensityUtils.dp2px(BaseApplication.mContext, 4), getResources().getColor(R.color.material_grey_200)));
        for (int i = 0; i < exchange_shopping_recycler.getItemDecorationCount(); i++) {
            if (i != 0) {
                exchange_shopping_recycler.removeItemDecorationAt(i);
            }
        }

    }

    @Override
    public void initData() {
        String headPath = getArguments().getString("headPath");
        myPointsed = getArguments().getString("MyPoints");
        isSign=  getArguments().getString("isSign");
        Imageloader.loadCircularImage(headPath,headImageView);
        myPoints.setText(myPointsed);
        if (isSign.equals("1")){
            tv_siginins.setText("已签到");
        } else {
            tv_siginins.setText("点击签到 +2");
        }
        getMallList("0","15");
        exchangeMallAdapter.setExchangeClick(new ExchangeMallAdapter.ExchangeClick() {
            @Override
            public void onClick(ExchangeMallList.JdataBean.CommodityBean commodityBean) {
                start(Fragment_exchange_shopping_detail.newInstance(commodityBean.getC_id()));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_signins:
                   if (isSign.equals("0")) {
                    Map map = new HashMap();
                    map.put("token", Constants.token);
                    getPresenter().startSign(map);
                } else {
                    Toast.makeText(BaseApplication.mContext, "已签到过了，无法再次签到", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.line_score:
                start(IntegralCenterFragment.newInstance());
                break;
        }
    }


    /**
     * 获取兑换商品列表
     * @param page
     * @param length
     */
    private void getMallList(String page, String length) {
        Map map = new HashMap();
        map.put("page", page);
        map.put("length", length);
        myIntegralCenterSignFragmentPresenter.getExchangeList(map);

    }
}
