package com.carsuper.coahr.mvp.view.myData.commodityOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.ImageVideoDataLoadProvider;
import com.carsuper.coahr.R;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.contract.myData.commodityOrder.LogisticsContract;
import com.carsuper.coahr.mvp.model.bean.LogisticsBean;
import com.carsuper.coahr.mvp.presenter.myData.commodityOrder.LogisticsPresenter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.logisticsRecyclerView.LogisticsRecyclerView;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/23.
 * Email：hengzwdhengzwd@qq.com
 * 物流详情
 */
public class LogisticsFragment extends BaseFragment<LogisticsContract.Presenter> implements LogisticsContract.View {


    @Inject
    LogisticsPresenter logisticsPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.tv_receiver)
    TextView tvReceiver;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tv_recieve_address)
    TextView tvRecieveAddress;
    @BindView(R.id.rv_logistics)
    LogisticsRecyclerView rvLogistics;
    @BindView(R.id.iv_company_img)
    ImageView ivCompanyImg;
    @BindView(R.id.tv_logistics_company)
    TextView tvLogisticsCompany;
    @BindView(R.id.tv_contract_number)
    TextView tvContractNumber;



    private String order_id;

    public static LogisticsFragment newInstance(String order_id) {
        LogisticsFragment fragment = new LogisticsFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_id);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public LogisticsContract.Presenter getPresenter() {
        return logisticsPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_logistics;
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
        order_id = getArguments().getString("order_id");
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id", order_id);
        getPresenter().getLogistics(map);

    }




    @Override
    public void onGetLogisticsSuccess(LogisticsBean bean) {
        if (bean.getJdata().getLogistics_company() != null) {
            tvLogisticsCompany.setText(bean.getJdata().getLogistics_company().getCompany());
            tvContractNumber.setText(bean.getJdata().getLogistics_company().getPhone());
            Imageloader.loadImage(bean.getJdata().getLogistics_company().getLogo(),ivCompanyImg);
        }


        tvReceiver.setText(bean.getJdata().getAddress().getUsername());

        tvRecieveAddress.setText(bean.getJdata().getAddress().getAddress());

        tvPhoneNumber.setText(bean.getJdata().getAddress().getTelephone());

        rvLogistics.setNewData(bean.getJdata().getLogistics());

    }

    @Override
    public void onGetLogisticsFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_SHORT).show();

    }


}
