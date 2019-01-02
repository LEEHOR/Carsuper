package com.carsuper.coahr.mvp.view.adapter.myMaintanceOrder;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MaintanceOrderFragment;

/**
 * Author： hengzwd on 2018/8/13.
 * Email：hengzwdhengzwd@qq.com
 *
 */
public class MyMaintanceOrderVPAdapter extends FragmentPagerAdapter {

//    private String[] title = {"全部", "待付款","待服务","待评价","已完成","已取消"};
//    private String[] order_statuss = {"-2","0","1","2","3","-1"};

    private String[] title = {"全部", "待付款","待服务","待评价","已取消"};
    private String[] order_statuss = {"-2","0","1","2","-1"};
    public MyMaintanceOrderVPAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return MaintanceOrderFragment.newInstance(order_statuss[position]);
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
