package com.carsuper.coahr.mvp.view.maintenance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.maintenance.OrderSuccessContract;
import com.carsuper.coahr.mvp.presenter.maintenance.OrderSuccessPresenter;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 * 预约成功
 */
public class OrderSuccessFragment extends BaseFragment<OrderSuccessContract.Presenter> implements OrderSuccessContract.View {
    @Inject
    OrderSuccessPresenter orderSuccessPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_see_detial)
    TextView tvSeeDetial;
    @BindView(R.id.tv_backto_order)
    TextView tvBacktoOrder;
    @BindView(R.id.image0)
    ImageView image0;
    @BindView(R.id.rl_service_oil)
    RelativeLayout rlServiceOil;
    @BindView(R.id.rl_service_tyre)
    RelativeLayout rlServiceTyre;


    @Override
    public OrderSuccessContract.Presenter getPresenter() {
        return orderSuccessPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_order_to_maintance_success;
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

    }




}
