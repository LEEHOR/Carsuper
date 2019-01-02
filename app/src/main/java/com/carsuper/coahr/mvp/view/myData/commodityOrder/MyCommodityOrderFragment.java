package com.carsuper.coahr.mvp.view.myData.commodityOrder;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.base.BaseContract;
import com.carsuper.coahr.mvp.view.adapter.myCommodityOrder.MyCommodityOrderVPAdapter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/9.
 * Email：hengzwdhengzwd@qq.com
 * 我的商品订单viewpage跳转
 */
public class MyCommodityOrderFragment extends BaseFragment {


    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tbl_commodity_order)
    TabLayout tblCommodityOrder;
    @BindView(R.id.vp_order_list_fragment)
    ViewPager vpOrderListFragment;


    private int showPosition = 0;
    private MyCommodityOrderVPAdapter myCommodityOrderVPAdapter;

    public static MyCommodityOrderFragment newInstance(int showPosition){
        MyCommodityOrderFragment fragment = new MyCommodityOrderFragment();
        Bundle arg = new Bundle();
        arg.putInt("showPosition",showPosition);
        fragment.setArguments(arg);
        return fragment;
    }
    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_mycommodityorder;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        tblCommodityOrder.addOnTabSelectedListener(new setTableListener());
    }

    @Override
    public void initData() {
        showPosition = getArguments().getInt("showPosition");
     //   String order_id = getArguments().getString("order_id");
        myCommodityOrderVPAdapter = new MyCommodityOrderVPAdapter(getChildFragmentManager());
        vpOrderListFragment.setAdapter(myCommodityOrderVPAdapter);
        tblCommodityOrder.setupWithViewPager(vpOrderListFragment);
        vpOrderListFragment.setCurrentItem(showPosition);
    }
    public class  setTableListener implements TabLayout.OnTabSelectedListener{
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

}
