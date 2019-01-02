package com.carsuper.coahr.mvp.view.maintenance;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.carsuper.coahr.R;
import com.carsuper.coahr.mvp.contract.maintenance.CarSerialContract;
import com.carsuper.coahr.mvp.model.bean.CarSerialBean;
import com.carsuper.coahr.mvp.presenter.maintenance.CarSerialPresenter;
import com.carsuper.coahr.mvp.view.adapter.CarPicker.CarSerialAdapter;
import com.carsuper.coahr.mvp.view.base.BaseApplication;
import com.carsuper.coahr.mvp.view.base.BaseFragment;
import com.carsuper.coahr.mvp.view.decoration.SpacesItemDecoration;
import com.carsuper.coahr.utils.DensityUtils;
import com.carsuper.coahr.utils.imageLoader.Imageloader;
import com.carsuper.coahr.widgets.myTittleBar.NormalTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/7.
 * Email：hengzwdhengzwd@qq.com
 * 车系选择
 */
public class CarSerialFragment extends BaseFragment<CarSerialContract.Presenter> implements CarSerialContract.View {


    @Inject
    CarSerialPresenter carSerialPresenter;
    @BindView(R.id.tb_tittle)
    NormalTittleBar tbTittle;
    @BindView(R.id.iv_car_brand)
    ImageView ivCarBrand;
    @BindView(R.id.tv_carbrand_name)
    TextView tvCarbrandName;
    @BindView(R.id.rv_carserial)
    RecyclerView rvCarserial;

    private String cb_id;
    private String brandIMG;
    private String brandName;

    private String carSerialName;

    private LinearLayoutManager linearLayoutManager;

    private CarSerialAdapter adapter;

    private int fromF;//来自哪个车型选择模块，服务订单确认  或者是  我的爱车页面  选择完成根据此返回fromF
    public static CarSerialFragment newInstance(int fromF,String cb_id,String brandIMG,String brandName) {
        CarSerialFragment fragment = new CarSerialFragment();
        Bundle arg = new Bundle();
        arg.putInt("fromF",fromF);
        arg.putString("cb_id", cb_id);
        arg.putString("brandIMG",brandIMG);
        arg.putString("brandName",brandName);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public CarSerialContract.Presenter getPresenter() {
        return carSerialPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_carserial;
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

        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        adapter = new CarSerialAdapter();
        adapter.setOnCarserialItemClickListener(new CarSerialAdapter.OnCarserialItemClickListener() {
            @Override
            public void onItemClick(CarSerialBean.JdataEntity.SerialEntity serialEntity) {
                   carSerialName =serialEntity.getCs_name();
                    start(CarMotorFragment.newInstance(fromF,serialEntity.getCs_id(),brandIMG,brandName,serialEntity.getCs_name()));
            }
        });
        rvCarserial.setLayoutManager(linearLayoutManager);
        rvCarserial.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.decoration_f5f5f8)));

        rvCarserial.setAdapter(adapter);
        fromF = getArguments().getInt("fromF");
        cb_id = getArguments().getString("cb_id");
        brandIMG = getArguments().getString("brandIMG");
        brandName = getArguments().getString("brandName");
        Imageloader.loadImage(brandIMG,ivCarBrand);
        tvCarbrandName.setText(brandName);
        Map map = new HashMap();
        map.put("cb_id", cb_id);
        getPresenter().getCarSerial(map);

    }

    @Override
    public void onGetCarSerialSuccess(CarSerialBean bean) {
            adapter.setNewData(bean.getJdata().getSerial());
    }

    @Override
    public void onGetCarSerialFailure(String failure) {
        Toast.makeText(BaseApplication.mContext,failure,Toast.LENGTH_LONG).show();
    }



}
