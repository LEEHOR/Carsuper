package com.carsuper.coahr.mvp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.carsuper.coahr.mvp.view.myData.MyCoupon.MyCouponMainFragment;


public class CouponViewPagerAdapter extends FragmentPagerAdapter {
    private int  counts;
    private int fromFragment;
    private String  distance;
    private double orderPricr;
    public CouponViewPagerAdapter(FragmentManager fm,int fromFragment,String distance,double orderPrice) {
        super(fm);
        this.fromFragment=fromFragment;
        this.distance=distance;
        this.orderPricr=orderPrice;
    }

    @Override
    public Fragment getItem(int position) {
        return MyCouponMainFragment.newInstance(position,fromFragment,distance,orderPricr);
    }

    @Override
    public int getCount() {
        return 2 ;
    }
}
