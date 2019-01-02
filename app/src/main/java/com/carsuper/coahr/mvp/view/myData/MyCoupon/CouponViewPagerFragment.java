package com.carsuper.coahr.mvp.view.myData.MyCoupon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.model.bean.MessageEvent_coupon;
import com.carsuper.coahr.mvp.view.adapter.CouponViewPagerAdapter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 我的优惠券主页
 */
public class CouponViewPagerFragment extends BaseFragment {

    @BindView(R.id.coupon_tittle)
    NormalTittleBar tittleBar;
    @BindView(R.id.coupon_radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.coupon_Overdue)
    RadioButton radioButton_Right;
    @BindView(R.id.coupon_Standby)
    RadioButton radioButton_Left;
    @BindView(R.id.coupon_viewpager)
    ViewPager viewPager;
    @BindView(R.id.go_receive_coupon)
    Button go_receive_coupon;



    private CouponViewPagerAdapter pagerAdapter;

    public static CouponViewPagerFragment newInstance(int FormFragment,String distance,double orderPrice) {
        CouponViewPagerFragment fragment = new CouponViewPagerFragment();
        Bundle arg = new Bundle();
        arg.putInt("from",FormFragment);
        arg.putString("distance",distance);
        arg.putDouble("orderPrice",orderPrice);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_coupon;
    }

    @Override
    public void initView() {
        tittleBar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        radioButton_Left.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
        go_receive_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(CouponReceiveFragment.newInstance("优惠券"));
            }
        });

    }

    @Override
    public void initData() {
        int from = getArguments().getInt("from");
        String distance = getArguments().getString("distance");
        double orderPrice = getArguments().getDouble("orderPrice");
      /*  if (from==Constants.CONFIRMCOMMODITYORDERFRAGMENT){
            go_receive_coupon.setVisibility(View.GONE);
        }*/
        pagerAdapter = new CouponViewPagerAdapter(getChildFragmentManager(),from,distance,orderPrice);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPageListener());
        if (from==Constants.CONFIRMCOMMODITYORDERFRAGMENT){
            tittleBar.getRightTitle().setVisibility(View.GONE);
        }
    }

    class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.coupon_Overdue:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.coupon_Standby:
                    viewPager.setCurrentItem(0);
                    break;
            }
        }
    }

    class ViewPageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    radioButton_Left.setChecked(true);
                    break;
                case 1:
                    radioButton_Right.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /*
     * 获取屏幕的宽度
     */
    public int getW(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowMgr.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public void getBtn_LeftCount(int count) {
        radioButton_Left.setText(getActivity().getResources().getText(R.string.coupon_Standby) + "(" + count + ")");
    }

    public void getBtn_RightCount(int count) {
        radioButton_Right.setText(getActivity().getResources().getText(R.string.coupon_Overdue) + "(" + count + ")");
    }
    public void getCouponBundle(Bundle bundle){
         setFragmentResult(1, bundle);
        _mActivity.onBackPressed();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent_coupon messageEvent) {
        radioButton_Left.setText(getActivity().getResources().getText(R.string.coupon_Standby) + "(" + messageEvent.getLeft_count() + ")");
        radioButton_Right.setText(getActivity().getResources().getText(R.string.coupon_Overdue) + "(" + messageEvent.getRight_count() + ")");

    }

}
