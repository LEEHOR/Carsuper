package com.carsuper.coahr.mvp.view.adapter.integralCenter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.carsuper.coahr.mvp.view.myData.integralCenter.PointFragment;

/**
 * Author： hengzwd on 2018/8/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class IntegralCenterVpAdapter extends FragmentPagerAdapter {

    private String[] title = {"全部", "收入","支出"};
    private String[] filters = {"default","income","outcome"};

    public IntegralCenterVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PointFragment.newInstance(filters[position]);
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
