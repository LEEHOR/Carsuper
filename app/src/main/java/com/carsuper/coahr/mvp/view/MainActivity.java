package com.carsuper.coahr.mvp.view;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.MainActivityContract;
import com.carsuper.coahr.mvp.presenter.MainActivityPresenter;
import com.carsuper.coahr.mvp.view.base.BaseActivity;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.main.MainFragment;

import com.carsuper.coahr.mvp.view.myData.MyDataFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.ShoppingMallFragment;
import com.carsuper.coahr.mvp.view.store.StoreFragment;
import com.carsuper.coahr.utils.ActivityManagerUtils;
import com.carsuper.coahr.utils.NavigationBarUtils;
import com.carsuper.coahr.utils.Permission.OnRequestPermissionListener;
import com.carsuper.coahr.utils.Permission.RequestPermissionUtils;
import com.carsuper.coahr.utils.StringUtils;
import com.carsuper.coahr.widgets.bottomNavigation.MyBottomNavigation;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseActivity<MainActivityContract.Presenter> implements MainActivityContract.View {

    @Inject
    MainActivityPresenter presenter;

    @BindView(R.id.fl_container_main_fragment)
    FrameLayout flContainer;
    @BindView(R.id.bottom_navigation_main_fragment)
    MyBottomNavigation bottomNavigation;
    /**
     * 增量更新修复bug
     */
    private AlertDialog mDialog;
    private CountDownTimer timer;
    private int showPosition=0; //支付成功页面返回
    private String type;  //判断支付成功页面返回参数
    private  String detailType;
   private String order_id; //订单号
    private long exitTime = 0;
    private static final long INTERVAL_TIME = 2000;

    //正在展示的fragment的position
    private int bottomNavigationPreposition = 0;
    private SupportFragment[] mFragments = new SupportFragment[5];


    @Override
    public  void onCreate(@Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mFragments[0] = findFragment(MainFragment.class);
            mFragments[1] = findFragment(ShoppingMallFragment.class);
            mFragments[2] = findFragment(BlankFragment.class);
            mFragments[3] = findFragment(StoreFragment.class);
            mFragments[4] = findFragment(MyDataFragment.class);
        } else {
            mFragments[0] = MainFragment.newInstance();
            mFragments[1] = ShoppingMallFragment.newInstance("");
            mFragments[2] = BlankFragment.newInstance();
            mFragments[3] = StoreFragment.newInstance();
            mFragments[4] = MyDataFragment.newInstance();
        }
        super.onCreate(savedInstanceState);
        if (NavigationBarUtils.hasNavigationBarFun(this)){
            if (NavigationBarUtils.isNavigationBarShow(this)){
                NavigationBarUtils.setNavigationColor(this,getResources().getColor(R.color.material_white));
            }
        }
    }

    @Override
    public MainActivityContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public int binLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        Intent intent=getIntent();
        if (intent !=null){
             showPosition = intent.getIntExtra("showPosition", 0);
             type = intent.getStringExtra("type");//返回
             detailType = intent.getStringExtra("detailType");
             order_id = intent.getStringExtra("order_id"); //订单号
            loadMultipleRootFragment(R.id.fl_container_main_fragment, showPosition, mFragments);
            showFragment(showPosition);
           // bottomNavigation.beenSelect(showPosition);
            if (type !=null) {
                if (type.equals("1")) { //返回商城
                } else if (type.equals("2")) { //返回预约保养
                    Intent intent_oder = new Intent(MainActivity.this, ContainerActiivty.class);
                    intent_oder.putExtra("tofragment", Constants.ORDERTOMAINTENANCEFRAGMENT);
                    startActivity(intent_oder);
                }
            }
            if (detailType !=null){
                if (detailType.equals("1")){ //查看商品详情
                       Intent intent_detail_shopping = new Intent(MainActivity.this, ContainerActiivty.class);

                       intent_detail_shopping.putExtra("showPosition", 2);
                       intent_detail_shopping.putExtra("tofragment", Constants.MYCOMMODITYORDERFRAGMENT);
                      // intent_detail_shopping.putExtra("fromfragment", Constants.MYDATAFRAGMENT);
                            startActivity(intent_detail_shopping);

                } else if (detailType.equals("2")){ //查看预约保养详情

                    Intent intent_detail_MainTance = new Intent(MainActivity.this, ContainerActiivty.class);

                    intent_detail_MainTance.putExtra("showPosition", "2");
                    intent_detail_MainTance.putExtra("tofragment", Constants.MYMAINTANCEORDERFRAGMENT);
                    // intent_detail_shopping.putExtra("fromfragment", Constants.MYDATAFRAGMENT);
                    startActivity(intent_detail_MainTance);
                }
            }



        } else {
            loadMultipleRootFragment(R.id.fl_container_main_fragment, showPosition, mFragments);
        }
        //getIntent().getIntExtra()
        bottomNavigation.setOnTabPositionListener(new MyBottomNavigation.OnTabPositionListener() {
            @Override
            public void onPositionTab(int position) {

                if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, ContainerActiivty.class);
                    intent.putExtra("tofragment",Constants.ORDERTOMAINTENANCEFRAGMENT);
                    startActivity(intent);
                } else {
                    showFragment(position);
                }

            }
        });
    }

    private void showFragment(int position) {
        showHideFragment(mFragments[position], mFragments[bottomNavigationPreposition]);
        bottomNavigation.beenSelect(position);
        bottomNavigationPreposition = position;
    }

    @Override
    public void initData() {
        getPresenter().startLocation();
    }


    @Override
    public void locationFailure(int failureCode) {
        switch (failureCode) {
            case 62:
            case 63:
                //网络问题
                Toast.makeText(MainActivity.this,"请检查网络状况是否良好",Toast.LENGTH_LONG).show();
                break;
            case 167:
                //定位权限

                break;
        }
    }

    @Override
    public void locationSuccess(BDLocation bdLocation) {
        Constants.latitude = bdLocation.getLatitude();
        Constants.longitude = bdLocation.getLongitude();
        String a[] = bdLocation.getAddrStr().split("-");
        Constants.locationDistrict=bdLocation.getDistrict();
        Constants.locationProvince=bdLocation.getProvince();
        Constants.locationAddress = a[0];
        Constants.locationStreet=bdLocation.getStreet();
//        bdLocation.getStreet();
//        bdLocation.getAddress();
//        bdLocation.getAddrStr();
//        KLog.d("定位", bdLocation.getStreet(),
//        bdLocation.getAddress().street,
//        bdLocation.getAddrStr());
        Constants.locationcity= bdLocation.getCity();
        if (TextUtils.isEmpty(Constants.pointCity)) {
            Constants.pointCity=StringUtils.getCityName(Constants.locationcity);
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressedSupport() {
        if ((System.currentTimeMillis() - exitTime) > INTERVAL_TIME) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.carsuper_exit), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            if (Constants.isKill){
                ActivityManagerUtils.getInstance().appExit();
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }

}
