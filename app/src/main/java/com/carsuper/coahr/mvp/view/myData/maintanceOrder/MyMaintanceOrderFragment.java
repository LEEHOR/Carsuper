package com.carsuper.coahr.mvp.view.myData.maintanceOrder;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.view.adapter.myMaintanceOrder.MyMaintanceOrderVPAdapter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/14.
 * Email：hengzwdhengzwd@qq.com
 * 我的上门保养viewpage
 */
public class MyMaintanceOrderFragment extends BaseFragment {


    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tbl_maintance_order)
    TabLayout tblMaintanceOrder;
    @BindView(R.id.vp_order_list_fragment)
    ViewPager vpOrderListFragment;


    private MyMaintanceOrderVPAdapter myMaintanceOrderVPAdapter;

    public static MyMaintanceOrderFragment newInstance(String showPosition){
        MyMaintanceOrderFragment fragment = new MyMaintanceOrderFragment();
        Bundle arg = new Bundle();
        arg.putString("showPosition",showPosition);
        fragment.setArguments(arg);
        return fragment;
    }
    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_mymaintanceorder;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

    }

    @Override
    public void initData() {
        String showPosition = getArguments().getString("showPosition");

        myMaintanceOrderVPAdapter = new MyMaintanceOrderVPAdapter(getChildFragmentManager());
        vpOrderListFragment.setAdapter(myMaintanceOrderVPAdapter);
        tblMaintanceOrder.setupWithViewPager(vpOrderListFragment);
        if (showPosition !=null){
            vpOrderListFragment.setCurrentItem(Integer.parseInt(showPosition));
        } else {
            vpOrderListFragment.setCurrentItem(0);
        }
    }

}
