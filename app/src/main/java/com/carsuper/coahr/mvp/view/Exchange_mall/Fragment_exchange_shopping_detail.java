package com.carsuper.coahr.mvp.view.Exchange_mall;

import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.main.Exchange_shopping_detail_Contract;
import com.carsuper.coahr.mvp.model.bean.ExchangeByStone;
import com.carsuper.coahr.mvp.model.bean.ExchangeDetail;
import com.carsuper.coahr.mvp.model.bean.ExchangeRe;
import com.carsuper.coahr.mvp.presenter.main.Exchange_shopping_detail_Presenter;
import com.carsuper.coahr.mvp.view.Exchange_mall.adapter.ExchangeDetailAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.utils.KeyBoardUtils;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.SetCustomBannerUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;
import com.donkingliang.banner.CustomBanner;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/20
 * on 14:42
 */
public class Fragment_exchange_shopping_detail extends BaseFragment<Exchange_shopping_detail_Contract.Presenter> implements Exchange_shopping_detail_Contract.View {
    @Inject
    Exchange_shopping_detail_Presenter p;
    @BindView(R.id.exchange_detail_title)
    NormalTittleBar exchange_detail_title;
    @BindView(R.id.exchange_detail_banner)
    CustomBanner<String> exchange_detail_banner;
    @BindView(R.id.exchange_detail_describe)
    TextView exchange_detail_describe;  //描述
    @BindView(R.id.exchange_detail_price)
    TextView exchange_detail_price;
    @BindView(R.id.exchange_detail_recycler)
    RecyclerView exchange_detail_recycler;
    @BindView(R.id.tv_exchange)
    TextView tv_exchange; //兑换按钮
    private String c_id;
    private String commodityC_id;
    private String o_status;
    private boolean isLogin;
    private List<String> cp_path_CustomBanner=new ArrayList<>();
    private ExchangeDetailAdapter exchangeDetailAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public Exchange_shopping_detail_Contract.Presenter getPresenter() {
        return p;
    }

    public static Fragment_exchange_shopping_detail newInstance(String c_id) {
        Fragment_exchange_shopping_detail detail = new Fragment_exchange_shopping_detail();
        Bundle bundle = new Bundle();
        bundle.putString("c_id", c_id);
        detail.setArguments(bundle);
        return detail;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_exchange_shopping_detail;
    }

    @Override
    public void initView() {

        //设置轮播图大小
        Imageloader.setViewSize(exchange_detail_banner,ScreenUtils.getScreenWidth(BaseApplication.mContext)
                ,ScreenUtils.getScreenWidth(BaseApplication.mContext)*9/16,null);

        //详情
        exchangeDetailAdapter = new ExchangeDetailAdapter();
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        exchange_detail_recycler.setLayoutManager(linearLayoutManager);
        exchange_detail_recycler.setAdapter(exchangeDetailAdapter);

        exchange_detail_title.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });

   }

    @Override
    public void initData() {
        if (getArguments() != null) {
            c_id = getArguments().getString("c_id");
            if (haslogin()){
                isLogin=true;
                getDetail();
            } else {
                isLogin=false;
                getDetail_noLogin();
            }

        }
        tv_exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogin) {
                    KLog.d("领取1", "osatus", o_status);
                    if (o_status.equals("1")) {  //领取
                        FragmentPayPass payPass = FragmentPayPass.newInstance(c_id, o_status);
                        payPass.setListener(new FragmentPayPass.ReceiveListener() {
                            @Override
                            public void ReceiveSuccess(AppCompatDialogFragment dialogFragment) {
                                o_status = "-1";
                                tv_exchange.setText("立即兑换");
                                KLog.d("领取");
                                KeyBoardUtils.hideKeybord(tv_exchange,_mActivity);
                                tv_exchange.setBackground(getResources().getDrawable(R.drawable.bg_bule500_frame_exchange_background));
                                dialogFragment.dismiss();
                            }

                            @Override
                            public void ReceiveFailure() {

                            }
                        });
                        payPass.show(getChildFragmentManager(), TAG);
                    } else {
                        ExchangeByStone();
                    }
                } else {
                    Toast.makeText(BaseApplication.mContext,"请退出登录",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void getDetailSuccess(ExchangeDetail detail) {
        ExchangeDetail.JdataBean.CommodityBean commodity = detail.getJdata().getCommodity();  //基本详情
        if (commodity != null) {
            commodityC_id = commodity.getC_id();
            exchange_detail_describe.setText(commodity.getC_name());
            exchange_detail_price.setText(commodity.getC_price());
            o_status = commodity.getO_status();
            if (o_status.equals("1")){
                tv_exchange.setText("立即领取");
                tv_exchange.setBackground(getResources().getDrawable(R.drawable.bg_red500_frame_exchange_background));
            } else {
                tv_exchange.setText("立即兑换");
                tv_exchange.setBackground(getResources().getDrawable(R.drawable.bg_bule500_frame_exchange_background));

            }
            List<String> cp_path = commodity.getCp_path();
            if (cp_path != null && cp_path.size()>0) {
                cp_path_CustomBanner.clear();
                cp_path_CustomBanner.addAll(cp_path);
                SetCustomBannerUtils.setCustomBanner(exchange_detail_banner,cp_path,ImageView.ScaleType.FIT_CENTER);
            }
        }
        ExchangeDetail.JdataBean.CommodityDescriptionBean commodity_description = detail.getJdata().getCommodity_description();
        if (commodity_description != null) {
            List<ExchangeDetail.JdataBean.CommodityDescriptionBean.DesPicBean> des_pic = commodity_description.getDes_pic();
            if (des_pic != null  && des_pic.size()>0) {
                exchangeDetailAdapter.setNewData(des_pic);
            }
        }
    }

    @Override
    public void getDetailFailure(String failure) {

    }

    @Override
    public void exChangeByStoneSuccess(ExchangeByStone stone) {
        Toast.makeText(getActivity(),stone.getMsg(),Toast.LENGTH_LONG).show();
                o_status=stone.getJdata().getO_status();
                 tv_exchange.setText("立即领取");
        tv_exchange.setBackground(getResources().getDrawable(R.drawable.bg_red500_frame_exchange_background));

    }

    @Override
    public void exChangeByStoneFailure(String failure) {
        Toast.makeText(_mActivity,failure,Toast.LENGTH_LONG).show();
    }

    @Override
    public void exChangeReSuccess(ExchangeRe exchangeRe) {

    }

    @Override
    public void exChangeReFailure(String failure) {

    }

    @Override
    public void getDetailNoLoginSuccess(ExchangeDetail detail) {
        ExchangeDetail.JdataBean.CommodityBean commodity = detail.getJdata().getCommodity();  //基本详情
        if (commodity != null) {
            commodityC_id = commodity.getC_id();
            exchange_detail_describe.setText(commodity.getC_name());
            exchange_detail_price.setText(commodity.getC_price());
            List<String> cp_path = commodity.getCp_path();
            if (cp_path != null && cp_path.size()>0) {
                cp_path_CustomBanner.clear();
                cp_path_CustomBanner.addAll(cp_path);
                SetCustomBannerUtils.setCustomBanner(exchange_detail_banner,cp_path,ImageView.ScaleType.FIT_CENTER);
            }
        }
        ExchangeDetail.JdataBean.CommodityDescriptionBean commodity_description = detail.getJdata().getCommodity_description();
        if (commodity_description != null) {
            List<ExchangeDetail.JdataBean.CommodityDescriptionBean.DesPicBean> des_pic = commodity_description.getDes_pic();
            if (des_pic != null  && des_pic.size()>0) {
                exchangeDetailAdapter.setNewData(des_pic);
            }
        }
    }

    @Override
    public void getDetailNoLoginFailure(String failure) {

    }

    /**
     * 获取详情登录状态
     */
    private void getDetail() {
        Map map = new HashMap();
        map.put("c_id", c_id);
        map.put("token", Constants.token);
        p.getDetail(map);
    }

    /**
     * 没登陆
     */
    private void getDetail_noLogin() {
        Map map = new HashMap();
        map.put("c_id",c_id);
        p.getDetailNoLogin(map);
    }

    /**
     * 用宝石兑换
     */
    private void ExchangeByStone(){
        Map map=new HashMap();
        map.put("c_id",commodityC_id);
        map.put("token",Constants.token);
        map.put("o_status",o_status);
        p.exChangeByStone(map);
    }

}
