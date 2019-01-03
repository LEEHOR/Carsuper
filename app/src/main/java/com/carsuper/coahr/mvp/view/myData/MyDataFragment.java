package com.carsuper.coahr.mvp.view.myData;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.MyDataContract;
import com.carsuper.coahr.mvp.model.bean.MainBean;
import com.carsuper.coahr.mvp.model.bean.MyDataAdList;
import com.carsuper.coahr.mvp.model.bean.PersonInfoBean;
import com.carsuper.coahr.mvp.model.bean.ResultBean;
import com.carsuper.coahr.mvp.model.bean.VerifyTokenBean;
import com.carsuper.coahr.mvp.presenter.myData.MyDataPresenter;
import com.carsuper.coahr.mvp.view.ContainerActiivty;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.base.BaseLazyFragment;
import com.carsuper.coahr.utils.PreferenceUtils;
import com.carsuper.coahr.utils.ScreenUtils;
import com.carsuper.coahr.utils.SetCustomBannerUtils;
import com.carsuper.coahr.utils.UriUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.CircleImageView;
import com.chendong.gank.library.SuperBadgeHelper;
import com.donkingliang.banner.CustomBanner;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.chendong.gank.library.SuperBadgeHelper.init;

/**
 * Author： hengzwd on 2018/6/21.
 * Email：hengzwdhengzwd@qq.com
 * 我的页面
 */
public class MyDataFragment extends BaseLazyFragment<MyDataContract.Presenter> implements MyDataContract.View, View.OnClickListener {


    @Inject
    MyDataPresenter myDataPresenter;
    @BindView(R.id.iv_user_headerimg)
    ImageView ivUserHeaderimg;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.tv_signin)
    TextView tvSignin;

    @BindView(R.id.tv_needtopay)
    TextView tvNeedtopay;
    @BindView(R.id.tv_needtosend)
    TextView tvNeedtosend;
    @BindView(R.id.tv_needtorecieve)
    TextView tvNeedtorecieve;
    @BindView(R.id.tv_needtoevaluate)
    TextView tvNeedtoevaluate;
    @BindView(R.id.tv_return_change)
    TextView tvReturnChange;


    @BindView(R.id.tv_maintanceorder)
    TextView tvMaintanceorder;
    @BindView(R.id.tv_shopping_cart)
    TextView tvShoppingCart;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.tv_receiving_address)
    TextView tvReceivingAddress;
    @BindView(R.id.tv_lovely_car)
    TextView tvLovelyCar;
    @BindView(R.id.tv_myscore)
    TextView tvMyscore;
    @BindView(R.id.tv_opinions)
    TextView tvOpinions;
    @BindView(R.id.tv_invitation)
    TextView tvInvitation;
    @BindView(R.id.iv_advertisement)
    CustomBanner<String> ivAdvertisement;
    Unbinder unbinder;
    @BindView(R.id.tv_integration)
    TextView tvIntegration;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_all_commodityorder)
    TextView tvAllCommodityOrder;
    @BindView(R.id.my_shoping_order_count)
    LinearLayout my_shoping_order_count;
    private List<String> adList =new ArrayList<>();
private  SuperBadgeHelper need_to_play,need_to_send,need_to_recieve,need_to_evaluate,need_to_change,root_badge;
    private int isSign = 0;//0表示未签到，1表示已签到
    private boolean isShow = false;

    private boolean isTokenEnable = false;


    private String headPath;

    private String MyPoints;
    private List<MyDataAdList.JdataBean> jdata=new ArrayList<>();

    public static MyDataFragment newInstance() {
        return new MyDataFragment();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {//被其他fragment 覆盖 ，之后show出来都会调用
        super.onHiddenChanged(hidden);
        isShow = !hidden;
        if (isShow) {
            if (haslogin()) {
                getPresenter().verifyToken(Constants.token);
            } else {//没有登录过
                // startLoginForResult();
                tvLogin.setVisibility(View.VISIBLE);
                tvIntegration.setText("");
                tvUserName.setText(Constants.nickname);
                Imageloader.loadCircularImage(UriUtils.getUriFromDrawableRes(BaseApplication.mContext,R.mipmap.primary),ivUserHeaderimg);
            }
        }
    }

    @Override
    public void onResume() {//被activity覆盖之后show出来，会调用
        if (haslogin()) {
            getPresenter().verifyToken(Constants.token);
        } else {//没有登录过
            if (isShow) {
                // startLoginForResult();
                tvLogin.setVisibility(View.VISIBLE);
                tvIntegration.setText("");
                tvUserName.setText(Constants.nickname);
                Imageloader.loadCircularImage(UriUtils.getUriFromDrawableRes(BaseApplication.mContext,R.mipmap.primary),ivUserHeaderimg);

            }
        }
        super.onResume();
    }


    @Override
    public MyDataContract.Presenter getPresenter() {
        return myDataPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_mydata;
    }

    @Override
    public void initView() {
        Imageloader.setViewSize(ivAdvertisement,ScreenUtils.getScreenWidth(BaseApplication.mContext),ScreenUtils.getScreenWidth(BaseApplication.mContext)/2,null);
        ivAdvertisement.setOnClickListener(this);
        ivMessage.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
        ivUserHeaderimg.setOnClickListener(this);
        tvInvitation.setOnClickListener(this);
        tvUserName.setOnClickListener(this);
        tvCoupon.setOnClickListener(this);
        tvLovelyCar.setOnClickListener(this);
        tvMaintanceorder.setOnClickListener(this);
        tvMyscore.setOnClickListener(this);
        tvNeedtoevaluate.setOnClickListener(this);
        tvNeedtopay.setOnClickListener(this);
        tvNeedtorecieve.setOnClickListener(this);
        tvOpinions.setOnClickListener(this);
        tvReceivingAddress.setOnClickListener(this);
        tvNeedtosend.setOnClickListener(this);
        tvReturnChange.setOnClickListener(this);
        tvShoppingCart.setOnClickListener(this);
        tvSignin.setOnClickListener(this);
        tvIntegration.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvAllCommodityOrder.setOnClickListener(this);
    }

    @Override
    public void initData() {
        root_badge = init(_mActivity, my_shoping_order_count, "父级",0,SuperBadgeHelper.STYLE_GONE);
        need_to_play = SuperBadgeHelper.init(_mActivity, tvNeedtopay, "待付款", 0, SuperBadgeHelper.STYLE_DEFAULT);
        need_to_send = SuperBadgeHelper.init(_mActivity, tvNeedtosend, "待发货", 0, SuperBadgeHelper.STYLE_DEFAULT);
        need_to_recieve = SuperBadgeHelper.init(_mActivity, tvNeedtorecieve, "待收获", 0, SuperBadgeHelper.STYLE_DEFAULT);
        need_to_evaluate = SuperBadgeHelper.init(_mActivity, tvNeedtoevaluate, "待评价", 0, SuperBadgeHelper.STYLE_DEFAULT);
        need_to_change = SuperBadgeHelper.init(_mActivity, tvReturnChange, "退换货", 0, SuperBadgeHelper.STYLE_DEFAULT);

        need_to_play.bindView(root_badge);
        need_to_send.bindView(root_badge);
        need_to_recieve.bindView(root_badge);
        need_to_evaluate.bindView(root_badge);
        need_to_change.bindView(root_badge);

        need_to_play.setHideOnNull(true);
        need_to_send.setHideOnNull(true);
        need_to_recieve.setHideOnNull(true);
        need_to_evaluate.setHideOnNull(true);
        need_to_change.setHideOnNull(true);
        getAdList();
    }

    private void getUserInfo() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("uid", Constants.uid + "");
        getPresenter().getUserInfo(map);
    }

    @Override
    public void onGetInfoSuccess(final PersonInfoBean personInfoBean) {

        tvUserName.setText(personInfoBean.getJdata().getUser().getNickname());
        tvIntegration.setText(personInfoBean.getJdata().getUser().getPoints());
        if (personInfoBean.getJdata().getUser().getUserheadimg() !=null && !personInfoBean.getJdata().getUser().getUserheadimg().equals("")){
            Imageloader.loadCircularImage(personInfoBean.getJdata().getUser().getUserheadimg(), ivUserHeaderimg);
        } else {
            Uri uriFromDrawableRes = UriUtils.getUriFromDrawableRes(BaseApplication.mContext, R.mipmap.primary);
            Imageloader.loadCircularImage(uriFromDrawableRes, ivUserHeaderimg);
        }
        this.headPath = personInfoBean.getJdata().getUser().getUserheadimg();
        this.MyPoints = personInfoBean.getJdata().getUser().getPoints();
        tvLogin.setVisibility(View.GONE);
        isSign = personInfoBean.getJdata().getUser().getBonus_status();
        if (isSign==0) {
            tvSignin.setText("未签到");
        } else {
            tvSignin.setText("已签到");
        }
        if (personInfoBean.getJdata().getOrder_count() !=null) {
            if (root_badge!=null) {
                root_badge.read();
                need_to_play.addNum(Integer.parseInt(personInfoBean.getJdata().getOrder_count().getStatus_a()));
                need_to_send.addNum(Integer.parseInt(personInfoBean.getJdata().getOrder_count().getStatus_b()));
                need_to_recieve.addNum(Integer.parseInt(personInfoBean.getJdata().getOrder_count().getStatus_c()));
                need_to_evaluate.addNum(Integer.parseInt(personInfoBean.getJdata().getOrder_count().getStatus_d()));
                need_to_change.addNum(Integer.parseInt(personInfoBean.getJdata().getOrder_count().getStatus_e()));
            }
        } else {

        }


    }

    @Override
    public void onGetInfoFailure(String throwle) {
        Toast.makeText(_mActivity, "获取个人信息失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void verifySuccess(VerifyTokenBean verifyTokenBean) {
        if (verifyTokenBean.getJdata().getStatus() == 1) {
            getUserInfo();
            isTokenEnable = true;

        } else {//token验证过期了，从新登录
            Constants.token = "";
            Constants.uid = "";
            PreferenceUtils.remove(BaseApplication.mContext, "token");
            PreferenceUtils.remove(BaseApplication.mContext, "uid");
            startLoginForResult();
        }
    }

    @Override
    public void verifyFailure(VerifyTokenBean bean) {
        //未知错误致使访问失败  这里可以搞个计数  5此之后，不再访问
//        getPresenter().verifyToken(Constants.token);
        Constants.token = "";
        Constants.uid = "";
        PreferenceUtils.remove(BaseApplication.mContext, "token");
        PreferenceUtils.remove(BaseApplication.mContext, "uid");
        startLoginForResult();
//        Toast.makeText(_mActivity, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSigninSuccess(ResultBean bean) {
        if (bean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext, bean.getJdata().getJmsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BaseApplication.mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
        }
        tvSignin.setText("已签到");
        isSign = 1;
    }

    @Override
    public void onSignInFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getAdListSuccess(MyDataAdList myDataAdList) {
        adList.clear();
        if (myDataAdList.getJdata() != null) {
            jdata = myDataAdList.getJdata();
            for (int i = 0; i <jdata.size() ; i++) {
                adList.add(jdata.get(i).getAdv_img());
            }
            SetCustomBannerUtils.setCustomBanner(ivAdvertisement,adList,ImageView.ScaleType.FIT_XY);
            setBannerLister(jdata,ivAdvertisement);
        }
    }

    @Override
    public void getAdListFailure(String failure) {

    }
    private void setBannerLister(final List<MyDataAdList.JdataBean> lists, CustomBanner customBanner){
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
    private void startLoginForResult() {
        Intent intent = new Intent(_mActivity, ContainerActiivty.class);
        intent.putExtra("tofragment", Constants.LOGINFRAGMENT);
        intent.putExtra("fromfragment", Constants.MYDATAFRAGMENT);
        startActivityForResult(intent, 0);
    }


    private void startMyCommodityOrder(int showPosition) {
        Intent intent = new Intent(_mActivity, ContainerActiivty.class);
        intent.putExtra("showPosition", showPosition);
        intent.putExtra("tofragment", Constants.MYCOMMODITYORDERFRAGMENT);
        intent.putExtra("fromfragment", Constants.MYDATAFRAGMENT);
        startActivity(intent);
    }


    private void startFragment(int toF) {
        Intent intent = new Intent(_mActivity, ContainerActiivty.class);
        intent.putExtra("tofragment", toF);
        intent.putExtra("fromfragment", Constants.MYDATAFRAGMENT);
        startActivity(intent);
    }

    private void startSign(int toF) {
        Intent intent = new Intent(_mActivity, ContainerActiivty.class);
        intent.putExtra("headPath", headPath);
        intent.putExtra("MyPoints", MyPoints);
        intent.putExtra("isSign", String.valueOf(isSign));
        intent.putExtra("tofragment", toF);
        intent.putExtra("fromfragment", Constants.MYDATAFRAGMENT);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
      /*  if (!isTokenEnable) {
            startLoginForResult();
            return;
        }*/
        switch (v.getId()) {
            case R.id.iv_setting:
                startFragment(Constants.SETTINGFRAGMENT);
                break;
            case R.id.tv_login:
                //startLoginForResult();
                break;
            case R.id.tv_needtoevaluate:
                if (haslogin()) {
                    startMyCommodityOrder(4);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_all_commodityorder:
                if (haslogin()) {
                    startMyCommodityOrder(-2);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_needtopay:
                if (haslogin()) {
                    startMyCommodityOrder(1);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_needtorecieve:
                if (haslogin()) {
                    startMyCommodityOrder(3);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_needtosend:
                if (haslogin()) {
                    startMyCommodityOrder(2);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_return_change:
                if (haslogin()) {
                    startMyCommodityOrder(5);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_maintanceorder:
                if (haslogin()) {
                    startFragment(Constants.MYMAINTANCEORDERFRAGMENT);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_lovely_car:
                if (haslogin()) {
                    startFragment(Constants.MYLOVELYCARFRAGMENT);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_receiving_address:
                if (haslogin()) {
                    startFragment(Constants.MYADDRESSFRAGMENT);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_opinions:
                if (haslogin()) {
                    startFragment(Constants.OPINIONFRAGMENT);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_shopping_cart:
                if (haslogin()) {
                    startFragment(Constants.SHOPPINGCARTFRAGMENT);
                } else {
                    Toast.makeText(_mActivity, "请登录后再操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_myscore:
               /* if (haslogin()) {
                    startFragment(Constants.INTEGRALCENTERFRAGMENT);
                } else {
                    Toast.makeText(_mActivity, "请登录后在操作", Toast.LENGTH_LONG).show();
                }*/
                if (haslogin()) {
                    startSign(Constants.MYINTEGRALCENTERFRAGMENT);
                } else {
                    Toast.makeText(_mActivity, "请登录后在操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_signin:
               /* if (isSign.equals("0")) {
                    Map map = new HashMap();
                    map.put("token", Constants.token);
                    getPresenter().signin(map);
                } else {
                    Toast.makeText(BaseApplication.mContext, "已签到过了，无法再次签到", Toast.LENGTH_SHORT).show();
                }*/
                if (haslogin()) {
                    startSign(Constants.MYINTEGRALCENTERFRAGMENT);
                } else {
                    Toast.makeText(_mActivity, "请登录后在操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_coupon:
                if (haslogin()) {
                    startFragment(Constants.MYMAINCOUPON);
                } else {
                    Toast.makeText(_mActivity, "请登录后在操作", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_invitation: //邀请有礼
                if (haslogin()) {
                    startFragment(Constants.MyInvitesCourtesy);
                } else {
                    Toast.makeText(_mActivity, "请登录后在操作", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.iv_user_headerimg: //点击登陆
                if (haslogin()) {
                  //  Toast.makeText(_mActivity, "已登录", Toast.LENGTH_LONG).show();
                } else {
                    startLoginForResult();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            isTokenEnable = true;
            getUserInfo();
        }
    }

    /**
     * 获取轮播图列表
     */
    private void getAdList(){
        Map map=new HashMap();
        map.put("asid","6");
        myDataPresenter.getAdList(map);
    }
}
