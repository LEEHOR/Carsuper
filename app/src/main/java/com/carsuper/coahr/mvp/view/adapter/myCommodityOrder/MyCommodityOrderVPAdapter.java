package com.carsuper.coahr.mvp.view.adapter.myCommodityOrder;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.carsuper.coahr.mvp.view.myData.commodityOrder.CommodityOrderFragment;

/**
 * Author： hengzwd on 2018/8/9.
 * Email：hengzwdhengzwd@qq.com
 */
public class MyCommodityOrderVPAdapter extends FragmentPagerAdapter {

    private String[] title = {"全部", "待付款","待发货","待收货","待评价","退换货"};
    private String[] order_statuss = {"-2","0","1","2","4","5"};
    private String order_id;
    public MyCommodityOrderVPAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setOrder_id(String order_id){
        this.order_id=order_id;
    }


    @Override
    public Fragment getItem(int position) {
        return CommodityOrderFragment.newInstance(order_statuss[position]);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
