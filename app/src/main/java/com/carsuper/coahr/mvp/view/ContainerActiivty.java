package com.carsuper.coahr.mvp.view;

import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.model.myData.integralCenter.MyIntegralCenterSignFragmentModel;
import com.carsuper.coahr.mvp.view.Exchange_mall.Fragment_Exchange_mall;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.base.BaseSupportActivity;
import com.carsuper.coahr.mvp.view.maintenance.CarBrandFragment;
import com.carsuper.coahr.mvp.view.maintenance.OrderToMaintenanceFragment;
import com.carsuper.coahr.mvp.view.myData.InvitesCourtesy.Fragment_InvitesCourtesy;
import com.carsuper.coahr.mvp.view.myData.MyAddressFragment;
import com.carsuper.coahr.mvp.view.myData.MyCoupon.CouponReceiveFragment;
import com.carsuper.coahr.mvp.view.myData.MyCoupon.CouponViewPagerFragment;
import com.carsuper.coahr.mvp.view.myData.MyCoupon.MyCouponMainFragment;
import com.carsuper.coahr.mvp.view.myData.MyLovelyCarFragment;
import com.carsuper.coahr.mvp.view.myData.OpinionFragment;
import com.carsuper.coahr.mvp.view.myData.ShoppingCartFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.MyCommodityOrderFragment;
import com.carsuper.coahr.mvp.view.myData.LoginFragment;
import com.carsuper.coahr.mvp.view.myData.integralCenter.IntegralCenterFragment;
import com.carsuper.coahr.mvp.view.myData.integralCenter.MyIntegralCenterSignFagment;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MyMaintanceOrderFragment;
import com.carsuper.coahr.mvp.view.myData.setting.JoinUsFragment;
import com.carsuper.coahr.mvp.view.myData.setting.SettingFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityDetailFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.PaymentSuccessFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.ShoppingMallFragment;
import com.carsuper.coahr.mvp.view.store.StoreDetailFragment;
import com.carsuper.coahr.utils.NavigationBarUtils;

/**
 * Author： hengzwd on 2018/7/25.
 * Email：hengzwdhengzwd@qq.com
 *
 */
public class ContainerActiivty extends BaseSupportActivity implements MyCouponMainFragment.FragmentCouponCallBack {


    @Override
    public int binLayout() {
        return R.layout.activity_container;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (NavigationBarUtils.hasNavigationBarFun(this)){
            if (NavigationBarUtils.isNavigationBarShow(this)){
                NavigationBarUtils.setNavigationColor(this,getResources().getColor(R.color.material_white));
            }
        }
    }

    @Override
    public void initView() {
        switch (getIntent().getIntExtra("tofragment", 0)) {

            //搜索页面
            case Constants.SEARCHFRAGMENT:
                loadRootFragment(R.id.fl_container, SearchFragment.newInstance());
                break;

            //登陆页面
            case Constants.LOGINFRAGMENT:
                loadRootFragment(R.id.fl_container, LoginFragment.newInstance(getIntent().getIntExtra("fromfragment", 0)));
                break;

            //商品详情页
            case Constants.COMMODITYDETAILFRAGMENT:
                loadRootFragment(R.id.fl_container, CommodityDetailFragment.newInstance(getIntent().getStringExtra("c_id")));
                break;


            //门店详情
            case Constants.STOREDETAILFRAGMENT:
                loadRootFragment(R.id.fl_container, StoreDetailFragment.newInstance(getIntent().getStringExtra("s_id")));

                break;

            //预约保养
            case Constants.ORDERTOMAINTENANCEFRAGMENT:
                loadRootFragment(R.id.fl_container, OrderToMaintenanceFragment.newInstance());
                break;


            //我的商品订单
            case Constants.MYCOMMODITYORDERFRAGMENT:
                loadRootFragment(R.id.fl_container, MyCommodityOrderFragment.newInstance(getIntent().getIntExtra("showPosition", -1)));
                break;


            //我的上门保养
            case Constants.MYMAINTANCEORDERFRAGMENT:
                loadRootFragment(R.id.fl_container, MyMaintanceOrderFragment.newInstance(getIntent().getStringExtra("showPosition")));
                break;


            //我的爱车
            case Constants.MYLOVELYCARFRAGMENT:
                loadRootFragment(R.id.fl_container, MyLovelyCarFragment.newInstance(getIntent().getIntExtra("fromfragment",0)));
                break;

            //收货地址
            case Constants.MYADDRESSFRAGMENT:
                loadRootFragment(R.id.fl_container, MyAddressFragment.newInstance(getIntent().getIntExtra("fromfragment", -2)));
                break;


            //意见反馈
            case Constants.OPINIONFRAGMENT:
                loadRootFragment(R.id.fl_container, OpinionFragment.newInstance());
                break;


            //设置页面
            case Constants.SETTINGFRAGMENT:
                loadRootFragment(R.id.fl_container, SettingFragment.newInstance());
                break;

            //购物车
            case Constants.SHOPPINGCARTFRAGMENT:
                loadRootFragment(R.id.fl_container, ShoppingCartFragment.newInstance());
                break;

            /*//我的积分2
            case Constants.INTEGRALCENTERFRAGMENT:
                loadRootFragment(R.id.fl_container, IntegralCenterFragment.newInstance());
                break;*/
            //我的积分1
            case Constants.MYINTEGRALCENTERFRAGMENT:
                loadRootFragment(R.id.fl_container, MyIntegralCenterSignFagment.newInstance(getIntent().getStringExtra("headPath"), getIntent().getStringExtra("MyPoints"), getIntent().getStringExtra("isSign")));
                break;
            //商城
            case Constants.SHOPPINGMALLFRAGMENT:
                loadRootFragment(R.id.fl_container, ShoppingMallFragment.newInstance(getIntent().getStringExtra("selectedClassification")));
                break;

            //下单成功
            case Constants.PAYMENTSUCCESSFRAGMENT:
                loadRootFragment(R.id.fl_container, PaymentSuccessFragment.newInstance(Constants.LAST_PAYING_ORDERID, Constants.LAST_PAYING_PAGER));
                break;
            //优惠券
            case Constants.MYMAINCOUPON:
                loadRootFragment(R.id.fl_container, CouponViewPagerFragment.newInstance(getIntent().getIntExtra("fromfragment",0),getIntent().getStringExtra("distance"),getIntent().getDoubleExtra("orderPrice",0)));
                break;
                //领取优惠券页面
            case Constants.MYRECIVECOUPON:
                loadRootFragment(R.id.fl_container,CouponReceiveFragment.newInstance(getIntent().getStringExtra("toform")));
                break;
            //邀请有礼
            case Constants.MyInvitesCourtesy:
                loadRootFragment(R.id.fl_container, Fragment_InvitesCourtesy.newInstance());
                break;

                //首页进入新闻链接用与设置页进入无关
            case Constants.MyMainFragment:
                loadRootFragment(R.id.fl_container, JoinUsFragment.newInstance(getIntent().getStringExtra("url"),getIntent().getIntExtra("type",0),getIntent().getStringExtra("title")));
                break;
            case Constants.FragmentExchange:
                loadRootFragment(R.id.fl_container, Fragment_Exchange_mall.newInstance());
                break;
        }
    }

    @Override
    public void initData() {

    }
    //从  supportfragment 栈顶显示栈底某个fragment，并传递一个bundle
    public void jumpToSupportFragment(Class clazz, Bundle bundle) {
        getSupportFragmentManager().popBackStackImmediate(clazz.getName(), 0);
        BaseFragment fragment = (BaseFragment) findFragment(clazz);
        fragment.recieveData(bundle);
    }

    //登录成功回退到我的页面
    public void onLoginSuccessResult(int result) {
        setResult(result);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * 我的优惠券回调
     *
     * @param count
     */
    @Override
    public void btn_LeftCallback(int count) {
       // CouponViewPagerFragment couponViewPagerFragment = (CouponViewPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container);
       // couponViewPagerFragment.getBtn_LeftCount(count);
    }

    @Override
    public void btn_rightCallback(int count) {
      //  CouponViewPagerFragment couponViewPagerFragment = (CouponViewPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container);
       // couponViewPagerFragment.getBtn_RightCount(count);

    }

    @Override
    public void used_couponByConfirmCommodity(Bundle bundle) {
       // CouponViewPagerFragment couponViewPagerFragment = (CouponViewPagerFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container);
       // couponViewPagerFragment.getCouponBundle(bundle);
    }
}

